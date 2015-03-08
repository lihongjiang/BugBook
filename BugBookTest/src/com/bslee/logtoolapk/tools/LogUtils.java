package com.bslee.logtoolapk.tools;

import android.util.Log;

public class LogUtils {

	public static boolean allowD = true;
	public static String customTagPrefix = "2B";

	private static String generateTag(StackTraceElement stack) {
		String tag = "%s.%s(L:%d)";
		String className = stack.getClassName();
		className = className.substring(className.lastIndexOf(".") + 1);
		tag = String.format(tag, stack.getClassName(), className,
				stack.getLineNumber());
		tag = customTagPrefix == null ? tag : customTagPrefix + ":" + tag;
		return tag;
	}

	public static void d(String content) {
		if (!allowD) {
			return;
		}
		StackTraceElement caller = Thread.currentThread().getStackTrace()[3];
		String tag = generateTag(caller);
		Log.d(tag, content);
	}

	public static void d(String content, Throwable thr) {
		if (!allowD) {
			return;
		}
		StackTraceElement caller = Thread.currentThread().getStackTrace()[3];
		String tag = generateTag(caller);
		Log.d(tag, content, thr);
	}
}
