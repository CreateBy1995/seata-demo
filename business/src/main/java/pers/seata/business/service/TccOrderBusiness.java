package pers.seata.business.service;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.seata.common.facade.TccActionOne;

@Slf4j
@Service
public class TccOrderBusiness {
    @Reference(retries = -1, timeout = 60000, check = false)
    private TccActionOne tccActionOne;
    @Autowired
    private TccActionTwo tccActionTwo;

    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-order-tcc")
    public void doAction(Integer param) {
        log.info("TccOrderBusiness doAction");
        Long result = tccActionOne.create(null, param);
        log.info("result :{}", result);
        tccActionTwo.action(null, param);
        if (param > 10){
            throw new RuntimeException("ActionBusiness Exception");
        }
    }
}
