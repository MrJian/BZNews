package com.bz.fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.NetworkImageView;
import com.bz.activity.AppContext;
import com.bz.activity.R;
import com.bz.https.MySingleton;
import com.bz.utils.DisplayUtils;
import com.bz.utils.UrlUtils;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/11/17.
 */
public class WelcomeFragment extends BaseFragment {

    private NetworkImageView networkImageView;

    private static WelcomeFragment welcomeFragment;


    public static WelcomeFragment getInstance() {

        if (welcomeFragment == null) {
            welcomeFragment = new WelcomeFragment();
        }
        return welcomeFragment;
    }

    @Override
    protected void registerEvent() {

    }

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container) {

        networkImageView = (NetworkImageView) inflater.inflate(R.layout.fragment_welcome, container, false);
        return networkImageView;
    }

    @Override
    protected void initData() {

        String url = UrlUtils.formatUrl(DisplayUtils.getScreenWidth(getActivity()), DisplayUtils.getScreenHeight(getActivity()));
        networkImageView.setImageUrl(url, MySingleton.newInstance(AppContext.mContext).getImageLoader());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMainFragment();
            }
        }, 3000);
    }

    private void goToMainFragment() {

        EventBus.getDefault().post(MainFragment.getInstance());
    }

    @Override
    protected void unRegisterEvent() {

    }

    @Override
    public boolean finish() {
        return true;
    }
}
