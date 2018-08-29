package com.hubin.util;

import android.text.*;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @项目名： UtilDemo
 * @包名： com.hubin.util
 * @文件名: StringUtils
 * @创建者: 胡英姿
 * @创建时间: 2018/8/29 14:34
 * @描述： 字符串工具类
 */
public class StringUtils {
    public final static String UTF_8 = "utf-8";

    /** 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false */
    public static boolean isEmpty(String value) {
        if (value != null && !"".equalsIgnoreCase(value.trim())
                && !"null".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }

    /** 判断多个字符串是否相等，如果其中有一个为空字符串或者null，则返回false，只有全相等才返回true */
    public static boolean isEquals(String... agrs) {
        String last = null;
        for (int i = 0; i < agrs.length; i++) {
            String str = agrs[i];
            if (isEmpty(str)) {
                return false;
            }
            if (last != null && !str.equalsIgnoreCase(last)) {
                return false;
            }
            last = str;
        }
        return true;
    }

    /**
     * 返回一个高亮spannable
     *
     * @param content
     *            文本内容
     * @param color
     *            高亮颜色
     * @param start
     *            起始位置
     * @param end
     *            结束位置
     * @return 高亮spannable
     */
    public static CharSequence getHighLightText(String content, int color,
                                                int start, int end) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        start = start >= 0 ? start : 0;
        end = end <= content.length() ? end : content.length();
        SpannableString spannable = new SpannableString(content);
        CharacterStyle span = new ForegroundColorSpan(color);
        spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    /**
     * 获取链接样式的字符串，即字符串下面有下划线
     *
     * @param resId
     *            文字资源
     * @return 返回链接样式的字符串
     */
    public static Spanned getHtmlStyleString(int resId) {
        StringBuilder sb = new StringBuilder();
        sb.append("<a href=\"\"><u><b>").append(UIUtils.getString(resId))
                .append(" </b></u></a>");
        return Html.fromHtml(sb.toString());
    }

    /** 格式化文件大小，不保留末尾的0 */
    public static String formatFileSize(long len) {
        return formatFileSize(len, false);
    }

    /** 格式化文件大小，保留末尾的0，达到长度一致 */
    public static String formatFileSize(long len, boolean keepZero) {
        String size;
        DecimalFormat formatKeepTwoZero = new DecimalFormat("#.00");
        DecimalFormat formatKeepOneZero = new DecimalFormat("#.0");
        if (len < 1024) {
            size = String.valueOf(len + "B");
        } else if (len < 10 * 1024) {
            // [0, 10KB)，保留两位小数
            size = String.valueOf(len * 100 / 1024 / (float) 100) + "KB";
        } else if (len < 100 * 1024) {
            // [10KB, 100KB)，保留一位小数
            size = String.valueOf(len * 10 / 1024 / (float) 10) + "KB";
        } else if (len < 1024 * 1024) {
            // [100KB, 1MB)，个位四舍五入
            size = String.valueOf(len / 1024) + "KB";
        } else if (len < 10 * 1024 * 1024) {
            // [1MB, 10MB)，保留两位小数
            if (keepZero) {
                size = String.valueOf(formatKeepTwoZero.format(len * 100 / 1024
                        / 1024 / (float) 100))
                        + "MB";
            } else {
                size = String.valueOf(len * 100 / 1024 / 1024 / (float) 100)
                        + "MB";
            }
        } else if (len < 100 * 1024 * 1024) {
            // [10MB, 100MB)，保留一位小数
            if (keepZero) {
                size = String.valueOf(formatKeepOneZero.format(len * 10 / 1024
                        / 1024 / (float) 10))
                        + "MB";
            } else {
                size = String.valueOf(len * 10 / 1024 / 1024 / (float) 10)
                        + "MB";
            }
        } else if (len < 1024 * 1024 * 1024) {
            // [100MB, 1GB)，个位四舍五入
            size = String.valueOf(len / 1024 / 1024) + "MB";
        } else {
            // [1GB, ...)，保留两位小数
            size = String.valueOf(len * 100 / 1024 / 1024 / 1024 / (float) 100)
                    + "GB";
        }
        return size;
    }

    /**
     * 将Int数组转换为以三个空格分隔的字符串 每8个换一行
     * @param arr
     */
    public static String intToString(int[] arr) {
        StringBuffer mStringBuffer = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            if (i==arr.length-1) {
                mStringBuffer.append(arr[i]);
            }else {
                mStringBuffer.append(arr[i]+"   ");
            }
            if ((i+1) % 10 == 0) {
                mStringBuffer.append("\n");
            }
        }
        return mStringBuffer.toString();
    }

