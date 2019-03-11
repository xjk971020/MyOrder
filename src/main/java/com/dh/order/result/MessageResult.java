package com.dh.order.result;

import com.dh.order.result.enums.HttpExceptionEnum;
import com.dh.order.result.enums.ResultEnums;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author xjk
 * @date 2019/3/7 -  10:38
 **/
@NoArgsConstructor
@Getter
public class MessageResult {
    /**
     * 状态码
     */
    private Integer code;

    /**
     *状态信息
     */
    private String message;

    public MessageResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public MessageResult(Integer code, ResultEnums resultEnums) {
        this.code = code;
        this.message = resultEnums.getInfo();
    }

    public MessageResult(Integer code, HttpExceptionEnum httpExceptionEnum) {
        this.code = code;
        this.message = httpExceptionEnum.getInfo();
    }
}
