package com.bz.view;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bz.activity.R;
import com.bz.bean.HomeLvItem;
import com.bz.model.FontsHelper;
import com.mrjian.banner.CBViewHolderCreator;
import com.mrjian.banner.ConvenientBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * 轮播图
 * Created by Administrator on 2015/10/26.
 */
public class AdViewPage extends RelativeLayout implements ConvenientBanner.PageItemCallBack {


    private ConvenientBanner convenientBanner;

    private TextView textView;

    private List<String> imageUrls = new ArrayList<>();

    private List<String> title = new ArrayList<>();

    private List<HomeLvItem.Top_storiesEntity> list;

    public AdViewPage(Context context) {
        super(context);

        initView();
        convenientBanner.setCallBack(this);

    }

    private void init() {


        convenientBanner.setPageIndicator(new int[]{R.mipmap.img_dot_gray, R.mipmap.img_dot_white});
        convenientBanner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        convenientBanner.setPageTransformer(ConvenientBanner.Transformer.DefaultTransformer);
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new NetworkImageHolderView(list);
            }
        }, imageUrls);

    }

    private void initView() {

        View.inflate(getContext(), R.layout.item_lv_header, this);

        convenientBanner = (ConvenientBanner) findViewById(R.id.my_test_ad);
        textView = (TextView) findViewById(R.id.tv_msg);
    }

    @Override
    public void itemCall(int position) {

        if (!title.isEmpty()) {
            FontsHelper.applyFonts(textView).setText(title.get(position));
        }
    }

    public ConvenientBanner getConvenientBanner() {

        return convenientBanner;
    }


    //TODO 修改
    public void loadingData(List<HomeLvItem.Top_storiesEntity> list) {

        this.list = list;
        if (imageUrls.size() > 0) {
            imageUrls.clear();
        }
        if (title.size() > 0) {
            title.clear();
        }
        for (HomeLvItem.Top_storiesEntity entity : list) {

            imageUrls.add(entity.getImage());
            title.add(entity.getTitle());
        }

        init();
    }


}
