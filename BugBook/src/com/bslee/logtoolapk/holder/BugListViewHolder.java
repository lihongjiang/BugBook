package com.bslee.logtoolapk.holder;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bslee.logtoolapk.R;
import com.bslee.logtoolapk.model.ExceptionBug;

public class BugListViewHolder extends BaseViewHolder {
	public TextView mMessage;
	public TextView mClassName;
	public TextView mMethedName;
	public TextView mLineNumber;

	public BugListViewHolder(WeakReference<Context> context) {
		super(context);
	}

	public int getViewLayoutId() {
		return R.layout.listview_buglist;
	}

	public void ViewInit(View mWholeView) {
		mMessage = (TextView) mWholeView.findViewById(R.id.message);
		mClassName = (TextView) mWholeView.findViewById(R.id.className);
		mMethedName = (TextView) mWholeView.findViewById(R.id.methodName);
		mLineNumber = (TextView) mWholeView.findViewById(R.id.lineNumber);
	}

	public void process(Object mObject) {
		ExceptionBug obj = (ExceptionBug) mObject;
		mMessage.setText("应用程序名称：" + obj.getAppName() + "\n  错误类型:"
				+ obj.getMessage());
		mClassName.setText("  类名:" + obj.getClassName());
		mMethedName.setText("  方法:" + obj.getMethedName());
		mLineNumber.setText("  行号:" + obj.getLineNumber());

	}
}
