package com.ztiany.web;

import android.app.Activity;
import android.content.Intent;
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

import com.android.base.utils.android.ResourceUtil;
import com.ztiany.web.filechooser.OpenFileChooserActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }



    private RecyclerView mRecyclerView;
    private List<Pair<String, Class<? extends Activity>>> mData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.frag_main_list_rv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(new MainAdapter(mData));
    }

    private void initData() {
        mData = new ArrayList<>();
        add(R.string.file_chooser, OpenFileChooserActivity.class);
    }

    private void add(int overlay, Class<? extends Activity> overlayActivityClass) {
        mData.add(new Pair<String, Class<? extends Activity>>(ResourceUtil.getString(overlay), overlayActivityClass));
    }

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
    }

}
