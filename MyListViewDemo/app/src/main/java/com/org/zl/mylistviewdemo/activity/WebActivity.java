package com.org.zl.mylistviewdemo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.org.zl.mylistviewdemo.R;
import com.org.zl.mylistviewdemo.base.BaseActivity;
import com.org.zl.mylistviewdemo.base.BaseWebView;

/**
 * 作者：朱亮 on 2017/1/26 16:31
 * 邮箱：171422696@qq.com
 *
 * @Description: ${todo}(这里用一句话描述这个方法的作用)
 */

public class WebActivity extends BaseActivity {
    private BaseWebView webView;
    private Button goToMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dh_activity);

        webView = (BaseWebView) findViewById(R.id.webView);
        goToMain = (Button) findViewById(R.id.goToMain);
        goToMain.setVisibility(View.GONE);


        String url =   getIntent().getStringExtra("url");
        setImg(url);
    }
    private void setImg(String url) {
        if(TextUtils.isEmpty(url)){
            return;
        }
        webView.getWebView().loadUrl(url);
    }
}
