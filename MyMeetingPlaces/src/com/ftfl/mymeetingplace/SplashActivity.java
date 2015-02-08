package com.ftfl.mymeetingplace;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		Thread mThread = new Thread() {
			public void run() {

				try {
						// Thread will sleep for 3 seconds
						sleep(2 * 1000);
	
						// After 2 seconds redirect to another intent
						
						// Bellow code will do the same thing....						
						
						Intent mIntent = new Intent(SplashActivity.this, HomeActivity.class);
						startActivity(mIntent);						
					
						// Remove activity
						finish(); // so that, it will not get back in the previous
									// file.

					} catch (Exception e) {

				}
			}
		}; 
		// start thread
		mThread.start();
	}
}
