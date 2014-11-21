package com.android.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.demo.event.domain.Exit;
import com.android.demo.utils.Logutils;

import de.greenrobot.event.EventBus;

public class EventBusActivity extends Activity{

	private static final String TAG = "EventBusActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_bus_layout);
		
		EventBus.getDefault().register(this);
		
		findViewById(R.id.btn1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(EventBusActivity.this, EventBusTwoActivity.class));
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
		Logutils.loge(TAG, "onDestroy");
	}
	
	public void onEventMainThread(Exit exit) {
		finish();
	}
}
