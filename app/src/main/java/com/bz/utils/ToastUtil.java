package com.bz.utils;

import android.content.Context;
import android.widget.Toast;

import com.bz.activity.AppContext;

/**
 * Created by Administrator on 2015/10/26.
 */
public class ToastUtil {
    public static boolean DEBUG = true;

    public static void showToast( String content) {
        if (DEBUG) {
            Toast.makeText(AppContext.mContext, content, Toast.LENGTH_SHORT).show();
        }
    }
}
