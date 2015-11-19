package com.bz.event;

import com.bz.bean.ArticlesEntity;
import com.bz.bean.HomeLvItem;

import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 */
public class HomeRefreshDataEvent {

    //列表数据
    private List<ArticlesEntity> homeAddDataBeans;

    //ViewPager数据
    private List<HomeLvItem.Top_storiesEntity> topStoriesEntitys;

    public HomeRefreshDataEvent(List<ArticlesEntity> homeAddDataBeans, List<HomeLvItem.Top_storiesEntity> topStoriesEntitys) {
        this.homeAddDataBeans = homeAddDataBeans;
        this.topStoriesEntitys = topStoriesEntitys;
    }

    public List<ArticlesEntity> getHomeAddDataBeans() {
        return homeAddDataBeans;
    }

    public List<HomeLvItem.Top_storiesEntity> getTopStoriesEntitys() {
        return topStoriesEntitys;
    }
}
