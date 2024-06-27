package pers.seata.order.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import pers.seata.common.facade.TccActionOne;

/**
 * @Author: dongcx
 * @CreateTime: 2024-06-27
 * @Description:
 */
@Slf4j
@Service
@LocalTCC
public class TccActionOneImpl implements TccActionOne {
    @Override
    @TwoPhaseBusinessAction(name = "tcc-action-one", commitMethod = "commit", rollbackMethod = "rollback")
    public Long create(BusinessActionContext actionContext, @BusinessActionContextParameter(paramName = "param") Integer param) {
        log.info("tcc-action-one create, param:{}, context:{}", param, actionContext);
        return param + 1L;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        log.warn("tcc-action-one commit, context:{}", actionContext);
        return true;
    }


    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        log.error("tcc-action-one rollback, context:{}", actionContext);
        return true;
    }

}
