package pers.seata.service;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.seata.dao.domain.Order;
import pers.seata.dao.domain.OrderItem;
import pers.seata.ro.OrderCreateRO;

@Service
public class OrderBusiness {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;


    @GlobalTransactional(timeoutMills = 300000, name = "spring-seata-tx")
    public void create(OrderCreateRO ro) {
        Order order = new Order();
        order.setUserId(ro.getUserId());
        orderService.create(order);
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(order.getId());
        orderItem.setItemName(ro.getItemName());
        orderItemService.create(orderItem);
        if (ro.getUserId() > 5) {
            throw new RuntimeException("exception mock!");
        }
    }

}
