package com.dh.order.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Food {
    private Integer foodId;

    private String foodName;

    private Integer merchantId;

    private String foodUrl;

    private Float foodPrice;

    private Float foodDiscountPrice;

    private Boolean isSpecialty;

    private Integer foodStatue;

    private Integer foodCount;

    private Category category;

    private String foodDescription;

    private Float foodGrade;
}