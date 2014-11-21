package com.android.demo.utils;

import android.util.Log;

public class Logutils {
	
	public static final boolean DEBUG = true;
	
	public static void logi(String tag, String msg){
		if(DEBUG){
			Log.i(tag, msg);
		}
	}
	
	public static void loge(String tag, String msg){
		if(DEBUG){
			Log.e(tag, msg);
		}
	}
}
