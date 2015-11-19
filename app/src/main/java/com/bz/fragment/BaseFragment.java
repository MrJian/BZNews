package com.bz.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Fragment 基类
 * Created by Administrator on 2015/11/17.
 */
public abstract class BaseFragment extends Fragment {

    private String mTag;

    public BaseFragment() {
        this.mTag = getClass().getSimpleName();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerEvent();
    }

    /**
     * 注册广播
     */
    protected abstract void registerEvent();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflaterView(inflater, container);
        ButterKnife.bind(this, view);
        return view;
    }

    protected abstract View inflaterView(LayoutInflater inflater, ViewGroup container);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
        initEvent();
    }

    protected void initEvent() {

    }


    /**
     * 初始化View
     */
    protected void initView() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 加载数据
     */
    protected abstract void initData();


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

    }

    public String getmTag() {
        return mTag;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unRegisterEvent();
    }

    /**
     * 注销广播
     */
    protected abstract void unRegisterEvent();


    /**
     * 是否需要pass
     */
    public boolean finish() {
        return false;
    }

    /**
     * 把返回键交给Fragment
     */
    public boolean onBack() {
        return false;
    }
}
