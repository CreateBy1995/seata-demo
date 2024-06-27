package pers.seata.common.facade;

import io.seata.rm.tcc.api.BusinessActionContext;

/**
 * @Author: dongcx
 * @CreateTime: 2024-06-27
 * @Description:
 */
public interface TccActionOne {
    Long create(BusinessActionContext actionContext, Integer param);

    boolean commit(BusinessActionContext actionContext);

    boolean rollback(BusinessActionContext actionContext);
}
