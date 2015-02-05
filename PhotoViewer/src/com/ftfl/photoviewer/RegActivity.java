package com.ftfl.photoviewer;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class RegActivity extends Activity {
	
	private Button mBtnReg = null;
	private Button mBtnRet = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		
		mBtnReg = (Button) findViewById(R.id.btnReg);
		mBtnRet = (Button) findViewById(R.id.btnRet);
		
		mBtnReg.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent mIntentOne = new Intent(RegActivity.this, TakePhotoActivity.class);
				startActivity(mIntentOne);
				
			}			
		});
		
		mBtnRet.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent mIntentTwo = new Intent(RegActivity.this, ViewPhotoListActivity.class);
				startActivity(mIntentTwo);
				
			}			
		});
	}	
}
