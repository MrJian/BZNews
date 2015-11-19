package com.bz.base;

import java.util.List;

/**
 * Created by Administrator on 2015/10/31.
 */
public interface MultiplyListResource<T> {

    public abstract List<T> getData();
    public abstract int[] getLayoutIds();

}
