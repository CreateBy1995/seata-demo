package pers.seata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.seata.ro.OrderCreateRO;
import pers.seata.service.OrderBusiness;

/**
 * 无配置规则表测试接口
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderBusiness orderBusiness;

    @PostMapping("/create")
    public void createOrder(@RequestBody OrderCreateRO ro) {
        orderBusiness.create(ro);
    }
}
