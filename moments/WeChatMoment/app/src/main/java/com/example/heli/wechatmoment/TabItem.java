package com.example.heli.wechatmoment;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class TabItem
{
    private final int mSelectTextcolor;
    private ImageView mIvTab;

    private int imageNormal;

    private int imageSelected;

    private TextView mTvTab;

    private String tabText;

    private Class<? extends Fragment> fragmentClass;

    private View mTabView;

    private TextView mTvNewMsg;

    public TabItem(int imageNormal, int imageSelected, String text, Class<? extends Fragment> fragmentClass,int selectTextcolor) {
        this.imageNormal = imageNormal;
        this.imageSelected = imageSelected;
        this.tabText = text;
        this.fragmentClass = fragmentClass;
        mSelectTextcolor = selectTextcolor;
    }

    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }

    public String getTabText() {
        return tabText;
    }

    public void setChecked(boolean checked) {
        if (checked) {
            mTvTab.setTextColor(mSelectTextcolor);
            mIvTab.setImageResource(imageSelected);
        } else {
            mTvTab.setTextColor(Color.WHITE);
            mIvTab.setImageResource(imageNormal);
        }
    }

    public View getTabView(Context context) {
        mTabView = View.inflate(context, R.layout.view_tab, null);
        mIvTab = (ImageView) mTabView.findViewById(R.id.iv_tab);
        mTvTab = (TextView) mTabView.findViewById(R.id.tv_tab);
        mTvNewMsg = (TextView) mTabView.findViewById(R.id.tv_new_msg);
        mIvTab.setImageResource(imageNormal);
        mTvTab.setText(tabText);
        return mTabView;
    }

    public void setNewMsgCount(int count) {
        if (count > 0) {
            mTvNewMsg.setText(String.valueOf(count));
        }
//        mTvNewMsg.setBackgroundResource(R.drawable.ic_new_msg);
    }
}
