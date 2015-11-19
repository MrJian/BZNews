package com.bz.event;

import com.bz.bean.ArticlesEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 */
public class HomeAddDataEvent {

    private List<ArticlesEntity> articlesEntitys;

    public HomeAddDataEvent(List<ArticlesEntity> articlesEntity) {
        this.articlesEntitys = articlesEntity;
    }

    public List<ArticlesEntity> getHomeAddDataBean() {
        return articlesEntitys;
    }
}
