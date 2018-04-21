package com.example.heli.wechatmoment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heli.wechatmoment.App;
import com.example.heli.wechatmoment.R;
import com.example.heli.wechatmoment.entity.Tweets;
import com.example.heli.wechatmoment.views.NineGridTestLayout;

public class RecyclerMomentAdapter extends RecyclerBaseAdapter<Tweets> {
    private Context mContext;

    public RecyclerMomentAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_nine_grid, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).bind(getDataSet().get(position));
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mPortraitIv;
        TextView mUsernameTv;
        TextView mContentTv;
        NineGridTestLayout mLayout;
        TextView mTimeTv;
        TextView mReplyTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            mPortraitIv = (ImageView) itemView.findViewById(R.id.portrait);
            mUsernameTv = (TextView) itemView.findViewById(R.id.username);
            mContentTv = (TextView) itemView.findViewById(R.id.content);
            mLayout = (NineGridTestLayout) itemView.findViewById(R.id.layout_nine_grid);
            mTimeTv = (TextView) itemView.findViewById(R.id.time);
            mReplyTv = (TextView) itemView.findViewById(R.id.replay);
        }

        public void bind(Tweets tweets) {
            mPortraitIv.setImageDrawable(App.getInstance().getDrawable(R.drawable.tab_wechat_selected));
            mUsernameTv.setText(tweets.getSender().getUsername());
            mContentTv.setText(tweets.getContent());
            mLayout.setUrlList(tweets.getImages());
        }
    }
}
