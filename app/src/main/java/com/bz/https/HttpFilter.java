package com.bz.https;

/**
 * Created by Administrator on 2015/11/3.
 */
public interface HttpFilter {
    void onHttpResult(String err, String result);
}
