package com.bz.model;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.bz.bean.CommentBase;
import com.bz.bean.CommentsBean;
import com.bz.bean.DetailBean;
import com.bz.bean.DetailSecondBean;
import com.bz.bean.HottestEntity;
import com.bz.bean.LatestEntity;
import com.bz.event.DataLoadingFailureEvent;
import com.bz.event.DetailCommentEvent;
import com.bz.event.DetailLoadingEvent;
import com.bz.event.DetailLoadingSecondEvent;
import com.bz.https.Http;
import com.bz.https.HttpFilter;
import com.bz.utils.L;
import com.bz.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/11/19.
 */
public class DetailHelper {

    private DetailHelper() {
    }

    public static DetailHelper helper;

    public static DetailHelper newInstance() {

        if (helper == null) {
            synchronized (DetailHelper.class) {

                if (helper == null) {
                    helper = new DetailHelper();
                }
            }
        }
        return helper;
    }

    /**
     * 加载头部数据
     */
    public void loading(String url) {

        Http.getRequest(url, loadingHeader);

    }

    /**
     * 加载头部数据
     */
    public void loadingSecond(String id) {

        String url = UrlUtils.commentUrlExtra(id);
        Http.getRequest(url, loadingHeaderSend);

    }

    /**
     * 加载评论数据
     */
    public void loadingComment(String id) {

        String url = UrlUtils.commentUrl(id);
        Http.getRequest(url, commentCall);
    }

    private HttpFilter loadingHeader = new HttpFilter() {
        @Override
        public void onHttpResult(String err, String result) {

            if (!TextUtils.isEmpty(err)) {
                L.d("DetailHelper  数据刷新失败");
                EventBus.getDefault().post(new DataLoadingFailureEvent());
            } else {
                DetailBean bean = JSON.parseObject(result, DetailBean.class);
                EventBus.getDefault().post(new DetailLoadingEvent(bean));
            }
        }
    };

    private HttpFilter loadingHeaderSend = new HttpFilter() {
        @Override
        public void onHttpResult(String err, String result) {

            if (!TextUtils.isEmpty(err)) {
                L.d("DetailHelper  数据刷新失败");
                EventBus.getDefault().post(new DataLoadingFailureEvent());
            } else {

                DetailSecondBean bean = JSON.parseObject(result, DetailSecondBean.class);
                EventBus.getDefault().post(new DetailLoadingSecondEvent(bean));
            }
        }
    };

    private HttpFilter commentCall = new HttpFilter() {
        @Override
        public void onHttpResult(String err, String result) {

            if (!TextUtils.isEmpty(err)) {
                L.d("DetailHelper  数据刷新失败");
                EventBus.getDefault().post(new DataLoadingFailureEvent());
            } else {

                CommentsBean bean = JSON.parseObject(result, CommentsBean.class);
                List<CommentBase> list = handlerWith(bean);
                EventBus.getDefault().post(new DetailCommentEvent(list));
            }
        }
    };

    /**
     * 处理数据
     */
    private List<CommentBase> handlerWith(CommentsBean bean) {

        List<CommentBase> list = new ArrayList<>();
        CommentBase base = new CommentBase();
        base.type = 0;
        base.date = "热门评论";
        list.add(base);
        for (HottestEntity entity : bean.hottest) {
            entity.type = 1;
            list.add(entity);
        }
        CommentBase news = new CommentBase();
        news.type = 0;
        news.date = "最新评论";
        list.add(news);
        for (LatestEntity entity : bean.latest) {
            entity.type = 1;
            list.add(entity);
        }

        return list;
    }

}
