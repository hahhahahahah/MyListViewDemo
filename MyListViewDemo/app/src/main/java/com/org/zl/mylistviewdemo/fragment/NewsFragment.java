package com.org.zl.mylistviewdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.org.zl.mylistviewdemo.R;
import com.org.zl.mylistviewdemo.adapter.NewsAdapter;
import com.org.zl.mylistviewdemo.base.BaseFragment;
import com.org.zl.mylistviewdemo.constant.HttpManager;
import com.org.zl.mylistviewdemo.utils.GetCallBack;

import java.util.HashSet;

/**
 * 作者：朱亮 on 2017/1/24 16:00
 * 邮箱：171422696@qq.com
 *
 * @Description: ${新闻界面}(这里用一句话描述这个方法的作用)
 */

public class NewsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout swipeRefreshView;
    private RelativeLayout bd_empty_view;
    private ListView listView;
    private NewsAdapter newsAdapter;
    private JSONArray jsonArray = null;
    private HashSet<String> hashSet ;
    private int startPos;
    private int endPos;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.newsfragment, container, false);//关联布局文件
        swipeRefreshView = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshView);
        //设置刷新时动画的颜色，可以设置4个
        swipeRefreshView.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshView.setOnRefreshListener(this);
        listView = (ListView) rootView.findViewById(R.id.newsListView);
        bd_empty_view = (RelativeLayout) rootView.findViewById(R.id.bd_empty_view);
        initAdapter();//初始化填充
        return rootView;
    }
    //设置Adapter
    private void initAdapter() {
        hashSet = new HashSet<>();
        jsonArray = new JSONArray();
        newsAdapter = new NewsAdapter(getContext());
        listView.setAdapter(newsAdapter);
        listView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(),true,true));
    }

    //下拉刷新时
    @Override
    public void onRefresh() {
        HttpManager.getNews(new GetCallBack(getContext()) {
            @Override
            public void onSuccessState(JSONObject result) {
                swipeRefreshView.setRefreshing(false);

                JSONArray arrays = result.getJSONObject("result").getJSONArray("data");
                for(int x = 0 ; x < arrays.size() ; x++){//去重复数据
                    if(!hashSet.contains(arrays.getJSONObject(x).getString("uniquekey"))){
                        hashSet.add(arrays.getJSONObject(x).getString("uniquekey"));
                        jsonArray.add(arrays.getJSONObject(x));
                    }
                }
                newsAdapter.setData(jsonArray);
            }

            @Override
            public void OnErrorFailure(String text) {
                swipeRefreshView.setRefreshing(false);
                Log.e("LOGS","获取失败  = " + text );
            }
        });
    }

    //当没有数据是，显示没有数据背景
    private void setEmptyBackground(){
        bd_empty_view.setVisibility(View.VISIBLE);
        listView.setEmptyView(bd_empty_view);
    }

}
