package pers.seata.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pers.seata.dao.domain.Order;
import pers.seata.dao.mapper.OrderMapper;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderMapper mapper;

    public int create(Order order) {
        return mapper.create(order);
    }

}
