package com.bslee.logtoolapk.widget;

import com.bslee.logtoolapk.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CommonHeader extends LinearLayout {
	
	private TextView mLeftBtn, mRightBtn;
	private ImageButton mRightBtn2;
	private TextView mTitleTv;
	private LinearLayout mLeftContainer, mCenterContainer;
	private LinearLayout mRightContainer;
	private int mLeftRightTextPadding;

	private RelativeLayout mWhoelView;

	public CommonHeader(Context context) {
		super(context, null);
		init(context, null);
	}

	public CommonHeader(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	

	@SuppressWarnings("deprecation")
	private void init(Context mContext, AttributeSet attrs) {
		LayoutInflater.from(mContext).inflate(R.layout.title_bar, this);
		mWhoelView = (RelativeLayout) findViewById(R.id.wholeView);
		mLeftBtn = (TextView) findViewById(R.id.header_left_btn);
		mRightBtn = (TextView) findViewById(R.id.header_right_btn);
		mRightBtn2 = (ImageButton) findViewById(R.id.header_right_btn2);
		mTitleTv = (TextView) findViewById(R.id.header_title_text);
		mTitleTv.setSelected(true);
		mLeftContainer = (LinearLayout) findViewById(R.id.header_left);
		mCenterContainer = (LinearLayout) findViewById(R.id.header_center);
		mRightContainer = (LinearLayout) findViewById(R.id.header_right);
		if (attrs != null) {
			TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.CommonHeader);
			if (a != null) {
				// title的左右间距 ,默认为60dp
				// int titleContainerPadding = (int)
				// a.getDimension(R.styleable.CommonHeader_titleTextLayoutPadding,
				// getResources().getDimensionPixelSize(R.dimen.px120));
				// mCenterContainer.setPadding(titleContainerPadding, 0,
				// titleContainerPadding, 0);
				// 文字button的左右paddding值 ,默认为10dp
				mLeftRightTextPadding = (int) a.getDimension(R.styleable.CommonHeader_leftRightTextPadding,
						getResources().getDimensionPixelSize(R.dimen.px20));

				// 设置左边btn文字
				String leftText = a.getString(R.styleable.CommonHeader_leftText);
				if (!TextUtils.isEmpty(leftText)) {
					mLeftBtn.setText(leftText);
					mLeftBtn.setPadding(mLeftRightTextPadding, 0, mLeftRightTextPadding, 0);
				}
				// 设置右边btn文字
				String rightText = a.getString(R.styleable.CommonHeader_rightText);
				if (!TextUtils.isEmpty(rightText)) {
					mRightBtn.setText(rightText);
					mRightBtn.setPadding(mLeftRightTextPadding, 0, mLeftRightTextPadding, 0);
				}
				// 设置title文字
				String titleText = a.getString(R.styleable.CommonHeader_titleText);
				if (!TextUtils.isEmpty(titleText)) {
					mTitleTv.setText(titleText);
				}
				// 设置右边btn文字颜色
				ColorStateList rColorStateList = a.getColorStateList(R.styleable.CommonHeader_rightTextColor);
				if (rColorStateList != null) {
					mRightBtn.setTextColor(rColorStateList);
				}

				// 设置左边btn文字颜色
				ColorStateList lColorStateList = a.getColorStateList(R.styleable.CommonHeader_leftTextColor);
				if (lColorStateList != null) {
					mLeftBtn.setTextColor(lColorStateList);
				}

				// 设置右边btn背景
				Drawable rBgDrawable = a.getDrawable(R.styleable.CommonHeader_rightBgDrawable);
				if (rBgDrawable != null) {
					mRightBtn.setBackgroundDrawable(rBgDrawable);
					setPadding(0, 0, 0, 0);
				}

				// 设置左边btn背景
				Drawable lBgDrawable = a.getDrawable(R.styleable.CommonHeader_leftBgDrawable);
				if (lBgDrawable != null) {
					mLeftBtn.setBackgroundDrawable(lBgDrawable);
					setPadding(0, 0, 0, 0);
				}

			}
			a.recycle();
		}

		mLeftBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getContext() instanceof Activity) {
					((Activity) getContext()).finish();
				}
			}
		});
	}

	public LinearLayout getLeftContainer() {
		return mLeftContainer;
	}

	public LinearLayout getRightContainer() {
		mRightContainer.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				return true;
			}
		});
		return mRightContainer;
	}

	public LinearLayout getCenterContainer() {
		return mCenterContainer;
	}

	public TextView getLeftButton() {
		return mLeftBtn;
	}

	public TextView getRightButton() {
		return mRightBtn;
	}

	public ImageButton getRightButton2() {
		mRightBtn.setVisibility(View.GONE);
		mRightBtn2.setVisibility(View.VISIBLE);
		return mRightBtn2;
	}

	public TextView getTitleTextView() {
		return mTitleTv;
	}

	/**
	 * 设置左右button的padding值
	 * 
	 * @param paddingPx
	 */
	public void setButtonTextPaddint(int paddingPx) {
		mLeftRightTextPadding = paddingPx;
		setViewLRPadding(mLeftBtn);
		setViewLRPadding(mRightBtn);
	}

	/**
	 * 如果Button是文字显示的话，设置button的左右padding值
	 * 
	 * @param v
	 */
	private void setViewLRPadding(TextView v) {
		if (v.getText().length() > 0) {
			v.setPadding(mLeftRightTextPadding, 0, mLeftRightTextPadding, 0);
		}
	}

	/**
	 * 点击空白区域 scrollView 回滚到顶部
	 * 
	 * @param mListener
	 */
	public void setSpaceOnClickListener(OnClickListener mListener) {
		this.setOnClickListener(mListener);
	}

	public RelativeLayout getWholeView() {
		return mWhoelView;
	}

}
