package pers.seata.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.seata.dao.domain.Order;
import pers.seata.dao.domain.OrderItem;
import pers.seata.ro.OrderCreateRO;

@Slf4j
@Service
public class ActionBusiness {
    @Autowired
    private ActionOne actionOne;
    @Autowired
    private ActionTwo actionTwo;

    @GlobalTransactional(timeoutMills = 300000, name = "spring-seata-tx")
    public void doAction(Integer param) {
        log.info("ActionBusiness doAction");
        actionOne.action(null, param);
        actionTwo.action(null, param);
        if (param > 10){
            throw new RuntimeException("ActionBusiness Exception");
        }
    }
}
