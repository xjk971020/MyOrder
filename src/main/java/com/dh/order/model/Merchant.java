package com.dh.order.model;

import lombok.Data;

import java.util.Date;
import java.sql.Timestamp;

@Data
public class Merchant {
    private Integer merchantId;

    private String merchantName;

    private String merchantNation;

    private String merchantProvince;

    private String merchantCity;

    private String merchantAddress;

    private String merchantPhone;

    private String merchantUrl;

    private Double merchantLongitude;

    private Double merchantLatitude;

    private Integer merchantPrice;

    private Double merchantGrade;

    private Date merchantBeginTime;

    private Date merchantEndTime;

    private Timestamp merchantCreateTime;

    private String merchantDescribe;

}