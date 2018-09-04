package com.course.test;

import com.course.study_163.NeteaseJsoup;
import com.course.study_163.NeteaseDataStorage;

public class NeteaseTest {

    public static void main(String[] args){
        NeteaseDataStorage.getNeteaseDataStorage(NeteaseJsoup.getNeteaseRun("{\"pageIndex\":4,\"pageSize\":50,\"relativeOffset\":150,\"frontCategoryId\":-1,\"searchTimeType\":-1,\"orderType\":50,\"priceType\":-1,\"activityId\":0,\"keyword\":\"\"}"));
    }

}
