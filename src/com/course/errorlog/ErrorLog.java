package com.course.errorlog;

import java.util.logging.Logger;

/**
 * 类：ErrorLog
 * 作用：输出错误日志
 */

public class ErrorLog {

    private static Logger logger = Logger.getLogger("错误为：");

    public static void getLog(String Msg) {
        try {
            // Msg为定义的错误消息
            logger.info(Msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
