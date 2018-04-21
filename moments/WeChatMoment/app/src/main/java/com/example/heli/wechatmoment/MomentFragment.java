package com.example.heli.wechatmoment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.heli.wechatmoment.adapter.LoadMoreAdapterRecycler;
import com.example.heli.wechatmoment.adapter.NineGridTest2Adapter;
import com.example.heli.wechatmoment.adapter.RecyclerBaseAdapter;
import com.example.heli.wechatmoment.adapter.RecyclerMomentAdapter;
import com.example.heli.wechatmoment.entity.Model;
import com.example.heli.wechatmoment.entity.Moment;
import com.example.heli.wechatmoment.network.NetHelper;
import com.example.heli.wechatmoment.network.NetRequestHelper;
import com.example.heli.wechatmoment.network.NetResponseListener;
import com.example.heli.wechatmoment.utils.Constants;
import com.example.heli.wechatmoment.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class MomentFragment extends Fragment implements NetResponseListener {

    private RecyclerView mRecyclerView;
    private View mView;
    private RecyclerView.LayoutManager mLayoutManager;
    private NineGridTest2Adapter nAdapter;
    private RecyclerBaseAdapter mAdapter;
    private RecyclerMomentAdapter adapter;
    private Moment mMoment;

    private List<Model> mList = new ArrayList<>();
    private String[] mUrls = new String[]{"http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://img3.fengniao.com/forum/attachpics/537/165/21472986.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=ea218b2c5566d01661199928a729d498/a08b87d6277f9e2fd4f215e91830e924b999f308.jpg",
            "http://img4.imgtn.bdimg.com/it/u=3445377427,2645691367&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=2644422079,4250545639&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1444023808,3753293381&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=882039601,2636712663&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=4119861953,350096499&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2437456944,1135705439&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3251359643,4211266111&fm=21&gp=0.jpg",
            "http://img4.duitang.com/uploads/item/201506/11/20150611000809_yFe5Z.jpeg",
            "http://img5.imgtn.bdimg.com/it/u=1717647885,4193212272&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2024625579,507531332&fm=21&gp=0.jpg"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_moments, container, false);
        initView();
        return mView;
    }


    private void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.momentRv);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        if (!NetHelper.isConnectNet(getContext())) {
            loadTestData(getString(R.string.net_error));
        } else {

            adapter = new RecyclerMomentAdapter(getContext());
            mAdapter = new LoadMoreAdapterRecycler(adapter, new LoadMoreAdapterRecycler.OnLoad() {
                @Override
                public void load(int pagePosition, int pageSize, LoadMoreAdapterRecycler.ILoadCallback callback) {
//                NetRequestHelper.getInstance().request(Constants.REQUEST_TWEETS);
                }
            });
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private void initData() {
        NetRequestHelper.getInstance().setResponseListener(this);
        NetRequestHelper.getInstance().request(Constants.REQUEST_TWEETS);
    }

    private void initTestData() {
        Model model1 = new Model();
        model1.urlList.add(mUrls[0]);
        mList.add(model1);

        Model model2 = new Model();
        model2.urlList.add(mUrls[4]);
        mList.add(model2);

        Model model4 = new Model();
        for (int i = 0; i < mUrls.length; i++) {
            model4.urlList.add(mUrls[i]);
        }
        model4.isShowAll = false;
        mList.add(model4);

        Model model5 = new Model();
        for (int i = 0; i < mUrls.length; i++) {
            model5.urlList.add(mUrls[i]);
        }
        model5.isShowAll = true;//显示全部图片
        mList.add(model5);

        Model model6 = new Model();
        for (int i = 0; i < 9; i++) {
            model6.urlList.add(mUrls[i]);
        }
        mList.add(model6);

        Model model7 = new Model();
        for (int i = 3; i < 7; i++) {
            model7.urlList.add(mUrls[i]);
        }
        mList.add(model7);

        Model model8 = new Model();
        for (int i = 3; i < 6; i++) {
            model8.urlList.add(mUrls[i]);
        }
        mList.add(model8);
    }

    @Override
    public void getUser(String userStr) {

    }

    @Override
    public void getTweets(String tweetsStr) {
        String correctStr = tweetsStr.substring(1, tweetsStr.length() - 1).trim();
        try {
            mMoment = JsonUtil.parseJsonWithGson(correctStr, Moment.class);
            adapter.appendData(mMoment.getMoments());
        } catch (Exception e) {
            e.printStackTrace();
            loadTestData(getString(R.string.json_error));
        }
    }

    private void loadTestData(String reason) {
        Toast.makeText(getContext(), reason, Toast.LENGTH_LONG).show();
        initTestData();
        nAdapter = new NineGridTest2Adapter(getContext());
        nAdapter.setList(mList);
        mRecyclerView.setAdapter(nAdapter);
    }

}
