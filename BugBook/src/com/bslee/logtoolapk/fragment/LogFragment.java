package com.bslee.logtoolapk.fragment;

import java.util.List;

import org.litepal.crud.DataSupport;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.bslee.logtoolapk.R;
import com.bslee.logtoolapk.activity.BugInfoActivity;
import com.bslee.logtoolapk.adapter.BugListAdapter;
import com.bslee.logtoolapk.db.ExceptionBugDao;
import com.bslee.logtoolapk.model.ExceptionBug;
import com.bslee.logtoolapk.widget.CommonHeader;

public class LogFragment extends BaseFragment {

	public ListView mListView;
	public BugListAdapter mAdapter;
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
		mAdapter = new BugListAdapter(getActivity());
		mListView.setAdapter(mAdapter);
		mCommonHeader.getLeftButton().setVisibility(View.GONE);
		mCommonHeader.getTitleTextView().setText("日志");
		mCommonHeader.getRightButton().setVisibility(View.GONE);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ExceptionBug bug = (ExceptionBug) arg0.getAdapter().getItem(
						arg2);
				Intent intent = new Intent(getActivity(), BugInfoActivity.class);
				intent.putExtra("bug", bug);
				startActivity(intent);
			}
		});

		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			

			@Override
			public boolean onItemLongClick(final AdapterView<?> adapter,
					View arg1, final int arg2, long arg3) {
				new AlertDialog.Builder(getActivity())
						.setMessage("是否删除？")
						.setNegativeButton("Yes",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										try {
											ExceptionBug bug=((ExceptionBug) adapter.getAdapter()
													.getItem(arg2));
											DataSupport.delete(ExceptionBug.class, bug.getId());
											process();
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								}).setNeutralButton("No", null).show();
				return true;
			}
		});
	}

	@Override
	public void process() {
		mAdapter.clear();
		List<ExceptionBug> list = ExceptionBugDao.findAll();
		mAdapter.addMoreData(list);
		mAdapter.notifyDataSetChanged();
	}

}
