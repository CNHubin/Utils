package com.hubin.util;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @项目名： UtilDemo
 * @包名： com.hubin.util
 * @文件名: ThreadUtils
 * @创建者: 胡英姿
 * @创建时间: 2018/8/29 14:29
 * @描述： 线程池工具类
 */
public class ThreadUtils {
    /**
     * 获取UI线程的handler
     */
    public static Handler mainHandler = new Handler(Looper.getMainLooper());


    public static String getThreadName() {
        return Thread.currentThread().getName() + " 线程ID：" + Thread.currentThread().getId();
    }

    /**
     * 当前是否在主线程
     * @return
     */
    public static boolean isMainThread() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return true;
        }
        return false;
    }

    /**
     * 创建一个 CPU核心数+1 大小的线程池
     */
    private static final ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime()
            .availableProcessors() + 1);

    /**
     * 运行一个新的线程
     * @param runnable
     */
    public static void runOnNewThread(Runnable runnable) {
        service.execute(runnable);
    }
}
