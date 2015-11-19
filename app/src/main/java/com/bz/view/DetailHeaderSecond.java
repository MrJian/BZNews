package com.bz.view;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bz.activity.AppContext;
import com.bz.activity.R;
import com.bz.bean.DetailSecondBean;
import com.bz.model.CircleImageHelper;
import com.bz.model.FontsHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/11/19.
 */
public class DetailHeaderSecond extends RelativeLayout {


    @Bind(R.id.id_author)
    CircleImageView idAuthor;
    @Bind(R.id.id_author_name)
    TextView idAuthorName;
    @Bind(R.id.id_author_summary)
    TextView idAuthorSummary;

    public DetailHeaderSecond(Context context) {
        super(context);

        initView();
        ButterKnife.bind(this);
    }

    private void initView() {

        View.inflate(AppContext.mContext, R.layout.item_share_msg, this);
    }

    public void loadingData(DetailSecondBean bean) {

        CircleImageHelper.requestCircleImg(bean.author_avatar, 80, 80, idAuthor);
        FontsHelper.applyFonts(idAuthorName).setText(bean.author_name);
        FontsHelper.applyFonts(idAuthorSummary).setText(bean.author_summary);
    }
}
