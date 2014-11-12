package com.android.demo.adapter;

import java.util.ArrayList;
import java.util.List;

import com.android.demo.fragment.MyFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MPagerAdapter extends FragmentStatePagerAdapter{

	private List<Fragment> list = new ArrayList<Fragment>();
	
	public MPagerAdapter(FragmentManager fm) {
		super(fm);
		
		for(int i=0; i<3; i++){
			MyFragment fragment = new MyFragment();
			list.add(fragment);
		}
	}

	@Override
	public Fragment getItem(int position) {
		return list.get(position);
	}

	@Override
	public int getCount() {
		return list.size();
	}

}
