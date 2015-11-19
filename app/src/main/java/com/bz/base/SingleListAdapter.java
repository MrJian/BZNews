package com.bz.base;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bz.activity.AppContext;


/**
 * Created by Administrator on 2015/10/31.
 */
public abstract class SingleListAdapter<T> extends BaseAdapter implements SingleListResource<T> {
    private SingleListResource<T> mListResource;

    public SingleListAdapter() {
        super();
        mListResource = this;
    }

    @Override
    public int getCount() {
        return (mListResource.getData() != null) ? mListResource.getData().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return (mListResource.getData() != null && mListResource.getData().size() > position && position >= 0) ? mListResource.getData().get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(AppContext.mContext).inflate(mListResource.getLayoutId(), parent, false);
        }
        handleItem(convertView, (T) getItem(position));
        return convertView;
    }

    /**
     * 一个传了用来操作的子类显示详细的函数。 使用 {@link SingleListAdapter#FindFromConvertView(View, int)}
     * 方法可以获得每个子类的对象
     *
     * @param convertView 要操作的listItem
     * @param data        数据源
     */
    public abstract void handleItem(View convertView, T data);

    protected static <T> View FindFromConvertView(View convertView, int viewId) {
        SparseArray<View> holder = (SparseArray<View>) convertView.getTag();

        View view = null;
        if (holder == null) {
            holder = new SparseArray<View>();
            convertView.setTag(holder);
        }

        if (holder.get(viewId)!=null) {
            view = holder.get(viewId);
        } else {
            view = convertView.findViewById(viewId);
            holder.put(viewId, view);
        }
        return view;
    }

}
