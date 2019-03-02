package com.dh.order.model;

import lombok.Data;

import java.util.Date;

@Data
public class Orders {
    private Integer orderId;

    private Integer merchantId;

    private Date orderCreateTime;

    private Boolean isDisplay;

    private Integer orderStatue;

    private String orderContent;
}