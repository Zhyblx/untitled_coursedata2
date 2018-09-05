package com.course.study_qq;

/**
 * 类：QQDataFile
 * 作用：通过循环遍历腾讯课堂的课程信息，并进行抓取和存储。
 *
 */

public final class QQDataFile implements Runnable {

    private static final String URL = "https://ke.qq.com/course/list?mt=1002&task_filter=0000000&&page=";

    @Override
    public void run() {
        try {

            for (int i = 1; i < 35; i++) {
                Thread.sleep(10000);
                QQDataStorage.getQQDataStorage(QQJsoup.getQQRun("https://ke.qq.com/course/list?mt=1002&task_filter=0000000&&page=" + i));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new Thread(new QQDataFile()).start();
    }


}
