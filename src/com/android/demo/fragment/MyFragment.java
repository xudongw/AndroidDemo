package com.android.demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.demo.R;
import com.android.demo.utils.Logutils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MyFragment extends Fragment{
	
	public static final String TAG = "MyFragment";
	
	private boolean init = true;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view  = inflater.inflate(R.layout.my_fragment, null, false);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		
		if(isVisibleToUser && init){
			initData();
		}
	}
	
	private void initData(){
		System.out.println(TAG + " initData");
		doNetworkForData();
		init = false;
	}
	
	private void doNetworkForData(){
		AsyncHttpClient client = new AsyncHttpClient();
		client.setTimeout(10000);
		client.get("http://www.baidu.com", new TestAsyncResponse());
	}
	
	class TestAsyncResponse extends AsyncHttpResponseHandler{
		
		@Override
		public void onSuccess(int arg0, String arg1) {
			Logutils.logi(TAG, arg1);
		}
		
		@Override
		public void onFailure(Throwable arg0, String arg1) {
			Logutils.loge(TAG, arg1);
		}
		
		@Override
		public void onFinish() {
			super.onFinish();
			Logutils.logi(TAG, "onFinish");
		}
	}

}
