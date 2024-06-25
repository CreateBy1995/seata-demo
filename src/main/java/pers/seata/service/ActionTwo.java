package pers.seata.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@LocalTCC
public class ActionTwo {

    @TwoPhaseBusinessAction(name = "ActionOne", commitMethod = "commit", rollbackMethod = "rollback")
    public boolean action(BusinessActionContext actionContext, @BusinessActionContextParameter(paramName = "param") Integer param) {
        log.info("ActionTwo action, param:{}, context:{}", param, actionContext);
        return true;
    }

    public boolean commit(BusinessActionContext actionContext) {
        log.warn("ActionTwo commit, context:{}", actionContext);
        return true;
    }

    public boolean rollback(BusinessActionContext actionContext) {
        log.error("ActionTwo rollback, context:{}", actionContext);
        return true;
    }
}
