package com.org.zl.mylistviewdemo.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * @auto yangtao
 * @time 2015/12/18 9:18
 * @describe  让Toast不重复
 */
public class ToastManager {

    private   static Toast   mtoast=null;




    public   static   void   show(Context  context,String text){

        if(context==null)
            return;

        if(TextUtils.isEmpty(text)) {
            text = "";
        }


        if (mtoast != null) {//结束上个toast
            mtoast.cancel();
        }

        mtoast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        mtoast.show();

    }
}
