package com.sdwfqin.spring_boot_demo.bean;

import lombok.Data;

/**
 * 设备标识token
 * <p>
 *
 * @author 张钦
 * @date 2019/9/9
 */
@Data
public class DeviceTokenBean {

    private String android;
    private String ios;
    private String web;
}
