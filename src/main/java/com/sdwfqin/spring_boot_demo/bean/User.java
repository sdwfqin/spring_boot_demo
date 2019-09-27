package com.sdwfqin.spring_boot_demo.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 实体bean
 */
@Data
public class User implements Serializable {

    private String id;
    private String name;
    private Integer age;
    private String email;
    private String des;
    private Integer state;
    private String password;
    private String created_at;
}
