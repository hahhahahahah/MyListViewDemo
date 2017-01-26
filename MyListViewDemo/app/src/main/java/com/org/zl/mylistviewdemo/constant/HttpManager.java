package com.org.zl.mylistviewdemo.constant;

import com.org.zl.mylistviewdemo.utils.GetCallBack;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 作者：朱亮 on 2017/1/25 15:39
 * 邮箱：171422696@qq.com
 *
 * @Description: ${网络连接}(这里用一句话描述这个方法的作用)
 */

public class HttpManager {
    /**
     * 获取新闻信息
     */
    public static  void getNews(GetCallBack getCallBack){
        String url= MyConstant.newsUrl;
        Request request = new Request.Builder().url(url).get().build();
        new OkHttpClient().newCall(request).enqueue(getCallBack);
    }
}
