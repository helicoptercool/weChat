package com.example.heli.wechatmoment.network;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.heli.wechatmoment.utils.Constants;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetRequestHelper {
    private static final String TAG = "NetRequestHelper";
    private static final NetRequestHelper ourInstance = new NetRequestHelper();
    private static NetResponseListener mListener;
    private ResponseHandler mHandler = new ResponseHandler();

    public static NetRequestHelper getInstance() {
        return ourInstance;
    }

    private NetRequestHelper() {
    }

    public void request(int requestMode) {
        requestAsync(requestMode);
    }

    public void setResponseListener(NetResponseListener listener) {
        mListener = listener;
    }

    private void requestAsync(final int requestMode) {
        String url;
        switch (requestMode) {
            case Constants.REQUEST_USER:
                url = Constants.URL_USER;
                break;
            case Constants.REQUEST_TWEETS:
                url = Constants.URL_TWEETS;
                break;
            default:
                return;
        }
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "request fail");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Log.i(TAG, "response success : " + result);
                    if (mListener != null) {
                        sendMsg(requestMode, result);
                    }
                }
            }
        });
    }

    private void sendMsg(int requestMode, String obj) {
        Message msg = new Message();
        msg.arg1 = requestMode;
        msg.obj = obj;
        mHandler.sendMessage(msg);
    }

    private static class ResponseHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int requestMode = msg.arg1;
            String jsonStr = (String) msg.obj;
            switch (requestMode) {
                case Constants.REQUEST_USER:
                    mListener.getUser(jsonStr);
                    break;
                case Constants.REQUEST_TWEETS:
                    mListener.getTweets(jsonStr);
                    break;
                default:
                    break;
            }
        }
    }
}
