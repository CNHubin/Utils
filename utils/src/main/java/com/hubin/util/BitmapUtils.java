package com.hubin.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

/**
 * @项目名： UtilDemo
 * @包名： com.hubin.util
 * @文件名: BitmapUtils
 * @创建者: 胡英姿
 * @创建时间: 2018/8/29 14:55
 * @描述： Bitmap图像的工具类
 */
public class BitmapUtils {
    /**
     * 通过资源id转化成Bitmap
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap ReadBitmapById(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;

        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    /**
     * 按长方形裁切图片
     *
     * @param bitmap
     * @param heightScale 裁剪后余下高度的百分比
     * @return
     */
    public static Bitmap ImageCropWithRect(Bitmap bitmap, int heightScale) {
        if (bitmap == null) {
            return null;
        }

        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();

        int nw, nh, retX, retY;

        //新的宽度和高度
        nw = w; //宽度不变
        nh = h *heightScale/100; //高度按指定的百分比裁剪
        //第一个像素的坐标
        retX = 0;
        retY = h-nh;


        // 下面这句是关键
        Bitmap bmp = Bitmap.createBitmap(bitmap, retX, retY, nw, nh, null,
                false);
       /* if (bitmap != null && !bitmap.equals(bmp) && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }*/
        return bmp;
    }

}
