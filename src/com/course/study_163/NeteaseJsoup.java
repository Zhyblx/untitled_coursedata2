package com.course.study_163;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

public final class NeteaseJsoup {

    private static final String URL="https://study.163.com/p/search/studycourse.json";
    private static final String USERAGENT="Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36";

    private static Connection connection=null;
    private static Document document=null;

    public static String getNeteaseRun(String strJson){
        String strDoc="";
        try{

            connection=Jsoup.connect(NeteaseJsoup.URL);
            connection.userAgent(NeteaseJsoup.USERAGENT);
            connection.ignoreContentType(true);
            connection.timeout(10000);
            connection.header("Accept","application/json");
            connection.header("Accept-Encoding","gzip, deflate, br");
            connection.header("Accept-Language","zh-CN,zh;q=0.9");
            connection.header("Connection","keep-alive");
            connection.header("Content-Length","148");
            connection.header("Content-Type","application/json");
            connection.cookie("EDUWEBDEVICE","720485d2d3844caebacda643ee8cdb53");
            // requestBody()方法是Jsoup post请求中用于发送 json 的请求参数
            connection.requestBody(strJson);
            document=connection.post();
            strDoc=document.text();
            System.out.println(strDoc);

        }catch (Exception e){
            // 异常不处理
        }
        return strDoc;
    }

}
