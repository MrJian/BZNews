package com.bz.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.bz.activity.R;
import com.bz.adapter.DetailAdapter;
import com.bz.bean.DetailBean;
import com.bz.event.DetailCommentEvent;
import com.bz.event.DetailLoadingEvent;
import com.bz.event.DetailLoadingSecondEvent;
import com.bz.event.ExitEvent;
import com.bz.model.DetailHelper;
import com.bz.view.DetailHeader;
import com.bz.view.DetailHeaderSecond;
import com.bz.view.Waitting;

import butterknife.Bind;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by Administrator on 2015/11/19.
 */
public class DetailFragment extends BaseFragment implements View.OnClickListener {


    @Bind(R.id.id_detail_back)
    ImageButton idHomeMenu;
    @Bind(R.id.id_detail_lv)
    ListView idDetailLv;
    @Bind(R.id.id_waiting)
    Waitting idWaiting;

    @Override
    protected void registerEvent() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container) {

        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    protected void initEvent() {
        idHomeMenu.setOnClickListener(this);
    }

    DetailHeader header;
    DetailHeaderSecond second;
    DetailAdapter adapter;

    @Override
    protected void initView() {

        /*header = new DetailHeader(AppContext.mContext);
        second = new DetailHeaderSecond(AppContext.mContext); */
        header = new DetailHeader(getActivity());
        second = new DetailHeaderSecond(getActivity());
        adapter = new DetailAdapter();

        idDetailLv.addHeaderView(header);
        idDetailLv.addHeaderView(second);
        idDetailLv.setAdapter(adapter);
    }

    @Override
    protected void initData() {

        Bundle bundle = getArguments();
        if (bundle != null) {
            String url = bundle.getString("url");
            String id = bundle.getString("id");
            idWaiting.startAnim();
            DetailHelper.newInstance().loading(url);
            DetailHelper.newInstance().loadingSecond(id);
            DetailHelper.newInstance().loadingComment(id);
        }
    }

    /**
     * ListView 第一个Header返回
     */
    @Subscribe
    public void onEventMainThread(DetailLoadingEvent event) {

        idWaiting.stopAnim();
        DetailBean bean = event.bean;
        header.loadingData(bean);

    }

    /**
     * ListView 第二个Header返回
     */
    @Subscribe
    public void onEvent(DetailLoadingSecondEvent event) {
        second.loadingData(event.bean);
    }

    /**
     * 适配器数据
     */
    @Subscribe
    public void onEvent(DetailCommentEvent event) {
        adapter.setList(event.commentBase);
    }

    @Override
    protected void unRegisterEvent() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        EventBus.getDefault().post(new ExitEvent());
    }
}
