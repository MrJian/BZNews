package com.bz.base;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bz.activity.AppContext;
import com.bz.utils.L;

import java.lang.reflect.Field;


/**
 * Created by Administrator on 2015/10/31.
 */
public abstract class MultiplyListAdapter<T> extends BaseAdapter implements MultiplyListResource<T> {
    protected MultiplyListResource<T> mListResource;

    public MultiplyListAdapter() {
        super();
        mListResource = this;
    }


    @Override
    public int getItemViewType(int position) {
        T obj = (mListResource.getData() != null) ? mListResource.getData().get(position) : null;
        if (obj != null) {
            Class c = obj.getClass();
            try {
//                Field field = c.getDeclaredField("type");
                Field field = c.getField("type");
                field.setAccessible(true);
                L.i("type_obj=", field.getInt(obj) + "");
                return field.getInt(obj);
               /* Object type = field.get(obj);
                if (type instanceof String) {
                    return Integer.parseInt((String) type);
                }
                return (Integer) type;*/
            } catch (Exception e) {
                L.i("[MultiplyListAdapter::getItemViewType]  obj =  " + obj);
                e.printStackTrace();
            }
        }

        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return (mListResource.getLayoutIds() != null) ? mListResource.getLayoutIds().length : 1;
    }

    @Override
    public int getCount() {
//        L.i("[MultiplyListAdapter::getCount]");
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
            int type = getItemViewType(position);
            L.d("type=", type + "");
            convertView = LayoutInflater.from(AppContext.mContext).inflate(mListResource.getLayoutIds()[type], parent, false);
        }
        handleItem(convertView, (T) getItem(position));
        return convertView;
    }

    /**
     * 一个传了用来操作的子类显示详细的函数。 使用 {@link MultiplyListAdapter#FindFromConvertView(View, int)}
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

        if (holder.get(viewId) != null) {
            view = holder.get(viewId);
        } else {
            view = convertView.findViewById(viewId);
            holder.put(viewId, view);
        }
        return view;
    }

}
