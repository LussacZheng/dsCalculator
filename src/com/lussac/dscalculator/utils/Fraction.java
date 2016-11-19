package com.lussac.dscalculator.utils;

import java.util.StringTokenizer;

import com.lussac.dscalculator.MainActivity;

import android.widget.*;

/**
 * This is the main calculator of this app!
 *
 * 分数四则运算 fraction_分数，numerator_分子，denominator_分母
 * Optimized by LussacZheng
 * Source code from : http://m.blog.csdn.net/article/details?id=12446717
 */
public class Fraction {
	int numerator;
	int denominator;

	public Fraction() { // 重载三种构造方法
	}

	public Fraction(String str) {
		if (str.contains(".")) {
			// if(str.matches("[-+]?[0-9]*")){
			String[] array = new String[2];
			array = str.split("\\.");
			int a = Integer.parseInt(array[0]);// 获取整数部分
			int b = Integer.parseInt(array[1]);// 获取小数部分
			int x = (int) (a * Math.pow(10, array[1].length()) + b);
			int y = (int) Math.pow(10, array[1].length());
			setFractionInLowestTerm(x, y);
		} else {
			setFractionInLowestTerm(Integer.parseInt(str), 1);
		}
	}

	Fraction(int a, int b) {
		if (a == 0) {
			numerator = 0;
			denominator = 1;
		} else {
			setFractionInLowestTerm(a, b);
		}
	}

	public int getNumerator() {
		return numerator;
	}

	public int getDenominator() {
		return denominator;
	}

	private void setFractionInLowestTerm(int a, int b) { // 获得最简分数的分子分母
		int c = getGreastCommonFactor(Math.abs(a), Math.abs(b));
		numerator = a / c;
		denominator = b / c;
		if (numerator < 0 && denominator < 0) {
			numerator = -numerator;
			denominator = -denominator;
		}
		if (a == 2147483647 | b == 2147483647)
			Toast.makeText(MainActivity.mainactivity, "当前结果异常!", Toast.LENGTH_LONG).show();
	}

	private int getGreastCommonFactor(int a, int b) { // 求最大公约数
		if (a < b) {
			int c = a;
			a = b;
			b = c;
		}
		int r = a % b;
		while (r != 0) {
			a = b;
			b = r;
			r = a % b;
		}
		return b;
	}

	private Fraction add(Fraction r) {
		int a = r.getNumerator();
		int b = r.getDenominator();
		int newNumerator = numerator * b + denominator * a;
		int newDenominator = denominator * b;
		Fraction result = new Fraction(newNumerator, newDenominator);
		return result;
	}

	private Fraction sub(Fraction r) {
		int a = r.getNumerator();
		int b = r.getDenominator();
		int newNumerator = numerator * b - denominator * a;
		int newDenominator = denominator * b;
		Fraction result = new Fraction(newNumerator, newDenominator);
		return result;
	}

	private Fraction multi(Fraction r) {
		int a = r.getNumerator();
		int b = r.getDenominator();
		int newNumerator = numerator * a;
		int newDenominator = denominator * b;
		Fraction result = new Fraction(newNumerator, newDenominator);
		return result;
	}

	private Fraction div(Fraction r) {
		int a = r.getNumerator();
		int b = r.getDenominator();
		int newNumerator = numerator * b;
		int newDenominator = denominator * a;
		Fraction result = new Fraction(newNumerator, newDenominator);
		return result;
	}

	/*
	static Fraction qiuyu(double left,double right){ // 求余运算
		double point_result = Math.IEEEremainder(left, right);
		int r1 = (int)point_result;
		double r2 = point_result - r1;
		int newNumerator = r1*10000 + (int)(r2*10000);
	    int newDenominator = 10000;
		Fraction result = new Fraction(newNumerator,newDenominator);
		return result;
	}

	static Fraction pow(double left,double right){ // 求幂运算left^right
		double point_result = Math.pow(left, right);
		int r1 = (int)point_result;
		double r2 = point_result - r1;
		int newNumerator = r1*10000 + (int)(r2*10000);
	    int newDenominator = 10000;
		Fraction result = new Fraction(newNumerator,newDenominator);
		return result;
	}

	static Fraction max(double left,double right){ // 求两数中的较大值
		double point_result = Math.min(left, right);
		int r1 = (int)point_result;
		double r2 = point_result - r1;
		int newNumerator = r1*10000 + (int)(r2*10000);
	    int newDenominator = 10000;
		Fraction result = new Fraction(newNumerator,newDenominator);
		return result;
	}

	static Fraction min(double left,double right){ // 求两数中的较小值
		double point_result = Math.min(left, right);
		int r1 = (int)point_result;
		double r2 = point_result - r1;
		int newNumerator = r1*10000 + (int)(r2*10000);
	    int newDenominator = 10000;
		Fraction result = new Fraction(newNumerator,newDenominator);
		return result;
	}
	*/
	
	// 封装了具体运算，主要为对输入进行转换，对输出封装
	public String compute(String data1, String operation, String data2) {
		if (data1.equals("0") || data1.matches("0*\\.0*"))
			data1 = "0/1";
		if (data2.equals("0") || data2.matches("0*\\.0*"))
			data2 = "0/1"; // useful when exp is "1+0","1-0","1*0","0+1","0-1","0*1","0/1" and so on
		if (operation.equals("/") && data2.equals("0/1"))
			return "ERROR_DIVIDED_BY_ZERO"; // when divided by zero

		Fraction r1 = new Fraction();
		Fraction r2 = new Fraction();
		Fraction result = new Fraction();
		if (data1.contains("/")) {
			StringTokenizer fenxi = new StringTokenizer(data1, "/");
			int data1_1 = Integer.parseInt(fenxi.nextToken());
			int data1_2 = Integer.parseInt(fenxi.nextToken());
			r1 = new Fraction(data1_1, data1_2);
		} else {
			r1 = new Fraction(data1);
		}
		if (data2.contains("/")) {
			StringTokenizer fenxi = new StringTokenizer(data2, "/");
			int data2_1 = Integer.parseInt(fenxi.nextToken());
			int data2_2 = Integer.parseInt(fenxi.nextToken());
			r2 = new Fraction(data2_1, data2_2);
		} else {
			r2 = new Fraction(data2);
		}

		switch (operation) {
		case "+":
			result = r1.add(r2);
			break;
		case "-":
			result = r1.sub(r2);
			break;
		case "*":
			result = r1.multi(r2);
			break;
		case "/":
			result = r1.div(r2);
			break;
		}
		String answer = result.getNumerator() + "/" + result.getDenominator();
		return answer;

		/*
		if(operation.equals("%")){ // 两数求余
			double left = (double)data1_1/(double)data1_2;
			double right = (double)data2_1/(double)data2_2;
			result = qiuyu(left,right);
			}
		if(operation.equals("^")){ // 两数求幂
			double left = (double)data1_1/(double)data1_2;
			double right = (double)data2_1/(double)data2_2;
			result = pow(left,right);
		}

		if(operation.equals("max")){ // 两数中的较大值
			double left = (double)data1_1/(double)data1_2;
			double right = (double)data2_1/(double)data2_2;
			result = max(left,right);
		}

		if(operation.equals("min")){ // 两数中的较小值
			double left = (double)data1_1/(double)data1_2;
			double right = (double)data2_1/(double)data2_2;
			result = min(left,right);
		}
		*/
		
	}
}
