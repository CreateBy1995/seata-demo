package pers.seata;

/**
 * @Author: dongcx
 * @CreateTime: 2024-05-28
 * @Description:
 */
public class Test {
    /**
     * SeataCoreAutoConfiguration 主要负责 Seata 核心配置的自动化，包括事务管理器和协调器的基本设置。
     *
     * 核心配置：初始化 Seata 核心组件，如 TransactionManagerHolder、TMF (Transaction Manager Facade) 等。
     * 配置加载：加载和解析 Seata 的核心配置参数，如事务协调器的地址、事务超时设置等。
     * 启动顺序：通常较早启动，确保核心组件在其他依赖组件之前初始化。
     */

    /**
     * SeataAutoConfiguration
     *  更侧重于 Seata 和 Spring Boot 之间的集成，提供了与 Spring 相关的配置和增强。
     * 事务代理：配置全局事务扫描器（GlobalTransactionScanner），用于扫描和代理带有 @GlobalTransactional 注解的方法。
     * 事务拦截器：配置 Spring 的 AOP 拦截器，用于拦截全局事务方法并进行相应的事务处理。
     * 整合支持：提供与 Spring 事务管理器的整合支持，使得 Seata 的全局事务可以无缝集成到 Spring 事务管理体系中。
     */

    /**
     * SeataDataSourceAutoConfiguration 主要负责 Seata 数据源代理的配置和初始化。
     *
     * 数据源代理：自动配置和初始化 DataSourceProxy，用于代理和管理实际的数据库连接池，从而实现分布式事务。
     * 数据源增强：在 Spring 应用中使用 Seata 提供的数据源代理类，确保数据库操作能够被 Seata 拦截和管理，以实现分布式事务控制。
     * 配置加载：加载数据源相关的配置参数，如数据源类型、连接池配置等。
     */
    /**
     * SeataHttpAutoConfiguration 主要用于配置和集成 Seata 的 HTTP 模块，通常用于支持 HTTP 协议的分布式事务场景。
     *
     * HTTP 事务处理：配置用于处理 HTTP 协议的全局事务组件，确保 HTTP 请求能够参与 Seata 的分布式事务。
     * HTTP 客户端拦截器：配置和注册 HTTP 客户端拦截器，用于在 HTTP 请求中注入 Seata 的事务上下文信息。
     * HTTP 服务端拦截器：配置和注册 HTTP 服务端拦截器，用于在处理 HTTP 请求时获取并解析 Seata 的事务上下文信息。
     */


}
