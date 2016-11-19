package com.lussac.dscalculator;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Lab_Activity extends Activity implements OnClickListener {

	private TextView labTab_tv_Prime;
	private TextView labTab_tv_Factor;
	private TextView labTab_tv_Random;

	private Fragment labFragmentPrime;
	private Fragment labFragmentFactor;
	private Fragment labFragmentRandom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lab);

		initView();
		initEvent();
		
		Intent intent = getIntent();
		int showWhichragmnet = intent.getIntExtra("showWhichFragment", 0);
		setTab(showWhichragmnet);
	}

	private void initView() {
		labTab_tv_Prime = (TextView) findViewById(R.id.lab_tab_prime);
		labTab_tv_Factor = (TextView) findViewById(R.id.lab_tab_factor);
		labTab_tv_Random = (TextView) findViewById(R.id.lab_tab_random);
	}

	private void initEvent() {
		labTab_tv_Prime.setOnClickListener(this);
		labTab_tv_Factor.setOnClickListener(this);
		labTab_tv_Random.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.lab_tab_prime:
			setTab(0);
			break;
		case R.id.lab_tab_factor:
			setTab(1);
			break;
		case R.id.lab_tab_random:
			setTab(2);
			break;
		default:
			break;
		}

	}

	private void setTab(int i) {
		resetBackgroud();
		
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		hideFragment(transaction);
		switch (i) {
		case 0:
			if (labFragmentPrime == null) {
				labFragmentPrime = new LabPrime_Fragment();
				transaction.add(R.id.framelayout_lab, labFragmentPrime);
			} else {
				transaction.show(labFragmentPrime);
			}
			labTab_tv_Prime.setBackgroundColor(Color.rgb(29, 190, 229));
			getWindow().setTitle("质数枚举");
			break;

		case 1:
			if (labFragmentFactor == null) {
				labFragmentFactor = new LabFactor_Fragment();
				transaction.add(R.id.framelayout_lab, labFragmentFactor);
			} else {
				transaction.show(labFragmentFactor);
			}
			labTab_tv_Factor.setBackgroundColor(Color.rgb(29, 190, 229));
			getWindow().setTitle("质因数分解");
			break;

		case 2:
			if (labFragmentRandom == null) {
				labFragmentRandom = new LabRandom_Fragment();
				transaction.add(R.id.framelayout_lab, labFragmentRandom);
			} else {
				transaction.show(labFragmentRandom);
			}
			labTab_tv_Random.setBackgroundColor(Color.rgb(29, 190, 229));
			getWindow().setTitle("随机数生成");
			break;

		default:
			break;
		}
		transaction.commit();
	}

	private void hideFragment(FragmentTransaction transaction) {
		if (labFragmentPrime != null) {
			transaction.hide(labFragmentPrime);
		}
		if (labFragmentFactor != null) {
			transaction.hide(labFragmentFactor);
		}
		if (labFragmentRandom != null) {
			transaction.hide(labFragmentRandom);
		}
	}

	private void resetBackgroud() {
		labTab_tv_Prime.setBackgroundColor(Color.rgb(13, 207, 246));
		labTab_tv_Factor.setBackgroundColor(Color.rgb(13, 207, 246));
		labTab_tv_Random.setBackgroundColor(Color.rgb(13, 207, 246));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lab_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.lab_returnToCalc:
			finish();
			break;

			
		case R.id.lab_help:
			if(labFragmentPrime.isVisible()){
				DialogFragment fragment_help_prime = new LabPrimeHelpDialogFragment();
				fragment_help_prime.show(getFragmentManager(), "mydialog");
			}else if(labFragmentFactor.isVisible()){
				DialogFragment fragment_help_factor = new LabFactorHelpDialogFragment();
				fragment_help_factor.show(getFragmentManager(), "mydialog");
			}else if(labFragmentRandom.isVisible()){
				DialogFragment fragment_help_random = new LabRandomHelpDialogFragment();
				fragment_help_random.show(getFragmentManager(), "mydialog");
			}else{
				break;
			}
			break;

		case R.id.lab_about:
			DialogFragment fragment_about = new AboutDialogFragment();
			fragment_about.show(getFragmentManager(), "mydialog");
			break;

		case R.id.lab_exit:
			finish();
			MainActivity.mainactivity.finish();
			break;

		}
		return true;
		// return super.onOptionsItemSelected(item);
	}

	
	private float touchEvent_startX, touchEvent_startY;// ,touchEvent_upX,touchEvent_upY;
	private long touchEvent_startTime;
	private boolean touchEvent_isLongTouched_flag, touchEvent_isRightMoved_flag;//,touchEvent_isLeftMoved_flag;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touchEvent_startX = event.getX();
			touchEvent_startY = event.getY();
			touchEvent_startTime = event.getEventTime();
			touchEvent_isLongTouched_flag = true;
			//touchEvent_isLeftMoved_flag = false;
			touchEvent_isRightMoved_flag = false;
			break;
		case MotionEvent.ACTION_MOVE:
			if (event.getX() - touchEvent_startX > 100 && event.getY() - touchEvent_startY < 100
					&& touchEvent_startY - event.getY() < 100) {
				touchEvent_isRightMoved_flag = true;
				//touchEvent_isLeftMoved_flag = false;
				touchEvent_isLongTouched_flag = false;
				// Toast.makeText(this,event.getX()+"-"+touchEvent_startX,0).show();
			}
			break;
		case MotionEvent.ACTION_UP:
			if (touchEvent_isRightMoved_flag) {
				finish();
				touchEvent_isLongTouched_flag = false;
				touchEvent_isRightMoved_flag = false;
			}
			if (event.getEventTime() - touchEvent_startTime > 500 && touchEvent_isLongTouched_flag) {
				this.openOptionsMenu();
			}
			break;
		}
		return super.onTouchEvent(event);
	}
	
	
}
