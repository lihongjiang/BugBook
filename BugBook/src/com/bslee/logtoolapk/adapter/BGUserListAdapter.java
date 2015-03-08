package com.bslee.logtoolapk.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.bslee.logtoolapk.holder.BGUserListViewHolder;
import com.bslee.logtoolapk.model.BGUser;

public class BGUserListAdapter extends BaseListAdapter<BGUser> {

	public BGUserListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		BGUserListViewHolder holder = null;
		if (view == null) {
			holder = new BGUserListViewHolder(mContext);
			view = holder.getWholeView();
			view.setTag(holder);
		} else {
			holder = (BGUserListViewHolder) view.getTag();
		}

		holder.process(mDatas.get(arg0));
		return view;
	}

	

}
