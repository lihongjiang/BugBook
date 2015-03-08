package com.bslee.logtoolapk.holder;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class BaseViewHolder {

	public WeakReference<Context> mContext;
	public View mWholeView;

	public BaseViewHolder(WeakReference<Context> context) {
		this.mContext = context;
		this.mWholeView = LayoutInflater.from(mContext.get()).inflate(
				getViewLayoutId(), null);
		ViewInit(mWholeView);
	}

	public int getViewLayoutId() {
		return 0;
	}

	public View getWholeView() {
		return mWholeView;
	}

	public void ViewInit(View mWholeView) {
	}

	public void process(Object mObject) {
	}

}
