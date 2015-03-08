package com.bslee.logtoolapk.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;

public class BugHandler implements UncaughtExceptionHandler {

	private static final String INSERT = "content://com.bslee.logtoolapk.db.BugProvider/bug/insert";
	private static String DebugPackage = "com.bslee.logtoolapk";
	private Thread.UncaughtExceptionHandler mDefaultHandler;
	private static BugHandler INSTANCE;
	private Context mContext;

	private BugHandler() {
	}

	public static BugHandler getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new BugHandler();
		}
		return INSTANCE;
	}

	public void init(Context ctx) {
		mContext = ctx;
		DebugPackage = ctx.getPackageName();
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	private boolean handleException(final Throwable ex, Thread thread) {
		if (ex == null) {
			return false;
		}
		String file = saveBugFileInfo(ex);

		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
		}

		remoteBug(ex, file, thread);

		return true;
	}

	private void remoteBug(Throwable e, String filePath, Thread thread) {
		ContentResolver contentResolver = mContext.getContentResolver();
		Uri insertUri = Uri.parse(BugHandler.INSERT);
		List<ContentValues> list = getTraceInfo(e, filePath, thread);
		for (ContentValues contentValues : list) {
			contentResolver.insert(insertUri, contentValues);
		}
	}

	public String saveBugFileInfo(Throwable ex) {
		StringBuffer sb = new StringBuffer();
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		String result = writer.toString();
		sb.append(result);
		String fileName = "crash-" + System.currentTimeMillis() + ".txt";
		File file = new File(Environment.getExternalStorageDirectory()
				+ File.separator + "log" + File.separator
				+ mContext.getPackageName() + File.separator);
		if (!file.exists()) {
			file.mkdirs();
		}
		File file1 = new File(file, fileName);
		try {
			FileOutputStream fos = new FileOutputStream(file1);
			fos.write(sb.toString().getBytes());
			fos.close();
			return file1.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public List<ContentValues> getTraceInfo(Throwable e, String filePath,
			Thread thread) {

		List<ContentValues> lists = new ArrayList<ContentValues>();
		long time = System.currentTimeMillis();

		StackTraceElement[] stacks = e.getStackTrace();
		for (int i = 0; i < stacks.length; i++) {
			if (stacks[i].getClassName().contains(BugHandler.DebugPackage)) {
				ContentValues cv = new ContentValues();
				cv.put("fileName", stacks[i].getFileName());
				cv.put("message", getExceptionMessage(e));
				cv.put("methedName", stacks[i].getMethodName());
				cv.put("className", stacks[i].getClassName());
				cv.put("createTime", time);
				cv.put("lineNumber", stacks[i].getLineNumber());
				cv.put("messageInfoPath", filePath);
				cv.put("appName", getApplicationName());
				cv.put("appPackage", mContext.getPackageName());
				cv.put("phoneInfo", BugUtils.getPhoneInfo());
				cv.put("taskinfo", BugUtils.getTaskStackTopActivity(mContext));
				
				lists.add(cv);
				break;
			}
		}

		if (lists.size() == 0) {
			ContentValues cv = new ContentValues();
			cv.put("fileName", mContext.getPackageName());
			cv.put("message", "not know Exception，please view info.");
			cv.put("methedName", "callback");
			cv.put("className", mContext.getPackageName());
			cv.put("createTime", time);
			cv.put("lineNumber", "0");
			cv.put("messageInfoPath", filePath);
			cv.put("appName", getApplicationName());
			cv.put("appPackage", mContext.getPackageName());
			cv.put("phoneInfo", BugUtils.getPhoneInfo());
			cv.put("taskinfo", BugUtils.getTaskStackTopActivity(mContext));
		
			lists.add(cv);
		}

		return lists;
	}

	public static String getExceptionMessage(Throwable e) {
		String message = e.toString();
		if (message.lastIndexOf(":") != -1)
			message = message.substring(0, message.lastIndexOf(":"));
		return message;
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		handleException(ex, thread);
		// 如果用户没有处理则让系统默认的异常处理器来处理
		mDefaultHandler.uncaughtException(thread, ex);
	}

	public String getApplicationName() {
		PackageManager packageManager = null;
		ApplicationInfo applicationInfo = null;
		try {
			packageManager = mContext.getPackageManager();
			applicationInfo = packageManager.getApplicationInfo(
					mContext.getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			applicationInfo = null;
		}
		String applicationName = (String) packageManager
				.getApplicationLabel(applicationInfo);
		return applicationName;
	}
}