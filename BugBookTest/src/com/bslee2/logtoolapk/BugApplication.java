package com.bslee2.logtoolapk;

import org.litepal.LitePalApplication;

public class BugApplication extends LitePalApplication  {
	@Override
	public void onCreate() {
		super.onCreate();
		//打开日志记录
		com.bslee.logtoolapk.tools.BugHandler.getInstance().init(getApplicationContext());
	}
}
