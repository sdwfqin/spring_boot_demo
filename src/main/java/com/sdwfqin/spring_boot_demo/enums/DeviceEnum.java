package com.sdwfqin.spring_boot_demo.enums;

/**
 * 设备code
 * <p>
 *
 * @author 张钦
 * @date 2019/8/30
 */
public enum DeviceEnum {

    Android(0, "android"),
    Ios(1, "ios"),
    Web(2, "web");

    private Integer code;
    private String type;

    DeviceEnum(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
