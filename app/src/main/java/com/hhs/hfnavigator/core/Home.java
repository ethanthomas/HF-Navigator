package com.hhs.hfnavigator.core;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.hhs.hfnavigator.R;
import com.hhs.hfnavigator.constants.Colors;
import com.hhs.hfnavigator.harbinger.LiveStreamFragment;
import com.hhs.hfnavigator.slidingtabs.adapters.HFAdapter;
import com.hhs.hfnavigator.slidingtabs.adapters.HomeAdapter;
import com.hhs.hfnavigator.slidingtabs.adapters.ResourcesAdapter;
import com.hhs.hfnavigator.slidingtabs.adapters.ToolsAdapter;
import com.hhs.hfnavigator.teacherdirectory.TeacherDirectoryFragment;

import at.markushi.ui.RevealColorView;
import me.drakeet.materialdialog.MaterialDialog;

public class Home extends ActionBarActivity {

    //Drawer objects
    DrawerLayout mDrawerLayout;
    ListView mLeftDrawer;
    ActionBarDrawerToggle mDrawerToggle;
    LinearLayout drawer;
    TextView title;
    CharSequence mDrawerTitle;
    CharSequence mTitle;
    String[] mFragmentTitles;

    //Stylized objects
    Toolbar toolbar;
    Window window;
    RevealColorView reveal;

    //Pager Tabs Objects
    ViewPager pager;
    PagerSlidingTabStrip tabs;

    //Pager Adapters
    HomeAdapter homeAdapter;
    ResourcesAdapter resourcesAdapter;
    ToolsAdapter toolsAdapter;
    HFAdapter hfAdapter;
    FrameLayout homeHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeViews();
        setupDrawer(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getAttributes().flags &= (~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.home, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawerLayout.isDrawerOpen(drawer)) {
                    mDrawerLayout.closeDrawer(drawer);
                } else {
                    mDrawerLayout.openDrawer(drawer);
                }
//            case R.id.spin:
//                ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
//                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                        float slideOffset = (Float) valueAnimator.getAnimatedValue();
//                        mDrawerToggle.onDrawerSlide(mDrawerLayout, slideOffset);
//                    }
//                });
//                anim.setInterpolator(new DecelerateInterpolator());
//                anim.setDuration(500);
//                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                anim.start();
                return true;
        }
        return true;
    }

    public void initializeViews() {
        toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        window = getWindow();

        mTitle = mDrawerTitle = getTitle();
        mFragmentTitles = getResources().getStringArray(R.array.fragments);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mLeftDrawer = (ListView) findViewById(R.id.left_drawer);
        drawer = (LinearLayout) findViewById(R.id.drawer);

        title = (TextView) findViewById(R.id.title);

        tabs = (PagerSlidingTabStrip) findViewById(R.id.pagerTabStrip);
        pager = (ViewPager) findViewById(R.id.viewPager);

        reveal = (RevealColorView) findViewById(R.id.reveal);

        homeHeader = (FrameLayout) findViewById(R.id.homeHeader);
    }

    public void setupDrawer(Bundle savedInstanceState) {
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);

        mLeftDrawer.setAdapter(new CustomAdapter(this, mFragmentTitles, new int[]{R.drawable.home, R.drawable.resources,
                R.drawable.tools, R.drawable.teacherdir, R.drawable.hf,
                R.drawable.play, R.drawable.menu_feedback, R.drawable.icon_light_info}));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mLeftDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
                ((CustomAdapter) parent.getAdapter()).selectItem(position);
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (!getSupportActionBar().isShowing())
                    getSupportActionBar().show();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if (savedInstanceState == null) {
            selectItem(0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                window.setStatusBarColor(getResources().getColor(R.color.grey_blue_950));
        }

    }

    private void selectItem(int position) {
        Fragment newFragment = null;
        FragmentManager fm = getSupportFragmentManager();

        homeAdapter = new HomeAdapter(getSupportFragmentManager());
        resourcesAdapter = new ResourcesAdapter(getSupportFragmentManager());
        toolsAdapter = new ToolsAdapter(getSupportFragmentManager());
        hfAdapter = new HFAdapter(getSupportFragmentManager());

        final int pageMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                        .getDisplayMetrics()
        );
        pager.setPageMargin(pageMargin);

        final int cx = (tabs.getLeft() + tabs.getRight()) / 2;
        final int cy = isTablet() ? calculateHeightInDp(141) : calculateHeightInDp(130);

        pager.setOffscreenPageLimit(3);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        switch (position) {
            case 0:
                pager.setVisibility(View.VISIBLE);
                tabs.setVisibility(View.VISIBLE);
                homeHeader.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, cy));

                pager.setAdapter(homeAdapter);
                tabs.setViewPager(pager);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reveal.reveal(cx, cy, getColor(R.color.grey_blue_800), 0, 340, null);

                    }
                }, 300);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            window.setStatusBarColor(getColor(R.color.grey_blue_950));
                        }
                    }, 400);
                break;
            case 1:
                if (pager.getVisibility() != View.VISIBLE || tabs.getVisibility() != View.VISIBLE) {
                    pager.setVisibility(View.VISIBLE);
                    tabs.setVisibility(View.VISIBLE);
                    homeHeader.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, cy));
                }
                pager.setAdapter(resourcesAdapter);
                tabs.setViewPager(pager);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reveal.reveal(cx, cy, getColor(R.color.blue_800), 0, 340, null);
                    }
                }, 300);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            window.setStatusBarColor(getColor(R.color.blue_950));
                        }
                    }, 400);
                break;
            case 2:
                if (pager.getVisibility() != View.VISIBLE || tabs.getVisibility() != View.VISIBLE) {
                    pager.setVisibility(View.VISIBLE);
                    tabs.setVisibility(View.VISIBLE);
                    homeHeader.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, cy));
                }
                pager.setAdapter(toolsAdapter);
                tabs.setViewPager(pager);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reveal.reveal(cx, cy, getColor(R.color.red_800), 0, 340, null);
                    }
                }, 300);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            window.setStatusBarColor(getColor(R.color.red_950));
                        }
                    }, 400);
                break;
            case 3:
                newFragment = new TeacherDirectoryFragment();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reveal.reveal(cx, cy, getColor(R.color.orange_800), 0, 340, null);
                    }
                }, 300);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            window.setStatusBarColor(getColor(R.color.orange_950));
                        }
                    }, 400);
                break;
            case 4:
                if (pager.getVisibility() != View.VISIBLE || tabs.getVisibility() != View.VISIBLE) {
                    pager.setVisibility(View.VISIBLE);
                    tabs.setVisibility(View.VISIBLE);
                    homeHeader.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, cy));
                }
                pager.setAdapter(hfAdapter);
                tabs.setViewPager(pager);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reveal.reveal(cx, cy, getColor(R.color.green_800), 0, 340, null);
                    }
                }, 300);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            window.setStatusBarColor(getColor(R.color.green_950));
                        }
                    }, 400);

                break;
            case 5:
                newFragment = new LiveStreamFragment();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reveal.reveal(cx, cy, getColor(R.color.dark_blue_800), 0, 340, null);
                    }
                }, 300);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            window.setStatusBarColor(getColor(R.color.dark_blue_950));
                        }
                    }, 400);
                break;
