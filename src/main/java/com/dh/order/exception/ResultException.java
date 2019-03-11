package com.dh.order.exception;

import com.dh.order.result.enums.ResultEnums;

/**
 * @author xjk
 * @date 2019/3/7 -  12:19
 **/
public class ResultException extends RuntimeException {
    public ResultException(String message) {
        super(message);
    }

    public ResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultException(ResultEnums resultEnums) {

    }
}
