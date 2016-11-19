package com.lussac.dscalculator;

import com.lussac.dscalculator.R;
import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SlidingMenu extends HorizontalScrollView {

	private LinearLayout mWapper;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private int mScreenWidth;
	private int mMenuRightPadding = 50;// dp
	private int mMenuWidth;

	private boolean once = false;
	private boolean isLeftMenuOpen;

	public SlidingMenu(Context context) {
		this(context, null);
	}

	public SlidingMenu(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		// 获取定义的属性
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SlidingMenu, defStyleAttr, 0);

		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.SlidingMenu_rightPadding:
				// 把dp转化为px
				mMenuRightPadding = a.getDimensionPixelSize(attr, (int) TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()));
				break;
			}
		}

		a.recycle();

		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (!once) {
			mWapper = (LinearLayout) getChildAt(0);
			mMenu = (ViewGroup) mWapper.getChildAt(0);
			mContent = (ViewGroup) mWapper.getChildAt(1);
			mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
			mContent.getLayoutParams().width = mScreenWidth;
			once = true;
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);

		if (changed) {
			this.scrollTo(mMenuWidth, 0);
		}
	}

	private float touchEvent_startX, touchEvent_startY;// ,touchEvent_upX,touchEvent_upY;
	private long touchEvent_startTime;
	private boolean touchEvent_isLongTouched_flag, touchEvent_isLeftMoved_flag;

	@Override
	public boolean onTouchEvent ( MotionEvent ev) {

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touchEvent_startX = ev.getX();
			touchEvent_startY = ev.getY();
			touchEvent_startTime = ev.getEventTime();
			touchEvent_isLongTouched_flag = true;
			touchEvent_isLeftMoved_flag = false;
			break;
		case MotionEvent.ACTION_MOVE:
			if (touchEvent_startX - ev.getX() > 100 && ev.getY() - touchEvent_startY < 100
					&& touchEvent_startY - ev.getY() < 100 && !isLeftMenuOpen) {
				touchEvent_isLeftMoved_flag = true;
				touchEvent_isLongTouched_flag = false;
			}
			break;
		case MotionEvent.ACTION_UP:
			// 隐藏在左边的宽度
			int scrollX = getScrollX();
			if (scrollX >= mMenuWidth / 2) {
				this.smoothScrollTo(mMenuWidth, 0);
				isLeftMenuOpen = false;
			} else {
				this.smoothScrollTo(0, 0);
				isLeftMenuOpen = true;
			}
			if (touchEvent_isLeftMoved_flag && !isLeftMenuOpen) {
				Intent intent = new Intent(MainActivity.mainactivity, Lab_Activity.class);
				MainActivity.mainactivity.startActivity(intent);
				touchEvent_isLongTouched_flag = false;
				touchEvent_isLeftMoved_flag = false;
			}
			if (ev.getEventTime() - touchEvent_startTime > 500 && touchEvent_isLongTouched_flag
					&& !touchEvent_isLeftMoved_flag) {
				MainActivity.mainactivity.openOptionsMenu();
			}
			return true;
		}
		return super.onTouchEvent(ev);
	}

	public void toggleMenu() {
		if (isLeftMenuOpen) {
			this.smoothScrollTo(mMenuWidth, 0);
			isLeftMenuOpen = false;
		} else {
			this.smoothScrollTo(0, 0);
			isLeftMenuOpen = true;
		}
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {

		super.onScrollChanged(l, t, oldl, oldt);
		// 调用属性动画，设置TranslaionX
		float scale = l * 1.0f / mMenuWidth;// 1~0
		ViewHelper.setTranslationX(mMenu, mMenuWidth * scale);

	}

	public boolean getIsLeftMenuOpen() {
		return isLeftMenuOpen;
	}

}
