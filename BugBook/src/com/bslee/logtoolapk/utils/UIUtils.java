package com.bslee.logtoolapk.utils;

import android.widget.ListView;

public class UIUtils {
	public static ListViewPosition getPosition(ListView listView) {
		if (listView == null || listView.getChildCount() <= 0) {
			return null;
		}
		int position = listView.getFirstVisiblePosition();
		int top = 0;
		if (position > -1) {
			top = listView.getChildAt(0).getTop();
		}
		return new ListViewPosition(position, top);
	}

	public static class ListViewPosition {

		public int position;
		public int top;

		public ListViewPosition(int position, int top) {
			this.position = position;
			this.top = top;
		}
	}

	public static void restorePostion(ListView listView,
			ListViewPosition position, int count) {
		if (position != null) {
			position.position = position.position + count;
			restorePosition(listView, position);
		} else {
			restorePosition(listView, position);
		}
	}

	public static void restorePosition(ListView listView,
			ListViewPosition position) {
		if (listView == null) {
			return;
		}
		if (position != null) {
			listView.setSelectionFromTop(position.position, position.top);
		}
	}
}
