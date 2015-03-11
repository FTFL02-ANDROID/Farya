package com.ftfl.icaremyself;

import com.ftfl.icaremyself.database.MyProfileDBSource;
import com.ftfl.icaremyself.util.MyProfileModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewProfileActivity extends Activity {
	
	// Widget GUI
	public TextView mName = null;
	public TextView mDateOfBirth = null;		
	public TextView mBloodGroup = null;
	public TextView mGender = null;		
	public TextView mHeight = null;
	public TextView mWeight = null;		
	public TextView mImportantNote = null;
	
    //database initialization
 	MyProfileDBSource mDBSource;  	
  	MyProfileModel mProfile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_profile);
		
    	mDBSource = new MyProfileDBSource(this);		
    	mProfile = mDBSource.getDetail();
		
        //variable initialization
        mName = (TextView) findViewById(R.id.viewName);		
		mImportantNote = (TextView) findViewById(R.id.viewNote);		
		mDateOfBirth = (TextView) findViewById(R.id.viewBOD);	
		mBloodGroup = (TextView) findViewById(R.id.viewBlood);			
		mGender = (TextView) findViewById(R.id.viewGender);			
		mHeight = (TextView) findViewById(R.id.viewHeight);	
		mWeight = (TextView) findViewById(R.id.viewWeight);
		
		mName.setText(mProfile.getmName());		
		mImportantNote.setText(mProfile.getmImportantNote());			
		mGender.setText(mProfile.getmGender());	
		mHeight.setText(mProfile.getmHeight());		
		mWeight.setText(mProfile.getmWeight());	
		mBloodGroup.setText(mProfile.getmBloodGroup());	
		mDateOfBirth.setText(mProfile.getmDateOfBirth());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_profile, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// Take appropriate action for each action item click
				switch (item.getItemId()) {					
				case R.id.back:
					back();
					return true;
					
				case R.id.edit:
					edit();
					return true;
					
				default:
					return super.onOptionsItemSelected(item);
		}
	}

	private void edit() {
		
		Intent i = new Intent( this, UpdateProfileActivity.class);
		//i.putExtra("selected_id",selectedId);
		startActivity(i);
		// Remove activity
		finish(); // so that, it will not get back in the previous
					// file.
		
	}

	private void back() {
		
		Intent i = new Intent( this, HomeActivity.class);
		startActivity(i);
		// Remove activity
		finish(); // so that, it will not get back in the previous
					// file.
		
	}

	
}
