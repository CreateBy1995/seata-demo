package pers.seata.business.service;

import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.seata.business.dao.domain.OrderItem;
import pers.seata.business.dao.mapper.OrderItemMapper;
import pers.seata.business.ro.OrderCreateRO;
import pers.seata.common.facade.OrderService;

@Service
public class OrderBusiness {
    @Reference(retries = -1, timeout = 60000)
    private OrderService orderService;
    @Autowired
    private OrderItemMapper orderItemMapper;


    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-order-at")
    public void create(OrderCreateRO ro) {
        Long orderId = orderService.create(ro.getUserId());
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);
        orderItem.setItemName(ro.getItemName());
        orderItemMapper.create(orderItem);
        if (ro.getUserId() > 5) {
            throw new RuntimeException("exception mock!");
        }
    }

}
