package com.catt.client.vo;

import com.catt.client.enums.HttpRequestEnum;
import lombok.Data;

@Data
public class HttpResultVO {

    private Integer code;

    private String body;

    public HttpResultVO() {
    }

    public HttpResultVO(Integer code, String body) {
        this.code = code;
        this.body = body;
    }

    public HttpResultVO(HttpRequestEnum httpRequestEnum) {
        this.code = httpRequestEnum.getCode();
        this.body = httpRequestEnum.getMsg();
    }
}
