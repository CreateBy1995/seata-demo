package pers.seata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.seata.dao.domain.Order;
import pers.seata.dao.mapper.OrderMapper;

@Service
public class OrderService {
    @Autowired
    private OrderMapper mapper;

    public int create(Order order) {
        return mapper.create(order);
    }
}
