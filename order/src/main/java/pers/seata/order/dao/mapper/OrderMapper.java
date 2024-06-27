package pers.seata.order.dao.mapper;


import pers.seata.order.dao.domain.Order;

public interface OrderMapper {
    int create(Order order);
}
