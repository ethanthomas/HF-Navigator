package com.hhs.hfnavigator.core;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hhs.hfnavigator.R;
import com.hhs.hfnavigator.harbinger.HarbingerFragment;
import com.hhs.hfnavigator.slidingtabs.hhs.HHSNesterFragment;
import com.hhs.hfnavigator.slidingtabs.home.HomeNesterFragment;
import com.hhs.hfnavigator.slidingtabs.resources.ResNesterFragment;
import com.hhs.hfnavigator.slidingtabs.tools.ToolsNesterFragment;
import com.hhs.hfnavigator.teacherdirectory.TeacherList;

public class Home extends FragmentActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mLeftDrawer;
    private ActionBarDrawerToggle mDrawerToggle;

    LinearLayout drawer;

    TextView title;
    ImageButton drawerButton;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mFragmentTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        getActionBar().hide();

        // --------------------------------Nav Drawer--------------------------------------------------------------

        mTitle = mDrawerTitle = getTitle();
        mFragmentTitles = getResources().getStringArray(R.array.fragments);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mLeftDrawer = (ListView) findViewById(R.id.left_drawer);
        drawer = (LinearLayout) findViewById(R.id.drawer);

//        settings = (LinearLayout) findViewById(R.id.dr_settings);
//        help = (LinearLayout) findViewById(R.id.dr_help);

        title = (TextView) findViewById(R.id.title);
        drawerButton = (ImageButton) findViewById(R.id.drawerButton);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);
        mLeftDrawer.setAdapter(new CustomAdapter(this, mFragmentTitles, new int[]{R.drawable.home, R.drawable.resources,
                R.drawable.tools, R.drawable.teacherdir, R.drawable.hf, R.drawable.play, R.drawable.menu_feedback, R.drawable.icon_light_info}));

//
//        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                switch (position) {
//
//                    case 0:
//
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        Uri data = Uri.parse("mailto:obsidiandevelopers@gmail.com" + "?subject=HF Navigator - Feedback" + "&body=");
//                        intent.setData(data);
//                        startActivity(intent);
//
//                        break;
//
//                    case 1:
//
//                        Intent i = new Intent(getApplicationContext(),
//                                AboutDeveloper.class);
//                        startActivity(i);
//
//                        break;
//                }
//            }
//        });

        mLeftDrawer.setOnItemClickListener(new DrawerItemClickListener());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setIcon(R.drawable.ic_ab_drawer_mask);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.invisible, R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View v) {
                title.setText(mTitle);
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View v) {
//                title.setText(mDrawerTitle);
                supportInvalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if (savedInstanceState == null) {
            selectItem(0);
        }

        drawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mDrawerLayout.isDrawerOpen(drawer)) {
                    mDrawerLayout.closeDrawer(drawer);
                } else {
                    mDrawerLayout.openDrawer(drawer);
                }
            }
        });

//        settings.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
////                Intent i = new Intent(getApplicationContext(),
////                        AppPreferences.class);
////                startActivity(i);
//
//            }
//        });

//        help.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
////                Intent i = new Intent(getApplicationContext(),
////                        AppPreferences.class);
////                startActivity(i);
//
//            }
//        });


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(drawer);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawerLayout.isDrawerOpen(drawer)) {
                    mDrawerLayout.closeDrawer(drawer);
                } else {
                    mDrawerLayout.openDrawer(drawer);
                }
                return true;

        }

        return true;
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position,
                                long id) {
            selectItem(position);
            ((CustomAdapter) parent.getAdapter()).selectItem(position);


        }
    }

    private void selectItem(int position) {
        Fragment newFragment = new HomeNesterFragment();
        FragmentManager fm = getSupportFragmentManager();
        switch (position) {

            case 0:
                newFragment = new HomeNesterFragment();
                break;

            case 1:
                newFragment = new ResNesterFragment();
                break;

            case 2:
                newFragment = new ToolsNesterFragment();
                break;

            case 3:
                newFragment = new TeacherList();

//                Intent i = new Intent(getApplicationContext(), TeacherList.class);
//                startActivity(i);
                break;

            case 4:

                newFragment = new HHSNesterFragment();
//                Intent i = new Intent(getApplicationContext(), LiveStream.class);
//                startActivity(i);
                break;

            case 5:
                newFragment = new HarbingerFragment();

//                Intent i = new Intent(getApplicationContext(), LiveStream.class);
//                startActivity(i);

                break;

            case 6:

                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:obsidiandevelopers@gmail.com" + "?subject=HF Navigator - Feedback" + "&body=");
                intent.setData(data);
                startActivity(intent);

                break;

            case 7:

                Intent i = new Intent(getApplicationContext(),
                        AboutDeveloper.class);
                startActivity(i);

                break;


        }

        if (position == 6 || position == 7) {


        } else {

            fm.beginTransaction().replace(R.id.content_frame, newFragment).commit();

            mLeftDrawer.setItemChecked(position, true);
            setTitle(mFragmentTitles[position]);
            mDrawerLayout.closeDrawer(drawer);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(title);
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


//    public class MyArrayAdapter extends ArrayAdapter<String> {
//
//        private int selectedItem;
//
//        public MyArrayAdapter(Context context, int resource, String[] objects) {
//            super(context, resource, objects);
//        }
//
//        public void selectItem(int selectedItem) {
//            this.selectedItem = selectedItem;
//            notifyDataSetChanged();
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            convertView = super.getView(position, convertView, parent);
//            ((TextView) convertView).setTextColor(
//                    position == selectedItem ? Color.parseColor("#039409") : Color.parseColor("#013004"));
//
//            ((TextView) convertView).setTypeface(null, position == selectedItem ? Typeface.BOLD : Typeface.NORMAL);
//
//
//
//            return convertView;
//        }
//    }


    public class CustomAdapter extends BaseAdapter {

        // Declare Variables
        Context context;
        String[] mTitle;
        int[] mIcon;
        LayoutInflater inflater;
        private int selectedItem;


        public CustomAdapter(Context context, String[] title,
                             int[] icon) {
            this.context = context;
            this.mTitle = title;
            this.mIcon = icon;
        }

        public void selectItem(int selectedItem) {
            this.selectedItem = selectedItem;
            notifyDataSetChanged();
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
            // Declare Variables
            TextView txtTitle;
            ImageView imgIcon;
            View v;


            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.new_drawer_list_item, parent,
                    false);

            // Locate the TextViews in drawer_list_item.xml
            txtTitle = (TextView) itemView.findViewById(R.id.drawerItemText);

            v = (View) itemView.findViewById(R.id.view);
            // Locate the ImageView in drawer_list_item.xml
            imgIcon = (ImageView) itemView.findViewById(R.id.drawerItemImage);

            // Set the results into TextViews
            txtTitle.setText(mTitle[position]);

            // Set the results into ImageView
            imgIcon.setImageResource(mIcon[position]);


            if (position == 5) {


                v.setVisibility(View.VISIBLE);


            }

            if (position == 6 || position == 7) {


            } else {

                txtTitle.setTextColor(
                        position == selectedItem ? Color.parseColor("#00d96b") : Color.parseColor("#777777"));

                txtTitle.setTypeface(null, position == selectedItem ? Typeface.BOLD : Typeface.NORMAL);


                imgIcon.setColorFilter(position == selectedItem ? Color.parseColor("#00d96b") : Color.parseColor("#777777"));

            }
            return itemView;
        }

    }


}