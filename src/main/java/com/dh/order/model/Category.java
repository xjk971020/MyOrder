package com.dh.order.model;

import lombok.Data;

@Data
public class Category {
    private Integer categoryId;

    private String categoryName;

    private Integer merchantId;

}