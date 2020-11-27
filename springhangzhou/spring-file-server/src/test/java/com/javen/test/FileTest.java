package com.javen.test;

import com.alibaba.fastjson.JSONObject;
import com.javen.controller.FileController;
import com.sun.deploy.util.ArrayUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.io.IOException;
import java.text.ParseException;

public class FileTest {

    private ApplicationContext context;
    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("spring.xml");
    }

    @Test
    public void getFileTest01() {
        FileController fileController = context.getBean(FileController.class);
        /*JSONObject fileInfo = fileController.getFileInfo();
        System.out.println(fileInfo);
*/
    }

    @Test
    public void getFileTest02() {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet("http://localhost:8080/file?name=jinwei");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                //responseEntity.getContent().mark(84);
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                String filelength = response.getHeaders("File-Length")[0].getElements()[0].getName();
                System.out.println("文件内容长度为:" + filelength);
                String msglength = response.getHeaders("Msg-Length")[0].getElements()[0].getName();
                System.out.println("报文内容长度为:" + msglength);
                //System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
                byte[] bytes = EntityUtils.toString(responseEntity).getBytes();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
