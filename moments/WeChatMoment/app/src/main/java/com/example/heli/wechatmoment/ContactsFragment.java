package com.example.heli.wechatmoment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.heli.wechatmoment.network.NetRequestHelper;
import com.example.heli.wechatmoment.utils.Constants;

public class ContactsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        testRequest();
    }

    private void testRequest(){
        NetRequestHelper.getInstance().request(Constants.REQUEST_USER);
//        NetRequestHelper.getInstance().request(Constants.REQUEST_TWEETS);
    }
}
