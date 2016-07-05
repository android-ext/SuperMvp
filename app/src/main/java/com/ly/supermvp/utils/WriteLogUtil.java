package com.ly.supermvp.utils;

import com.ly.supermvp.MyApplication;
import com.orhanobut.logger.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <Pre>
 *     将日志文件输出在本地日志
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/21 11:49
 */
public class WriteLogUtil {
    public static final String PATH = MyApplication.cacheDir + "/Log";
    public static final String LOG_FILE_NAME = "log.txt";

    /**
     * 是否写入日志文件
     */
    public static final boolean LOG_WRITE_TO_FILE = true;


    /**
     * 错误信息
     *
     * @param TAG
     * @param msg
     */
    public final static void e(String TAG, String msg) {
        Logger.e(TAG, msg);
        if (LOG_WRITE_TO_FILE)
            writeLogtoFile("e", TAG, msg);
    }

    /**
     * 警告信息
     *
     * @param TAG
     * @param msg
     */
    public final static void w(String TAG, String msg) {
        Logger.w(TAG, msg);
        if (LOG_WRITE_TO_FILE)
            writeLogtoFile("w", TAG, msg);
    }

    /**
     * 调试信息
     *
     * @param TAG
     * @param msg
     */
    public final static void d(String TAG, String msg) {
        Logger.d(TAG, msg);
        if (LOG_WRITE_TO_FILE)
            writeLogtoFile("d", TAG, msg);
    }

    /**
     * 提示信息
     *
     * @param TAG
     * @param msg
     */
    public final static void i(String TAG, String msg) {
        Logger.i(TAG, msg);
        if (LOG_WRITE_TO_FILE)
            writeLogtoFile("i", TAG, msg);
    }


    /**
     * 写入日志到文件中
     *
     * @param logType
     * @param tag
     * @param msg
     */
    private static void writeLogtoFile(String logType, String tag, String msg) {
        isExist(PATH);
        //isDel();
        String needWriteMessage = "\r\n"
                + TimeUtil.getNowMDHMSTime()
                + "\r\n"
                + logType
                + "    "
                + tag
                + "\r\n"
                + msg;
        File file = new File(PATH, LOG_FILE_NAME);
        try {
            FileWriter filerWriter = new FileWriter(file, true);
            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(needWriteMessage);
            bufWriter.newLine();
            bufWriter.close();
            filerWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除日志文件
     */
    public static void delFile() {

        File file = new File(PATH, LOG_FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 判断文件夹是否存在,如果不存在则创建文件夹
     *
     * @param path
     */
    public static void isExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

    }

}