//            case 6:
//                newFragment = new HarbingerNewsFragment();
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        reveal.reveal(cx, cy, getColor(R.color.green_800), 0, 340, null);
//                    }
//                }, 300);
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            window.setStatusBarColor(getColor(R.color.green_950));
//                        }
//                    }, 400);
//                break;
            case 6:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:ethanthomas33@gmail.com" + "?subject=HF Navigator - Feedback" + "&body=");
                intent.setData(data);
                startActivity(intent);
                break;
            case 7:
                MaterialDialog dialog = new MaterialDialog(this);
                View view = getLayoutInflater().inflate(R.layout.dialog_about, null);
                dialog.setContentView(view);
                dialog.setCanceledOnTouchOutside(true);

                Button website = (Button) view.findViewById(R.id.website);
                Button gplus = (Button) view.findViewById(R.id.gplus);
                Button github = (Button) view.findViewById(R.id.github);

                website.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        goToUrl("http://ethanthomas.me");
                    }
                });

                gplus.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        goToUrl("https://plus.google.com/u/0/+EthanThomas33/posts");
                    }
                });

                github.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        goToUrl("https://github.com/ethanthomas");
                    }
                });
                dialog.show();
                break;
        }

        if (!(position == 6 || position == 7)) {
            if (newFragment != null) {
                findViewById(R.id.content_frame).setVisibility(View.VISIBLE);
                fm.beginTransaction().replace(R.id.content_frame, newFragment).commit();
                pager.setAdapter(null);
                tabs.setVisibility(View.GONE);
                pager.setVisibility(View.GONE);
                homeHeader.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        getSupportActionBar().getHeight()));
            } else {
                fm.beginTransaction().replace(R.id.content_frame, new LiveStreamFragment()).commit();
                findViewById(R.id.content_frame).setVisibility(View.GONE);
            }
            mLeftDrawer.setItemChecked(position, true);
            setTitle(mFragmentTitles[position]);
            mDrawerLayout.closeDrawer(drawer);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public class CustomAdapter extends BaseAdapter {

        Context context;
        String[] mTitle;
        int[] mIcon;
        LayoutInflater inflater;
        private int selectedItem;

        public CustomAdapter(Context context, String[] title, int[] icon) {
            this.context = context;
            this.mTitle = title;
            this.mIcon = icon;
        }

        public void selectItem(int selectedItem) {
            if (selectedItem != 6 && selectedItem != 7) {
                this.selectedItem = selectedItem;
                notifyDataSetChanged();
            }
        }

        @Override
        public int getCount() {
            return mTitle.length;
        }

        @Override
        public Object getItem(int position) {
            return mTitle[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            TextView txtTitle;
            ImageView imgIcon;
            View v;

            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.list_item_drawer, parent, false);

            txtTitle = (TextView) itemView.findViewById(R.id.drawerItemText);

            v = itemView.findViewById(R.id.view);

            imgIcon = (ImageView) itemView.findViewById(R.id.drawerItemImage);

            txtTitle.setText(mTitle[position]);

            imgIcon.setImageResource(mIcon[position]);

            if (position == 5)
                v.setVisibility(View.VISIBLE);

            if (position == 6 || position == 7) {
                txtTitle.setTypeface(null, Typeface.BOLD);
                txtTitle.setTextColor(Color.parseColor("#626D6D"));
            } else if (position == 5) {
                txtTitle.setTypeface(null, position == selectedItem ? Typeface.BOLD : Typeface.NORMAL);
                txtTitle.setTextColor(position == selectedItem ? Color.parseColor("#336699") : Color.parseColor("#4C5858"));
                imgIcon.setColorFilter(position == selectedItem ? Color.parseColor("#336699") : android.R.color.transparent);
            } else if (position == 4) {
                txtTitle.setTypeface(null, position == selectedItem ? Typeface.BOLD : Typeface.NORMAL);
                txtTitle.setTextColor(position == selectedItem ? Color.parseColor("#4AB86E") : Color.parseColor("#4C5858"));
                imgIcon.setColorFilter(position == selectedItem ? Color.parseColor("#4AB86E") : android.R.color.transparent);
            } else if (position == 3) {
                txtTitle.setTypeface(null, position == selectedItem ? Typeface.BOLD : Typeface.NORMAL);
                txtTitle.setTextColor(position == selectedItem ? Colors.materialColors[4] : Color.parseColor("#4C5858"));
                imgIcon.setColorFilter(position == selectedItem ? Colors.materialColors[4] : android.R.color.transparent);
            } else if (position == 2) {
                txtTitle.setTypeface(null, position == selectedItem ? Typeface.BOLD : Typeface.NORMAL);
                txtTitle.setTextColor(position == selectedItem ? Color.parseColor("#E64545") : Color.parseColor("#4C5858"));
                imgIcon.setColorFilter(position == selectedItem ? Color.parseColor("#E64545") : android.R.color.transparent);
            } else if (position == 1) {
                txtTitle.setTypeface(null, position == selectedItem ? Typeface.BOLD : Typeface.NORMAL);
                txtTitle.setTextColor(position == selectedItem ? Color.parseColor("#3399FF") : Color.parseColor("#4C5858"));
                imgIcon.setColorFilter(position == selectedItem ? Color.parseColor("#3399FF") : android.R.color.transparent);
            } else {
                txtTitle.setTypeface(null, position == selectedItem ? Typeface.BOLD : Typeface.NORMAL);
                txtTitle.setTextColor(position == selectedItem ? getResources().getColor(R.color.grey_blue_800) : Color.parseColor("#4C5858"));
                imgIcon.setColorFilter(position == selectedItem ? getResources().getColor(R.color.grey_blue_800) : android.R.color.transparent);
            }
            return itemView;
        }
    }

    private Point getLocationInView(View src, View target) {
        final int[] l0 = new int[2];
        src.getLocationOnScreen(l0);

        final int[] l1 = new int[2];
        target.getLocationOnScreen(l1);

        l1[0] = l1[0] - l0[0] + target.getWidth() / 2;
        l1[1] = l1[1] - l0[1] + target.getHeight();

        return new Point(l1[0], l1[1]);
    }

    public int calculateHeightInDp(int height) {
        final float scale = getResources().getDisplayMetrics().density;
        int hdp = (int) (height * scale + 0.5f);
        return hdp;
    }

    public boolean isTablet() {
        return (getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    private void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    public int getColor(int id) {
        return getResources().getColor(id);
    }
}