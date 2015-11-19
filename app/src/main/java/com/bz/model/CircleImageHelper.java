package com.bz.model;

import android.graphics.Bitmap;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.bz.activity.AppContext;
import com.bz.activity.R;
import com.bz.https.MySingleton;
import com.bz.utils.L;
import com.bz.view.CircleImageView;

/**
 * Created by Administrator on 2015/11/19.
 */
public class CircleImageHelper {

    private static String TAG  = "CircleImageHelper";

    /**
     * 请求圆形图片
     */
    public static void requestCircleImg(String url, int maxWidth, int maxHeight, final CircleImageView img) {

        img.setImageResource(R.drawable.default_image);

        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                if (response != null) {
                    img.setImageBitmap(response);
                }
            }
        }, maxWidth, maxHeight, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                L.e(TAG, error.toString());
            }
        });

        MySingleton.newInstance(AppContext.mContext).addRequestToQueue(imageRequest);
    }

}
