package com.lussac.dscalculator;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.lussac.dscalculator.R;
import com.lussac.dscalculator.utils.Factor;
import com.lussac.dscalculator.utils.Fraction;
import com.lussac.dscalculator.utils.Prime;
import com.lussac.dscalculator.utils.RandomNumber;
import com.lussac.dscalculator.utils.ReversePolishNotation;

import java.math.*;
import android.content.*;
import android.graphics.*;

public class MainActivity extends Activity implements OnClickListener {

	private Button btn_0;
	private Button btn_1;
	private Button btn_2;
	private Button btn_3;
	private Button btn_4;
	private Button btn_5;
	private Button btn_6;
	private Button btn_7;
	private Button btn_8;
	private Button btn_9;
	private Button btn_point;
	private Button btn_plus;
	private Button btn_minus;
	private Button btn_multiply;
	private Button btn_divide;
	private Button btn_equal;

	private Button btn_clear;
	private Button btn_delete;
	private EditText input;
	private TextView tv_expression;
	private TextView tv_fractionAnswer;

	private Button btn_leftParenthesis;
	private Button btn_rightParenthesis;

	private String exp;
	//private String Ans;

	public static MainActivity mainactivity;
	private SlidingMenu mLeftMenu;
	
	private Button mainMenu_prime;
	private Button mainMenu_factor;
	private Button mainMenu_random;
	private Button main_help;
	private Button about;
	private Button aboutUpdate;
	private Button exit;
	private Button setting;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		mainactivity = MainActivity.this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_slidingmenu);

		Toast.makeText(this, "右划可显示菜单", Toast.LENGTH_SHORT).show();
		init();

	}

	private void init() {
		btn_0 = (Button) findViewById(R.id.btn_0);
		btn_1 = (Button) findViewById(R.id.btn_1);
		btn_2 = (Button) findViewById(R.id.btn_2);
		btn_3 = (Button) findViewById(R.id.btn_3);
		btn_4 = (Button) findViewById(R.id.btn_4);
		btn_5 = (Button) findViewById(R.id.btn_5);
		btn_6 = (Button) findViewById(R.id.btn_6);
		btn_7 = (Button) findViewById(R.id.btn_7);
		btn_8 = (Button) findViewById(R.id.btn_8);
		btn_9 = (Button) findViewById(R.id.btn_9);
		btn_point = (Button) findViewById(R.id.btn_point);
		btn_plus = (Button) findViewById(R.id.btn_plus);
		btn_minus = (Button) findViewById(R.id.btn_minus);
		btn_multiply = (Button) findViewById(R.id.btn_multiply);
		btn_divide = (Button) findViewById(R.id.btn_divide);
		btn_equal = (Button) findViewById(R.id.btn_equal);
		btn_clear = (Button) findViewById(R.id.btn_clear);
		btn_delete = (Button) findViewById(R.id.btn_delete);
		input = (EditText) findViewById(R.id.input);
		tv_expression = (TextView) findViewById(R.id.tv_expression);
		tv_fractionAnswer = (TextView) findViewById(R.id.tv_fractionAnswer);

		btn_leftParenthesis = (Button) findViewById(R.id.btn_leftParenthesis);
		btn_rightParenthesis = (Button) findViewById(R.id.btn_rightParenthesis);

		btn_0.setOnClickListener(this);
		btn_1.setOnClickListener(this);
		btn_2.setOnClickListener(this);
		btn_3.setOnClickListener(this);
		btn_4.setOnClickListener(this);
		btn_5.setOnClickListener(this);
		btn_6.setOnClickListener(this);
		btn_7.setOnClickListener(this);
		btn_8.setOnClickListener(this);
		btn_9.setOnClickListener(this);
		btn_point.setOnClickListener(this);
		btn_plus.setOnClickListener(this);
		btn_minus.setOnClickListener(this);
		btn_multiply.setOnClickListener(this);
		btn_divide.setOnClickListener(this);
		btn_equal.setOnClickListener(this);
		btn_clear.setOnClickListener(this);
		btn_delete.setOnClickListener(this);
		btn_leftParenthesis.setOnClickListener(this);
		btn_rightParenthesis.setOnClickListener(this);

		mLeftMenu = (SlidingMenu) findViewById(R.id.sliding_menu);
		mainMenu_prime = (Button) findViewById(R.id.mainmenu_prime);
		mainMenu_factor = (Button) findViewById(R.id.mainmenu_factor);
		mainMenu_random = (Button) findViewById(R.id.mainmenu_random);
		main_help = (Button) findViewById(R.id.main_help);
		about = (Button) findViewById(R.id.about);
		aboutUpdate = (Button) findViewById(R.id.aboutUpdate);
		setting = (Button) findViewById(R.id.setting);
		exit = (Button) findViewById(R.id.exit);
		
		mainMenu_prime.setOnClickListener(this);
		mainMenu_factor.setOnClickListener(this);
		mainMenu_random.setOnClickListener(this);
		main_help.setOnClickListener(this);
		about.setOnClickListener(this);
		aboutUpdate.setOnClickListener(this);
		setting.setOnClickListener(this);
		exit.setOnClickListener(this);
		
		btn_delete.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				exp = input.getText().toString();
				input.setText("");
				return true;
			}
		});
		btn_clear.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				input.setText(exp);
				return true;
			}
		});
		
		mainMenu_prime.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				Intent intent = new Intent(MainActivity.this, Lab_Activity.class);
				intent.putExtra("showWhichFragment", 0);
				startActivity(intent);
				return true;
			}
		});
		
		mainMenu_factor.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				Intent intent = new Intent(MainActivity.this, Lab_Activity.class);
				intent.putExtra("showWhichFragment", 1);
				startActivity(intent);
				return true;
			}
		});
		
		mainMenu_random.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				Intent intent = new Intent(MainActivity.this, Lab_Activity.class);
				intent.putExtra("showWhichFragment", 2);
				startActivity(intent);
				return true;
			}
		});
		
	}

	@Override
	public void onClick(View v) {

		String str = input.getText().toString();
		switch (v.getId()) {
		case R.id.btn_0:
		case R.id.btn_1:
		case R.id.btn_2:
		case R.id.btn_3:
		case R.id.btn_4:
		case R.id.btn_5:
		case R.id.btn_6:
		case R.id.btn_7:
		case R.id.btn_8:
		case R.id.btn_9:
			if (str.matches(".*[^.\\d]+0|0"))
				// when a number starts with 0, if you try inputting another
				// number, the new number will replace 0.
				str = str.substring(0, str.length() - 1);
			input.setText(str + ((Button) v).getText());
			break;

		case R.id.btn_point:
			if (str.matches(".*\\d*\\.\\d*"))
				// you can't input a point again when it's a decimal
				break;
			input.setText(str + ((Button) v).getText());
			break;

		case R.id.btn_plus:
		case R.id.btn_minus:
		case R.id.btn_multiply:
		case R.id.btn_divide:
			if (str.matches(".*\\d+\\)?[+\\-×÷\\.]"))
				// if you just input an operation or a point, new operation will
				// replace it.
				str = str.substring(0, str.length() - 1);
			input.setText(str + ((Button) v).getText());
			break;

		case R.id.btn_leftParenthesis:
		case R.id.btn_rightParenthesis:
			if (str.matches(".*\\d+\\)?\\."))
				// if you just input a point,new parenthesis will replace it.
				str = str.substring(0, str.length() - 1);
			input.setText(str + ((Button) v).getText());
			break;

		case R.id.btn_clear:
			tv_expression.setText("");
			tv_fractionAnswer.setText("");
			input.setText("");
			break;

		case R.id.btn_delete:
			if (str != null && !str.equals("")) {
				input.setText(str.substring(0, str.length() - 1));
			}
			break;

		case R.id.btn_equal:
			exp = input.getText().toString();
			if (!exp.equals("")) {
				tv_expression.setText(exp + " =");
				tv_expression.requestFocus();
				tv_fractionAnswer.setText("");
				tv_fractionAnswer.setTextSize(18);
				tv_fractionAnswer.setTextColor(Color.rgb(255, 255, 255));
			}

			try {
				getResult();
				// input.setText(formatting(exp));
			} catch (Exception e) {
				tv_fractionAnswer.setTextSize(15);
				tv_fractionAnswer.setTextColor(Color.rgb(102, 255, 255));
				tv_fractionAnswer.setText("算式错误或无法计算");
			}

			break;
			
		default:
			if(mLeftMenu.getIsLeftMenuOpen())
				switchMenuItems(v.getId());
			break;
		}
	}

	private void getResult() {

		if (exp == null || exp.equals(""))
			return;
		if (exp.matches("[-+]?[0-9]*"))
			return; // 若只有整数，没有小数或运算符
		exp = formatting(exp);

		if (!exp.matches("[\\-+]?\\d+\\.\\d+")) {// [-+]?\\d+\\.\\d*|[-+]?\\d*\\.\\d+")){
			String fianlResult = new ReversePolishNotation().calculate(exp);
			if (fianlResult.equals("ERROR_DIVIDED_BY_ZERO")) {
				tv_fractionAnswer.setTextSize(15);
				tv_fractionAnswer.setTextColor(Color.rgb(102, 255, 255));
				tv_fractionAnswer.setText("除数不能为零，屌丝!");
				return;
			}

			String a = fianlResult.substring(0, fianlResult.indexOf("/"));
			String b = fianlResult.substring(fianlResult.indexOf("/") + 1);
			if (b.equals("1")) {
				input.setText(a + "");
			} else {
				BigDecimal numerator = new BigDecimal(a);
				BigDecimal denominator = new BigDecimal(b);
				double fractionToDecimal = numerator.divide(denominator, 10, BigDecimal.ROUND_HALF_UP).doubleValue();
				if (fractionToDecimal % 10 == 0)
					input.setText((int) fractionToDecimal + "");
				else
					input.setText(BigDecimal.valueOf(fractionToDecimal).stripTrailingZeros() + "");
				tv_fractionAnswer.setText(fianlResult);
			}
		} else {
			if (exp.matches("0*\\.0*")) { // 如果输入0.0,0.000,00.0,.00,00.,之类的，则输出0
				input.setText("0");
				return;
			}
			Fraction deximalToFraction = new Fraction(exp);
			int numerator = deximalToFraction.getNumerator();
			int denominator = deximalToFraction.getDenominator();
			if (denominator == 1) {
				input.setText(numerator + "");
			} else {
				String isError = "";
				if (numerator == 2147483647 || denominator == 2147483647) {
					isError = "ERROR!!! ";
					Toast.makeText(this, "当前结果异常!", Toast.LENGTH_LONG).show();
				}
				if (exp.substring(exp.indexOf(".") + 1).length() >= 8 && numerator < 2147483647
						&& denominator < 2147483647) {
					Toast.makeText(this, "警告:小数部分过长!\n分数结果可能异常!", Toast.LENGTH_SHORT).show();
				}
				tv_fractionAnswer.setText(isError + String.valueOf(numerator) + "/" + String.valueOf(denominator));
			}
		}
	}

	// formating the expression
	private String formatting(String exp) {
		// add left-out multiplication_sign
		exp = exp.replaceAll("\\)(?=\\d|\\.)", ")×");
		exp = exp.replaceAll("(?<=\\d|\\.)\\(", "×(");
		// turn point-numbers to zero-point-numbers
		exp = exp.replaceAll("(?<=[+\\-×÷(]|^)\\.", "0.");
		// turn number-point to number-point-zero
		exp = exp.replaceAll("(?<=\\d)\\.(?=[+\\-×÷()])", ".0");
		exp = exp.replaceAll("\\.$", ".0");

		return exp;
	}
	
	private void switchMenuItems(int id) {
		switch (id) {
		case R.id.mainmenu_prime:
			mLeftMenu.toggleMenu();
			showDialogPrime();
			break;
		case R.id.mainmenu_factor:
			mLeftMenu.toggleMenu();
			showDialogFactor();
			break;
		case R.id.mainmenu_random:
			mLeftMenu.toggleMenu();
			showDialogRandom();
			break;
		case R.id.main_help:
			DialogFragment fragment_help = new HelpDialogFragment();
			fragment_help.show(getFragmentManager(), "mydialog");
			break;
		case R.id.about:
			DialogFragment fragment_about = new AboutDialogFragment();
			fragment_about.show(getFragmentManager(), "mydialog");
			break;
		case R.id.aboutUpdate:
			DialogFragment fragment_aboutUpdate = new AboutUpdateDialogFragment();
			fragment_aboutUpdate.show(getFragmentManager(), "mydialog");
			break;
		case R.id.setting:
			Toast.makeText(this, "假装进入了设置界面（尚未开发）", Toast.LENGTH_SHORT).show();
			showDialogSetting();
			break;
		case R.id.exit:
			finish();
			break;
		}
	}

	private void showDialogPrime() {
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.dialog_layout_prime, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.Lab_prime);
		builder.setView(view);
		builder.create();
		builder.setNeutralButton("切换至主界面", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				Intent intent = new Intent(MainActivity.this, Lab_Activity.class);
				intent.putExtra("showWhichFragment", 0);
				startActivity(intent);
			};
		});
		
		final EditText et_Lab_primeUp = (EditText)view.findViewById(R.id.et_Lab_primeUp);
		final EditText et_Lab_primeDown = (EditText)view.findViewById(R.id.et_Lab_primeDown);;
		final TextView tv_Lab_primeResult = (TextView) view.findViewById(R.id.tv_Lab_primeResult);
		
		view.findViewById(R.id.btn_Lab_primeCalc).setOnClickListener(new android.view.View.OnClickListener() {
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
						tv_Lab_primeResult.setText("[" + a + "," + b + "] 内共有" + new Prime(a,b).countPrime() + "个质数");
					}else{
						Toast.makeText(MainActivity.this, "上限应大于下限", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(MainActivity.this, "请输入查找上限", Toast.LENGTH_SHORT).show();
				}
			}
		});
		builder.show();
		
	}

	private void showDialogFactor() {
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.dialog_layout_factor, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.Lab_factor);
		builder.setView(view);
		builder.create();
		builder.setNeutralButton("切换至主界面", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				Intent intent = new Intent(MainActivity.this, Lab_Activity.class);
				intent.putExtra("showWhichFragment", 1);
				startActivity(intent);
			};
		});
		
		final EditText et_Lab_factorNum = (EditText) view.findViewById(R.id.et_Lab_factorNum);
		final TextView tav_Lab_factorResult = (TextView) view.findViewById(R.id.tv_Lab_factorResult);
		view.findViewById(R.id.btn_Lab_factorFind).setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String isEmpty = et_Lab_factorNum.getText().toString();
				if (!isEmpty.isEmpty()) {
					int num = Integer.valueOf(isEmpty);
					tav_Lab_factorResult.setText(new Factor().mainFactor(num));
				}	
			}
		});
		builder.show();
	}

	private void showDialogRandom() {
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.dialog_layout_random, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.Lab_random);
		builder.setView(view);
		builder.create();
		builder.setNeutralButton("切换至主界面", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				Intent intent = new Intent(MainActivity.this, Lab_Activity.class);
				intent.putExtra("showWhichFragment", 2);
				startActivity(intent);
			};
		});
		
		final EditText et_Lab_randomUp = (EditText)view.findViewById(R.id.et_Lab_randomUp);
		final EditText et_Lab_randomDown = (EditText)view.findViewById(R.id.et_Lab_randomDown);;
		final TextView tv_Lab_randomResult = (TextView) view.findViewById(R.id.tv_Lab_randomResult);
		
		view.findViewById(R.id.btn_Lab_randomCalc).setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int a, b;
				String left = et_Lab_randomDown.getText().toString();
				String right = et_Lab_randomUp.getText().toString();
				if (!right.equals("")) {
					if (left.equals(""))
						a = 1;
					else
						a = Integer.parseInt(left);
					b = Integer.parseInt(right);
					if(a <= b)
						tv_Lab_randomResult.setText(new RandomNumber().getRandomInt(a, b) + "");
					else
						Toast.makeText(MainActivity.this, "上限不能小于下限", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MainActivity.this, "请输入范围上限", Toast.LENGTH_SHORT).show();
				}
			}
		});
		builder.show();
	}

	private void showDialogSetting() {
		//mLeftMenu.setPadding(left, top, right, bottom);
	}

}

/*
设置EditText隐藏输入法
onCreate方法下
	int sdkInt = Build.VERSION.SDK_INT;//16(android 4.1)
	Toast.makeText(getApplicationContext(),sdkInt+"",Toast.LENGTH_SHORT).show();
	if(sdkInt >= 11){
		Class<EditText> cls = EditText.class;
		try{
			Method setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
			setShowSoftInputOnFocus.setAccessible(false);
			setShowSoftInputOnFocus.invoke(input, false);
			setShowSoftInputOnFocus.invoke(input, false);
		}catch (Exception e){
			e.printStackTrace();
		}
	}else{
		input.setInputType(InputType.TYPE_NULL);
	}
*/