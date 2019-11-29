package com.catt.client;

import com.catt.client.utils.HttpClientUtil;
import com.catt.client.vo.HttpResultVO;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

class ClientApplicationTests {

    /**
     * form/data传参之get
     */
    @Test
    void hello() {
        HttpResultVO httpResultVO = HttpClientUtil.doGet("http://localhost:8080/hello", new HashMap<String, Object>() {{
            put("name", "Coco");
        }});
        System.out.println(httpResultVO);
    }

    /**
     * form/data传参之post
     */
    @Test
    void login() {
        HttpResultVO httpResultVO = HttpClientUtil.doPost("http://localhost:8080/login", new HashMap<String, Object>() {{
            put("username", "root");
            put("password", "root");
        }});
        System.out.println(httpResultVO);
    }

    /**
     * application/json传参
     */
    @Test
    void list() throws JSONException, UnsupportedEncodingException {
        String url = "http://localhost:8080/list";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page", 1);
        jsonObject.put("pageSize", 1);
        jsonObject.put("search", "search");

        StringEntity stringEntity = new StringEntity(jsonObject.toString());
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/json");//发送json数据需要设置contentType

        HttpResultVO httpResultVO = HttpClientUtil.doPost(url, stringEntity);
        System.out.println(httpResultVO);
    }
}
