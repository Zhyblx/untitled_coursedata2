# 课程数据获取项目

----
## 一、项目背景
1）获取网易云课堂、腾讯课堂、荔枝微课以上3个网站的课程信息。<br>
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


**B.腾讯课堂：**
1）腾讯课堂的数据获取Job在目录study_qq文件夹中。<br>
2）分析网站规则：<br>

    a.直接通过Jsoup Elements类对页面进行解析，得到课程名称、课程作者、学习人数以及价格的字段信息。示例代码:<br>
        public static String getQQRun(String URL){
            int i=0;
            String price="";
            String data="";
            String num="";
            try{
    
                connection=Jsoup.connect(URL);
                connection.userAgent(USERAGENT);
                connection.ignoreContentType(true);
                connection.timeout(10000);
                connection.header("content-type","text/html; charset=utf-8");
                connection.header("accept-encoding","gzip, deflate, br");
                connection.header("accept-language","zh-CN,zh;q=0.9");
                connection.header("content-encoding","gzip");
                connection.header("server","openresty");
                connection.header("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
                document=connection.get();
    //            System.out.println(document.html());
                elements=document.select("[class=course-card-list]");
                elements1=elements.select("[class=course-card-item]");
                while (i<elements1.size()){
                    if(i==24){
                        break;
                    }
    //                System.out.println(elements1.get(i).html());
                    elements2=elements1.get(i).select("[class=item-tt]");
                    elements3=elements2.select("[class=item-tt-link]");
                    //System.out.println(elements3.html());//课程名称
    
    
                    elements4=elements1.get(i).select("[class=item-line item-line--middle]");
                    elements5=elements4.select("[class=item-source-link]");
                    //System.out.println(elements5.html());//课程作者
    
    
                    elements6=elements4.select("[class=line-cell item-user]");
                    //System.out.println(elements6.html());//学习人数
                    String regex = "\\d*";
                    Pattern p = Pattern.compile(regex);
                    Matcher m = p.matcher(elements6.html());
                    while (m.find()) {
                        if (!"".equals(m.group())){
                            //System.out.println(m.group());//学习人数
                            num=m.group();
    
                        }
                    }
    
    
                    elements7=elements1.get(i).select("[class=item-line item-line--bottom]");
                    elements8=elements7.select("[class=line-cell item-price free]");
                    //System.out.println(elements8.html());//课程价格(免费)
                    elements9=elements7.select("[class=line-cell item-price]");
                    //System.out.println(elements9.html());//课程价格(费用)
                    price=elements8.html()+elements9.html();
    
                    data=data+elements3.html()+"|"+elements5.html()+"|"+"null"+"|"+num+"|"+price+"\r\n";
    
                    i++;
    
                }
                System.out.println(data);
    
            }catch(Exception e){
                // 异常不处理
            }
    
            return data;
        }


    b.获取到的数据通过QQDataStorage类进行存储;存放地址为根目录下QQData.txt文件中。<br>
    c.通过QQDataFile类循环遍历腾讯课堂的课程信息。示例代码：
                for (int i = 1; i < 35; i++) {
                    Thread.sleep(10000);
                    QQDataStorage.getQQDataStorage(QQJsoup.getQQRun("https://ke.qq.com/course/list?mt=1002&task_filter=0000000&&page=" + i));
                }

    备注：腾讯课堂的课程信息没有提供课程评分数据。


3）数据格式：<br>
  
  <table>
  <tr>
  <td>课程名称</td>
  <td>课程作者</td>
  <td>课程评分</td>
  <td>学习人数</td>
  <td>课程价格</td>
  </tr>
  
  <tr>
  <td>UG基础到产品设计</td>
  <td>东湖教育</td>
  <td>null</td>
  <td>461</td>
  <td>0</td>
  </tr>
  
  </table>