package com.javen.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

public class IndexOperation {
    public static final String ipaddress = "139.224.70.66";
    public static final int port = 8081;

    public static void main(String[] args) throws Exception{
        RestClientBuilder builder = RestClient.builder(new HttpHost(ipaddress, port, "http"));
        RestHighLevelClient client = new RestHighLevelClient(builder);


        //1、创建索引
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("user");
        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse.isAcknowledged());

        //2、查看索引
        GetIndexRequest getIndexRequest = new GetIndexRequest("user");
        GetIndexResponse getIndexResponse = client.indices().get(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(getIndexResponse.getSetting("user", "index.uuid"));
        System.out.println(getIndexResponse.getSettings());

        //3、删除索引
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("user");
        AcknowledgedResponse acknowledgedResponse = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println(acknowledgedResponse.isAcknowledged());


        client.close();
    }
}
