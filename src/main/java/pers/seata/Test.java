//package pers.seata;
//
//import io.netty.channel.ChannelHandlerContext;
//import io.seata.common.exception.FrameworkErrorCode;
//import io.seata.common.exception.NotSupportYetException;
//import io.seata.common.exception.ShouldNeverHappenException;
//import io.seata.common.loader.EnhancedServiceLoader;
//import io.seata.common.util.CollectionUtils;
//import io.seata.core.context.RootContext;
//import io.seata.core.model.BranchType;
//import io.seata.core.protocol.MessageTypeAware;
//import io.seata.core.protocol.RpcMessage;
//import io.seata.core.rpc.netty.AbstractNettyRemoting;
//import io.seata.core.rpc.processor.Pair;
//import io.seata.core.rpc.processor.RemotingProcessor;
//import io.seata.rm.datasource.StatementProxy;
//import io.seata.rm.datasource.exception.TableMetaException;
//import io.seata.rm.datasource.exec.*;
//import io.seata.rm.datasource.exec.mariadb.MariadbInsertOnDuplicateUpdateExecutor;
//import io.seata.rm.datasource.exec.mariadb.MariadbUpdateJoinExecutor;
//import io.seata.rm.datasource.exec.mysql.MySQLInsertOnDuplicateUpdateExecutor;
//import io.seata.rm.datasource.exec.mysql.MySQLUpdateJoinExecutor;
//import io.seata.rm.datasource.exec.polardbx.PolarDBXInsertOnDuplicateUpdateExecutor;
//import io.seata.rm.datasource.exec.polardbx.PolarDBXUpdateJoinExecutor;
//import io.seata.rm.datasource.exec.sqlserver.SqlServerDeleteExecutor;
//import io.seata.rm.datasource.exec.sqlserver.SqlServerSelectForUpdateExecutor;
//import io.seata.rm.datasource.exec.sqlserver.SqlServerUpdateExecutor;
//import io.seata.rm.datasource.sql.SQLVisitorFactory;
//import io.seata.rm.datasource.sql.struct.TableRecords;
//import io.seata.sqlparser.SQLRecognizer;
//import io.seata.sqlparser.SQLType;
//import io.seata.sqlparser.util.JdbcConstants;
//import io.seata.tm.api.GlobalTransaction;
//import io.seata.tm.api.GlobalTransactionContext;
//import io.seata.tm.api.TransactionalExecutor;
//import io.seata.tm.api.transaction.Propagation;
//import io.seata.tm.api.transaction.SuspendedResourcesHolder;
//import io.seata.tm.api.transaction.TransactionInfo;
//import org.slf4j.MDC;
//
//import java.io.IOException;
//import java.lang.management.ManagementFactory;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.RejectedExecutionException;
//
///**
// * @Author: dongcx
// * @CreateTime: 2024-05-28
// * @Description:
// */
//public class Test {
//    /**
//     * SeataCoreAutoConfiguration 主要负责 Seata 核心配置的自动化，包括事务管理器和协调器的基本设置。
//     *
//     * 核心配置：初始化 Seata 核心组件，如 TransactionManagerHolder、TMF (Transaction Manager Facade) 等。
//     * 配置加载：加载和解析 Seata 的核心配置参数，如事务协调器的地址、事务超时设置等。
//     * 启动顺序：通常较早启动，确保核心组件在其他依赖组件之前初始化。
//     */
//
//    /**
//     * SeataAutoConfiguration
//     *  更侧重于 Seata 和 Spring Boot 之间的集成，提供了与 Spring 相关的配置和增强。
//     * 事务代理：配置全局事务扫描器（GlobalTransactionScanner），用于扫描和代理带有 @GlobalTransactional 注解的方法。
//     * 事务拦截器：配置 Spring 的 AOP 拦截器，用于拦截全局事务方法并进行相应的事务处理。
//     * 整合支持：提供与 Spring 事务管理器的整合支持，使得 Seata 的全局事务可以无缝集成到 Spring 事务管理体系中。
//     */
//
//    /**
//     * SeataDataSourceAutoConfiguration 主要负责 Seata 数据源代理的配置和初始化。
//     *
//     * 数据源代理：自动配置和初始化 DataSourceProxy，用于代理和管理实际的数据库连接池，从而实现分布式事务。
//     * 数据源增强：在 Spring 应用中使用 Seata 提供的数据源代理类，确保数据库操作能够被 Seata 拦截和管理，以实现分布式事务控制。
//     * 配置加载：加载数据源相关的配置参数，如数据源类型、连接池配置等。
//     */
//    /**
//     * SeataHttpAutoConfiguration 主要用于配置和集成 Seata 的 HTTP 模块，通常用于支持 HTTP 协议的分布式事务场景。
//     *
//     * HTTP 事务处理：配置用于处理 HTTP 协议的全局事务组件，确保 HTTP 请求能够参与 Seata 的分布式事务。
//     * HTTP 客户端拦截器：配置和注册 HTTP 客户端拦截器，用于在 HTTP 请求中注入 Seata 的事务上下文信息。
//     * HTTP 服务端拦截器：配置和注册 HTTP 服务端拦截器，用于在处理 HTTP 请求时获取并解析 Seata 的事务上下文信息。
//     */
//
//
//    /**
//     * AbstractNettyRemotingClient.ClientHandler # userEventTriggered 定时发送心跳包
//     */
//
//
//    /**
//     * TransactionalTemplate#execute  全局事务执行流程
//     */
//    public Object execute(TransactionalExecutor business) throws Throwable {
//        // 1. 获取事务信息
//        TransactionInfo txInfo = business.getTransactionInfo();
//        if (txInfo == null) {
//            throw new ShouldNeverHappenException("transactionInfo does not exist");
//        }
//        // 1.1 根据当前事务角色 如果已经有XID 那么当前即为参与者，否则为发起者
//        GlobalTransaction tx = GlobalTransactionContext.getCurrent();
//
//        // 1.2 Handle the transaction propagation.
//        Propagation propagation = txInfo.getPropagation();
//        SuspendedResourcesHolder suspendedResourcesHolder = null;
//        try {
//            // 根据传播级别创建事务
//            switch (propagation) {
//            }
//
//            try {
//                // 开启事务
//                beginTransaction(txInfo, tx);
//
//                Object rs;
//                try {
//                    // 执行业务逻辑
//                    // Do Your Business
//                    rs = business.execute();
//                } catch (Throwable ex) {
//                    // 3. 事务回滚
//                    completeTransactionAfterThrowing(txInfo, tx, ex);
//                    throw ex;
//                }
//
//                // 提交事务
//                // 4. everything is fine, commit.
//                commitTransaction(tx, txInfo);
//
//                return rs;
//            } finally {
//                //5. clear
//                resumeGlobalLockConfig(previousConfig);
//                triggerAfterCompletion(tx);
//                cleanUp();
//            }
//        } finally {
//            // If the transaction is suspended, resume it.
//            if (suspendedResourcesHolder != null) {
//                tx.resume(suspendedResourcesHolder);
//            }
//        }
//    }
//
//
//    /**
//     * ExecuteTemplate#execute  分支事务执行流程
//     */
//    public static <T, S extends Statement> T execute(List<SQLRecognizer> sqlRecognizers,
//                                                     StatementProxy<S> statementProxy,
//                                                     StatementCallback<T, S> statementCallback,
//                                                     Object... args) throws SQLException {
//        if (!RootContext.requireGlobalLock() && BranchType.AT != RootContext.getBranchType()) {
//            // Just work as original statement
//            return statementCallback.execute(statementProxy.getTargetStatement(), args);
//        }
//
//        String dbType = statementProxy.getConnectionProxy().getDbType();
//        if (CollectionUtils.isEmpty(sqlRecognizers)) {
//            sqlRecognizers = SQLVisitorFactory.get(
//                    statementProxy.getTargetSQL(),
//                    dbType);
//        }
//        Executor<T> executor;
//        if (CollectionUtils.isEmpty(sqlRecognizers)) {
//            executor = new PlainExecutor<>(statementProxy, statementCallback);
//        } else {
//            if (sqlRecognizers.size() == 1) {
//                SQLRecognizer sqlRecognizer = sqlRecognizers.get(0);
//                switch (sqlRecognizer.getSQLType()) {
//                    case INSERT:
//                        executor = EnhancedServiceLoader.load(InsertExecutor.class, dbType,
//                                new Class[]{StatementProxy.class, StatementCallback.class, SQLRecognizer.class},
//                                new Object[]{statementProxy, statementCallback, sqlRecognizer});
//                        break;
//                    case UPDATE:
//                        if (JdbcConstants.SQLSERVER.equalsIgnoreCase(dbType)) {
//                            executor = new SqlServerUpdateExecutor<>(statementProxy, statementCallback, sqlRecognizer);
//                        } else {
//                            executor = new UpdateExecutor<>(statementProxy, statementCallback, sqlRecognizer);
//                        }
//                        break;
//                    case DELETE:
//                        if (JdbcConstants.SQLSERVER.equalsIgnoreCase(dbType)) {
//                            executor = new SqlServerDeleteExecutor<>(statementProxy, statementCallback, sqlRecognizer);
//                        } else {
//                            executor = new DeleteExecutor<>(statementProxy, statementCallback, sqlRecognizer);
//                        }
//                        break;
//                    case SELECT_FOR_UPDATE:
//                        if (JdbcConstants.SQLSERVER.equalsIgnoreCase(dbType)) {
//                            executor = new SqlServerSelectForUpdateExecutor<>(statementProxy, statementCallback, sqlRecognizer);
//                        } else {
//                            executor = new SelectForUpdateExecutor<>(statementProxy, statementCallback, sqlRecognizer);
//                        }
//                        break;
//                    case INSERT_ON_DUPLICATE_UPDATE:
//                        switch (dbType) {
//                            case JdbcConstants.MYSQL:
//                                executor =
//                                        new MySQLInsertOnDuplicateUpdateExecutor(statementProxy, statementCallback, sqlRecognizer);
//                                break;
//                            case JdbcConstants.MARIADB:
//                                executor =
//                                        new MariadbInsertOnDuplicateUpdateExecutor(statementProxy, statementCallback, sqlRecognizer);
//                                break;
//                            case JdbcConstants.POLARDBX:
//                                executor = new PolarDBXInsertOnDuplicateUpdateExecutor(statementProxy, statementCallback, sqlRecognizer);
//                                break;
//                            default:
//                                throw new NotSupportYetException(dbType + " not support to INSERT_ON_DUPLICATE_UPDATE");
//                        }
//                        break;
//                    case UPDATE_JOIN:
//                        switch (dbType) {
//                            case JdbcConstants.MYSQL:
//                                executor = new MySQLUpdateJoinExecutor<>(statementProxy, statementCallback, sqlRecognizer);
//                                break;
//                            case JdbcConstants.MARIADB:
//                                executor = new MariadbUpdateJoinExecutor<>(statementProxy, statementCallback, sqlRecognizer);
//                                break;
//                            case JdbcConstants.POLARDBX:
//                                executor = new PolarDBXUpdateJoinExecutor<>(statementProxy, statementCallback, sqlRecognizer);
//                                break;
//                            default:
//                                throw new NotSupportYetException(dbType + " not support to " + SQLType.UPDATE_JOIN.name());
//                        }
//                        break;
//                    default:
//                        executor = new PlainExecutor<>(statementProxy, statementCallback);
//                        break;
//                }
//            } else {
//                executor = new MultiExecutor<>(statementProxy, statementCallback, sqlRecognizers);
//            }
//        }
//        T rs;
//        try {
//            rs = executor.execute(args);
//        } catch (Throwable ex) {
//            if (!(ex instanceof SQLException)) {
//                // Turn other exception into SQLException
//                ex = new SQLException(ex);
//            }
//            throw (SQLException) ex;
//        }
//        return rs;
//    }
//
//    protected T executeAutoCommitFalse(Object[] args) throws Exception {
//        try {
//            // 1.生成执行前镜像
//            TableRecords beforeImage = beforeImage();
//            // 2.执行真实sql
//            T result = statementCallback.execute(statementProxy.getTargetStatement(), args);
//            // 3.生成执行后镜像
//            TableRecords afterImage = afterImage(beforeImage);
//            // 4.生成undoLog
//            prepareUndoLog(beforeImage, afterImage);
//            return result;
//        } catch (TableMetaException e) {
//            LOGGER.error("table meta will be refreshed later, due to TableMetaException, table:{}, column:{}",
//                    e.getTableName(), e.getColumnName());
//            statementProxy.getConnectionProxy().getDataSourceProxy().tableMetaRefreshEvent();
//            throw e;
//        }
//    }
//
//    AbstractNettyRemoting#processMessage  接收到TC的消息后的处理逻辑
//    protected void processMessage(ChannelHandlerContext ctx, RpcMessage rpcMessage) throws Exception {
//        if (LOGGER.isDebugEnabled()) {
//            LOGGER.debug(String.format("%s msgId:%s, body:%s", this, rpcMessage.getId(), rpcMessage.getBody()));
//        }
//        Object body = rpcMessage.getBody();
//        if (body instanceof MessageTypeAware) {
//            MessageTypeAware messageTypeAware = (MessageTypeAware) body;
//            final Pair<RemotingProcessor, ExecutorService> pair = this.processorTable.get((int)    messageTypeAware.getTypeCode());
//            if (pair != null) {
//                if (pair.getSecond() != null) {
//                    try {
//                        pair.getSecond().execute(() -> {
//                            try {
//                                pair.getFirst().process(ctx, rpcMessage);
//                            } catch (Throwable th) {
//                                LOGGER.error(FrameworkErrorCode.NetDispatch.getErrCode(), th.getMessage(), th);
//                            } finally {
//                                MDC.clear();
//                            }
//                        });
//                    } catch (RejectedExecutionException e) {
//                        LOGGER.error(FrameworkErrorCode.ThreadPoolFull.getErrCode(),
//                                "thread pool is full, current max pool size is " + messageExecutor.getActiveCount());
//                        if (allowDumpStack) {
//                            String name = ManagementFactory.getRuntimeMXBean().getName();
//                            String pid = name.split("@")[0];
//                            long idx = System.currentTimeMillis();
//                            try {
//                                String jstackFile = idx + ".log";
//                                LOGGER.info("jstack command will dump to " + jstackFile);
//                                Runtime.getRuntime().exec(String.format("jstack %s > %s", pid, jstackFile));
//                            } catch (IOException exx) {
//                                LOGGER.error(exx.getMessage());
//                            }
//                            allowDumpStack = false;
//                        }
//                    }
//                } else {
//                    try {
//                        pair.getFirst().process(ctx, rpcMessage);
//                    } catch (Throwable th) {
//                        LOGGER.error(FrameworkErrorCode.NetDispatch.getErrCode(), th.getMessage(), th);
//                    }
//                }
//            } else {
//                LOGGER.error("This message type [{}] has no processor.", messageTypeAware.getTypeCode());
//            }
//        } else {
//            LOGGER.error("This rpcMessage body[{}] is not MessageTypeAware type.", body);
//        }
//    }
//
//    /**
//     *   TccActionInterceptorHandler#doInvoke TCC核心代理逻辑
//     *   构造上下文
//     *   注册分支信息
//     */
//
//}
