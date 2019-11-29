package com.catt.client.enums;

import lombok.Getter;

@Getter
public enum HttpRequestEnum {
    SUCCESS(200, "请求成功"),
    ERROR(400, "请求失败");

    private Integer code;

    private String msg;

    HttpRequestEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
