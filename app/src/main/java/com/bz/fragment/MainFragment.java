package com.bz.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.bz.activity.AppContext;
import com.bz.activity.R;
import com.bz.adapter.HomeAdapter;
import com.bz.bean.ArticlesEntity;
import com.bz.event.HomeAddDataEvent;
import com.bz.event.HomeRefreshDataEvent;
import com.bz.model.FontsHelper;
import com.bz.model.HomeHelper;
import com.bz.utils.UrlUtils;
import com.bz.view.AdViewPage;
import com.bz.view.FootView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import butterknife.Bind;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * 主页面
 * Created by Administrator on 2015/11/17.
 */
public class MainFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {


    private static MainFragment mainFragment;
    @Bind(R.id.id_comment_lv)
    ListView idCommentLv;
    @Bind(R.id.id_swipe_rf)
    SwipeRefreshLayout idSwipeRf;
    @Bind(R.id.title_bar_msg)
    TextView titleBarMsg;
    private SlidingMenu menu;

    @Bind(R.id.id_home_menu)
    ImageButton idHomeMenu;


    public static MainFragment getInstance() {

        if (mainFragment == null) {
            mainFragment = new MainFragment();
        }
        return mainFragment;
    }

    @Override
    protected void registerEvent() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    protected void initData() {

        //请求加载数据
        HomeHelper.newInstance().refreshDatas();

    }

    AdViewPage header;
    HomeAdapter adapter;
    FootView footView;

    @Override
    protected void initView() {

        FontsHelper.applyFonts(titleBarMsg).setText(R.string.bar_msg);
        initSlidingMenu();
        idSwipeRf.setColorSchemeResources(R.color.red, R.color.green, R.color.blue);
        idSwipeRf.setOnRefreshListener(this);
        idHomeMenu.setOnClickListener(this);

        header = new AdViewPage(AppContext.mContext);
        adapter = new HomeAdapter();
        footView = new FootView(AppContext.mContext);
        idCommentLv.addHeaderView(header);
        idCommentLv.addFooterView(footView);
        footView.setVisibility(View.GONE);
        idCommentLv.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {

        idCommentLv.setOnScrollListener(this);
        idCommentLv.setOnItemClickListener(this);
    }

    /**
     * 初始化侧滑菜单
     */
    private void initSlidingMenu() {

        menu = new SlidingMenu(AppContext.mContext);
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        menu.setShadowWidthRes(R.dimen.shadow_width);
//        menu.setShadowDrawable(R.drawable.shadow);
        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */
        menu.attachToActivity(getActivity(), SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.drawer_menu);

    }

    @Subscribe
    public void onEventMainThread(HomeRefreshDataEvent refreshDataEvent) {

        if (isRefreshRequest) {
            idSwipeRf.setRefreshing(false);
            isRefreshRequest = false;

        }
        header.loadingData(refreshDataEvent.getTopStoriesEntitys());
        adapter.setList(refreshDataEvent.getHomeAddDataBeans());
    }


    @Subscribe
    public void onEventAddDatas(HomeAddDataEvent event) {

        footView.setVisibility(View.GONE);
        //上拉加载
        adapter.addList(event.getHomeAddDataBean());

    }

    @Override
    public void onResume() {
        super.onResume();
        if (header != null) {
            header.getConvenientBanner().startTurning(5000);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (header != null) {
            header.getConvenientBanner().stopTurning();
        }
    }

    @Override
    protected void unRegisterEvent() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {

        if (menu.isMenuShowing()) {
            menu.toggle();
        } else {
            menu.showMenu();
        }
    }

    boolean isRefreshRequest = false;

    @Override
    public void onRefresh() {

        isRefreshRequest = true;
        //请求加载数据
        HomeHelper.newInstance().refreshDatas();
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        //判断是否滑动停止
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            //判断是否滑动到底部
            if (idCommentLv.getLastVisiblePosition() == idCommentLv.getCount() - 1) {
                footView.setVisibility(View.VISIBLE);
                HomeHelper.newInstance().addDatas(UrlUtils.beforeDate(AppContext.getNewDate()));
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ArticlesEntity item = (ArticlesEntity) adapter.getItem(position - 1);
        Bundle bundle = new Bundle();
        bundle.putString("url", item.getUrl());
        bundle.putString("id", item.getId());
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(bundle);
        EventBus.getDefault().post(fragment);
    }
}
