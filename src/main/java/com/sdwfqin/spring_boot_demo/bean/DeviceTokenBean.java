package com.sdwfqin.spring_boot_demo.bean;

/**
 * 设备标识token
 * <p>
 *
 * @author 张钦
 * @date 2019/9/9
 */
public class DeviceTokenBean {

    private String android;
    private String ios;
    private String web;

    public String getAndroid() {
        return android;
    }

    public void setAndroid(String android) {
        this.android = android;
    }

    public String getIos() {
        return ios;
    }

    public void setIos(String ios) {
        this.ios = ios;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
