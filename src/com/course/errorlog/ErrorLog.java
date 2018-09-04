package com.course.errorlog;


import java.util.logging.Logger;

public class ErrorLog {

    private static Logger logger = Logger.getLogger("错误为：");

    public static void getLog(String Msg) {
        try {
            logger.info(Msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
