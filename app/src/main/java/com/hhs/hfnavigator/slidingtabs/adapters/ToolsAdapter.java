package com.hhs.hfnavigator.slidingtabs.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hhs.hfnavigator.slidingtabs.resources.BellFragment;
import com.hhs.hfnavigator.slidingtabs.resources.MidRegFragment;
import com.hhs.hfnavigator.slidingtabs.tools.CastleFragment;
import com.hhs.hfnavigator.slidingtabs.tools.EdmodoFragment;
import com.hhs.hfnavigator.slidingtabs.tools.PortalFragment;


public class ToolsAdapter extends FragmentStatePagerAdapter {

    private final String[] titles = {"Portal Login", "Edmodo", "Castle Learning"};

    public ToolsAdapter(FragmentManager fm) {
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

                PortalFragment portalFragment = new PortalFragment();
                return portalFragment;

            case 1:

                EdmodoFragment edmodoFragment = new EdmodoFragment();
                return edmodoFragment;

            case 2:

                CastleFragment castleFragment = new CastleFragment();
                return castleFragment;


        }
        return null;
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
