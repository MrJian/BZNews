package com.bz.event;

import com.bz.bean.DetailSecondBean;

/**
 * Created by Administrator on 2015/11/19.
 */
public class DetailLoadingSecondEvent {

    public DetailSecondBean bean;

    public DetailLoadingSecondEvent(DetailSecondBean bean) {
        this.bean = bean;
    }
}
