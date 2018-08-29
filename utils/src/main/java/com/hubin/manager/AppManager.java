package com.hubin.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.hubin.util.LogUtils;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * @项目名： UtilDemo
 * @包名： com.hubin.manager
 * @文件名: AppManager
 * @创建者: 胡英姿
 * @创建时间: 2018/8/29 14:23
 * @描述： APP管理的单例 对activity进行压栈弹栈管理
 */
public class AppManager {
    private static AppManager mAppManager;
    private static Stack<WeakReference<Activity>> mActivityStack;

    /**
     * 获得AppManager单例
     *
     * @return
     */
    public static AppManager getAppManager() {
        if (mAppManager == null) {
            synchronized (AppManager.class) {
                if (mAppManager == null) {
                    mAppManager = new AppManager();
                }
            }
        }
        return mAppManager;
    }

    /**
     * 添加activity到堆栈
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        LogUtils.d("addActivity  D  : 添加了任务到栈堆");
        if (mActivityStack == null) {
            mActivityStack = new Stack<WeakReference<Activity>>();
        }
        mActivityStack.push(new WeakReference<Activity>(activity));
        LogUtils.d("addActivity  D  : 任务栈中现有任务数：" + mActivityStack.size());
    }

    /**
     * 返回栈顶activity的字节码
     *
     * @return
     */
    public Class<?> getTopActivity() {
        Class<? extends Activity> aClass = null;
        if (mActivityStack != null && mActivityStack.size() != 0) {
            WeakReference<Activity> peek = mActivityStack.peek();
            Activity activity = peek.get();
            if (activity != null) {
                aClass = activity.getClass();
            }
        }
        return aClass;
    }

    /**
     * 结束栈顶的activity
     */
    public void finishActivity() {
        if (mActivityStack != null && mActivityStack.size() != 0) {
            WeakReference<Activity> pop = mActivityStack.pop();
            Activity activity = pop.get();
            LogUtils.d("finishActivity  D  : 弹栈：" + activity.getClass());
            activity.finish();
            activity = null;
            LogUtils.d("finishActivity  D  : 任务栈中现有任务数" + mActivityStack.size());
        }

    }

    /**
     * 结束指定的activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            LogUtils.d( "finishActivity  D  : 弹栈：" + activity.getClass());
            mActivityStack.remove(activity);
            activity.finish();
            activity = null;
        }
        LogUtils.d("finishActivity  D  : 任务栈中现有任务数" + mActivityStack.size());
    }

    /**
     * 结束指定类名的activity
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        for (WeakReference<Activity> activity : mActivityStack) {
            Activity a = activity.get();
            if (a != null && a.equals(cls)) {
                LogUtils.d("finishActivity  cls D  : 弹栈：");
                finishActivity(a);
            }
        }
        LogUtils.d("finishActivity  cls D  : 任务栈中现有任务数" + mActivityStack.size());
    }

    /**
     * 结束所有的activity
     */
    public void finishAllActivity() {
        for (int i = 0; i < mActivityStack.size(); i++) {
            if (null != mActivityStack.get(i)) {
                WeakReference<Activity> activity = mActivityStack.get(i);
                Activity a = activity.get();
                if (a != null) {
                    a.finish();
                }
            }
        }
        mActivityStack.clear();
    }


    /**
     * 完整退出应用  需要添加权限KILL_BACKGROUND_PROCESSES
     *
     * @param context
     */
    @SuppressLint("MissingPermission")
    public void exitApp(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(context
                    .ACTIVITY_SERVICE);
            activityManager.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
