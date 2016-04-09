package com.ztiany.android.share_element;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.base.ui.BaseFragment;
import com.android.base.utils.android.UnitConverter;
import com.ztiany.android.R;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-09 0:25      <br/>
 * Description：
 */
public class GridLayoutFragment extends BaseFragment {


    private RecyclerView mRecyclerView;
    private int[] mImages = {
            R.drawable.placekitten_1,
            R.drawable.placekitten_2,
            R.drawable.placekitten_3,
            R.drawable.placekitten_4,
            R.drawable.placekitten_5,
            R.drawable.placekitten_6
    };

    static GridLayoutFragment newInstance() {
        return new GridLayoutFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grid_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = findView(R.id.frag_grid_rv);
    }

    private void openDetaiFragment(View view,int position) {
        if (getContext() instanceof GridFragmentCallback) {
            ((GridFragmentCallback) getContext()).onOpenDetailFragment(view,mImages[position]);
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(glm);

        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = UnitConverter.dpToPx(5);
                outRect.left = UnitConverter.dpToPx(5);
                outRect.right = UnitConverter.dpToPx(5);
            }
        });

        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            public View.OnClickListener mOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag();
                    openDetaiFragment(v,position);
                }
            };

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_grid, parent, false);

                return new RecyclerView.ViewHolder(view) {
                };
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ImageView iv = (ImageView) holder.itemView.findViewById(R.id.item_grid_iv);
                iv.setImageResource(mImages[position]);
                iv.setTag(position);
                iv.setOnClickListener(mOnClickListener);
                ViewCompat.setTransitionName(iv, String.valueOf(position) + "_image");
            }

            @Override
            public int getItemCount() {
                return mImages.length;
            }
        });
    }


    protected interface GridFragmentCallback {
        void onOpenDetailFragment(View view,int drawableId);
    }


}
