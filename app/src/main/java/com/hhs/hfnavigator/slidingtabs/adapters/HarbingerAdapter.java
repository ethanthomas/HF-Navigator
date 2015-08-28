package com.hhs.hfnavigator.slidingtabs.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hhs.hfnavigator.slidingtabs.harbinger.LiveStreamFragment;
import com.hhs.hfnavigator.slidingtabs.harbinger.SportsFragment;


public class HarbingerAdapter extends FragmentStatePagerAdapter {

    private final String[] titles = {"Live Stream", "Sports"};

    public HarbingerAdapter(FragmentManager fm) {
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
                return new LiveStreamFragment();
            case 1:
                return new SportsFragment();
        }
        return null;
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