    /**
     * 将Int数组转换为以三个空格分隔的字符串 不换行
     * @param arr
     */
    public static String intToString2(int[] arr) {
        StringBuffer mStringBuffer = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            if (i==arr.length-1) {
                mStringBuffer.append(arr[i]);
            }else {
                mStringBuffer.append(arr[i]+" ");
            }
        }
        return mStringBuffer.toString();
    }

    /**
     * 带花色的字符串
     * @param arr
     * @return
     */
    public static String strToWatchMode(int[] arr) {
        StringBuffer mStringBuffer = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            String cardStr = cardToWatch(arr[i]);
            if (i==arr.length-1) {
                mStringBuffer.append(cardStr);
            }else {
                mStringBuffer.append(cardStr+"   ");
            }
            if ((i+1) % 10 == 0) {
                mStringBuffer.append("\n");
            }
        }
        return mStringBuffer.toString();
    }

    public static String dealArrToShow(int[] arr) {
        StringBuffer mStringBuffer = new StringBuffer();
        for (int i = 0; i < arr.length; i+=2) {
            if (arr[i+1] == 1) {
                mStringBuffer.append("派");
            }
            if (arr[i+1] == 2) {
                mStringBuffer.append("公");
            }
            if (arr[i+1] == 3) {
                mStringBuffer.append("去");
            }
            mStringBuffer.append(arr[i]+"张  ");
        }
        return mStringBuffer.toString();

    }

    /**
     * 数字转换成扑克
     * @param number
     * @return
     */
    public static String cardToWatch(int number) {
        StringBuffer mStringBuffer = new StringBuffer();
        switch (number/100) {
            case 1:
                mStringBuffer.append("黑");
                break;
            case 2:
                mStringBuffer.append("红");
                break;
            case 3:
                mStringBuffer.append("梅");
                break;
            case 4:
                mStringBuffer.append("方");
                break;
            case 5:
                if (number == 513) {
                    mStringBuffer.append("大S");
                }
                if (number == 514) {
                    mStringBuffer.append("小S");
                }
                if (number == 516) {
                    mStringBuffer.append("广告");
                }
                return mStringBuffer.toString();
        }

        if (number % 100 == 1) {
            mStringBuffer.append("A");
        } else if (number % 100 == 10){
            mStringBuffer.append("0");
        } else if (number % 100 == 11){
            mStringBuffer.append("J");
        } else if (number % 100 == 12){
            mStringBuffer.append("Q");
        } else if (number % 100 == 13){
            mStringBuffer.append("K");
        } else {
            mStringBuffer.append(number%100);
        }
        return mStringBuffer.toString();

    }
    /**
     * 合并空格
     * @param str
     */
    public static String[] mergeSpace(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String[] split = str.split(" ");
        ArrayList<String> mArrayList = new ArrayList<String>();

        for (int i = 0; i < split.length; i++) {
            if (!(split[i].equals(" ")||split[i].equals(""))) {
                mArrayList.add(split[i].trim());
            }
        }

        String[] newArr = new String[mArrayList.size()];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = mArrayList.get(i);
        }
        return newArr;
    }

    /**
     * 将用数字和逗号拼接的字符串转换为数字数组
     * @param string
     * @return
     */
    public static int[] string2arr(String string) {
        String[] split = string.split(",");
        int[] mint = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            mint[i] = Integer.parseInt(split[i]);
        }
        return mint;
    }
}
