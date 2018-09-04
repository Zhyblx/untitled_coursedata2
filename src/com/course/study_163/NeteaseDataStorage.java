package com.course.study_163;

import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

/**
 * 类：NeteaseDataStorage
 * 作用：存储数据；对传入方法getNeteaseDataStorage(String strDoc) 的strDoc参数(或字符串)会存储到NeteaseData.txt文件当中。
 */

public final class NeteaseDataStorage {

    private static File file = null;
    private static OutputStream outputStream = null;
    private static OutputStreamWriter outputStreamWriter = null;
    private static BufferedWriter bufferedWriter = null;

    public static void getNeteaseDataStorage(String strDoc) {
        try {

            file = new File("NeteaseData.txt");
            outputStream = new FileOutputStream(file, true);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            // 将参数strDoc存储到NeteaseData.txt文件中。
            bufferedWriter.write(strDoc + "\r\n");
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();
            System.out.println(strDoc + "\r\n");
        } catch (Exception e) {
            // 异常不处理
        }
    }

}
