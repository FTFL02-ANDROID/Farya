package com.ftfl.atm;

import java.util.List;

import com.ftfl.atm.adapter.ProfileListAdapter;

import com.ftfl.atm.database.AtmDBSource;
import com.ftfl.atm.uitl.ProfileModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ViewProfileActivity extends Activity {
	
	ListView mListView = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_profile);
		
			
		AtmDBSource mDataSource = new AtmDBSource(this);
		
		List<ProfileModel> allProfile = mDataSource.atmProfilesList();
		ProfileListAdapter arrayAdapter = new ProfileListAdapter(this, allProfile);
		
		// adding it to the list view.
		mListView = (ListView) findViewById(R.id.lv);
		mListView.setAdapter(arrayAdapter);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent i = new Intent( ViewProfileActivity.this, MapActivity.class );
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
