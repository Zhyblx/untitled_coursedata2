package com.course.study_163;

import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

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
