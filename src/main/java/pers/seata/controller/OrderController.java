package pers.seata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.seata.ro.OrderCreateRO;
import pers.seata.service.ActionBusiness;
import pers.seata.service.OrderBusiness;

/**
 * 无配置规则表测试接口
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderBusiness orderBusiness;
    @Autowired
    private ActionBusiness actionBusiness;

    @PostMapping("/create")
    public void createOrder(@RequestBody OrderCreateRO ro) {
        orderBusiness.create(ro);
    }

    @GetMapping("/createByTcc/{param}")
    public void createByTcc(@PathVariable(name = "param") Integer param) {
        actionBusiness.doAction(param);
    }
}
