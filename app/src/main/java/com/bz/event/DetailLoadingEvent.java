package com.bz.event;

import com.bz.bean.DetailBean;

/**
 * Created by Administrator on 2015/11/19.
 */
public class DetailLoadingEvent {

    public DetailBean bean;

    public DetailLoadingEvent(DetailBean bean) {
        this.bean = bean;
    }
}
