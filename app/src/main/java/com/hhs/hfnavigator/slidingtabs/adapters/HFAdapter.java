package com.hhs.hfnavigator.slidingtabs.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hhs.hfnavigator.slidingtabs.hhs.AboutFragment;
import com.hhs.hfnavigator.slidingtabs.hhs.CalendarFragment;
import com.hhs.hfnavigator.slidingtabs.hhs.LibFragment;


public class HFAdapter extends FragmentStatePagerAdapter {

    private final String[] titles = {"Calendar", "Library", "About"};

    public HFAdapter(FragmentManager fm) {
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
                CalendarFragment calendarFragment = new CalendarFragment();
                return calendarFragment;
            case 1:
                LibFragment libFragment = new LibFragment();
                return libFragment;
            case 2:
                AboutFragment aboutFragment = new AboutFragment();
                return aboutFragment;
        }
        return null;
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
