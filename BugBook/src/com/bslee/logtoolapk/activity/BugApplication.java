package com.bslee.logtoolapk.activity;

import org.litepal.LitePalApplication;

import com.bslee.logtoolapk.tools.BugHandler;

public class BugApplication extends LitePalApplication  {
	@Override
	public void onCreate() {
		super.onCreate();
		//打开日志记录
		BugHandler.getInstance().init(getApplicationContext());
	}
}
