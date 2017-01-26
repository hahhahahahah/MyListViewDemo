package com.org.zl.mylistviewdemo.base;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


/**
 * @product: QCY_Sport
 * @Description: 这个类是全局单例的，用于数据传递，数据共享 等,数据缓存(用一句话描述该文件做什么)
 * @author: 朱亮(171422696@qq.com)
 * Date: 2016-11-22
 * Time: 13:45
 * @company:蓝米科技 version        V1.0
 */
public class BaseApplication extends Application {
    private static BaseApplication mApplication;
    private static Context _context;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        _context = getApplicationContext();
        initImageLoader(_context);
    }

    public static Context getMyApplicationContext() {
        return _context;
    }
    public synchronized static BaseApplication getInstance() {
        return mApplication;
    }
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(config);
    }
}
