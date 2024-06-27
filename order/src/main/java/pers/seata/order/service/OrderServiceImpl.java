package pers.seata.order.service;

import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pers.seata.common.facade.OrderService;
import pers.seata.order.dao.domain.Order;
import pers.seata.order.dao.mapper.OrderMapper;

/**
 * @Author: dongcx
 * @CreateTime: 2024-06-27
 * @Description:
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public Long create(Long userId) {
        log.info("order create xid:{}", RootContext.getXID());
        Order order = new Order();
        order.setUserId(userId);
        int result = orderMapper.create(order);
        return (long) result;
    }
}
