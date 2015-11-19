package com.bz.base;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2015/11/19.
 */
public abstract class CommentTypeAdapter<T> extends MultiplyListAdapter<T> {

    @Override
    public int getItemViewType(int position) {

        boolean aBoolean = false;
        T obj = (mListResource.getData() != null) ? mListResource.getData().get(position) : null;
        if (obj != null) {
            Class<?> clz = obj.getClass();
            try {
                Field field = clz.getDeclaredField("hottest");
                field.setAccessible(true);
                aBoolean = field.getBoolean(obj);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (aBoolean) {
            return 0;
        }
        return 1;
    }
}
