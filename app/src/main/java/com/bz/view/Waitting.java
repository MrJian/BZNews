package com.bz.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bz.activity.R;


/**
 * 网络加载等待页面
 * Created by Administrator on 2015/10/22.
 */
public class Waitting extends RelativeLayout {

    private ImageView imageView;

    private AnimationDrawable mAnimationDrawable;

    public Waitting(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    /**
     * 初始化
     */
    private void initView() {

        View view  = LayoutInflater.from(getContext()).inflate(R.layout.waitting_view,this,true);

        imageView = (ImageView) view.findViewById(R.id.iv_waitting);

        mAnimationDrawable = (AnimationDrawable) imageView.getBackground();

    }

    /**
     * 开启动画
     */
    public void startAnim(){

        this.setVisibility(View.VISIBLE);
        mAnimationDrawable.start();
    }

    /**
     * 结束动画
     */
    public void stopAnim(){
        this.setVisibility(View.GONE);
        mAnimationDrawable.stop();

    }
}
