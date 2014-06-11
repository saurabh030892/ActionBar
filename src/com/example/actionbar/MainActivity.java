package com.example.actionbar;

import java.util.ArrayList;

import com.example.actionbar.adapter.TitleNavigationAdapter;
import com.example.actionbar.model.SpinnerNavItem;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

public  class MainActivity extends Activity implements ActionBar.OnNavigationListener{
 
	 // action bar
    private ActionBar actionBar;
 
    // Title navigation Spinner data
    private ArrayList<SpinnerNavItem> navSpinner;
     
    // Navigation adapter
    private TitleNavigationAdapter adapter;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        actionBar = getActionBar();
         
        // Hide the action bar title
        actionBar.setDisplayShowTitleEnabled(false);
 
        // Enabling Spinner dropdown navigation
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
         
        // Spinner title navigation data
        navSpinner = new ArrayList<SpinnerNavItem>();
        navSpinner.add(new SpinnerNavItem("Local", R.drawable.ic_location));
        navSpinner.add(new SpinnerNavItem("My Places", R.drawable.ic_my_places));
        navSpinner.add(new SpinnerNavItem("Checkins", R.drawable.ic_checkin));
        navSpinner.add(new SpinnerNavItem("Latitude", R.drawable.ic_latitude));     
         
        // title drop down adapter
        adapter = new TitleNavigationAdapter(getApplicationContext(), navSpinner);
 
        // assigning the spinner navigation     
        actionBar.setListNavigationCallbacks(adapter, this);
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
 
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
 
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case R.id.action_search:
            // search action
            return true;
        case R.id.action_location_found:
            // location found
            LocationFound();
            return true;
        case R.id.action_refresh:
            // refresh
            return true;
        case R.id.action_help:
            // help action
            return true;
        case R.id.action_check_updates:
            // check for updates action
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
 
    /**
     * Launching new activity
     * */
    private void LocationFound() {
        Intent i = new Intent(MainActivity.this, LocationFound.class);
        startActivity(i);
    }

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}
}
