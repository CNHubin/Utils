package com.hubin.util;

import android.app.Activity;
import android.view.Display;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * @项目名： UtilDemo
 * @包名： com.hubin.util
 * @文件名: MeasureUtils
 * @创建者: 胡英姿
 * @创建时间: 2018/8/29 14:40
 * @描述： 测量工具
 */
public class MeasureUtils {
    public static int getWindownWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        int windownWidth = display.getWidth(); //屏幕宽度
        return windownWidth;
    }

    /**
     * 测量listview的高度
     *
     * @param listView
     */
    public static int getListViewHeight(Activity activity, ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null||listAdapter.getCount()==0) {
            return 0;
        }
        int totalHeight = 0;
//        int listViewWidth = windownWidth - UIUtils.dp2px(10);//listView在布局时的宽度
        int widthSpec = View.MeasureSpec.makeMeasureSpec(getWindownWidth(activity), View.MeasureSpec
                .AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(widthSpec, 0);
            int itemHeight = listItem.getMeasuredHeight();//条目宽度
            totalHeight += itemHeight;
        }
        // 减掉底部分割线的高度
        int historyHeight = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        return historyHeight;
    }

    /**
     * 测量listview第一个Item和分割线的高度
     * @param listView
     */
    public static int getListViewItemHeight(Activity activity, ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return 0;
        }
//        int listViewWidth = windownWidth - UIUtils.dp2px(10);//listView在布局时的宽度
        int widthSpec = View.MeasureSpec.makeMeasureSpec(getWindownWidth(activity), View.MeasureSpec
                .AT_MOST);
        View listItem = listAdapter.getView(0, null, listView);
        listItem.measure(widthSpec, 0);
        int itemHeight = listItem.getMeasuredHeight();//条目宽度
        // 减掉底部分割线的高度
        return itemHeight + listView.getDividerHeight();
    }
}
