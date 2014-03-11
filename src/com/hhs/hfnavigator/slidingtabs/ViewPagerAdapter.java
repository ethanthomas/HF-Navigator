package com.hhs.hfnavigator.slidingtabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hhs.hfnavigator.slidingtabs.fragments.HomeFragment;
import com.hhs.hfnavigator.slidingtabs.fragments.LinksFragment;
import com.hhs.hfnavigator.slidingtabs.fragments.NotificationFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

	// Declare the number of ViewPager pages
	final int PAGE_COUNT = 3;
	private String titles[] = new String[] { "Home", "Notifications", "Teacher Links" };

	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	
	@Override
	public Fragment getItem(int position) {
		switch (position) {

		case 0:
			HomeFragment tab1 = new HomeFragment();
			return tab1;

		case 1:
			NotificationFragment tab2 = new NotificationFragment();
			return tab2;
			
		case 2:
			LinksFragment tab3 = new LinksFragment();
			return tab3;
			
		}
		return null;
	}

	public CharSequence getPageTitle(int position) {
		return titles[position];
	}

	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

}