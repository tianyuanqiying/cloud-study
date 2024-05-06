package com.cloud.study.order.service;

import com.cloud.study.entity.Order;
import com.cloud.study.order.vo.OrderVo;
import io.seata.core.exception.TransactionException;

public interface OrderService {

    /**
     * 保存订单
     */
    Order saveOrder(OrderVo orderVo) throws TransactionException;
}