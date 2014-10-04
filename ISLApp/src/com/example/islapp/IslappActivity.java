package com.example.islapp;

import java.util.ArrayList;

import com.islapp.adapter.NavDrawerListAdapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class IslappActivity extends FragmentActivity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private Menu mymenu;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mItemTitles;
	int[] icon;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_main);
		mTitle = mDrawerTitle = getTitle();
		mItemTitles = getResources().getStringArray(R.array.items_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// All Fixtures
		navDrawerItems.add(new NavDrawerItem(mItemTitles[0], navMenuIcons.getResourceId(0, -1)));

		// Live Fixtures
		navDrawerItems.add(new NavDrawerItem(mItemTitles[1], navMenuIcons.getResourceId(1, -1)));

		// News Feed
		navDrawerItems.add(new NavDrawerItem(mItemTitles[2], navMenuIcons.getResourceId(2, -1)));

		// League Table
		navDrawerItems.add(new NavDrawerItem(mItemTitles[3], navMenuIcons.getResourceId(3, -1)));

		// Feedback
		navDrawerItems.add(new NavDrawerItem(mItemTitles[4], navMenuIcons.getResourceId(4, -1)));

		// Rate Us
		navDrawerItems.add(new NavDrawerItem(mItemTitles[5], navMenuIcons.getResourceId(5, -1)));

		//Extras
		navDrawerItems.add(new NavDrawerItem(mItemTitles[6], navMenuIcons.getResourceId(6, -1)));

		// Recycle the typed array
		navMenuIcons.recycle();

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);

		mDrawerList.setAdapter(adapter);

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setBackgroundDrawable(new ColorDrawable(0xFF000000));

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
				mDrawerLayout, /* DrawerLayout object */
				R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
				R.string.drawer_open, /* "open drawer" description for accessibility */
				R.string.drawer_close /* "close drawer" description for accessibility */
				) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu()
				//				setContentView(R.layout.drawer_main);
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu()
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			displayView(0);
		}

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
	}

	private class SlideMenuClickListener implements
	ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new AllFixturesFragment();
			//            fragment.getFragmentManager().findFragmentById(R.layout.all_matches_layout).getView().findViewById(R.id.team_1_score_match1).setText("1");
			//            fragment.View().findViewById(R.id.team_1_score_match1).setText("1");
			//            fragment.updateTextView();
			break;

		case 1:
			fragment = new LiveMatchesFragment();
			break;

		case 2:
			fragment = new NewsFeedFragment();
			break;

		case 3:
			fragment = new LeagueTableFragment();
			break;

		case 4:
			fragment = new FeedbackFragment();

			break;

		case 5:
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse("market://details?id=" + getPackageName()));
			startActivity(i);
			break;

		case 6:
			fragment = new ExtrasFragment();

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
			.replace(R.id.content_frame, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(mItemTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		mymenu = menu;
		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_share).setVisible(!drawerOpen);
		menu.findItem(R.id.action_refresh).setVisible(!drawerOpen);
		menu.findItem(R.id.action_settings).setVisible(drawerOpen);

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action buttons
		switch (item.getItemId()) {

		case R.id.action_share:
			Intent sendMailIntent = new Intent(Intent.ACTION_SEND);
			sendMailIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this app");
			sendMailIntent.putExtra(Intent.EXTRA_TEXT, "http://islapp.com");
			sendMailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
					"Check out the new app for Indian football");
			sendMailIntent.setType("text/plain");
			startActivity(Intent.createChooser(sendMailIntent,
					"Share ISLAPP with friends"));
			return true;

		case R.id.action_refresh:
			Toast.makeText(getApplicationContext(), "Refresh", 5).show();
			new UpdateTask(this).execute();
			return true;

		case R.id.action_settings:
			Intent settingsActivity = new Intent(getBaseContext(),Settings.class);
			startActivity(settingsActivity);
			SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
			StringBuilder builder = new StringBuilder();
			builder.append("\n" + sharedPrefs.getBoolean("cbNotification", false));
			builder.append("\n" + sharedPrefs.getBoolean("cbSound", false));
			builder.append("\n" + sharedPrefs.getBoolean("cbVibration", false));
			System.out.println("lalalalaalala" + builder);

			Toast.makeText(getApplicationContext(), "Settings", 5).show();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}

	public void resetUpdating()
	{
		// Get our refresh item from the menu
		MenuItem m = mymenu.findItem(R.id.action_refresh);
		if(m.getActionView()!=null)
		{
			// Remove the animation.
			//			m.getActionView().clearAnimation();
			m.setActionView(null);
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}
