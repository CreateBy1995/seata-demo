package pers.seata.dao.mapper;

import pers.seata.dao.domain.Order;

public interface OrderMapper {
    int create(Order order);
}
