package com.lussac.dscalculator;

import android.app.*;
import android.os.*;

class AboutDialogFragment extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.app_version);
		builder.setMessage("应用介绍:\n\n"
				+ "1. 此计算器对四则运算表达式采用 逆波兰式(Reverse Polish notation) 算法。同时为了保证结果的精确度，对所有数字进行分数化处理。故当 小数部分过长整数过大 或 小数部分过长 时，可能出现结果异常的情况。\n\n"
				+ "2. 请勿将此计算器用于精确度要求较高的计算。\n\n"
				+ "3. 功能实验室中集成了一些简(wu)单(liao)实(ji)用(lei)的小功能，点击 \"帮助\" 查看如何使用及注意事项。\n\n"
				+ "4. 此应用程序仅供学习交流，请勿作其它用途。\n"
				+ "作者：Lussac Zheng\n" + "邮箱：lussaclussac@163.com\n\n\n"
				+ "(PS：重复造轮子是个力气活)");
		return builder.create();
	}
}

class LabPrimeHelpDialogFragment extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.Lab_prime);
		builder.setMessage("1. 输入区间[a,b]，点击查找按钮开始计算（区间左端点默认为0，可修改）；\n\n"
				+ "2. 勾选后可枚举区间内所有质数，取消勾选可加快计算速度；\n\n"
				+ "3. 区间长度过大时计算可能需要一定时间，甚至有可能导致程序卡死。可尝试取消勾选或分割成多个小区间分别计算。\n\n"
				+ "4. 程序原理:埃拉托斯特尼筛法\n(sieve of Eratosthenes)");
		return builder.create();
	}
}

class LabFactorHelpDialogFragment extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.Lab_factor);
		builder.setMessage("1. 输入数字，点击分解按钮开始计算。\n\n"
				+ "2. 由于系统限制，当输入的数字大于 2147483647 时，会导致程序崩溃。（尽管我知道解释了你反而会去试）  눈_눈");
		return builder.create();
	}
}

class LabRandomHelpDialogFragment extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.Lab_random);
		builder.setMessage("输入区间[a,b]，点击按钮随机生成一个区间内的整数（区间左端点默认为1，可修改）。");
		return builder.create();
	}
}

class AboutUpdateDialogFragment extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.aboutUpdate);
		builder.setMessage("--------------- v 0.3.2 ---------------\n"
				+ "1. 实现了功能实验室的Tab界面\n"
				+ "2. 其他一些微小改动\n"
				+ "\n--------------- v 0.3.1 ---------------\n"
				+ "1. 实现了屏幕自适应；\n"
				+ "2. 添加了侧滑菜单，并实现划动手势响应；\n"
				+ "3. 功能实验室增加了随机整数生成器；\n"
				+ "4. 添加了C和del按钮的长按功能（点击帮助查看）；\n"
				+ "5. 采用正则表达式限制并处理算式的某些不合法输入、进行异常判断。并支持自动补全，如能够将\n"
				+ "\".1+.2(.3-.).4×.(5.÷.6).\"补全为:\"0.1+0.2×(0.3-0.0)×0.4×0.0×(5.0÷0.6)×0.0\"；\n"
				+ "6. 解决了某些已知关于小数的bug；\n"
				+ "7. 实现了顶部算式过长时的简单跑马灯效果。");
		return builder.create();
	}
}

class HelpDialogFragment extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.help);
		builder.setMessage("1. 长按C可恢复意外清除的算式；\n"
				+ "2. 右划展开侧边菜单栏，左划快速切换至功能实验室；\n"
				+ "3. 侧边菜单栏长按快速切换至对应功能界面。");
		return builder.create();
	}
}
