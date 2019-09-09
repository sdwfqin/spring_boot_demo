package com.sdwfqin.spring_boot_demo.utils.exception;

import com.sdwfqin.spring_boot_demo.enums.ResultEnum;

/**
 * 自定义服务异常，必须继承RuntimeException
 * <p>
 *
 * @author 张钦
 * @date 2019/8/30
 */
public class ServiceException extends RuntimeException {

    private Integer code;

    public ServiceException(ResultEnum resultErrorEnum) {
        super(resultErrorEnum.getMsg());
        this.code = resultErrorEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
