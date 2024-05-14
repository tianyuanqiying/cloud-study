package com.cloud.study.util;



import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SqlParser {
    private List<File> targetDirs = new ArrayList<>();

    private List<File> zipFiles = new ArrayList<>();

    private Map<String, String> sqlContentMap = new HashMap<>();

    private Map<String, File> sqlFileMap = new HashMap<>();

    private List<String> errorZipFiles = new ArrayList<>();

    private final Pattern notePattern = Pattern.compile("/\\*([\\s\\S]*?)\\*");

    private final Pattern pattern = Pattern.compile("'([^']*)'");

    private Map<String, String> sqlParsedErrorMap = new HashMap<>();

    private Set<String> allSqlTables = new HashSet<>();
    public static void main(String[] args) {
        SqlParser.parse("");
    }

    private static Set<String> parse(String rootDir) {
        SqlParser sqlParser = new SqlParser();

        sqlParser.findTargetDirectory(rootDir);

        sqlParser.findZip();

        sqlParser.parseZip();

        sqlParser.parseSqlAndGetAllTableName();
        return null;
    }

    private void parseSqlAndGetAllTableName() {
        sqlContentMap.forEach((sqlFileName, sqlContent) -> {
            String replacedNoteContent = replaceNote(sqlContent);

            String replacedSqlContent = replace(replacedNoteContent);

            doParseSqlAndGetTableName(replacedNoteContent, sqlFileName);
        });
    }

    private void doParseSqlAndGetTableName(String sqlContent, String sqlFileName) {
        String[] sqlArray = sqlContent.split(";");
        for (String sql : sqlArray) {
            String trimSql = sql.trim();

            if (StringUtils.isBlank(trimSql)) {
                continue;
            }

            if ((trimSql.startsWith("CREATE") && trimSql.contains("TABLE")) || trimSql.contains("comment ")
                    || trimSql.contains("ALTER") || trimSql.contains("alter") || trimSql.contains("call sysproc")) {
                continue;
            }

            if (trimSql.contains("--") && !(trimSql.contains("INSERT") || trimSql.contains("UPDATE") || trimSql.contains("DELETE"))) {
                continue;
            }

            if (trimSql.contains("--")) {
                if (trimSql.contains("INSERT")) {
                    trimSql = trimSql.substring(trimSql.indexOf("INSERT"));
                }else if (trimSql.contains("UPDATE")) {
                    trimSql = trimSql.substring(trimSql.indexOf("UPDATE"));
                }else if (trimSql.contains("DELETE")) {
                    trimSql = trimSql.substring(trimSql.indexOf("DELETE"));
                }
            }

            try {
                sqlParse(trimSql);
            }catch (Exception e) {
                sqlParsedErrorMap.put(sql, sqlFileName);
            }
        }
    }

    private void sqlParse(String sql) throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);

        TablesNamesFinder namesFinder = new TablesNamesFinder();

        List<String> tableList = namesFinder.getTableList(statement);

        tableList = tableList.stream().filter(table -> !table.contains("SYSTEM")).collect(Collectors.toList());

        tableList.stream().map(table -> table.contains(".") ? table.substring(table.indexOf(".")) + 1 : table).collect(Collectors.toList());

        allSqlTables.addAll(tableList);
    }

    private String replace(String sqlContent) {
        Matcher matcher = pattern.matcher(sqlContent);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(buffer, "'");
            buffer.append(" ");
            buffer.append("'");
        }

        matcher.appendTail(buffer);
        return buffer.toString();
    }

    private String replaceNote(String sqlContent) {
        Matcher matcher = notePattern.matcher(sqlContent);

        StringBuffer builder = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(builder, " ");
        }
        matcher.appendTail(builder);
        return builder.toString();
    }

    private void parseZip() {
        for (File file : zipFiles) {
            try (ZipInputStream zis = new ZipInputStream(new FileInputStream(file.getAbsolutePath()), Charset.forName("GBK"))) {
                ZipEntry zipEntry;
                while((zipEntry = zis.getNextEntry()) != null) {
                    if (!zipEntry.isDirectory() && zipEntry.getName().endsWith(".sql")) {
                        StringBuilder stringBuilder = new StringBuilder();
                        byte[] buffer = new byte[1024];
                        int len;
                        while((len = zis.read(buffer)) != -1) {
                            stringBuilder.append(new String(buffer, 0, len, "GBK"));
                        }

                        String content = stringBuilder.toString();
                        sqlContentMap.put(zipEntry.getName(), content);
                        sqlFileMap.put(zipEntry.getName(), file);
                    }
                }
            } catch (Exception e) {
                errorZipFiles.add(file.getAbsolutePath());
            }
        }
    }

    private void findZip() {
        for (File targetDir : targetDirs) {
            File[] files = targetDir.listFiles();
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".zip")) {
                    zipFiles.add(file);
                }
            }
        }

    }

    private void findTargetDirectory(String rootDir) {
        File rootDirFile = new File(rootDir);

        LinkedList<File> dirQueue = new LinkedList<>();

        dirQueue.add(rootDirFile);

        while(!dirQueue.isEmpty()) {
            File file = dirQueue.pop();

            File[] files = file.listFiles();

            for (File subFile : files) {
                if (subFile.isDirectory() && subFile.getName().contains("上线目标")) {
                    targetDirs.add(subFile);
                }

                if (subFile.isFile()) {
                    continue;
                }

                if (subFile.isDirectory()) {
                    dirQueue.add(subFile);
                }
            }
        }
    }
}
