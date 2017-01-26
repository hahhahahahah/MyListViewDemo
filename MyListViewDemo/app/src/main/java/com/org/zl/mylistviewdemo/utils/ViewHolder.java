package com.org.zl.mylistviewdemo.utils;

import android.util.SparseArray;
import android.view.View;

public class ViewHolder {
	@SuppressWarnings("unchecked")
	public static <T extends View> T get(View view, int id) {
		SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
		if (viewHolder == null) {
			viewHolder = new SparseArray<View>();
			view.setTag(viewHolder);
		}
		View childView = viewHolder.get(id);
		if (childView == null) {
			childView = view.findViewById(id);
			viewHolder.put(id, childView);
		}
		return (T) childView;
	}
}

/*
 * 使用方法： TextView newsTitle = ViewHolder.get(convertView,R.id.newsTitle);
 * 				newsTitle.settext("");
 */
