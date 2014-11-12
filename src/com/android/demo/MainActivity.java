package com.android.demo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.android.demo.adapter.MPagerAdapter;

public class MainActivity extends FragmentActivity {

	ViewPager mViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initUI();
	}

	private void initUI() {
		mViewPager = (ViewPager) findViewById(R.id.viewpage);
		mViewPager.setAdapter(new MPagerAdapter(getSupportFragmentManager()));
	}

}
