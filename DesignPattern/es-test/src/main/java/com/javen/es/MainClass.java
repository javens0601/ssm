package com.javen.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class MainClass {
    public static final String ipaddress = "139.224.70.66";
    public static final int port = 8081;

    public static void main(String[] args) throws Exception{
        RestClientBuilder builder = RestClient.builder(new HttpHost(ipaddress, port, "http"));
        RestHighLevelClient client = new RestHighLevelClient(builder);


        client.close();
    }
}
