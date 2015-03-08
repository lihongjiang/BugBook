package com.bslee.logtoolapk.adapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.BaseAdapter;

public abstract class BaseListAdapter<T> extends BaseAdapter {

	public List<T> mDatas = new ArrayList<T>();
	public WeakReference<Context> mContext;

	public BaseListAdapter(Context context) {
		this.mContext = new WeakReference<Context>(context);
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void addMoreData(T obj) {
		mDatas.add(obj);
	}

	public void addMoreData(List<T> obj) {
		mDatas.addAll(obj);
	}

	public void addHeaderData(List<T> obj) {
		mDatas.addAll(0, obj);
	}

	public void onRefreshBefore() {
	}

	public void onRefreshAfter() {
	}

	public void notifyDataSetChanged2() {
		onRefreshBefore();
		super.notifyDataSetChanged();
		onRefreshAfter();
	}

	public void clear() {
		mDatas.clear();
	}
}
