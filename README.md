# 课程数据获取项目

----
## 一、项目背景
1）获取网易云课堂、腾讯课堂、荔枝微课、千聊以上4个网站的课程信息。<br>
2）需获取课程字段包括： 课程名称、课程作者、课程评分、学习人数、课程价格。<br>
3）数据的输出格式为：Excel

## 二、各网站的数据获取情况
**A.网易云课堂：**

1）网易云课堂的数据获取Job在目录study_163文件夹中。<br>
2）分析网站规则：<br>
    
    a.网站的请求链接：private static final String URL="https://study.163.com/p/search/studycourse.json";
    b.post请求的数据格式为Json串;
    c.本项目的数据获取是基于Jsoup进行开发，Jsoup的post请求用于发送 json 格式的请求方法为.requestBody(strJson);
    d.请求参数json格式：{"pageIndex":1,"pageSize":50,"relativeOffset":0,"frontCategoryId":-1,"searchTimeType":-1,"orderType":50,"priceType":-1,"activityId":0,"keyword":""}
3）代码片段：<br>

    a.抓取网易云课堂数据:
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
                
    b.生成json串用作于请求参数：
                private String Stra = "{\"pageIndex\":";
                    private String Strb = ",\"pageSize\":50,\"relativeOffset\":";
                    private String Strc = ",\"frontCategoryId\":-1,\"searchTimeType\":-1,\"orderType\":50,\"priceType\":-1,\"activityId\":0,\"keyword\":\"\"}";
                
                    @Override
                    public void run() {
                        int j = 0;
                        try {
                
                            for (int i = 0; i <= 2950; i = i + 50) {
                                j++;
                                System.out.println(j + "-----------" + i);
                
                                NeteaseDataStorage.getNeteaseDataStorage(
                                        NeteaseJsoup.getNeteaseRun(Stra + j + Strb + i + Strc)
                                );
                            }
                
                        } catch (Exception e) {
                
                        }
                    }
    c.对获取到的json数据进行结果解析：
            while (( strTxt = bufferedReader.readLine() ) != null) {
                Thread.sleep(1000);
                JSONObject jsonObject = JSONObject.fromObject(strTxt);
                JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                JSONArray jsonArray = jsonObject1.getJSONArray("list");
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    String productName = jsonObject2.getString("productName");
                    String provider = jsonObject2.getString("provider");
                    String score = jsonObject2.getString("score");
                    String learnerCount = jsonObject2.getString("learnerCount");
                    String originalPrice = jsonObject2.getString("originalPrice");

                    File outFile = new File("NeteaseJsonData.txt");
                    OutputStream outputStream = new FileOutputStream(outFile, true);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    bufferedWriter.write(productName + "|" + provider + "|" + score + "|" + learnerCount + "|" + originalPrice + "\r\n");
                    bufferedWriter.close();
                    outputStreamWriter.close();
                    outputStream.close();
                }
             }
           
4）数据格式：<br>

<table>
<tr>
<td>课程名称</td>
<td>课程作者</td>
<td>课程评分</td>
<td>学习人数</td>
<td>课程价格</td>
</tr>

<tr>
<td>AE教程超级合辑【影视后期必学】</td>
<td>设计软件通</td>
<td>5</td>
<td>2154</td>
<td>699</td>
</tr>

</table>

