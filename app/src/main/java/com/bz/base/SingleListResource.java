package com.bz.base;

import java.util.List;

/**
 * Created by Administrator on 2015/10/31.
 */
public interface SingleListResource<T> {

    public abstract List<T> getData();
    public abstract int getLayoutId();

}
