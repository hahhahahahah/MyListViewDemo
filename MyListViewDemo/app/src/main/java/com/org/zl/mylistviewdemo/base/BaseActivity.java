package com.org.zl.mylistviewdemo.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zhy.autolayout.AutoLayoutActivity;

/**
 * @product: QCY_Sport
 * @Description: activtiy基础类(用一句话描述该文件做什么)
 * @author: 朱亮(171422696@qq.com)
 * Date: 2016-11-22
 * Time: 13:43
 * @company:蓝米科技 version        V1.0
 */
public class BaseActivity extends AutoLayoutActivity {
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }
    public Context getContext() {
        return mContext;
    }

    //跳转activity
    public void IntentActivity(Class<?> cls) {
        startActivity(new Intent(getContext(), cls));
    }
}
