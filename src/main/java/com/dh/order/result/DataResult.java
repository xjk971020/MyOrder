package com.dh.order.result;

import com.dh.order.result.code.OperationStatusCode;
import com.dh.order.result.enums.ResultEnums;
import lombok.Setter;

/**
 * @author xjk
 * @date 2019/3/7 -  10:38
 **/
@Setter
public class DataResult<T> {
    /**
     *状态码
     */
    private int code;

    /**
     *数据
     */
    private T data;

    public DataResult(int code) {
        this.code = code;
    }

    public static <T> DataResult<T> success(T data) {
        DataResult<T> result = new DataResult<>(OperationStatusCode.SUCCESS);
        result.setData(data);
        return result;
    }
}
