package com.dh.order.result.code;

import lombok.Setter;

/**
 * @author xjk
 * @date 2019/3/7 -  11:52
 * 操作状态码
 **/
@Setter
public class OperationStatusCode {
    /**
     * 成功
     */
    public static final int SUCCESS = 20000;
    /**
     * 失败
     */
    public static final int ERROR = 20001;
    /**
     * 用户名或密码错误
     */
    public static final int LOGINE_RROR = 20002;
    /**
     * 权限不足
     */
    public static final int ACCESS_ERROR = 20003;
    /**
     * 重复操作
     */
    public static final int REPEE_RROR = 20004;
    /**
     * 入参错误
     */
    public static final int PARAMETER_ERROR = 20005;

    /**
     *状态码
     */
    private int code;


}
