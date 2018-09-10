package com.hubin.util;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * @项目名： Utils
 * @包名： com.hubin.util
 * @文件名: ServiceUtils
 * @创建者: 胡英姿
 * @创建时间: 2018/9/10 15:59
 * @描述： 服务工具
 */
public class ServiceUtils {
    /**
     * 判断某个服务是否正在运行
     * @param mContext
     * @param serviceName
     *            是包名+服务的类名（例如：net.loonggg.testbackstage.TestService）
     * @return true代表正在运行，false代表服务没有正在运行
     */
    public static boolean isServiceRunning(Context mContext, String serviceName) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(40);
        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(serviceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }
}
