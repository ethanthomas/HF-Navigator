package com.hhs.hfnavigator.slidingtabs.hhs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.hhs.hfnavigator.R;
import com.hhs.hfnavigator.slidingtabs.home.BusFragment;
import com.hhs.hfnavigator.slidingtabs.home.NotificationFragment;

import java.lang.reflect.Field;

public class HHSNesterFragment extends Fragment {

    public static ViewPager pager;
    final int PAGE_COUNT = 3;
    private PagerSlidingTabStrip tabs;
    private MyPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_viewpager, container, false);

        tabs = (PagerSlidingTabStrip) v.findViewById(R.id.pagerTabStrip);
        pager = (ViewPager) v.findViewById(R.id.viewPager);
        adapter = new MyPagerAdapter(getFragmentManager());

        pager.setAdapter(adapter);


        final int pageMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                        .getDisplayMetrics()
        );
        pager.setPageMargin(pageMargin);

        tabs.setViewPager(pager);

        pager.setOffscreenPageLimit(9);

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


    public class MyPagerAdapter extends FragmentStatePagerAdapter {

        private final String[] titles = {"Calendar", "Library", "About"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }


        @Override
        public int getCount() {
            return PAGE_COUNT;
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


}