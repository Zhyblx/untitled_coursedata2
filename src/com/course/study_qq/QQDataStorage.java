package com.course.study_qq;

import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

/**
 * 类：QQDataStorage
 * 作用：对腾讯课堂的课程数据进行存储;存放地址为根目录下QQData.txt文件中。
 */

public final class QQDataStorage {

    private static File file = null;
    private static OutputStream outputStream = null;
    private static OutputStreamWriter outputStreamWriter = null;
    private static BufferedWriter bufferedWriter = null;

    public static void getQQDataStorage(String QQdata) {
        try {

            file = new File("QQData.txt");
            outputStream = new FileOutputStream(file, true);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(QQdata + "\r\n");
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();

        } catch (Exception e) {
            // 异常不处理
        }
    }

}
