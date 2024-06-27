package pers.seata.business.ro;

import lombok.Data;

@Data
public class OrderCreateRO {
    private Long userId;
    private String itemName;
}
