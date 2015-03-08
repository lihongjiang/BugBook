package com.bslee.logtoolapk.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

	private View mContentView;

	@Override
	public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (mContentView == null) {
			mContentView = inflater.inflate(getContentLayout(), container, false);
			findViews(mContentView);
			setListeners();
			process();
		}
		if (mContentView.getParent() != null) {
			((ViewGroup) mContentView.getParent()).removeView(mContentView);
		}
		return mContentView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		try {
			if( skipUmengAnalytic() ) {
				return ;
			}
			
			//MobclickAgent.onPageStart(this.getClass().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		try {
			if( skipUmengAnalytic() ) {
				return ;
			}
			
			//MobclickAgent.onPageEnd(this.getClass().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean skipUmengAnalytic() {
		//if( this instanceof LoginUserCenterFragment ||
		//		this instanceof NoLoginUserCenterFragment ) {
		//	return true;
		//}
		return false;
	}

	protected String getStringArgument(String key) {
		Bundle arguments = getArguments();
		return arguments != null ? arguments.getString(key) : null;
	}

	public abstract int getContentLayout();

	public abstract void findViews(View view);

	public abstract void setListeners();

	public abstract void process();
	
	public void scrollToTop(){};

	public void viewPagerScrollStop(){};
	
	public void viewPagerScrollStart() {};
}
