package com.lussac.dscalculator.utils;

import java.util.Random;

public class RandomNumber {

	public int getRandomInt(int a, int b){
		return new Random().nextInt(b - a + 1) + a;
	}
	public int[] getRandomIntArray(int a, int b, int length){
		int[] arr = new int[length];
		for(int i=0;i<length;i++){
			arr[i]=getRandomInt(a, b);
		}
		return arr;
	}
}
