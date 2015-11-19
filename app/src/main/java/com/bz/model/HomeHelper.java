package com.bz.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.bz.activity.AppContext;
import com.bz.bean.ArticlesEntity;
import com.bz.bean.HomeAddDataBean;
import com.bz.bean.HomeLvItem;
import com.bz.event.DataLoadingFailureEvent;
import com.bz.event.HomeAddDataEvent;
import com.bz.event.HomeRefreshDataEvent;
import com.bz.https.Http;
import com.bz.https.HttpFilter;
import com.bz.utils.Constans;
import com.bz.utils.L;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * 首页数据处理类
 * Created by Administrator on 2015/11/17.
 */
public class HomeHelper {


    private static HomeHelper helper;

    private HomeHelper() {
    }

    public static HomeHelper newInstance() {

        if (helper == null) {
            synchronized (HomeHelper.class) {
                if (helper == null) {
                    helper = new HomeHelper();
                }
            }
        }
        return helper;
    }

    /**
     * 刷新数据
     */
    public void refreshDatas() {

        Http.getRequest(Constans.URL.HOME_NEWS, reFreshCallBack);
    }

    /**
     * 添加数据
     */
    public void addDatas(String date) {

        //保存前一天日期到暂存区
        AppContext.putBeforeDate(date);
        String url = Constans.URL.HOME_BEFORE + date;
        Http.getRequest(url, addCallBack);
    }

    private HttpFilter reFreshCallBack = new HttpFilter() {
        @Override
        public void onHttpResult(String err, String result) {

            if (!TextUtils.isEmpty(err)) {
                L.d("HomeHelper  数据刷新失败");
                EventBus.getDefault().post(new DataLoadingFailureEvent());
            } else {

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getBoolean("is_today")) {

                        //TODO 是否把日期存到sharePreference

                        HomeLvItem homeLvItem = JSON.parseObject(result, HomeLvItem.class);
                        //保存最新日期
                        AppContext.putDate(homeLvItem.getDate());
                        EventBus.getDefault().post(new HomeRefreshDataEvent(dealWith(homeLvItem.getArticles()), homeLvItem.getTop_stories()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    };

    /**
     * 添加类型
     */
    private List<ArticlesEntity> dealWith(List<ArticlesEntity> list) {

        for (ArticlesEntity entity : list) {
            entity.setType(0);
        }
        return list;
    }


    private HttpFilter addCallBack = new HttpFilter() {
        @Override
        public void onHttpResult(String err, String result) {

            if (!TextUtils.isEmpty(err)) {
                L.d("HomeHelper  数据刷新失败");
                EventBus.getDefault().post(new DataLoadingFailureEvent());
            } else {

                if (TextUtils.isEmpty(result)) return;
                //TODO 是否把日期存到sharePreference
                HomeAddDataBean homeAddDataBean = JSON.parseObject(result, HomeAddDataBean.class);
                List<ArticlesEntity> datas = handlerType(homeAddDataBean);
                EventBus.getDefault().post(new HomeAddDataEvent(datas));
            }
        }
    };

    /**
     * 添加Time对象
     * @return
     */
    @NonNull
    private List<ArticlesEntity> handlerType(HomeAddDataBean homeAddDataBean) {
        List<ArticlesEntity> datas = dealWith(homeAddDataBean.getArticles());
        ArticlesEntity entity = new ArticlesEntity();
//        entity.setType("1");
        entity.setType(1);
        entity.setDate(homeAddDataBean.getDisplay_date());
        datas.set(0, entity);
        return datas;
    }
}
