package com.bslee.logtoolapk.holder;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bslee.logtoolapk.R;
import com.bslee.logtoolapk.model.BGUser;

public class BGUserListViewHolder extends BaseViewHolder {
	public TextView mNickName;
	public TextView mEmail;
	public TextView model;

	public BGUserListViewHolder(WeakReference<Context> context) {
		super(context);
	}

	public int getViewLayoutId() {
		return R.layout.listview_userlist;
	}

	public void ViewInit(View mWholeView) {
		mNickName = (TextView) mWholeView.findViewById(R.id.nickname);
		mEmail = (TextView) mWholeView.findViewById(R.id.email);
		model = (TextView) mWholeView.findViewById(R.id.model);
		
	}

	public void process(Object mObject) {
		BGUser obj = (BGUser) mObject;
		mNickName.setText( obj.getNickname());
		mEmail.setText(obj.getEmail());
		model.setText(TextUtils.isEmpty(obj.getModel())?"未知":obj.getModel());
	}
}
