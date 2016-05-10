package com.ztiany;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.base.utils.view.UnitConverter;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {


    private RecyclerView.Adapter mAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private RecyclerView mRecyclerView;
    private int mOffset = UnitConverter.dpToPx(5);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRecyclerView == null) {
            mRecyclerView = new RecyclerView(getContext());

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
            if (mAdapter != null) {
                mRecyclerView.setAdapter(mAdapter);
            }
        }
        return mRecyclerView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getContext(), 3);
    }


    public void setAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(adapter);
        }
    }


}
