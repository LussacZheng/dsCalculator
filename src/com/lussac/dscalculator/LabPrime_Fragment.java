package com.lussac.dscalculator;

import com.lussac.dscalculator.R;
import com.lussac.dscalculator.utils.Prime;

import android.os.*;
import android.app.Fragment;
import android.text.method.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class LabPrime_Fragment extends Fragment {
	private Button btn_Lab_primeCalc;
	private Button btn_Lab_primeClear;
	private TextView tv_Lab_primeList;
	private TextView tv_Lab_primeResult;
	private EditText et_Lab_primeUp;
	private EditText et_Lab_primeDown;
	private CheckBox cb_Lab_primeIfChecked;

	private boolean ifList = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_lab_prime, container, false);

		btn_Lab_primeCalc = (Button) view.findViewById(R.id.btn_Lab_primeCalc);
		btn_Lab_primeClear = (Button) view.findViewById(R.id.btn_Lab_primeClear);
		tv_Lab_primeList = (TextView) view.findViewById(R.id.tv_Lab_primeList);
		tv_Lab_primeResult = (TextView) view.findViewById(R.id.tv_Lab_primeResult);
		et_Lab_primeUp = (EditText) view.findViewById(R.id.et_Lab_primeUp);
		et_Lab_primeDown = (EditText) view.findViewById(R.id.et_Lab_primeDown);
		cb_Lab_primeIfChecked = (CheckBox) view.findViewById(R.id.cb_Lab_primeIfList);

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

					if (a < b) {
						String str = new String(new Prime(a, b).mainPrime(tv_Lab_primeResult, ifList));
						tv_Lab_primeList.setText(str);
					} else {
						Toast.makeText(getActivity(), "上限应大于下限", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getActivity(), "请输入查找上限", Toast.LENGTH_SHORT).show();
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
				Toast.makeText(getActivity(), "长按可没什么用，屌丝", Toast.LENGTH_SHORT).show();
				return true;
			}
		});

		cb_Lab_primeIfChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					ifList = true;
					Toast.makeText(getActivity(), "取消勾选可加快计算速度", Toast.LENGTH_SHORT).show();
				} else {
					ifList = false;
					Toast.makeText(getActivity(), "勾选可枚举区间内所有质数", Toast.LENGTH_SHORT).show();
				}
			}
		});

		return view;
	}

//	@Override
//	public void onHiddenChanged(boolean hidden) {
//		super.onHiddenChanged(hidden);
//		
//		if(hidden){
//			isFragmentPrimeHidden = false;
//		}else{
//			isFragmentPrimeHidden=true;
//		}
//		
// }

}
