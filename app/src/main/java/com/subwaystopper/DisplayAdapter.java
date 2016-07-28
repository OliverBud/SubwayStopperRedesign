package com.subwaystopper;	

import java.util.List;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

public class DisplayAdapter extends FragmentPagerAdapter{


	private List<DisplayFragment> fragments;
	
	

	public DisplayAdapter(android.support.v4.app.FragmentManager fragmentManager,
			List<DisplayFragment> fragments) {
		super(fragmentManager);
		this.fragments = fragments;
	}


	@Override
	public Fragment getItem(int position) {
		return this.fragments.get(position);
	}


	@Override
	public int getCount() {
		return this.fragments.size();
	}
	
	@Override
	public float getPageWidth(int position){
		float width = (float) 1;
		return width;
	}
}

