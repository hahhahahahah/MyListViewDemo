package com.org.zl.mylistviewdemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.org.zl.mylistviewdemo.R;
import com.org.zl.mylistviewdemo.adapter.MyFragmentPagerAdapter;
import com.org.zl.mylistviewdemo.base.BaseActivity;
import com.org.zl.mylistviewdemo.fragment.NewsFragment;
import com.org.zl.mylistviewdemo.fragment.OneFragment;
import com.org.zl.mylistviewdemo.fragment.TwoFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private long exitTime = 0;
    private NewsFragment newsFragment;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();//初始化fragment
    }
    private void initFragment() {
        fragmentList = new ArrayList<>();
        newsFragment = new NewsFragment();
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        fragmentList.add(newsFragment);
        fragmentList.add(oneFragment);
        fragmentList.add(twoFragment);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        //给ViewPager设置适配器
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());//页面变化时的监听器
        viewPager.setCurrentItem(0);//设置为第一个界面
    }


    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {        }
        @Override
        public void onPageScrollStateChanged(int arg0) {        }
        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:

                    break;
                case 1:

                    break;
                case 2:

                    break;
            }
        }
    }












    //这里已经定型，不要改动
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ExitApp();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    //退出APP。这里已经定型，不要改动
    public void ExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            MainActivity.this.finish();
        }
    }
}
