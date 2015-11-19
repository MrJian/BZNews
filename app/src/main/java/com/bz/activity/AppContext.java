package com.bz.activity;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015/11/17.
 */
public class AppContext extends Application {


    public static AppContext mContext;
    private static SharedPreferences spf;

    private static String newKey = "newDate";
    private static String defaultVale = "000000";
    private static String beforeKey = "beforeKey";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        spf = getSharedPreferences("date", MODE_PRIVATE);
    }

    /**
     * 从暂存区存数据
     */
    public static void putDate(String date) {


        SharedPreferences.Editor edit = spf.edit();
        if (!getDate(newKey).equals(date)) {
            edit.clear();
            edit.putString(newKey, date);
//            edit.commit();
            edit.apply();
        }

    }

    public static String getNewDate(){

        return getDate(newKey);
    }

    public static String getBeforeDate(){

        return getDate(beforeKey);
    }



    /**
     * 从暂存区取数据
     */
    public static String getDate(String key) {

        return spf.getString(key, defaultVale);
    }

    public static void putBeforeDate(String date){

        SharedPreferences.Editor edit = spf.edit();
        if (!getDate(beforeKey).equals(date)) {
            edit.clear();
            edit.putString(newKey, date);
//            edit.commit();
            edit.apply();
        }
    }

}
