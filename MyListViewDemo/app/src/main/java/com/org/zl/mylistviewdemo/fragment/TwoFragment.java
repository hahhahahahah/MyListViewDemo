package com.org.zl.mylistviewdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.org.zl.mylistviewdemo.R;
import com.org.zl.mylistviewdemo.base.BaseFragment;

/**
 * 作者：朱亮 on 2017/1/24 16:00
 * 邮箱：171422696@qq.com
 *
 * @Description: ${todo}(这里用一句话描述这个方法的作用)
 */

public class TwoFragment extends BaseFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.twofragment, container, false);//关联布局文件
        // 初始化控件
        return rootView;
    }
}
