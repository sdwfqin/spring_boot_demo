package com.sdwfqin.spring_boot_demo.enums;

/**
 * 返回值code
 * <p>
 *
 * @author 张钦
 * @date 2019/8/30
 */
public enum ResultEnum {
    ERROR_UNKONE(-1, "未知错误"),
    SUCCESS(0, "操作成功"),
    ERROR(1, "操作失败"),
    VALID_ERROR(2, "参数校验失败，请检查输入是否有误"),
    LOGIN_ERROR(3, "登录失败，用户名或密码错误"),
    TOKEN_ERROR(10001, "Token验证失败"),
    AUTHORITY_ERROR(10002, "无权访问，请联系管理员");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
