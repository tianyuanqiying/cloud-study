package com.cloud.study.seata_multipart_source.service.impl;


import com.cloud.study.seata_multipart_source.config.DataSourceKey;
import com.cloud.study.seata_multipart_source.config.DynamicDataSourceContextHolder;
import com.cloud.study.seata_multipart_source.entity.Order;
import com.cloud.study.seata_multipart_source.entity.OrderStatus;
import com.cloud.study.seata_multipart_source.mapper.OrderMapper;
import com.cloud.study.seata_multipart_source.service.AccountService;
import com.cloud.study.seata_multipart_source.service.OrderService;
import com.cloud.study.seata_multipart_source.service.StorageService;
import com.cloud.study.seata_multipart_source.vo.OrderVo;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Fox
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private StorageService storageService;
    
    @Override
//    @Transactional
    @GlobalTransactional(name="createOrder")
    public Order saveOrder(OrderVo orderVo){
        log.info("=============用户下单=================");
        //切换数据源
        DynamicDataSourceContextHolder.setDataSourceKey(DataSourceKey.ORDER);
        log.info("当前 XID: {}", RootContext.getXID());
        
        // 保存订单
        Order order = new Order();
        order.setUserId(orderVo.getUserId());
        order.setCommodityCode(orderVo.getCommodityCode());
        order.setCount(orderVo.getCount());
        order.setMoney(orderVo.getMoney());
        order.setStatus(OrderStatus.INIT.getValue());
    
        Integer saveOrderRecord = orderMapper.insert(order);
        log.info("保存订单{}", saveOrderRecord > 0 ? "成功" : "失败");
        
        //扣减库存
        storageService.deduct(orderVo.getCommodityCode(),orderVo.getCount());
        
        //扣减余额
        accountService.debit(orderVo.getUserId(),orderVo.getMoney());
    
        log.info("=============更新订单状态=================");
        //切换数据源
        DynamicDataSourceContextHolder.setDataSourceKey(DataSourceKey.ORDER);
        //更新订单
        Integer updateOrderRecord = orderMapper.updateOrderStatus(order.getId(),OrderStatus.SUCCESS.getValue());
        log.info("更新订单id:{} {}", order.getId(), updateOrderRecord > 0 ? "成功" : "失败");
        
        return order;
        
    }
}
