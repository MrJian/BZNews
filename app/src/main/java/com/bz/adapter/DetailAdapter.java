package com.bz.adapter;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.bz.activity.AppContext;
import com.bz.activity.R;
import com.bz.base.MultiplyListAdapter;
import com.bz.bean.CommentBase;
import com.bz.model.CircleImageHelper;
import com.bz.model.FontsHelper;
import com.bz.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
public class DetailAdapter extends MultiplyListAdapter<CommentBase> {

    private List<CommentBase> list;

    public DetailAdapter() {

        this.list = list == null ? new ArrayList<CommentBase>() : list;
    }

    /**
     * 设置数据
     */
    public void setList(List<CommentBase> list) {

        this.list = list;
        notifyDataSetChanged();
    }

    /**
     * 追加数据
     */
    public void addList(List<CommentBase> list) {

        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void handleItem(View convertView, CommentBase data) {

        int type = data.type;
        switch (type) {
            case 0:

                TextView tv = (TextView) convertView;
                FontsHelper.applyFonts(tv).setText(data.date);
                Drawable drawable = getDrawableByText(data.date);
                if (drawable != null)
                    tv.setCompoundDrawables(drawable, null, null, null);
                tv.setCompoundDrawablePadding(10);
                break;
            case 1:

                CircleImageView img = (CircleImageView) FindFromConvertView(convertView, R.id.id_comment_img);
                CircleImageHelper.requestCircleImg(data.user.real_avatar_url, 50, 50, img);

                TextView name = (TextView) FindFromConvertView(convertView, R.id.id_comment_name);
                FontsHelper.applyFonts(name).setText(data.user.name);

                TextView content = (TextView) FindFromConvertView(convertView, R.id.id_comment_content);
                FontsHelper.applyFonts(content).setText(data.content);

                TextView time = (TextView) FindFromConvertView(convertView, R.id.id_comment_time);
                FontsHelper.applyFonts(time).setText(data.readable_time);

                TextView like = (TextView) FindFromConvertView(convertView, R.id.id_comment_like);
                FontsHelper.applyFonts(like).setText(String.valueOf(data.likes));
                break;
        }
    }

    private Drawable getDrawableByText(String date) {

        if ("热门评论".equals(date)) {

            Drawable drawable = AppContext.mContext.getResources().getDrawable(R.mipmap.icon_article_hotcomment);
            if (drawable != null)
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            return drawable;
        } else if ("最新评论".equals(date)) {

            Drawable drawable = AppContext.mContext.getResources().getDrawable(R.mipmap.icon_article_newcomment);
            if (drawable != null)
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            return drawable;
        }
        return null;
    }

    @Override
    public List<CommentBase> getData() {
        return list;
    }

    @Override
    public int[] getLayoutIds() {
        return new int[]{R.layout.item_lv_time, R.layout.item_detail_comments};
    }
}
