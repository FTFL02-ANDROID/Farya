package com.ftfl.mymeetingplace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends Activity {
	
	Button mBtnReg = null;
	Button mBtnViewPlaces = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		mBtnReg = (Button) findViewById(R.id.btnRegitration);
		//use an anonymous inner class as a listener
		mBtnReg.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent mIntentOne = new Intent(HomeActivity.this, TakePhotoActivity.class);
				startActivity(mIntentOne);
				
			}			
		});
		
		mBtnViewPlaces = (Button) findViewById(R.id.btnRetrieve);
		//use an anonymous inner class as a listener
		mBtnViewPlaces.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent mIntentTwo = new Intent(HomeActivity.this, ViewPlacesListActivity.class);
				startActivity(mIntentTwo);
				
			}			
		});
	}
}
