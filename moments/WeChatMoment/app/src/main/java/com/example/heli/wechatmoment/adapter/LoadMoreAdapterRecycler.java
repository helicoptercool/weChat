package com.example.heli.wechatmoment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.heli.wechatmoment.R;

public class LoadMoreAdapterRecycler extends RecyclerBaseAdapter<String> {
    private RecyclerBaseAdapter mAdapter;
    private static final int mPageSize = 5;
    private int mPagePosition = 0;
    private boolean hasMoreData = true;
    private OnLoad mOnLoad;

    public LoadMoreAdapterRecycler(RecyclerBaseAdapter adapter, OnLoad onLoad) {
        mAdapter = adapter;
        mOnLoad = onLoad;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == R.layout.item_no_more) {
            View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
            return new NoMoreItemVH(view);
        } else if (viewType == R.layout.progress_bottom) {
            View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
            return new LoadingItemVH(view);
        } else {
            return mAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LoadingItemVH) {
            requestData(mPagePosition, mPageSize);
        } else if (holder instanceof NoMoreItemVH) {

        } else {
            mAdapter.onBindViewHolder(holder, position);
        }
    }

    private void requestData(int pagePosition, int pageSize) {
        if (mOnLoad != null) {
            mOnLoad.load(pagePosition, pageSize, new ILoadCallback() {
                @Override
                public void onSuccess() {
                    notifyDataSetChanged();
                    mPagePosition = (mPagePosition + 1) * mPageSize;
                    hasMoreData = true;
                }

                @Override
                public void onFailure() {
                    hasMoreData = false;
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            if (hasMoreData) {
                return R.layout.progress_bottom;
            } else {
                return R.layout.item_no_more;
            }
        } else {
            return mAdapter.getItemViewType(position);
        }
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + 1;
    }

    static class LoadingItemVH extends RecyclerView.ViewHolder {

        public LoadingItemVH(View itemView) {
            super(itemView);
        }

    }

    static class NoMoreItemVH extends RecyclerView.ViewHolder {

        public NoMoreItemVH(View itemView) {
            super(itemView);
        }
    }
    public interface OnLoad {
        void load(int pagePosition, int pageSize, ILoadCallback callback);
    }


    public interface ILoadCallback {
        void onSuccess();

        void onFailure();
    }
}
