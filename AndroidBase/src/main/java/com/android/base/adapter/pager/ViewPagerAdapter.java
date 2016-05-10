package com.android.base.adapter.pager;

import android.view.View;
import android.view.ViewGroup;

import com.android.base.R;
import com.android.base.adapter.pager.recycler.RecyclingPagerAdapter;
import com.android.base.utils.object.Checker;

import java.util.List;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-05-06 0:07      <br/>
 * Description：
 */
public abstract class ViewPagerAdapter<T> extends RecyclingPagerAdapter {

    public List<T> mData;

    public ViewPagerAdapter(List<T> data) {
        mData = data;
    }


    public void setData(List<T> data) {
        mData = data;
    }

    @Override
    @SuppressWarnings("unchecked")
    public View getView(int position, View convertView, ViewGroup container) {
        ViewHolder<T> viewHolder;
        if (convertView == null) {
            viewHolder = createViewHolder(container);
        } else {
            viewHolder = (ViewHolder) convertView.getTag(R.id.item_view_id);
        }
        T item = getItem(position);
        viewHolder.bindData(item);
        return viewHolder.itemView;
    }

    protected abstract ViewHolder<T> createViewHolder(ViewGroup container);



    @Override
    public int getCount() {
        return Checker.isEmpty(mData) ? 0 : mData.size();
    }


    protected T getItem(int position) {
        if (position < 0 || position >= mData.size()) {
            return null;
        }
        return mData.get(position);
    }


    public static abstract class ViewHolder<T> {
        public View itemView;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            itemView.setTag(R.id.item_view_id, this);
        }

        public abstract void bindData(T t);
    }

}
