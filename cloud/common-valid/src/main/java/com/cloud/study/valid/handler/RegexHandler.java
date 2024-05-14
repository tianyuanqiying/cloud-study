package com.cloud.study.valid.handler;


import com.cloud.study.valid.base.RegexValidator;
import com.cloud.study.valid.base.Validator;
import com.cloud.study.valid.exception.ValidException;
import com.cloud.study.valid.pojo.BaseDTO;
import com.cloud.study.valid.pojo.ValidationInfo;
import com.cloud.study.valid.util.ReflectUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 正则处理器
 * @author user
 */
@Order(Integer.MAX_VALUE)
@Component
public class RegexHandler extends AbstractFieldHandler {
    private ConcurrentHashMap<String, Pattern> patternMap = new ConcurrentHashMap<>();


    @Override
    public Boolean support(BaseDTO baseDTO) {
        ValidationInfo validationInfo = (ValidationInfo) baseDTO;

        return RegexValidator.class.isAssignableFrom(validationInfo.getValidation().rule());
    }

    @Override
    public Boolean handle(BaseDTO baseDTO){
        ValidationInfo validationInfo = (ValidationInfo) baseDTO;

        Validator validator = ReflectUtil.newInstance(validationInfo.getValidation().rule());
        RegexValidator regexValidator = (RegexValidator) validator;
        String regex = regexValidator.getRegex();
        Pattern pattern = getPattern(regex);
        Matcher matcher = pattern.matcher(validationInfo.getFieldValue().toString());

        if (!matcher.find()) {
            throw new ValidException(regexValidator.getMessage());
        }

        return true;
    }

    @Override
    public Boolean typeMatch(Class clazz) {
        return supportTypes.contains(clazz);
    }


    public Pattern getPattern(String regex) {
        if (patternMap.get(regex) != null) {
            return patternMap.get(regex);
        }
        Pattern pattern = Pattern.compile(regex);
        patternMap.put(regex, pattern);
        return pattern;
    }
}
