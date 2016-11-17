package com.lussac.dscalculator.utils;

import java.util.*;
import android.widget.*;

//int  -2147483647~2147483647
//long -9233372036854477808~9233372036854477808
public class Factor {

	public String mainFactor(int num) {
		if (num > 1 && num != 2147483647) {
			String result = num + " = ";
			if (!isPrime(num)) {
				ArrayList<Integer> factorList = getFactorList(num);
				ArrayList<Integer> primeFactorList = getPrimeFactorList(factorList);
				ArrayList<Integer> primeFactorPower = getPrimeFactorPower(primeFactorList, num);
				for (int x = 0; x < primeFactorList.size(); x++) {
					int isOne = primeFactorPower.get(x);
					if (isOne == 1) {
						result = result + primeFactorList.get(x) + " x ";
					} else {
						result = result + primeFactorList.get(x) + "^" + isOne + " x ";
					}
				}
				result = new StringBuilder(result).delete((result.length() - 3), result.length()).toString();
				return result;
			} else {
				return num + "������,���ܽ�����ʽ�ֽ�\n�������˰�";
			}
		} else if (num == 2147483647) {
			return "�����˿����������ϵ�����������\n�������ˣ�2147483647�Ǹ�������\n�������Լ��㰡";
		} else {
			return "��" + num + "�㻹Ҫ�ֽ⣬�����˿";
		}
	}

	private boolean isPrime(int a) {
		for (int i = 2; i < a; i++) {
			if (a % i == 0) {
				return false;
			}
		}
		return true;
	}

	private ArrayList<Integer> getFactorList(int num) {
		ArrayList<Integer> factorList = new ArrayList<Integer>();
		for (int i = 2; i <= num; i++) {
			if (num % i == 0) {
				factorList.add(i);
			}
		}
		return factorList;
	}

	private ArrayList<Integer> getPrimeFactorList(ArrayList<Integer> factorList) {
		ArrayList<Integer> primeFactorList = new ArrayList<Integer>();
		for (int i = 0; i < factorList.size(); i++) {
			if (isPrime(factorList.get(i))) {
				primeFactorList.add(factorList.get(i));
			}
		}
		return primeFactorList;
	}

	private ArrayList<Integer> getPrimeFactorPower(ArrayList<Integer> primeFactorList, int num) {
		ArrayList<Integer> primeFactorPower = new ArrayList<Integer>();
		for (int i = 0; i < primeFactorList.size(); i++) {
			int power = getPower(num, primeFactorList.get(i));
			primeFactorPower.add(power);
		}
		return primeFactorPower;
	}

	private int getPower(int num, int factor) {
		int powerCount = 0, temp = num;
		while (temp % factor == 0) {
			temp = temp / factor;
			powerCount++;
		}
		return powerCount;
	}

}
