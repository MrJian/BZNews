package com.bz.model;

import android.graphics.Typeface;
import android.widget.TextView;

import com.bz.activity.AppContext;

/**
 * 引用 assets文件夹下字体
 * Created by Administrator on 2015/11/11.
 */
public class FontsHelper {

    private static String path = "fonts/FZXiHei-YS01.ttf";

    private static Typeface tf = Typeface.createFromAsset(AppContext.mContext.getAssets(), path);

    /**
     * 应用外部字体
     */
    public static TextView applyFonts(TextView tv) {

        tv.setTypeface(tf);
        return tv;
    }
}
