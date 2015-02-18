package com.hhs.hfnavigator.slidingtabs.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hhs.hfnavigator.slidingtabs.home.BusFragment;
import com.hhs.hfnavigator.slidingtabs.home.NotificationFragment;
import com.hhs.hfnavigator.slidingtabs.home.PollsFragment;

public class HomeAdapter extends FragmentStatePagerAdapter {

    private final String[] titles = {"Notifications", "Bus Finder", "Polls"};

    public HomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                NotificationFragment tab1 = new NotificationFragment();
                return tab1;

            case 1:
                BusFragment tab2 = new BusFragment();
                return tab2;

            case 2:
                PollsFragment tab3 = new PollsFragment();
                return tab3;

        }
        return null;
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}