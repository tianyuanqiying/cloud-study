package com.cloud.study.dubbo;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectItem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlParserTest {
    public static void main(String[] args) throws JSQLParserException {
//        String sqlStr = "select 1 from dual where a=b";
//
//        PlainSelect select = (PlainSelect) CCJSqlParserUtil.parse(sqlStr);
//
//        SelectItem selectItem =
//                select.getSelectItems().get(0);
//
//
//        Table table = (Table) select.getFromItem();
//        System.out.println(table.getName());
//
//        EqualsTo equalsTo = (EqualsTo) select.getWhere();
//        Expression a = equalsTo.getLeftExpression();
//        Column b = (Column) equalsTo.getRightExpression();
//

        String input = "This is /*some\nmulti\nline\ncomment*/ with more text.";
        System.out.println(input);
        String output = replaceCommentContent(input);
        System.out.println(output);
    }

    public static String replaceCommentContent(String input) {
        // 正则表达式，使用DOTALL模式匹配/*和*/之间的任何内容，包括换行符
        Pattern pattern = Pattern.compile("/\\*([\\s\\S]*?)\\*/", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);

        // 使用StringBuffer来构建替换后的字符串
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            // 替换匹配到的内容为空格
            matcher.appendReplacement(sb, " ");
        }
        // 添加剩余部分到StringBuffer
        matcher.appendTail(sb);

        // 返回替换后的字符串
        return sb.toString();
    }
    /* */
}
