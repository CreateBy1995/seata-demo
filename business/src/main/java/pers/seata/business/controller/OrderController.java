package pers.seata.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.seata.business.ro.OrderCreateRO;
import pers.seata.business.service.OrderBusiness;
import pers.seata.business.service.TccOrderBusiness;

/**
 * 无配置规则表测试接口
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderBusiness orderBusiness;
    @Autowired
    private TccOrderBusiness tccOrderBusiness;

    @PostMapping("/create")
    public void createOrder(@RequestBody OrderCreateRO ro) {
        orderBusiness.create(ro);
    }

    @GetMapping("/createByTcc/{param}")
    public void createByTcc(@PathVariable(name = "param") Integer param) {
        tccOrderBusiness.doAction(param);
    }
}
