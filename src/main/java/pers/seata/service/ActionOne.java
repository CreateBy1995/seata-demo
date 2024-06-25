package pers.seata.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
@LocalTCC
public class ActionOne {

    @TwoPhaseBusinessAction(name = "ActionOne", commitMethod = "commit", rollbackMethod = "rollback")
    public boolean action(BusinessActionContext actionContext, @BusinessActionContextParameter(paramName = "param") Integer param) {
        log.info("ActionOne action, param:{}, context:{}", param, actionContext);
        return true;
    }

    public boolean commit(BusinessActionContext actionContext) {
        log.warn("ActionOne commit, context:{}", actionContext);
        return true;
    }

    public boolean rollback(BusinessActionContext actionContext) {
        log.error("ActionOne rollback, context:{}", actionContext);
        return true;
    }
}
