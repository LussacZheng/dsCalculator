package com.lussac.dscalculator;

import com.lussac.dscalculator.utils.RandomNumber;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabRandom_Fragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_lab_random, container, false);
		
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
						Toast.makeText(getActivity(), "上限不能小于下限", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getActivity(), "请输入范围上限", Toast.LENGTH_SHORT).show();
				}
			}
		});
		return view;
	}
}
