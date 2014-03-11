/*License

THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS 
OF THIS CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). 
THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW. 
ANY USE OF THE WORK OTHER THAN AS AUTHORIZED UNDER THIS LICENSE 
OR COPYRIGHT LAW IS PROHIBITED.

Creative Commons License

This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License; 
you may not use this work except in compliance with the License.

You may obtain a copy of the License in the LICENSE file, 
or at http://creativecommons.org/licenses/by-nc-nd/3.0/deed.en_US

BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, 
YOU ACCEPT AND AGREE TO BE BOUND BY THE TERMS OF THIS LICENSE. 
TO THE EXTENT THIS LICENSE MAY BE CONSIDERED TO BE A CONTRACT, 
THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED HERE IN CONSIDERATION 
OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 */

package com.hhs.hfnavigator.core;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.hhs.hfnavigator.R;
import com.hhs.hfnavigator.drawerfragments.AboutFragment;
import com.hhs.hfnavigator.drawerfragments.BusFragment;
import com.hhs.hfnavigator.drawerfragments.CalendarFragment;
import com.hhs.hfnavigator.drawerfragments.CastleFragment;
import com.hhs.hfnavigator.drawerfragments.EdmodoFragment;
import com.hhs.hfnavigator.drawerfragments.Fragment_12;
import com.hhs.hfnavigator.drawerfragments.Fragment_4;
import com.hhs.hfnavigator.drawerfragments.Fragment_9;
import com.hhs.hfnavigator.drawerfragments.Fragment_Sports;
import com.hhs.hfnavigator.drawerfragments.HarbingerFragment;
import com.hhs.hfnavigator.drawerfragments.LibFragment;
import com.hhs.hfnavigator.drawerfragments.PortalFragment;
import com.hhs.hfnavigator.slidingtabs.InitialNesterFragment;
import com.hhs.hfnavigator.teacherdirectory.TeacherList;

public class Main extends SherlockFragmentActivity {
	private DrawerLayout mDrawerLayout;
	private ListView mLeftDrawer;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mFragmentTitles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// --------------------------------Nav
		// Drawer--------------------------------------------------------------

		mTitle = mDrawerTitle = getTitle();
		mFragmentTitles = getResources().getStringArray(R.array.fragments);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mLeftDrawer = (ListView) findViewById(R.id.left_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		mLeftDrawer.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mFragmentTitles));
		mLeftDrawer.setOnItemClickListener(new DrawerItemClickListener());

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View v) {
				getSupportActionBar().setTitle(mTitle);
				supportInvalidateOptionsMenu();
			}

			public void onDrawerOpened(View v) {
				getSupportActionBar().setTitle(mDrawerTitle);
				supportInvalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		if (savedInstanceState == null) {
			selectItem(0);
		}

	}

	// ----------------------------RSS-----------------------

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mLeftDrawer);

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (mDrawerLayout.isDrawerOpen(mLeftDrawer)) {
				mDrawerLayout.closeDrawer(mLeftDrawer);
			} else {
				mDrawerLayout.openDrawer(mLeftDrawer);
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
			
		}
	}

	private void selectItem(int position) {
		Fragment newFragment = new InitialNesterFragment();
		FragmentManager fm = getSupportFragmentManager();
		switch (position) {
		case 0:
			newFragment = new InitialNesterFragment();
			break;
		case 1:
			newFragment = new Fragment_9();

			break;
		case 2:
			newFragment = new BusFragment();
			break;
		case 3:
			newFragment = new Fragment_4();
			break;
		case 4:
			Intent i = new Intent(this, TeacherList.class);
			startActivity(i);

			break;
		case 5:
			newFragment = new PortalFragment();
			break;
		case 6:
			newFragment = new CastleFragment();
			break;

		case 7:
			newFragment = new EdmodoFragment();
			break;
		case 8:
			newFragment = new HarbingerFragment();

			break;
		case 9:
			newFragment = new LibFragment();

			break;
		case 10:
			newFragment = new CalendarFragment();
			break;
		case 11:
			newFragment = new Fragment_Sports();
			break;
		case 12:
			newFragment = new AboutFragment();
			break;
		case 13:
			newFragment = new Fragment_12();
			break;

		}

		fm.beginTransaction().replace(R.id.content_frame, newFragment).commit();

		mLeftDrawer.setItemChecked(position, true);
		setTitle(mFragmentTitles[position]);
		mDrawerLayout.closeDrawer(mLeftDrawer);
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

}