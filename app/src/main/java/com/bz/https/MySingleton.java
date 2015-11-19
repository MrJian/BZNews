package com.bz.https;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.Volley;

/**
 * Volley 单例
 */
public class MySingleton {

    private RequestQueue mQueue;
    private ImageLoader mImageLoader;
    private static MySingleton mMySingleton;
    private Context mCtx;

    private MySingleton(Context context) {

        long maxMemory = Runtime.getRuntime().maxMemory();
        final int maxSize = (int) (maxMemory / 8);

        this.mCtx = context;
        this.mQueue = getRequestQueue();
        this.mImageLoader = new ImageLoader(mQueue, new ImageCache() {

            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(
                    maxSize) {

                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getByteCount();
                }
            };

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }
        });
    }

    public static MySingleton newInstance(Context context) {

        if (mMySingleton == null) {
            synchronized (MySingleton.class) {

                if (mMySingleton == null) {
                    mMySingleton = new MySingleton(context);
                }
            }
        }
        return mMySingleton;
    }

    /**
     * 返回ImageLoader实例
     */
    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    /**
     * 构建RequestQueue
     */
    public RequestQueue getRequestQueue() {

        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mQueue;
    }

    /**
     * 添加到队列
     */
    public <T> void addRequestToQueue(Request<T> request) {
        mQueue.add(request);
    }
}