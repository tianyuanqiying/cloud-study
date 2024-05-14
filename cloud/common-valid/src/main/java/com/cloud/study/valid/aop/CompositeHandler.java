package com.cloud.study.valid.aop;

import com.cloud.study.valid.base.Validation;
import com.cloud.study.valid.base.IValidHandler;
import com.cloud.study.valid.pojo.BizInfo;
import com.cloud.study.valid.pojo.ValidationInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
public class CompositeHandler implements IHandlerResolver, InitializingBean{
    private Map<Validation, List<IValidHandler>> validationListMap = new ConcurrentHashMap<>();
    private List<IValidHandler> bizValidators = new CopyOnWriteArrayList<>();

    private List<IValidHandler> validHandlers;

    @Autowired
    private List<IValidHandler> allValidHandlers;

    @Override
    public void afterPropertiesSet() throws Exception {
        //分组处理、 先找出处理@ValidateionInfo注解的处理器
        List<IValidHandler> validationInfoHandlers = allValidHandlers.stream().filter(iValidHandler -> iValidHandler.typeMatch(ValidationInfo.class)).collect(Collectors.toList());
        this.setValidHandlers(validationInfoHandlers);

        //找出处理IBizHandler的处理器；
        List<IValidHandler> bizInfoHandlers = allValidHandlers.stream().filter(iValidHandler -> iValidHandler.typeMatch(BizInfo.class)).collect(Collectors.toList());
        this.setBizValidators(bizInfoHandlers);
    }

    public List<IValidHandler> getHandler(ValidationInfo validationInfo) {
        List<IValidHandler> handlersCache = validationListMap.get(validationInfo.getValidation());
        if (!CollectionUtils.isEmpty(handlersCache)) {
            return handlersCache;
        }

        List<IValidHandler> iValidHandlers = new ArrayList<>();
        for (IValidHandler validHandler : validHandlers) {
            if (validHandler.support(validationInfo)) {
                iValidHandlers.add(validHandler);
            }
        }

        validationListMap.put(validationInfo.getValidation(), iValidHandlers);
        return iValidHandlers;
    }

    @Override
    public void resolveFiled(ValidationInfo validationInfo) {
        List<IValidHandler> validHandlers = getHandler(validationInfo);
        if (CollectionUtils.isEmpty(validHandlers)) {
            return;
        }

        validHandlers.forEach(iValidHandler -> {
            iValidHandler.handle(validationInfo);
        });
    }

    @Override
    public void resolveBiz(BizInfo bizInfo) {
        for (IValidHandler bizValidator : bizValidators) {
            bizValidator.handle(bizInfo);
        }
    }

    public void setBizValidators(List<IValidHandler> bizValidators) {
        this.bizValidators = bizValidators;
    }

    public void setValidHandlers(List<IValidHandler> validHandlers) {
        this.validHandlers = validHandlers;
    }
}
