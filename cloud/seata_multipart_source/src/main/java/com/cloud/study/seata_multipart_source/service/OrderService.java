package com.cloud.study.seata_multipart_source.service;


import com.cloud.study.seata_multipart_source.entity.Order;
import com.cloud.study.seata_multipart_source.vo.OrderVo;

public interface OrderService {

    /**
     * 保存订单
     */
    Order saveOrder(OrderVo orderVo);
}