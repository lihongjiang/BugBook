package com.bslee.logtoolapk.activity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.bslee.logtoolapk.R;
import com.bslee.logtoolapk.db.BGUserDao;
import com.bslee.logtoolapk.model.BGUser;
import com.bslee.logtoolapk.model.ExceptionBug;
import com.bslee.logtoolapk.utils.MailUtil;

public class BugInfoActivity extends BaseActivity {

	private TextView mBugInfoContent;
	private ExceptionBug bug;
	private TextView bugheader;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_buginfo);
		initTitleBar();
		mBugInfoContent = (TextView) findViewById(R.id.buginfoContent);
		bugheader = (TextView) findViewById(R.id.bugheader);
		bug = (ExceptionBug) getIntent().getSerializableExtra("bug");
		process();
	}

	String text;

	private void process() {
		bugheader.setText("Bug名称: " + bug.getMessage()
				+"\n设备信息: " + bug.getPhoneInfo()
				+"\n任务信息: " + bug.getTaskinfo());
		File file = new File(bug.getMessageInfoPath());
		if (file.exists()) {
			Uri uri = Uri.fromFile(file);

			try {
				InputStream in = getContentResolver().openInputStream(uri);
				InputStreamReader isr = new InputStreamReader(in, "UTF-8");
				BufferedReader bf = new BufferedReader(isr);
				StringBuffer sb = new StringBuffer();

				String data = bf.readLine();
				while (data != null) {
					sb.append(data);
					data = bf.readLine();
				}
				text = sb.toString();
				text = text.replaceAll("at ", "\n  @");
				text = text.replaceAll(" ", "");
				mBugInfoContent.setText(text);
				in.close();
				isr.close();
				bf.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			mBugInfoContent.setText("文件不存在");
		}

	}

	@Override
	public void initTitleBar() {
		super.initTitleBar();
		mHeader.getTitleTextView().setText("Bug详情");
		mHeader.getRightButton().setText("邮件发送");
		mHeader.getRightButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				List<BGUser> data = BGUserDao.findAll();
				if (data == null || data.isEmpty()) {
					return;
				}
				final String[] dataw = new String[data.size()];
				for (int i = 0; i < dataw.length; i++) {
					dataw[i] = data.get(i).getEmail();
				}
			
				new AlertDialog.Builder(BugInfoActivity.this)
						.setItems(dataw, new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0,
									final int arg1) {
								new Thread(new Runnable() {

									@Override
									public void run() {
										try {
											MailUtil.sendEmail(dataw[arg1],
													bug.getMessage(), text);
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								}).start();

							}

						}).setTitle("邮件列表").show();

			}
		});
	}

}
