package com.hubin.util;

import android.content.Context;
import android.content.res.Resources;

import com.hubin.Utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @项目名： UtilDemo
 * @包名： com.hubin.util
 * @文件名: UIUtils
 * @创建者: 胡英姿
 * @创建时间: 2018/8/29 14:30
 * @描述： 主要封装和ui操作相关的方法
 */
public class UIUtils {
    /**
     * 得到上下文
     */
    public static Context getContext() {
        return Utils.getContext();
    }

    /**
     * 得到resource对象
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 得到string.xml中的字符串信息
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 得到string.xml中字符串数组
     */
    public static String[] getStrings(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 得到color.xml中颜色信息
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 得到应用程序的包名
     *
     * @return
     */
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    /**
     * dip-->px
     *
     * @param dp
     * @return
     */
    public static int dp2px(int dp) {
        //1. px/ppi/160 = dp
        //2. px/dp = density

        float density = UIUtils.getResources().getDisplayMetrics().density;
        int px = (int) (dp * density + .5f);
        return px;
    }

    /**
     * px-->dip
     *
     * @param px
     * @return
     */
    public static int px2dp(int px) {
        //1. px/ppi/160 = dp
        //2. px/dp = density

        float density = UIUtils.getResources().getDisplayMetrics().density;
        int dp = (int) (px / density + .5f);
        return dp;
    }

    /**
     * 获取系统时间的工具方法
     * @return  格式化过后的系统时间 ： 24：00：00
     */
    public static String getSystemTime() {
        String hours;
        String minutes;
        String seconds;

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(GregorianCalendar.HOUR);       //时
        int minute = calendar.get(GregorianCalendar.MINUTE);  //分
        int second = calendar.get(GregorianCalendar.SECOND);  //秒

        if (hour < 10) {
            hours = "0" + hour;
        }else {
            hours = hour+"";
        }

        if (minute < 10) {
            minutes = "0" + minute;
        }else {
            minutes = minute+"";
        }
        if (second < 10) {
            seconds = "0" + second;
        }else {
            seconds = second+"";
        }
        return hours+"："+minutes+"："+seconds;
    }

    /**
     * 获取系统日期的工具方法
     * @return  格式化过后的系统日期 ： 2010-12-30
     */
    public static String getSystemData() {
        String months;
        String days;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(GregorianCalendar.YEAR);       //年
        int month = calendar.get(GregorianCalendar.MONTH)+ 1;  //月
        int day = calendar.get(GregorianCalendar.DAY_OF_MONTH);  //日

        if (month < 10) {
            months = "0" + month;
        } else {
            months = month + "";
        }
        if (day < 10) {
            days = "0" + day;
        } else {
            days = day + "";
        }
        return year + "-" + months + "-" + days;
    }

}
