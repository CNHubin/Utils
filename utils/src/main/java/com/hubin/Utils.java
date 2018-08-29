package com.hubin;

import android.app.Application;
import android.content.Context;

/**
 * @项目名： UtilDemo
 * @包名： com.hubin
 * @文件名: Utils
 * @创建者: 胡英姿
 * @创建时间: 2018/8/29 14:22
 * @描述： 使用此工具类前必须调用 init进行初始化
 */
public class Utils {
    private static Application mApp;
    private static Context mContext;
    private static Boolean DEBUG = true;
    public static String CRASH_DIR_NAME = "crash_log";

    public static void init(Application app, boolean isDebug) {
        mApp=app;
        init(app.getApplicationContext(),isDebug);
    }
    public static void init(Context context, boolean isDebug) {
        mContext=context;
        DEBUG = isDebug;
    }

    public static Context getContext() {
        return mContext;
    }

    public static Boolean isDebug() {
        return DEBUG;
    }

    /**
     * 设置收集崩溃日志的存储文件夹名字
     * @param crashDirName
     */
    public static void setCrashDirName(String crashDirName) {
        CRASH_DIR_NAME = crashDirName;
    }

    public static String getCrashDirName() {
        return CRASH_DIR_NAME;
    }
}
