package com.hhs.hfnavigator.slidingtabs.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hhs.hfnavigator.slidingtabs.resources.BellFragment;
import com.hhs.hfnavigator.slidingtabs.resources.MidRegFragment;


public class ResourcesAdapter extends FragmentStatePagerAdapter {

    private final String[] titles = {"Bell Schedule", "Final Exam Schedule"};

    public ResourcesAdapter(FragmentManager fm) {
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
                BellFragment bellFragment = new BellFragment();
                return bellFragment;
            case 1:
                MidRegFragment midRegFragment = new MidRegFragment();
                return midRegFragment;
        }
        return null;
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
