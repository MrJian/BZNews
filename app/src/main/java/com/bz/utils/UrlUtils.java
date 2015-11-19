package com.bz.utils;

/**
 * Created by Administrator on 2015/11/17.
 */
public class UrlUtils {

    private static String endUrl = "/comments";
    private static String endUrls = "/extra";

    /**
     * 根据屏幕分辨率拼接URL
     */
    public static String formatUrl(int width, int height) {

        return Constans.URL.WELCOME + width + "*" + height;
    }

    /**
     * 返回前一天的日期
     */
    public static String beforeDate(String date) {

        return String.valueOf(Integer.parseInt(date) - 1);
    }

    public static String commentUrl(String id) {
        return Constans.URL.Comment + id + endUrl;
    }

    public static String commentUrlExtra(String id) {
        return Constans.URL.CommentHeaderS + id + endUrls;
    }
}
