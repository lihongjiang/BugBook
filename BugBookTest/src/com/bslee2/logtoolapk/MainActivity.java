package com.bslee2.logtoolapk;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends Activity {
	public View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	public void btn1(View v) {

		Handler handler=new Handler();
		handler.postDelayed(runnable, 1000);
	}

	public void btn2(View v) {

		view.bringToFront();

	}

	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			view.bringToFront();
		}
	};
}
