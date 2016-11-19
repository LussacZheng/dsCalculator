package com.lussac.dscalculator;

import com.lussac.dscalculator.utils.Factor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabFactor_Fragment extends Fragment {
	private Button btn_Lab_factorFind;
	private EditText et_Lab_factorNum;
	private TextView tv_Lab_factorResult;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_lab_factor, container, false);
		
		btn_Lab_factorFind = (Button) view.findViewById(R.id.btn_Lab_factorFind);
		et_Lab_factorNum = (EditText) view.findViewById(R.id.et_Lab_factorNum);
		tv_Lab_factorResult = (TextView) view.findViewById(R.id.tv_Lab_factorResult);

		btn_Lab_factorFind.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String isEmpty = et_Lab_factorNum.getText().toString();
				if (!isEmpty.isEmpty()) {
					int num = Integer.valueOf(isEmpty);
					tv_Lab_factorResult.setText(new Factor().mainFactor(num));
				}else{
					Toast.makeText(getActivity(), "请输入要分解的数字", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		return view;
	}
}
