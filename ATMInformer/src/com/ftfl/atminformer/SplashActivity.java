package com.ftfl.atminformer;

import com.ftfl.atminformer.database.ATMDBSource;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class SplashActivity extends Activity {
	
	ATMDBSource mDataSource = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        mDataSource = new ATMDBSource(this);
        
        Thread background = new Thread() {
			public void run() {

				try {
						// Thread will sleep for 3 seconds
						sleep(2 * 1000);
	
						// After 2 seconds redirect to another intent
						
						// Bellow code will do the same thing....
						
						if(mDataSource.isEmpty())
						{
							Intent i = new Intent(SplashActivity.this, CreateProfileActivity.class);
							startActivity(i);
						}
						else{
							Intent i = new Intent( SplashActivity.this,	ViewProfileListActivity.class );
							startActivity(i);
						}
						
					
						// Remove activity
						finish(); // so that, it will not get back in the previous
									// file.

					} catch (Exception e) {

				}
			}
		};

		// start thread
		background.start();
		
    }
}

    
