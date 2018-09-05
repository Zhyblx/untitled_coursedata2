package com.course.test;

import com.course.study_qq.QQDataStorage;
import com.course.study_qq.QQJsoup;

public class QQTest {

    public static void main(String[] args) throws Exception {
//        QQJsoup.getQQRun("https://ke.qq.com/course/list?mt=1002&task_filter=0000000&&page=3");

        QQDataStorage.getQQDataStorage(QQJsoup.getQQRun("https://ke.qq.com/course/list?mt=1002&task_filter=0000000&&page=3"));
    }

}
