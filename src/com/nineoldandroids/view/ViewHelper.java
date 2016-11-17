package com.nineoldandroids.view;

import android.view.View;

import static com.nineoldandroids.view.animation.AnimatorProxy.NEEDS_PROXY;
import static com.nineoldandroids.view.animation.AnimatorProxy.wrap;

public final class ViewHelper {

	private ViewHelper() {
	}

	public static void setTranslationX(View view, float translationX) {
		if (NEEDS_PROXY) {
			wrap(view).setTranslationX(translationX);
		} else {
			Honeycomb.setTranslationX(view, translationX);
		}
	}

	private static final class Honeycomb {

		static void setTranslationX(View view, float translationX) {
			view.setTranslationX(translationX);
		}
	}

}
