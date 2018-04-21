package com.example.heli.wechatmoment.network;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetHelper {
    public static boolean isConnectNet(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }
}
