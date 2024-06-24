package pers.seata.ro;

import lombok.Data;

@Data
public class OrderCreateRO {
    private Long userId;
    private String itemName;
}
