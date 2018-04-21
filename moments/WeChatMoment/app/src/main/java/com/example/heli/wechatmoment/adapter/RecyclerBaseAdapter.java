package com.example.heli.wechatmoment.adapter;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecyclerBaseAdapter<T> extends Adapter{

    protected List<T> dataSet = new ArrayList<>();

    public void updateData(List dataSet) {
        this.dataSet.clear();
        appendData(dataSet);
    }

    public void appendData(List dataSet) {
        if (dataSet != null && !dataSet.isEmpty()) {
            this.dataSet.addAll(dataSet);
            notifyDataSetChanged();
        }
    }

    public List<T> getDataSet() {
        return dataSet;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
