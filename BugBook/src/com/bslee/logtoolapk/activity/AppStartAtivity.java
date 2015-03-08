package com.bslee.logtoolapk.activity;

import com.bslee.logtoolapk.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class AppStartAtivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_appstart);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				startActivity(new Intent(AppStartAtivity.this,
						MainActivity.class));
				finish();
			}
		}, 2000);
	}

}
