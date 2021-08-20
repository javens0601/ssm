package com.javen.es;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javen.es.dto.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class DocumentOperation {
    public static final String ipaddress = "139.224.70.66";
    public static final int port = 8081;

    public static void main(String[] args) throws Exception{

        RestClientBuilder clientBuilder = RestClient.builder(new HttpHost(ipaddress, port, HttpHost.DEFAULT_SCHEME_NAME));
        RestHighLevelClient client = new RestHighLevelClient(clientBuilder);

        //1、创建文档
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index("user").id("1");//自定义id

        User  user = new User();
        user.setAge(26);
        user.setName("javen");
        user.setSex("man");

        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        indexRequest.source(userJson, XContentType.JSON);//添加json格式的数据

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse.getResult());
        System.out.println(indexResponse.status());

        //2、修改文档
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("user").id("1");
        updateRequest.doc(XContentType.JSON, "name", "yumeiren");
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateResponse.status());

        //3、查询文档
        GetRequest getRequest = new GetRequest();
        getRequest.index("user").id("1");
        GetResponse documentFields = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(documentFields.getSourceAsString());

        //4、删除文档
        DeleteRequest deleteRequest = new DeleteRequest("user", "1");
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());

        client.close();
    }
}
