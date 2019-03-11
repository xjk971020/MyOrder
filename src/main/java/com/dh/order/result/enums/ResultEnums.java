package com.dh.order.result.enums;

/**
 * @author xjk
 * @date 2019/3/7 -  11:43
 * 操作请求处理
 **/
public enum ResultEnums {
    SUCCESS("操作成功"),
    ERROR("操作失败"),
    INNER_ERROR("系统发生异常"),
    INPUT_ERROR("输入信息有误"),
    LOGIN_SUCCESS("登录成功"),
    LOGIN_UNKNOWN("账户不存在"),
    LOGIN_ERROR("用户名或密码错误"),
    LOGIN_CHECK_ERROR("输入的旧密码不匹配"),
    PARAMETER_ERROR("入参错误");

    private String info;

    public String getInfo() {
        return info;
    }

    ResultEnums(String info) {
        this.info = info;
    }
}
