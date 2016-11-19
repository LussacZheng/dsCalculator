package com.lussac.dscalculator.utils;

import android.widget.TextView;

public class Prime {
	
	private int[] arr;
	private int count = 0,a ,b;
	private String str = "";
	
	public Prime(int a, int b){
		this.a=a;
		this.b=b;
		this.arr = new int[b + 1];
		for (int i = 2; i < arr.length; i++) {
			arr[i] = i;
		}
	}

	public String listPrime(){
		for (int i = 2; i <= b; i++) {
			if (arr[i] != 0) {
				int prime = arr[i];
				for (int j = 2 * prime; j <= b; j = j + prime) {
					arr[j] = 0;
				}
				if (arr[i] >= a) {
					str = str + arr[i] + ",";
					count++;
				}
			}
		}
		str = new StringBuilder(str).replace(str.length() - 1, str.length(), ".").toString();
		return str;
	}
	
	public int countPrime(){
		for (int i = 2; i <= b; i++) {
			if (arr[i] != 0) {
				int prime = arr[i];
				for (int j = 2 * prime; j <= b; j = j + prime) {
					arr[j] = 0;
				}
				if (arr[i] >= a) {
					count++;
				}
			}
		}
		return count;
	}
	
//	public int getPrimeCount(){
//		return count;
//	}
	
	public String mainPrime(TextView tv_result, boolean ifList) {
		if (ifList)
			listPrime();
		else
			countPrime();
		tv_result.setText("[" + a + "," + b + "] 内共有" + count + "个质数\n不信你自己数啊");
		return str;
	}

}
