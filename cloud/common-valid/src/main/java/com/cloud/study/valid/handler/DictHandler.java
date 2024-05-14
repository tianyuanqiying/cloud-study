package com.cloud.study.valid.handler;

import com.cloud.study.valid.base.Constant;
import com.cloud.study.valid.base.DictValidator;
import com.cloud.study.valid.base.Validation;
import com.cloud.study.valid.builder.DictInfoBuilder;
import com.cloud.study.valid.exception.ValidException;
import com.cloud.study.valid.pojo.BaseDTO;
import com.cloud.study.valid.pojo.DictInfo;
import com.cloud.study.valid.pojo.ValidationInfo;
import com.cloud.study.valid.util.ReflectUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据字典校验器
 * @author user
 */
@Order(Integer.MAX_VALUE)
@Component
public class DictHandler extends AbstractFieldHandler {
    @Autowired
    ICache<DictInfo> dictInfoCache;

    @Override
    public Boolean support(BaseDTO baseDTO) {
        ValidationInfo validationInfo = (ValidationInfo) baseDTO;

        return DictValidator.class.isAssignableFrom(validationInfo.getValidation().rule());
    }

    @Override
    public Boolean handle(BaseDTO baseDTO) {
        ValidationInfo validationInfo = (ValidationInfo) baseDTO;

        Validation validation = validationInfo.getValidation();
        DictValidator validator = (DictValidator) ReflectUtil.newInstance(validation.rule());
        String dictFileName = validator.getDictName().endsWith(".json") ? validator.getDictName() : validator.getDictName() + ".json";

        DictInfo dictInfo = dictInfoCache.get(dictFileName);

        if (!dictInfo.getData().containsKey(validationInfo.getFieldValue().toString())) {
            throw new ValidException(validator.getMessage());
        }

        return true;
    }

    @Override
    public Boolean typeMatch(Class clazz) {
        return supportTypes.contains(clazz);
    }


    /**
     * 字典缓存
     */
    @Component
    public static class DictInfoCache implements ApplicationContextAware,InitializingBean, ICache<DictInfo>{
        private ApplicationContext appContext;

        private Map<String, DictInfo> dictInfoCacheMap = new ConcurrentHashMap<>();
        private DictInfo dictInfo;

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.appContext = applicationContext;
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            URL dictUrl = this.getClass().getResource(Constant.DICT_DIRECTORY_PATH);
            if (Objects.isNull(dictUrl)) {
                return;
            }
            if (Objects.nonNull(dictUrl) && Objects.isNull(dictUrl.getFile())) {
                return;
            }
            //获取数据字典目录
            File dictDirectory = new File(dictUrl.getFile());
            if (!dictDirectory.exists() || !dictDirectory.isDirectory()) {
                return;
            }

            File[] dictFiles = dictDirectory.listFiles();
            for (File dictFile : dictFiles) {
                DictInfo dictInfo = new DictInfoBuilder(dictFile.getName()).build();
                dictInfoCacheMap.put(dictInfo.getDictName(), dictInfo);
            }
        }

        @Override
        public DictInfo get(String dictFileName) {
            DictInfo dictInfo = dictInfoCacheMap.get(dictFileName);
            if (Objects.nonNull(dictInfo)) {
                return dictInfo;
            }

            dictInfo = new DictInfoBuilder(dictFileName).build();
            dictInfoCacheMap.put(dictFileName, dictInfo);
            return dictInfo;
        }
    }
}
