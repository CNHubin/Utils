package com.hubin.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * @项目名： UtilDemo
 * @包名： com.hubin.util
 * @文件名: IOUtils
 * @创建者: 胡英姿
 * @创建时间: 2018/8/29 14:40
 * @描述： IO操作
 */
public class IOUtils {

    /**
     * 关流
     * @param io
     * @return
     */
    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                LogUtils.e(e.toString());
            }
        }
        return true;
    }
}
