package com.ztiany;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.base.utils.android.UnitConverter;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;
    private int mOffset = UnitConverter.dpToPx(5);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = new RecyclerView(getContext());
        return mRecyclerView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = mOffset;
                outRect.top = mOffset;
                outRect.left = mOffset;
                outRect.right = mOffset;
            }
        });
        mMainAdapter = new MainAdapter(null);
        mRecyclerView.setAdapter(mMainAdapter);
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getContext(), 3);
    }


    public void setData(List<Pair<String, Class<? extends Activity>>> data) {
        mMainAdapter.setData(data);
    }


    /**
     * 适配器
     */
    class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Pair<String, Class<? extends Activity>>> mData;
        private View.OnClickListener mItemOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressWarnings("unchecked")//i know it
                        Pair<String, Class<? extends Activity>> stringClassPair = (Pair<String, Class<? extends Activity>>) v.getTag();
                startActivity(new Intent(getActivity(), stringClassPair.second));
            }
        };

        MainAdapter(List<Pair<String, Class<? extends Activity>>> data) {
            mData = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_main_list, parent, false);

            return new RecyclerView.ViewHolder(view) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            TextView tv = (TextView) holder.itemView.findViewById(R.id.item_main_tv);
            Pair<String, Class<? extends Activity>> stringClassPair = mData.get(position);
            tv.setText(stringClassPair.first);
            holder.itemView.setTag(stringClassPair);
            holder.itemView.setOnClickListener(mItemOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }

        public void setData(List<Pair<String, Class<? extends Activity>>> data) {
            mData = data;
            notifyDataSetChanged();
        }
    }

}
