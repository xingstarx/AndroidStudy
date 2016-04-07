package com.ztiany.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.base.utils.android.ResourceUtil;
import com.ztiany.android.bean.ListItemInfo;
import com.ztiany.android.drawer.DrawerActivity;
import com.ztiany.android.tabhost.FragmentTabHostActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.frag_main_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new MainListAdapter(getContext(), initListInfo()));
    }

    private List<ListItemInfo> initListInfo() {
        List<ListItemInfo> infoList = new ArrayList<>();
        infoList.add(new ListItemInfo(FragmentTabHostActivity.class, ResourceUtil.getString(R.string.tab_host)));
        infoList.add(new ListItemInfo(DrawerActivity.class, ResourceUtil.getString(R.string.drawer_layout)));
        return infoList;
    }


}
