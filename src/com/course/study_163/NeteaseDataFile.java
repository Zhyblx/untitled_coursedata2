package com.course.study_163;

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
