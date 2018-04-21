package com.example.heli.wechatmoment;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int TAB_WECHAT = 0;
    public static final int TAB_FRIEND = 1;
    public static final int TAB_CONTACTS = 2;
    public static final int TAB_SETTING = 3;

    private List<TabItem> mFragmentList;

    private FragmentTabHost mFragmentTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabItemData();
    }

    public void updateMsgCount(int tabIndex, int msgCount) {
        mFragmentList.get(tabIndex).setNewMsgCount(msgCount);
    }

    private void initTabItemData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new TabItem(
                R.drawable.tab_wechat_normal,
                R.drawable.tab_wechat_selected,
                "微信",
                ContactsFragment.class, R.color.colorTabText
        ));

        mFragmentList.add(new TabItem(
                R.drawable.tab_wechat_normal,
                R.drawable.tab_wechat_selected,
                "朋友",
                MomentFragment.class, R.color.colorTabText
        ));

/*        mFragmentList.add(new TabItem(
                R.drawable.tab_contacts_normal,
                R.drawable.tab_contacts_selected,
                "联系人",
                ContactsFragment.class, R.color.colorTabText
        ));

        mFragmentList.add(new TabItem(
                R.drawable.tab_settings_normal,
                R.drawable.tab_settings_selected, "设置",
                SettingFragment.class, R.color.colorTabText
        ));*/

        mFragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mFragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        for (int i = 0; i < mFragmentList.size(); i++) {
            TabItem tabItem = mFragmentList.get(i);
            TabHost.TabSpec tabSpec = mFragmentTabHost.newTabSpec(
                    tabItem.getTabText()).
                    setIndicator(tabItem.getTabView(MainActivity.this));
            mFragmentTabHost.addTab(tabSpec, tabItem.getFragmentClass(), null);
            mFragmentTabHost.getTabWidget().setBackgroundResource(R.drawable.bottom_bar);
            if (i == 0) {
                tabItem.setChecked(true);
            } else {
                tabItem.setChecked(false);
            }
        }

        mFragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for (int i = 0; i < mFragmentList.size(); i++) {
                    TabItem tabItem = mFragmentList.get(i);
                    if (tabId.equals(tabItem.getTabText())) {
                        tabItem.setChecked(true);
                        mFragmentTabHost.getTabWidget().setVisibility(View.GONE);
                    } else {
                        tabItem.setChecked(false);
                    }
                }
            }
        });
    }
}
