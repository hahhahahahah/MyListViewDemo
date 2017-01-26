package com.org.zl.mylistviewdemo.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.org.zl.mylistviewdemo.R;
import com.org.zl.mylistviewdemo.base.BaseActivity;
import com.org.zl.mylistviewdemo.base.BaseWebView;
import com.org.zl.mylistviewdemo.utils.NetUtil;
import com.org.zl.mylistviewdemo.utils.ScreenUtils;


/**
 * 作者：朱亮 on 2017/1/20 19:31
 * 邮箱：171422696@qq.com
 * ${导航界面}(这里用一句话描述这个方法的作用)
 */

public class DaoHangActivity extends BaseActivity implements View.OnClickListener{
    private String url = "";
    private BaseWebView webView;
    private Button goToMain;
    private boolean falg = false;
    private CountDownTimer timer;
    private int time = 4;//设置倒计时时间
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dh_activity);
        webView = (BaseWebView) findViewById(R.id.webView);
        goToMain = (Button) findViewById(R.id.goToMain);goToMain.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goToMain://跳转到主界面
                IntentActivity(MainActivity.class);
                onDestroy();
                break;
        }
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    goToMain.setText(time+" | 跳过");
                    break;
                case 1:
                    IntentActivity(MainActivity.class);
                    onDestroy();
                    break;
            }
        }
    };
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(!hasFocus){//如果界面还灭加载完毕
            return;
        }
        if(falg){//如果加载完毕，就不再请求
            return;
        }
        //获取网络是否连接
        if(NetUtil.CheckNetworkState(getContext())){//如果有网络
            java.util.Random random=new java.util.Random();// 定义随机类
            int result=random.nextInt(3);// 返回[0,3)集合中的整数，注意不包括3
            switch (result){
                case 0:
                    url = "http://pic1.win4000.com/pic/e/08/f8b91258152.jpg";
                    break;
                case 1:
                    url = "http://pic1.win4000.com/pic/f/f1/3ca81257793.jpg";
                    break;
                case 2:
                    url = "http://pic1.win4000.com/pic/d/9c/daae1254766.jpg";
                    break;
            }
            setImg(url);//设置图片方法
        }else{//加载本地LOGO图片
            url = "file:///android_asset/bg_guide.png";
            setImg(url);//设置图片方法
        }

        timer =  new CountDownTimer(time * 1000, 1000) {//开始倒计时
            public void onTick(long millisUntilFinished) {
                handler.sendEmptyMessage(0);
                time -- ;
            }
            public void onFinish() {
                handler.sendEmptyMessage(1);
            }
        }.start();
    }

    private void setImg(String url) {
        falg = true;//是不是加载完毕
        Log.e("LOGSe","加载的地址是 :"+url);
        int num = ScreenUtils.getScreenWidth(getContext());//获取屏幕宽度
        Log.e("---------------------","  num = "+num);
        String body="<img  src=\"" + url + "\" width = "+num+"/>";//根据屏幕宽度动态设置图片大小自适应
        String html="<html><body>"+body+"</html></body>";
        webView.getWebView()
                .loadDataWithBaseURL(null, html, "text/html","UTF-8", null);
        webView.setZoom(false);//设置web不支持缩放(自适应)
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        DaoHangActivity.this.finish();
        timer.cancel();
        try {
            webView.webDestory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
