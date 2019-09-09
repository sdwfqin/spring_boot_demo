package com.sdwfqin.spring_boot_demo.utils.result;

/**
 * Result Base
 * <p>
 *
 * @author 张钦
 * @date 2019/8/30
 */
public class Result<T> {

    /**
     * 状态值
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;

    /**
     * 错误信息
     */
    private String errorLog;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorLog() {
        return errorLog;
    }

    public void setErrorLog(String errorLog) {
        this.errorLog = errorLog;
    }
}
