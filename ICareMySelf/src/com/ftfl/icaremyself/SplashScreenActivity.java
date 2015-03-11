package com.ftfl.icaremyself;

import com.ftfl.icaremyself.database.MyProfileDBSource;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreenActivity extends Activity {
	
	MyProfileDBSource mDataSource = null;	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		//Remove title bar
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	    //Remove notification bar
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_splash_screen);
        
        mDataSource = new MyProfileDBSource(this);
        
        Thread mThread = new Thread() {
			public void run() {

				try {
						// Thread will sleep for 3 seconds
						sleep(2 * 1000);
	
						// After 2 seconds redirect to another intent
						
						// Bellow code will do the same thing....						
						if(mDataSource.isEmpty())
						{
							Intent i = new Intent(SplashScreenActivity.this,	CreateProfileActivity.class);
							startActivity(i);
						}
						else{
							Intent i = new Intent(SplashScreenActivity.this, HomeActivity.class);
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
		mThread.start();
    }

}
