package com.ztiany.android;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.base.adapter.recycler.RecyclerAdapter;
import com.android.base.adapter.recycler.ViewHolder;
import com.ztiany.android.bean.ListItemInfo;

import java.util.List;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-07 15:46                                                       <br/>
 * description                                                                             <br/>
 * vsersion
 */
public class MainListAdapter extends RecyclerAdapter<ListItemInfo, MainViewHolder> {

    private View.OnClickListener mOnItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ListItemInfo info = (ListItemInfo) v.getTag();
            mContext.startActivity(new Intent(mContext,info.getClazz()));
        }
    };

    public MainListAdapter(Context context, List<ListItemInfo> data) {
        super(context, data);
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_main_list, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder viewHolder, int position) {

        ListItemInfo item = getItem(position);
        viewHolder.mTextView.setText(item.getInfoName());
        viewHolder.itemView.setTag(item);
        viewHolder.itemView.setOnClickListener(mOnItemClickListener);
    }
}


class MainViewHolder extends ViewHolder<ListItemInfo> {

    TextView mTextView;

    public MainViewHolder(View itemView) {
        super(itemView);
    }


    @Override
    protected void findViews() {
        mTextView = findView(R.id.item_main_tv);
    }

    @Override
    public void bindData(ListItemInfo data) {

    }


}