

package com.hhs.hfnavigator.slidingtabs;

import java.lang.reflect.Field;
import com.actionbarsherlock.app.SherlockFragment;
import com.hhs.hfnavigator.R;
import com.hhs.hfnavigator.slidingtabs.fragments.HomeFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InitialNesterFragment extends SherlockFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_viewpager, container, false);
		ViewPager mViewPager = (ViewPager) v.findViewById(R.id.viewPager);
		mViewPager.setAdapter(new ViewPagerAdapter(getFragmentManager()));

		return v;
		
	}
	

	  
	@Override
	public void onDetach() {
		super.onDetach();
		try {
			Field childFragmentManager = Fragment.class
					.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
}