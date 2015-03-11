package com.ftfl.icaremyself;

import com.ftfl.icaremyself.adapter.CustomDrawerAdapter;
import com.ftfl.icaremyself.fragment.FragmentAboutUs;
import com.ftfl.icaremyself.fragment.FragmentCareCenter;
import com.ftfl.icaremyself.fragment.FragmentDoctorProfile;
import com.ftfl.icaremyself.fragment.FragmentEmergencyCall;
import com.ftfl.icaremyself.fragment.FragmentHealthInformation;
import com.ftfl.icaremyself.fragment.FragmentPhotoGallery;
import com.ftfl.icaremyself.fragment.FragmentToDoList;
import com.ftfl.icaremyself.fragment.FragmentMyProfile;
import com.ftfl.icaremyself.util.DrawerItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;
 
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

@SuppressWarnings("deprecation")
public class HomeActivity extends Activity {
	
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    @SuppressWarnings("unused")
	private CharSequence mTitle;
    CustomDrawerAdapter adapter;

    List<DrawerItem> dataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(defaultOptions).build();
		ImageLoader.getInstance().init(config);
		
		// Initializing
        dataList = new ArrayList<DrawerItem>();
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        
        // Add Drawer Item to dataList
        dataList.add(new DrawerItem("My Profile", R.drawable.profile));       
        dataList.add(new DrawerItem("Doctor Profile", R.drawable.docpro));
        dataList.add(new DrawerItem("To Do List", R.drawable.todo));
        dataList.add(new DrawerItem("Photo gallery", R.drawable.photo));
        dataList.add(new DrawerItem("Health Information", R.drawable.info));
        dataList.add(new DrawerItem("Care Center", R.drawable.hospital));
        dataList.add(new DrawerItem("Emergency Call", R.drawable.emergency));
        dataList.add(new DrawerItem("About Us", R.drawable.aboutus));
        
        //initialize and set the adapter to the  drawer listview
        adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item, dataList);

        mDrawerList.setAdapter(adapter);        
        
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.drawable.ic_drawer,
        		R.string.drawer_open, R.string.drawer_close) {
        	
              public void onDrawerClosed(View view) {
                    getActionBar().setTitle(mDrawerTitle);
                    invalidateOptionsMenu(); // creates call to
                                                              // onPrepareOptionsMenu()
              }

              public void onDrawerOpened(View drawerView) {
                    getActionBar().setTitle(mDrawerTitle);
                    invalidateOptionsMenu(); // creates call to
                                                              // onPrepareOptionsMenu()
              }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
              
              
              Intent in = getIntent();
              Bundle b = null;
    	        // get the Bundle that stores the data of this Activity
    	        b = in.getExtras();
    	        
    	        if(b!=null)
    	        {
    	        	// getting data from bundle
    	        	int position = b.getInt("position");   
    	     	    SelectItem(position);
    	        }
    	        else
    	        	SelectItem(0);
    	 
        }
	  }
	
	
	  //to select the correct fragment
	  public void SelectItem(int possition) {
	
	        Fragment mFragment = null;
	        Bundle view = new Bundle();
	        switch (possition) {
	        
	        case 0:
        	  mFragment = new FragmentMyProfile();
              view.putString(FragmentMyProfile.ITEM_NAME, dataList.get(possition).getItemName());
              view.putInt(FragmentMyProfile.IMAGE_RESOURCE_ID, dataList.get(possition).getImgResID());
              break;	        
	        case 1:
        	  mFragment = new FragmentDoctorProfile();
              view.putString(FragmentDoctorProfile.ITEM_NAME, dataList.get(possition).getItemName());
              view.putInt(FragmentDoctorProfile.IMAGE_RESOURCE_ID, dataList.get(possition).getImgResID());
              break;
	        case 2:
        	  mFragment = new FragmentToDoList();
              view.putString(FragmentToDoList.ITEM_NAME, dataList.get(possition).getItemName());
              view.putInt(FragmentToDoList.IMAGE_RESOURCE_ID, dataList.get(possition).getImgResID());
              break;
	        case 3:
	        	mFragment = new FragmentPhotoGallery();
              view.putString(FragmentPhotoGallery.ITEM_NAME, dataList.get(possition).getItemName());
              view.putInt(FragmentPhotoGallery.IMAGE_RESOURCE_ID, dataList.get(possition).getImgResID());
              break;
	        case 4:
        	  mFragment = new FragmentHealthInformation();
              view.putString(FragmentHealthInformation.ITEM_NAME, dataList.get(possition).getItemName());
              view.putInt(FragmentHealthInformation.IMAGE_RESOURCE_ID, dataList.get(possition).getImgResID());
              break;
	        case 5:
        	  mFragment = new FragmentCareCenter();
              view.putString(FragmentCareCenter.ITEM_NAME, dataList.get(possition).getItemName());
              view.putInt(FragmentCareCenter.IMAGE_RESOURCE_ID, dataList.get(possition).getImgResID());
              break;              
	        case 6:
        	  mFragment = new FragmentEmergencyCall();
              view.putString(FragmentEmergencyCall.ITEM_NAME, dataList.get(possition).getItemName());
              view.putInt(FragmentEmergencyCall.IMAGE_RESOURCE_ID, dataList.get(possition).getImgResID());
              break;
	        case 7:
        	  mFragment = new FragmentAboutUs();
              view.putString(FragmentAboutUs.ITEM_NAME, dataList.get(possition).getItemName());
              view.putInt(FragmentAboutUs.IMAGE_RESOURCE_ID, dataList.get(possition).getImgResID());
              break;
              
	        
	        default:
              break;
        }

        mFragment.setArguments(view);
        FragmentManager mFrgManager = getFragmentManager();
        mFrgManager.beginTransaction().replace(R.id.content_frame, mFragment).commit();

        mDrawerList.setItemChecked(possition, true);
        setTitle(dataList.get(possition).getItemName());
        mDrawerLayout.closeDrawer(mDrawerList);

	  }
	
	  @Override
	  public void setTitle(CharSequence title) {
		  mTitle = title;
	        getActionBar().setTitle("I Care MySelf");
	  }

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

	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	        // The action bar home/up action should open or close the drawer.
	        // ActionBarDrawerToggle will take care of this.
	        if (mDrawerToggle.onOptionsItemSelected(item)) {
	              return true;
	        }
	
	        return false;
	  }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              SelectItem(position);

        }
  }

}