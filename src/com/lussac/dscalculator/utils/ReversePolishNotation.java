package com.lussac.dscalculator.utils;

import java.util.*;

//use Reverse Polish Notation to compute, then get answer in the form of fraction

public class ReversePolishNotation {

	private ArrayList<String> toArraryList(String str) {
		ArrayList<String> result = new ArrayList<String>();
		String num = "";
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				num = num + str.charAt(i);
			} else {
				if (".".equals(String.valueOf((str.charAt(i))))) {
					num = num + ".";
				} else {
					if (num != "") {
						result.add(num);
					}
					if ("×".equals(String.valueOf((str.charAt(i))))) {
						result.add("*");
					} else if ("÷".equals(String.valueOf((str.charAt(i))))) {
						result.add("/");
					} else {
						result.add(str.charAt(i) + "");
					}
					num = "";
				}
			}
		}
		if (num != "") {
			result.add(num);
		}
		return result;
	}

	private ArrayList<String> toPostOrder(ArrayList<String> inOrderList) {

		ArrayList<String> result = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < inOrderList.size(); i++) {
			if (Character.isDigit(inOrderList.get(i).charAt(0))) {
				result.add(inOrderList.get(i));
			} else {
				switch (inOrderList.get(i).charAt(0)) {
				case '(':
					stack.push(inOrderList.get(i));
					break;
				case ')':
					while (!stack.peek().equals("(")) {
						result.add(stack.pop());
					}
					stack.pop();
					break;
				default:
					while (!stack.isEmpty() && compare(stack.peek(), inOrderList.get(i))) {
						result.add(stack.pop());
					}
					stack.push(inOrderList.get(i));
					break;
				}
			}
		}
		while (!stack.isEmpty()) {
			result.add(stack.pop());
		}
		return result;
	}

	private boolean compare(String peek, String cur) {
		if ("*".equals(peek) && ("/".equals(cur) || "*".equals(cur) || "+".equals(cur) || "-".equals(cur))) {
			return true;
		} else if ("/".equals(peek) && ("/".equals(cur) || "*".equals(cur) || "+".equals(cur) || "-".equals(cur))) {
			return true;
		} else if ("+".equals(peek) && ("+".equals(cur) || "-".equals(cur))) {
			return true;
		} else if ("-".equals(peek) && ("+".equals(cur) || "-".equals(cur))) {
			return true;
		}
		return false;
	}

	private String computeInside(ArrayList<String> postOrder) {
		// boolean isDividedByZero=false;
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < postOrder.size(); i++) {
			if (Character.isDigit(postOrder.get(i).charAt(0))) {
				stack.push(postOrder.get(i));
			} else {
				String back = stack.pop();
				String front = stack.pop();
				String res = new Fraction().compute(front, postOrder.get(i), back);
				if (res.equals("错误")) {
					return "错误";
				}
				stack.push(res);
			}
		}
		return stack.pop();
	}

	// 方法封装，返回 Fraction类_分数形式 的结果
	public String calculate(String exp) {
		ReversePolishNotation rpn = new ReversePolishNotation();
		ArrayList<String> arrayList = rpn.toArraryList(exp); // String转换为ArrayList
		ArrayList<String> result = rpn.toPostOrder(arrayList); // 中缀表达式变为后缀表达式
		return rpn.computeInside(result);
	}

}
