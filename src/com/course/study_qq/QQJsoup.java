package com.course.study_qq;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类:QQJsoup
 * 作用：获取腾讯课堂的课程信息,通过Elements直接对页面结构进行解析以得到课程名称、课程作者、学习人数以及价格。
 * 备注：腾讯课堂的课程信息没有提供课程评分数据。
 *
 */

public final class QQJsoup {

    private static final String USERAGENT="Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36";

    private static Connection connection=null;
    private static Document document=null;
    private static Elements elements=null;
    private static Elements elements1=null;
    private static Elements elements2=null;
    private static Elements elements3=null;

    private static Elements elements4=null;
    private static Elements elements5=null;
    private static Elements elements6=null;

    private static Elements elements7=null;
    private static Elements elements8=null;
    private static Elements elements9=null;

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

}
