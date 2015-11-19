package com.bz.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.bz.activity.AppContext;
import com.bz.activity.R;
import com.bz.bean.DetailBean;
import com.bz.https.MySingleton;
import com.bz.model.CircleImageHelper;
import com.bz.model.FontsHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/11/19.
 */
public class DetailHeader extends RelativeLayout {


    @Bind(R.id.id_detail_img)
    NetworkImageView idDetailImg;
    @Bind(R.id.id_detail_author_avatar)
    CircleImageView idDetailAuthorAvatar;
    @Bind(R.id.id_detail_name)
    TextView idDetailName;
    @Bind(R.id.id_detail_time)
    TextView idDetailTime;
    @Bind(R.id.id_detail_web_view)
    WebView idDetailWebView;

    @Bind(R.id.id_detail_title)
    TextView idDetailTitle;

    @Bind(R.id.id_relative_loading)
    Waitting waitting;

    public DetailHeader(Context context) {
        super(context);
        initView();
        ButterKnife.bind(this);
    }

    /**
     * 初始化
     */
    private void initView() {

        View.inflate(AppContext.mContext, R.layout.item_detal_header, this);
    }

    public void loadingData(DetailBean bean) {

        idDetailImg.setDefaultImageResId(R.drawable.default_image);
        idDetailImg.setImageUrl(bean.image, MySingleton.newInstance(AppContext.mContext).getImageLoader());

        FontsHelper.applyFonts(idDetailTitle).setText(bean.title);

        CircleImageHelper.requestCircleImg(bean.author_avatar, 40, 40, idDetailAuthorAvatar);

        FontsHelper.applyFonts(idDetailName).setText(bean.author_name);

//        FontsHelper.applyFonts(idDetailTime).setText(bean.author_name);
        idDetailWebView.getSettings().setJavaScriptEnabled(true);


//        idDetailWebView.loadData(bean.url, bean.body, "text/html", "UTF-8", null);
        idDetailWebView.loadDataWithBaseURL(bean.url, bean.body, "text/html", "UTF-8", null);
        idDetailWebView.setWebViewClient(new MyWebViewClient());


    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

        }

        @Override
        public void onPageFinished(WebView view, String url) {

        }
    }

}
