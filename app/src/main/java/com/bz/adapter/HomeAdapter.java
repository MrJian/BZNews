package com.bz.adapter;

import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.bz.activity.AppContext;
import com.bz.activity.R;
import com.bz.base.MultiplyListAdapter;
import com.bz.bean.ArticlesEntity;
import com.bz.https.MySingleton;
import com.bz.model.FontsHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 */
public class HomeAdapter extends MultiplyListAdapter<ArticlesEntity> {

    private List<ArticlesEntity> list;

    public HomeAdapter() {

        this.list = list == null ? new ArrayList<ArticlesEntity>() : list;
    }

    /**
     * 设置数据
     */
    public void setList(List<ArticlesEntity> list) {

        this.list = list;
        notifyDataSetChanged();
    }

    /**
     * 追加数据
     */
    public void addList(List<ArticlesEntity> list) {

        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void handleItem(View convertView, ArticlesEntity data) {

        int type = data.getType();
        switch (type) {
            case 0:

                NetworkImageView img = (NetworkImageView) FindFromConvertView(convertView, R.id.id_thumbnail);
                img.setDefaultImageResId(R.mipmap.image_small_default);
                img.setImageUrl(data.getThumbnail(), MySingleton.newInstance(AppContext.mContext).getImageLoader());
                TextView title = (TextView) FindFromConvertView(convertView, R.id.id_title);
                FontsHelper.applyFonts(title).setText(data.getTitle());
                TextView section = (TextView) FindFromConvertView(convertView, R.id.section_count);
                FontsHelper.applyFonts(section).setText(data.getSection_name() + "|" + data.getHit_count_string());
                break;
            case 1:

                TextView time = (TextView) convertView;
                time.setText(data.getDate());
                break;
        }

    }

    @Override
    public List<ArticlesEntity> getData() {
        return list;
    }

    @Override
    public int[] getLayoutIds() {
        return new int[]{R.layout.item_lv_comment, R.layout.item_lv_time};
    }
}
