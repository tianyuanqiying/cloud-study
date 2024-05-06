package com.cloud.study.valid.handler;


import com.cloud.study.valid.base.RegexValidator;
import com.cloud.study.valid.base.Validator;

import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class RegexHandler implements IValidHandler{
    private ConcurrentHashMap<String, Pattern> patternMap = new ConcurrentHashMap<>();

    @Override
    public Boolean support(Validator validator) {
        return validator instanceof RegexValidator;
    }

    @Override
    public Boolean handle(Validator validator) {
        RegexValidator regexValidator = (RegexValidator) validator;
        String regex = regexValidator.getRegex();
        Pattern pattern = getPattern(regex);
//        pattern.matcher()

        return true;
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
