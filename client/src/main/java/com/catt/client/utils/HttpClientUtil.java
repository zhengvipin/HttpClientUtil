package com.catt.client.utils;

import com.catt.client.enums.HttpRequestEnum;
import com.catt.client.vo.HttpResultVO;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;


/**
 * HttpClient工具类
 */
public class HttpClientUtil {

    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static HttpResultVO doGet(String url, Map<String, Object> map) {
        try {
            HttpGet httpGet = new HttpGet();
            if (!CollectionUtils.isEmpty(map)) {
                URIBuilder uriBuilder = new URIBuilder(url);
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
                }
                httpGet.setURI(uriBuilder.build());
            }
            return getHttpResultVO(httpGet);
        } catch (Exception e) {
            return new HttpResultVO(HttpRequestEnum.ERROR);
        }
    }

    public static HttpResultVO doPost(String url, Map<String, Object> data) {
        HttpPost httpPost = new HttpPost(url);
        try {
            if (!CollectionUtils.isEmpty(data)) {
                List<NameValuePair> params = new ArrayList<>();
                for (Map.Entry<String, Object> entry : data.entrySet()) {
                    params.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
                }
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");// 创建form表单对象
                httpPost.setEntity(formEntity);
            }
            return getHttpResultVO(httpPost);
        } catch (Exception e) {
            return new HttpResultVO(HttpRequestEnum.ERROR);
        }
    }

    public static HttpResultVO doPost(String url, HttpEntity httpEntity) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(httpEntity);
        try {
            return getHttpResultVO(httpPost);
        } catch (Exception e) {
            return new HttpResultVO(HttpRequestEnum.ERROR);
        }
    }

    private static HttpResultVO getHttpResultVO(HttpRequestBase httpRequestBase) throws IOException {
        CloseableHttpResponse response;
        response = httpClient.execute(httpRequestBase);
        HttpResultVO httpResultVO = new HttpResultVO();
        httpResultVO.setCode(response.getStatusLine().getStatusCode());
        if (response.getEntity() != null) {
            httpResultVO.setBody(EntityUtils.toString(response.getEntity(), "UTF-8"));
        }
        return httpResultVO;
    }
}
