package com.javen.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class DocumentOperationBatchInsert {
    public static final String ipaddress = "139.224.70.66";
    public static final int port = 8081;

    public static void main(String[] args) throws Exception{

        RestClientBuilder clientBuilder = RestClient.builder(new HttpHost(ipaddress, port, HttpHost.DEFAULT_SCHEME_NAME));
        RestHighLevelClient client = new RestHighLevelClient(clientBuilder);


        //1、批量插入
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new IndexRequest().index("user").id("1").source(XContentType.JSON, "name", "hangman", "sex","man", "age", 60));
        bulkRequest.add(new IndexRequest().index("user").id("2").source(XContentType.JSON, "name", "lisi", "sex","man", "age", 30));
        bulkRequest.add(new IndexRequest().index("user").id("3").source(XContentType.JSON, "name", "wing1", "sex","man", "age", 40));
        bulkRequest.add(new IndexRequest().index("user").id("4").source(XContentType.JSON, "name", "wangwu", "sex","women", "age", 30));
        bulkRequest.add(new IndexRequest().index("user").id("5").source(XContentType.JSON, "name", "wing2", "sex","man", "age", 20));
        bulkRequest.add(new IndexRequest().index("user").id("6").source(XContentType.JSON, "name", "wangwu3", "sex","women", "age", 50));
        bulkRequest.add(new IndexRequest().index("user").id("7").source(XContentType.JSON, "name", "wangwu4", "sex","man", "age", 60));

        BulkResponse bulkItemResponses = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkItemResponses.getTook());
        System.out.println(bulkItemResponses.status());
        for (BulkItemResponse item : bulkItemResponses.getItems()) {
            System.out.println(item.getResponse());
            System.out.println(item.status());
        }

        System.out.println("============================================================================");

        //2、全量查询
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.status());
        SearchHits hits = searchResponse.getHits();
        System.out.println(hits.getTotalHits());
        System.out.println(searchResponse.getTook());
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }

        //3、条件查询
        SearchRequest searchRequest1 = new SearchRequest();
        searchRequest1.indices("user");
        searchRequest1.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("age", 30)));
        SearchResponse searchResponse1 = client.search(searchRequest1, RequestOptions.DEFAULT);
        System.out.println(searchResponse1.status());
        SearchHits hits1 = searchResponse1.getHits();
        System.out.println(hits.getTotalHits());
        System.out.println(searchResponse1.getTook());
        for (SearchHit hit : hits1.getHits()) {
            System.out.println(hit.getSourceAsString());
        }


        //、批量删除
        /*BulkRequest bulkRequest1 = new BulkRequest();
        bulkRequest1.add(new DeleteRequest().index("user").id("1"));
        bulkRequest1.add(new DeleteRequest().index("user").id("2"));
        bulkRequest1.add(new DeleteRequest().index("user").id("3"));
        bulkRequest1.add(new DeleteRequest().index("user").id("4"));
        bulkRequest1.add(new DeleteRequest().index("user").id("5"));
        bulkRequest1.add(new DeleteRequest().index("user").id("6"));
        bulkRequest1.add(new DeleteRequest().index("user").id("7"));
        BulkResponse bulkItemResponses1 = client.bulk(bulkRequest1, RequestOptions.DEFAULT);
        System.out.println(bulkItemResponses1.getTook());
        System.out.println(bulkItemResponses1.status());
        for (BulkItemResponse item : bulkItemResponses1.getItems()) {
            System.out.println(item.getResponse());
            System.out.println(item.status());
        }*/

        client.close();
    }
}
