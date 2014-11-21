package com.android.demo;

import com.android.demo.event.domain.Exit;
import com.android.demo.utils.Logutils;

import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EventBusTwoActivity extends Activity{
	
	private static final String TAG = "EventBusTwoActivity";
	private Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_bus_layout);
		
		EventBus.getDefault().register(this);
		
		btn = (Button) findViewById(R.id.btn1);
		btn.setText("exit");
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				EventBus.getDefault().post(new Exit());
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
