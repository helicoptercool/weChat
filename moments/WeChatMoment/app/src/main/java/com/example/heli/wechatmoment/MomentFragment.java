package com.example.heli.wechatmoment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.heli.wechatmoment.adapter.NineGridTest2Adapter;

import java.util.List;

/**
 * Created by heli on 2018/4/19.
 */

public class MomentFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private View mView;
    private RecyclerView.LayoutManager mLayoutManager;
    private NineGridTest2Adapter mAdapter;

    private List<Model> mList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_moments, container, false);
        return mView;
    }

    private void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.momentRv);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new NineGridTest2Adapter(this);
        mAdapter.setList(mList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
