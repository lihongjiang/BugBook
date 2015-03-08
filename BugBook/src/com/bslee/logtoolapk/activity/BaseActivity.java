package com.bslee.logtoolapk.activity;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.bslee.logtoolapk.R;
import com.bslee.logtoolapk.widget.CommonHeader;

public class BaseActivity extends FragmentActivity {

	// private MYProgressDialog mProgressDialog;
	public CommonHeader mHeader;

	private long mLastActionDownTime;
	private static final Class<?>[] SkipActivity = {};

	@Override
	protected void onResume() {
		try {
			super.onResume();

			if (skipUmengAnalytic()) {
				// MobclickAgent.onResume(this);
			} else {
				// MobclickAgent.onResume(this);
				// MobclickAgent.onPageStart(this.getClass().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void onPause() {
		try {
			super.onPause();

			if (skipUmengAnalytic()) {
				// MobclickAgent.onPause(this);
			} else {
				// MobclickAgent.onPageEnd(this.getClass().toString());
				// MobclickAgent.onPause(this);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private boolean skipUmengAnalytic() {
		// if (this instanceof HomeActivity || this instanceof CartActivity ||
		// this instanceof OrderInfoListActivity
		// || this instanceof NewsListActivity || this instanceof CouponActivity
		// || this instanceof LoginActivity
		// || this instanceof FreeBuyActivity || this instanceof
		// SecondKillActivity) {
		// return true;
		// }
		return false;
	}

	public void showProgressLoading() {
		showProgressLoading(true);
	}

	public void showProgressLoading(int resId) {

		showProgressLoading(getString(resId));
	}

	public void showProgressLoading(String message) {
		showProgressLoading(message, true);
	}

	public void showProgressLoading(final boolean cancelable) {
		showProgressLoading(null, cancelable);
	}

	public void showProgressLoading(String message, boolean cancelable) {
		// if (mProgressDialog != null && mProgressDialog.isShowing()) {
		// mProgressDialog.dismiss();
		// }
		// mProgressDialog = new MYProgressDialog(this);
		// mProgressDialog.setCancelable(cancelable);
		// if (message != null) {
		// mProgressDialog.setMessage(message);
		// }
		// mProgressDialog.show();
	}

	public void dismissProgressLoading() {
		// if (mProgressDialog != null && mProgressDialog.isShowing()) {
		// mProgressDialog.dismiss();
		// }
	}

	public void initTitleBar() {
		mHeader = (CommonHeader) findViewById(R.id.commonHeader);
		mHeader.getLeftButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	protected boolean allowMultiTouch() {
		return false;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if (!allowMultiTouch() && event.getActionIndex() != 0) {
			return true;
		}

		if (MotionEvent.ACTION_DOWN == event.getAction()) {
			long interval = SystemClock.elapsedRealtime() - mLastActionDownTime;
			if (!allowMultiTouch() && interval < 300) {
				return true;
			}
			mLastActionDownTime = SystemClock.elapsedRealtime();
		}

		return super.dispatchTouchEvent(event);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// ShareWeiboApis.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public Resources getResources() { // 禁止APP字体跟随系统字体放大缩小
		Resources res = super.getResources();
		Configuration config = new Configuration();
		config.setToDefaults();
		//config.fontScale=1*2f/res.getDisplayMetrics().density;
		res.updateConfiguration(config, res.getDisplayMetrics());
		return res;
	}

	@Override
	public void finish() {
		Class<?> cls = getClass();
		for (int i = 0; i < SkipActivity.length; i++) {
			if (cls == SkipActivity[i]) {
				super.finish();
				return;
			}
		}
		if (getActivityCount() <= 1) {
			// UiNavigation.startHomeActivity(this);
		}
		super.finish();
	}

	private int getActivityCount() {
		try {
			ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
			List<RunningTaskInfo> tasks = am.getRunningTasks(1);
			if (tasks == null || tasks.isEmpty()) {
				return 0;
			}
			return tasks.get(0).numActivities;
		} catch (Exception e) {
			return Integer.MAX_VALUE;
		}
	}
}
