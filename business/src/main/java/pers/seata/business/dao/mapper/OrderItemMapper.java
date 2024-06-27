package pers.seata.business.dao.mapper;


import pers.seata.business.dao.domain.OrderItem;

public interface OrderItemMapper {
    int create(OrderItem orderItem);
}
