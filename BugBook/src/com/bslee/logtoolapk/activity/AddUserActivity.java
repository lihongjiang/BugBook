package com.bslee.logtoolapk.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.bslee.logtoolapk.R;
import com.bslee.logtoolapk.model.BGUser;

public class AddUserActivity extends BaseActivity {

	private EditText userNickName;
	private EditText userEmail;
	private EditText userMode;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_adduser);
		initTitleBar();
		userNickName = (EditText) findViewById(R.id.userNickName);
		userEmail = (EditText) findViewById(R.id.userEmail);
		userMode = (EditText) findViewById(R.id.userMode);
		process();
	}

	private void process() {

	}

	@Override
	public void initTitleBar() {
		super.initTitleBar();
		mHeader.getTitleTextView().setText("添加成员");
		mHeader.getRightButton().setText("保存");
		mHeader.getRightButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				saveUserInfo();
			}
		});
	}

	public void saveUserInfo() {
		BGUser bug = new BGUser();
		String nickname = userNickName.getText().toString();
		String email = userEmail.getText().toString();
		String mode = userMode.getText().toString();

		if (TextUtils.isEmpty(nickname) || TextUtils.isEmpty(email)) {
			return;
		}
		bug.setNickname(nickname);
		bug.setEmail(email);
		bug.setModel(mode);
		bug.save();
		finish();
	}
}
