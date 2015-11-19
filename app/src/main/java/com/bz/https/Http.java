package com.bz.https;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bz.activity.AppContext;

/**
 * Created by Administrator on 2015/11/17.
 */
public class Http {


    private static final String TAG = "Http";

    /**
     * get请求
     */
    public static void getRequest(String url, final HttpFilter callBack){

        if (!checkNetWork()) {
            callBack.onHttpResult("404",null);
            return;
        }
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response == null) return;
                Log.i(TAG, "response:" + response);
                if (callBack != null) {
                    callBack.onHttpResult(null, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onHttpResult(error.toString(), null);
            }
        });
        MySingleton.newInstance(AppContext.mContext).addRequestToQueue(request);

    }

    /**
     * 判断网络是否有连接
     *
     * @return
     */
    private static boolean checkNetWork() {
        ConnectivityManager manager = (ConnectivityManager) AppContext.mContext
                .getSystemService(AppContext.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return true;
        }
        return false;
    }
}
