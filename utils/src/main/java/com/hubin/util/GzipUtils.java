package com.hubin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @项目名： UtilDemo
 * @包名： com.hubin.util
 * @文件名: GzipUtils
 * @创建者: 胡英姿
 * @创建时间: 2018/8/29 14:41
 * @描述： 压缩和解压工具类
 */
public class GzipUtils {
    public static void zip(String inPath, String outPath) throws Exception {
        zip(new File(inPath), new File(outPath));
    }

    public static void zip(File in, File out) throws Exception {
        zip(new FileInputStream(in), new FileOutputStream(out));
    }

    public static void zip(InputStream is, OutputStream os) throws Exception {
        GZIPOutputStream gos = new GZIPOutputStream(os);
        int len;
        byte[] buffer = new byte[1024];

        while ((len = is.read(buffer)) != -1) {
            gos.write(buffer, 0, len);
        }

        IOUtils.close(gos);
        IOUtils.close(is);
    }

    public static void unzip(String inPath, String outPath) throws Exception {
        unzip(new File(inPath), new File(outPath));
    }

    public static void unzip(File in, File out) throws Exception {
        unzip(new FileInputStream(in), new FileOutputStream(out));
    }

    public static void unzip(InputStream is, OutputStream os) throws Exception {

        GZIPInputStream gis = new GZIPInputStream(is);
        int len;
        byte[] buffer = new byte[1024];
        while ((len = gis.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        IOUtils.close(gis);
        IOUtils.close(os);
    }
}
