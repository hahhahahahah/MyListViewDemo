package com.org.zl.mylistviewdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.org.zl.mylistviewdemo.R;
import com.org.zl.mylistviewdemo.activity.WebActivity;
import com.org.zl.mylistviewdemo.myView.MultiImageView;
import com.org.zl.mylistviewdemo.utils.ViewHolder;

import java.util.ArrayList;


/**
 * 作者：朱亮 on 2017/1/24 20:16
 * 邮箱：171422696@qq.com
 *
 * @Description: ${新闻填充界面}(这里用一句话描述这个方法的作用)
 */

public class NewsAdapter extends BaseAdapter{
    private JSONArray jsonArray = null;
    private Context mContext;
    private String[] str ;
    public NewsAdapter(Context mContext) {
        this.mContext = mContext;
        jsonArray = new JSONArray();
        str = new String[3];//这里设置后台图片的数量
    }
    public JSONArray getData() {
        return jsonArray;
    }

    public void setData(JSONArray mdata) {
        this.jsonArray = mdata;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return jsonArray.size();
    }

    @Override
    public Object getItem(int position) {
        return jsonArray.getJSONObject(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.news_item,null);
        }
        RelativeLayout rela =  ViewHolder.get(convertView,R.id.rela);
        TextView newsTitle = ViewHolder.get(convertView,R.id.newsTitle);
        MultiImageView multiImageView = ViewHolder.get(convertView,R.id.multiImageView);
        TextView text_time = ViewHolder.get(convertView,R.id.text_time);
        TextView text_From = ViewHolder.get(convertView,R.id.text_From);


        newsTitle.setText(jsonArray.getJSONObject(position).getString("title"));
        text_time.setText(jsonArray.getJSONObject(position).getString("date"));
        text_From.setText(jsonArray.getJSONObject(position).getString("author_name"));

        ArrayList<String> list = new ArrayList<>();

        str[0] = jsonArray.getJSONObject(position).getString("thumbnail_pic_s");
        str[1] = jsonArray.getJSONObject(position).getString("thumbnail_pic_s02");
        str[2]  = jsonArray.getJSONObject(position).getString("thumbnail_pic_s03");
        for(int x = 0 ; x < str.length ; x ++){//将后台给的图片全部做成集合形式
            if(str[x] != null){
                list.add(str[x]);
            }
        }
        multiImageView.setList(list);//传递给自定义图片对象
        rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext ,WebActivity.class);
                intent.putExtra("url",jsonArray.getJSONObject(position).getString("url"));
                mContext.startActivity(intent);
            }
        });
        /**
         * 关注的状态
         */
        return convertView;
    }
}
