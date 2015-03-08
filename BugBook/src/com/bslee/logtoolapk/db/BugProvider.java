package com.bslee.logtoolapk.db;

import org.litepal.tablemanager.Connector;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;


public class BugProvider extends ContentProvider {

	private static final String URI_AUTH = "com.bslee.logtoolapk.db.BugProvider";
	private static final int INSERT = 1;
	private static final int DELETE = 2;

	private static final UriMatcher MATCHER = new UriMatcher(
			UriMatcher.NO_MATCH);

	static {
		MATCHER.addURI(URI_AUTH, "bug/insert", INSERT);
		MATCHER.addURI(URI_AUTH, "bug/delete/#", DELETE);
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		return null;

	}

	// 插入
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = Connector.getReadableDatabase();
		switch (MATCHER.match(uri)) {
		case INSERT:
			long id = db.insert("ExceptionBug", null, values);
			
			return ContentUris.withAppendedId(uri, id);
		default:
			throw new IllegalArgumentException("Uri不匹配");
		}
	}

	@Override
	public String getType(Uri uri) {
		switch (MATCHER.match(uri)) {
		case DELETE:
			return "vnd.android.cursor.item/bug";
		case INSERT:
			return "vnd.android.cursor.dir/bug";
		default:
			throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}

	}

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		return 0;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		return 0;
	}

	@Override
	public boolean onCreate() {
		return false;
	}

}