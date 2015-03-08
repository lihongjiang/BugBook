package com.bslee.logtoolapk.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.bslee.logtoolapk.R;
import com.bslee.logtoolapk.fragment.LogFragment;
import com.bslee.logtoolapk.fragment.TeamFragment;
import com.bslee.logtoolapk.widget.FragmentTabHost;

public class MainActivity extends BaseActivity {

	private FragmentTabHost mTabHost;

	private Class<?> fragmentArray[] = { LogFragment.class, TeamFragment.class};
	private int iconArray[] = { R.drawable.icon, R.drawable.icon};
	private String titleArray[] = { "日志", "团队"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmenttabhost);

		mTabHost = (FragmentTabHost) findViewById(R.id.tabhost);
		setupTabView();
	}

	private void setupTabView() {
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		mTabHost.getTabWidget().setDividerDrawable(null);

		int count = fragmentArray.length;

		for (int i = 0; i < count; i++) {
			TabHost.TabSpec tabSpec = mTabHost.newTabSpec(titleArray[i])
					.setIndicator(getTabItemView(i));
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
			mTabHost.getTabWidget().getChildAt(i)
					.setBackgroundResource(android.R.color.white);
		}

	}

	@SuppressLint("InflateParams")
	private View getTabItemView(int index) {
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View view = layoutInflater.inflate(R.layout.tab_bottom_nav, null);

		ImageView imageView = (ImageView) view.findViewById(R.id.iv_icon);
		imageView.setImageResource(iconArray[index]);

		TextView textView = (TextView) view.findViewById(R.id.tv_icon);
		textView.setText(titleArray[index]);

		return view;
	}
}
