package com.dh.order.model;

import lombok.Data;

/**
 * @author xjk
 * @date 2019/3/6 -  13:00
 **/
@Data
public class User {
    
    /**
     *用户表id
     */
    private Integer userId;

    /**
     *对应商家id
     */
    private Integer merchantId;

    /**
     *密码
     */
    private String password;

    /**
     *盐值
     */
    private String salt;

    /**
     *是否锁定
     */
    private boolean locked = Boolean.FALSE;

    /**
     * 密码加密的凭证
     * @return
     */
    public String getCrenditalSalt() {
        return merchantId + salt;
    }
}
