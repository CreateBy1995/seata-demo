package pers.seata.dao.domain;

import lombok.Data;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-26
 * @Description:
 */
@Data
public class OrderItem {
    private Long id;
    private Long orderId;
    private String itemName;
}
