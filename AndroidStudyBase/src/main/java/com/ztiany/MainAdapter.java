package com.ztiany;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * 适配器
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private List<String> mData;
    private OnItemClickListener mOnItemClickListener;


    private View.OnClickListener mItemOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Object tag = v.getTag();
            Integer position = (Integer) v.getTag();
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClick(position);

            }
        }
    };


    public MainAdapter(Context context, List<String> data) {
        mData = data;
        mContext = context;
    }


    public void setItemOnClickListener(OnItemClickListener itemOnClickListener) {
        mOnItemClickListener = itemOnClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_list, parent, false);

        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView tv = (TextView) holder.itemView.findViewById(R.id.item_main_tv);
        String text = mData.get(position);
        tv.setText(text);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(mItemOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    public interface OnItemClickListener {
        void onClick(int position);
    }
}
