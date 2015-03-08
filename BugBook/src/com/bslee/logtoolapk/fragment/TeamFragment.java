package com.bslee.logtoolapk.fragment;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.bslee.logtoolapk.R;
import com.bslee.logtoolapk.activity.AddUserActivity;
import com.bslee.logtoolapk.adapter.BGUserListAdapter;
import com.bslee.logtoolapk.db.BGUserDao;
import com.bslee.logtoolapk.model.BGUser;
import com.bslee.logtoolapk.widget.CommonHeader;

public class TeamFragment extends BaseFragment {
	public ListView mListView;
	public BGUserListAdapter mAdapter;
	public CommonHeader mCommonHeader;

	@Override
	public int getContentLayout() {
		return R.layout.activity_main;
	}

	@Override
	public void findViews(View view) {
		mListView = (ListView) view.findViewById(R.id.buglist);
		mCommonHeader = (CommonHeader) view.findViewById(R.id.commonHeader);
	}

	@Override
	public void setListeners() {
		mAdapter = new BGUserListAdapter(getActivity());
		mListView.setAdapter(mAdapter);
		mCommonHeader.getLeftButton().setVisibility(View.GONE);
		mCommonHeader.getTitleTextView().setText("团队");
		mCommonHeader.getRightButton().setText("添加");
		mCommonHeader.getRightButton().setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						startActivity(new Intent(getActivity(),
								AddUserActivity.class));
					}
				});
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> adapter, View arg1,
					final int arg2, long arg3) {
				new AlertDialog.Builder(getActivity())
						.setMessage("是否删除？")
						.setNegativeButton("Yes",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										((BGUser) adapter.getAdapter().getItem(
												arg2)).delete();
										update();
									}
								}).setNeutralButton("No", null).show();
			}
		});
	}

	@Override
	public void process() {
	}

	@Override
	public void onResume() {
		super.onResume();
		update();
	}

	private void update() {
		mAdapter.clear();
		List<BGUser> list = BGUserDao.findAll();
		mAdapter.addMoreData(list);
		mAdapter.notifyDataSetChanged();
	}

}
