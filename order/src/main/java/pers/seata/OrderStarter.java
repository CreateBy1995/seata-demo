package pers.seata;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({"pers.seata.order.dao.mapper"})
@EnableDubbo
@SpringBootApplication

public class OrderStarter {
    public static void main(String[] args) {
        SpringApplication.run(OrderStarter.class);
    }
}