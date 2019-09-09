package com.sdwfqin.spring_boot_demo.utils.result;

import com.sdwfqin.spring_boot_demo.enums.ResultEnum;
import com.sdwfqin.spring_boot_demo.utils.result.Result;

/**
 * Result响应工具类
 * <p>
 *
 * @author 张钦
 * @date 2019/8/30
 */
public class ResultUtils {

    public static <T> Result<T> resultData(ResultEnum resultEnum, T t) {
        return resultData(resultEnum.getCode(), resultEnum.getMsg(), t, "");
    }

    public static <T> Result<T> errorData(ResultEnum resultEnum, String errorLog) {
        return resultData(resultEnum.getCode(), resultEnum.getMsg(), null, errorLog);
    }

    public static <T> Result<T> errorData(Integer code, String msg, String errorLog) {
        return resultData(code, msg, null, errorLog);
    }

    private static <T> Result<T> resultData(Integer code, String msg, T t, String errorLog) {

        Result<T> result = new Result<>();

        result.setCode(code);
        result.setMsg(msg);
        result.setData(t);
        result.setErrorLog(errorLog);

        return result;
    }
}
