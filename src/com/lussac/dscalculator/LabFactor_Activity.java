 package com.lussac.dscalculator;

import com.lussac.dscalculator.R;
import com.lussac.dscalculator.utils.Factor;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class LabFactor_Activity extends Activity {
	private Button btn_Lab_factorFind;
	private EditText et_Lab_factorNum;
	private TextView tv_Lab_factorResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lab_factor);

		btn_Lab_factorFind = (Button) findViewById(R.id.btn_Lab_factorFind);
		et_Lab_factorNum = (EditText) findViewById(R.id.et_Lab_factorNum);
		tv_Lab_factorResult = (TextView) findViewById(R.id.tv_Lab_factorResult);

		btn_Lab_factorFind.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String isEmpty = et_Lab_factorNum.getText().toString();
				if (!isEmpty.isEmpty()) {
					int num = Integer.valueOf(isEmpty);
					tv_Lab_factorResult.setText(new Factor().mainFactor(num));
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lab_factor_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.lab_factor_to_prime:
			Intent intent = new Intent(LabFactor_Activity.this, LabPrime_Activity.class);
			startActivity(intent);
			finish();
			return true;
		case R.id.lab_returnToCalc:
			finish();
			return true;
		case R.id.lab_factor_help:
			DialogFragment fragment_help = new LabFactorHelpDialogFragment();
			fragment_help.show(getFragmentManager(), "mydialog");
			return true;
		case R.id.lab_about:
			DialogFragment fragment_about = new AboutDialogFragment();
			fragment_about.show(getFragmentManager(), "mydialog");
			return true;
		case R.id.lab_exit:
			finish();
			MainActivity.mainactivity.finish();
			return true;
		}
		return true;
	}

	private float touchEvent_startX,touchEvent_startY;//,touchEvent_upX,touchEvent_upY;
	private long touchEvent_startTime;
	private boolean touchEvent_isLongTouched_flag, touchEvent_isRightMoved_flag;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				touchEvent_startX=event.getX();
				touchEvent_startY=event.getY();
				touchEvent_startTime=event.getEventTime();
				touchEvent_isLongTouched_flag=true;
				touchEvent_isRightMoved_flag=false;
				break;
			case MotionEvent.ACTION_MOVE:
				if(event.getX()-touchEvent_startX > 100 && event.getY()-touchEvent_startY < 100 && touchEvent_startY - event.getY() < 100){
					touchEvent_isRightMoved_flag=true;
					touchEvent_isLongTouched_flag=false;
					//Toast.makeText(this,event.getX()+"-"+touchEvent_startX,0).show();
				}
				break;
			case MotionEvent.ACTION_UP:
				if(touchEvent_isRightMoved_flag){
					Intent intent = new Intent(this,LabPrime_Activity.class);
					startActivity(intent);
					finish();
					touchEvent_isLongTouched_flag=false;
					touchEvent_isRightMoved_flag=false;
				}
				if(event.getEventTime()-touchEvent_startTime>500 && touchEvent_isLongTouched_flag){
					this.openOptionsMenu();
				}
				break;
		}
		return super.onTouchEvent(event);
	}
	
}
