package com.lussac.dscalculator;

import com.lussac.dscalculator.R;
import com.lussac.dscalculator.utils.Prime;

import android.app.*;
import android.content.*;
import android.os.*;
import android.text.method.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class LabPrime_Activity extends Activity {
	private Button btn_Lab_primeCalc;
	private Button btn_Lab_primeClear;
	private TextView tv_Lab_primeList;
	private TextView tv_Lab_primeResult;
	private EditText et_Lab_primeUp;
	private EditText et_Lab_primeDown;
	private CheckBox cb_Lab_primeIfChecked;

	// public static LabPrime_Activity labActivity;
	private boolean ifList = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// labActivity=LabPrime_Activity.this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lab_prime);

		btn_Lab_primeCalc = (Button) findViewById(R.id.btn_Lab_primeCalc);
		btn_Lab_primeClear = (Button) findViewById(R.id.btn_Lab_primeClear);
		tv_Lab_primeList = (TextView) findViewById(R.id.tv_Lab_primeList);
		tv_Lab_primeResult = (TextView) findViewById(R.id.tv_Lab_primeResult);
		et_Lab_primeUp = (EditText) findViewById(R.id.et_Lab_primeUp);
		et_Lab_primeDown = (EditText) findViewById(R.id.et_Lab_primeDown);
		cb_Lab_primeIfChecked = (CheckBox) findViewById(R.id.cb_Lab_primeIfList);

		tv_Lab_primeList.setMovementMethod(ScrollingMovementMethod.getInstance());

		btn_Lab_primeCalc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				int a = 0, b = 0;
				String left = et_Lab_primeDown.getText().toString();
				String right = et_Lab_primeUp.getText().toString();
				if (!right.equals("")) {
					if (left.equals(""))
						a = 0;
					else
						a = Integer.parseInt(left);
					b = Integer.parseInt(right);

					if(a < b){
						String str = new String(new Prime(a,b).mainPrime(tv_Lab_primeResult, ifList));
						tv_Lab_primeList.setText(str);
					}else{
						Toast.makeText(LabPrime_Activity.this, "上限应大于下限", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(LabPrime_Activity.this, "请输入查找上限", Toast.LENGTH_SHORT).show();
				}
			}
		});

		btn_Lab_primeClear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv_Lab_primeList.setText("");
				tv_Lab_primeResult.setText("");
				et_Lab_primeDown.setText("");
				et_Lab_primeUp.setText("");
			}
		});

		btn_Lab_primeClear.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				Toast.makeText(LabPrime_Activity.this, "长按可没什么用，屌丝", Toast.LENGTH_SHORT).show();
				return true;
			}
		});

		cb_Lab_primeIfChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					ifList = true;
					Toast.makeText(LabPrime_Activity.this, "取消勾选可加快计算速度", Toast.LENGTH_SHORT).show();
				} else {
					ifList = false;
					Toast.makeText(LabPrime_Activity.this, "勾选可枚举区间内所有质数", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lab_prime_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.lab_prime_to_factor:
			Intent intent = new Intent(LabPrime_Activity.this, LabFactor_Activity.class);
			startActivity(intent);
			finish();
			return true;
		case R.id.lab_returnToCalc:
			finish();
			return true;
		case R.id.lab_prime_help:
			DialogFragment fragment_help = new LabPrimeHelpDialogFragment();
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

		return super.onOptionsItemSelected(item);
	}
	
	private float touchEvent_startX,touchEvent_startY;//,touchEvent_upX,touchEvent_upY;
	private long touchEvent_startTime;
	private boolean touchEvent_isLongTouched_flag,touchEvent_isLeftMoved_flag,touchEvent_isRightMoved_flag;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				touchEvent_startX=event.getX();
				touchEvent_startY=event.getY();
				touchEvent_startTime=event.getEventTime();
				touchEvent_isLongTouched_flag=true;
				touchEvent_isLeftMoved_flag=false;
				touchEvent_isRightMoved_flag=false;
				break;
			case MotionEvent.ACTION_MOVE:
				if(event.getX()-touchEvent_startX > 100 && event.getY()-touchEvent_startY < 100 && touchEvent_startY - event.getY() < 100){
					touchEvent_isRightMoved_flag=true;
					touchEvent_isLeftMoved_flag=false;
					touchEvent_isLongTouched_flag=false;
					//Toast.makeText(this,event.getX()+"-"+touchEvent_startX,0).show();
				}
				if(touchEvent_startX-event.getX() > 100 && event.getY() - touchEvent_startY < 100 && touchEvent_startY - event.getY() < 100){
					touchEvent_isLeftMoved_flag=true;
					touchEvent_isRightMoved_flag=false;
					touchEvent_isLongTouched_flag=false;
				}
				break;
			case MotionEvent.ACTION_UP:
				if(touchEvent_isLeftMoved_flag){
					Intent intent = new Intent(this,LabFactor_Activity.class);
					startActivity(intent);
					finish();
					touchEvent_isLongTouched_flag=false;
					touchEvent_isLeftMoved_flag=false;
				}else if(touchEvent_isRightMoved_flag){
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
