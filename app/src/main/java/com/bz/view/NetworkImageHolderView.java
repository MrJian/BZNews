package com.bz.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.toolbox.NetworkImageView;
import com.bz.activity.R;
import com.bz.bean.HomeLvItem;
import com.bz.fragment.DetailFragment;
import com.bz.https.MySingleton;
import com.mrjian.banner.CBPageAdapter;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public class NetworkImageHolderView implements CBPageAdapter.Holder<String> {

    private NetworkImageView networkImageView;

    private List<HomeLvItem.Top_storiesEntity> list;

    public NetworkImageHolderView(List<HomeLvItem.Top_storiesEntity> list) {
        this.list = list;
    }

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页

        networkImageView = new NetworkImageView(context);
        networkImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return networkImageView;
    }

    @Override
    public void UpdateUI(Context context, final int position, String data) {

        networkImageView.setDefaultImageResId(R.drawable.default_image);
        networkImageView.setImageUrl(data, MySingleton.newInstance(context).getImageLoader());

        networkImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("url", list.get(position).getUrl());
                bundle.putString("id", list.get(position).getId());
                DetailFragment fragment = new DetailFragment();
                fragment.setArguments(bundle);
                EventBus.getDefault().post(fragment);
            }
        });

    }
}
