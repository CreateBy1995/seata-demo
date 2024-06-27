package pers.seata.business.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@LocalTCC
public class TccActionTwo {

    @TwoPhaseBusinessAction(name = "tcc-action-two", commitMethod = "commit", rollbackMethod = "rollback")
    public boolean action(BusinessActionContext actionContext, @BusinessActionContextParameter(paramName = "param") Integer param) {
        log.info("tcc-action-two action, param:{}, context:{}", param, actionContext);
        return true;
    }

    public boolean commit(BusinessActionContext actionContext) {
        log.warn("tcc-action-two commit, context:{}", actionContext);
        return true;
    }

    public boolean rollback(BusinessActionContext actionContext) {
        log.error("tcc-action-two rollback, context:{}", actionContext);
        return true;
    }

}
