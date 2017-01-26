package com.org.zl.mylistviewdemo.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * @product: QCY_Sport
 * @Description: get方式获取(用一句话描述该文件做什么)
 * @author: Aaron(971503725@qq.com)
 * Date: 2016-06-08
 * Time: 16:56
 * @company:蓝米科技 version        V1.0
 */
public abstract class GetCallBack implements Callback {
    JSONObject resultJson;
    private Context mContext=null;
    //返回正确的结果
    public abstract   void onSuccessState(JSONObject result);
    //请求的code不是0_返回错误文本
    public abstract   void  OnErrorFailure(String text);
    /**
     * 构造
     */
    public GetCallBack(Context context){
        mContext=context;
    }

    /**
     * 构造
     * 可以传入上下文和dialog的文本
     * 加载此构造就自动显示dialog和toast
     */
    public GetCallBack(Context context, String text){
        mContext=context;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        mHandler.sendEmptyMessage(2);
    }

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    onSuccessState(resultJson);
                    break;
                case 2:
                    OnErrorFailure("网络错误");
                    break;

                case 3:
                    OnErrorFailure(resultJson.getString("msg"));
                    break;

            }

        }
    };

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        try {
            resultJson = JSONObject.parseObject(response.body().string());
            if (resultJson != null) {
                int code = resultJson.getIntValue("ret");
                if (code == 0) {
                    mHandler.sendEmptyMessage(1);
                } else {
                    mHandler.sendEmptyMessage(3);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}