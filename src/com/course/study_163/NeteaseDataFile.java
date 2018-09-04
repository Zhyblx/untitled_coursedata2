package com.course.study_163;

/**
 * 类：NeteaseDataFile
 * 作用：完成计算用于获取课程数据的json参数。
 * 原理：
 * 1、将json格式的请求参数划分为三段。分别体现在Stra、Strb、Strc。
 * 2、通过for循环完成计算请求参数中的pageIndex和relativeOffset的正确值。
 * 3、使用 NeteaseJsoup.getNeteaseRun()进行数据获取，同时使用NeteaseDataStorage.getNeteaseDataStorage()对获取的json数据进行存储。
 *
 */

public class NeteaseDataFile implements Runnable {

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

    public static void main(String[] args) throws Exception {
        new Thread(new NeteaseDataFile()).start();
    }


}
