package com.course.study_163;

import com.course.errorlog.ErrorLog;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

public class NeteaseDataProcessing implements Runnable {

    private File file = null;
    private InputStream inputStream = null;
    private InputStreamReader inputStreamReader = null;
    private BufferedReader bufferedReader = null;
    private String strTxt="";

    @Override
    public void run() {
        try {

            file = new File("NeteaseData.txt");
            inputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            strTxt = "";
            while (( strTxt = bufferedReader.readLine() ) != null) {
                Thread.sleep(1000);
//                System.out.println(strTxt);

                JSONObject jsonObject = JSONObject.fromObject(strTxt);
//                System.out.println(jsonObject.toString());

                JSONObject jsonObject1 = jsonObject.getJSONObject("result");
//                System.out.println(jsonObject1.toString());

//                JSONObject jsonObject2=jsonObject1.getJSONObject("query");
//                System.out.println(jsonObject2.toString());

                JSONArray jsonArray = jsonObject1.getJSONArray("list");
//                System.out.println(jsonArray.toString());

                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    String productName = jsonObject2.getString("productName");
//                    System.out.println(jsonObject2.getString("productName"));//产品名称
                    String provider = jsonObject2.getString("provider");
//                    System.out.println(jsonObject2.getString("provider"));//提供商
                    String score = jsonObject2.getString("score");
//                    System.out.println(jsonObject2.getString("score"));//评分
                    String learnerCount = jsonObject2.getString("learnerCount");
//                    System.out.println(jsonObject2.getString("learnerCount"));//学习者数量
                    String originalPrice = jsonObject2.getString("originalPrice");
//                    System.out.println(jsonObject2.getString("originalPrice"));//课程价格

                    File outFile = new File("NeteaseJsonData.txt");
                    OutputStream outputStream = new FileOutputStream(outFile, true);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    bufferedWriter.write(productName + "|" + provider + "|" + score + "|" + learnerCount + "|" + originalPrice + "\r\n");
                    bufferedWriter.close();
                    outputStreamWriter.close();
                    outputStream.close();
//                    System.out.println(productName + "|" + provider + "|" + score + "|" + learnerCount + "|" + originalPrice + "\r\n");

                }

//                JSONArray jsonArray = jsonObject.getJSONArray("code");
//                System.out.println(jsonArray.toString());
//                System.exit(0);
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (Exception e) {
//            e.printStackTrace();

            ErrorLog.getLog(strTxt);
        }
    }

    public static void main(String[] args) throws Exception {
        new Thread(new NeteaseDataProcessing()).start();
    }

}
