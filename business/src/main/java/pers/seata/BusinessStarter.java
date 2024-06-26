package pers.seata;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan({"pers.seata.business.dao.mapper"})
@EnableDubbo
@SpringBootApplication
public class BusinessStarter {
    public static void main(String[] args) {
        SpringApplication.run(BusinessStarter.class);
    }
}