package pers.seata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.seata.dao.domain.OrderItem;
import pers.seata.dao.mapper.OrderItemMapper;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemMapper mapper;

    public int create(OrderItem orderItem) {
        return mapper.create(orderItem);
    }
}
