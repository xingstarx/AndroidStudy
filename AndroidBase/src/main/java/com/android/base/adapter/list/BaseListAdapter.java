package com.android.base.adapter.list;

import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T> 数据模型
 * @author Ztiany
 * @description absListView通用的Adapter
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {

    protected List<T> mData;
    protected Context mContext;
    protected LayoutInflater mInflater;

    public BaseListAdapter(Context context) {
        this(context, null);
    }


    public BaseListAdapter(Context context, List<T> data) {
        this.mContext = context;
        this.mData = data;
        mInflater = LayoutInflater.from(context);
    }


    public List<T> getData() {
        return mData;
    }


    /**
     * 设置数据源，不清除已有的数据
     *
     * @param data
     */
    @UiThread
    public void setData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @UiThread
    public void addAll(List<T> data) {
        if (mData != null) {
            mData.addAll(data);
        } else {
            mData = data;
        }
        notifyDataSetChanged();
    }

    @UiThread
    public void clearData() {
        if (mData != null) {
            mData.clear();
            mData = null;
            notifyDataSetChanged();
        }
    }

    @UiThread
    public boolean addItem(T t) {
        if (t == null) {
            return false;
        }
        if (mData != null) {
            return mData.add(t);
        } else {
            mData = new ArrayList<>();
            mData.add(t);
        }
        notifyDataSetChanged();
        return true;
    }


    @Override
    public int getCount() {
        return getDataSize();
    }

    public int getDataSize() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    @CheckResult
    public T getItem(int position) {
        if (getCount() > position) {
            return mData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

    @Override
    public boolean isEmpty() {
        return getDataSize() == 0;
    }

}
