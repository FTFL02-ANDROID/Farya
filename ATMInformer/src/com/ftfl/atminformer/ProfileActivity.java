package com.ftfl.atminformer;

import com.ftfl.atminformer.database.ATMDBSource;
import com.ftfl.atminformer.uitl.ATMProfileModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends Activity {
	
	ImageView ivImage = null;
	TextView tvName = null;
	TextView tvBankName = null;
	TextView tvAddress = null;
	TextView tvLatitude = null;
	TextView tvLongitude = null;
	TextView tvPersonName = null;
	TextView tvPersonNumber = null;
	

	ATMDBSource mDBSource = null;
	ATMProfileModel mProfile = null;

	
	int mProfileId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		ivImage = (ImageView) findViewById(R.id.ivImage);
		tvName = (TextView) findViewById(R.id.tvName);
		tvBankName = (TextView) findViewById(R.id.tvBankName);
		tvAddress = (TextView) findViewById(R.id.tvAddress);
		tvLatitude = (TextView) findViewById(R.id.tvLatitude);
		tvLongitude = (TextView) findViewById(R.id.tvLongitude);
		tvPersonName = (TextView) findViewById(R.id.tvPersonName);
		tvPersonNumber = (TextView) findViewById(R.id.tvPersonNumber);

		mProfileId = getIntent().getExtras().getInt("selected_id");		

			/*
			 * get the activity which include all data from database according
			 * profileId of the clicked item.
			 */
			mDBSource = new ATMDBSource(this);
			mProfile = mDBSource.singleProfileData(mProfileId);

			String mName = mProfile.getmName();
			String mBankName = mProfile.getmBankName();
			String mAddress = mProfile.getmAddress();
			String mLatitude = mProfile.getmLatitude();
			String mLongitude = mProfile.getmLongitude();
			String mPersonName = mProfile.getmContactPersoneName();
			String mPersonNumber = mProfile.getmContactPersoneNumber();
			//String mImage = mProfile.getmImage();

			// set the value of database to the text field.

			tvName.setText(mName);
			tvName.setFocusable(false);
			tvName.setClickable(false);
			
			tvBankName.setText(mBankName);
			tvBankName.setFocusable(false);
			tvBankName.setClickable(false);
			
			tvAddress.setText(mAddress);
			tvAddress.setFocusable(false);
			tvAddress.setClickable(false);
			
			tvLatitude.setText(mLatitude);
			tvLatitude.setFocusable(false);
			tvLatitude.setClickable(false);
			
			tvLongitude.setText(mLongitude);
			tvLongitude.setFocusable(false);
			tvLongitude.setClickable(false);
			
			tvPersonName.setText(mPersonName);
			tvPersonName.setFocusable(false);
			tvPersonName.setClickable(false);
			
			tvPersonNumber.setText(mPersonNumber);
			tvPersonNumber.setFocusable(false);
			tvPersonNumber.setClickable(false);
			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.back) {
			back();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void back() {
		Intent i = new Intent(ProfileActivity.this, ViewProfileListActivity.class);
		startActivity(i);
		
	}
}
