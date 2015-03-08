package com.bslee.logtoolapk.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.bslee.logtoolapk.holder.BugListViewHolder;
import com.bslee.logtoolapk.model.ExceptionBug;

public class BugListAdapter extends BaseListAdapter<ExceptionBug> {

	public BugListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		BugListViewHolder holder = null;
		if (view == null) {
			holder = new BugListViewHolder(mContext);
			view = holder.getWholeView();
			view.setTag(holder);
		} else {
			holder = (BugListViewHolder) view.getTag();
		}

		holder.process(mDatas.get(arg0));
		return view;
	}

}
