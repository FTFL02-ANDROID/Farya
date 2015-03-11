package com.ftfl.icaremyself;

import com.ftfl.icaremyself.util.ICareMySelfConstants;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HealthInfoActivity extends Activity {
	
	// Widget GUI
	private TextView mTvHealthInfoParaOne = null;
	private TextView mTvHealthInfoParaTwo = null;
	private TextView mTvHealthInfoParaThree = null;
	
	//initialization string variable
	private String mHealthInfoParaOne = "";
	private String mHealthInfoParaTwo = "";
	private String mHealthInfoParaThree = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_general_info);
		
		//get static string data
		getStringData();
		
		mTvHealthInfoParaOne = (TextView) findViewById(R.id.tvHealthInfoParaOne);	
		mTvHealthInfoParaOne.setText(mHealthInfoParaOne);
		
		mTvHealthInfoParaTwo = (TextView) findViewById(R.id.tvHealthInfoParaTwo);	
		mTvHealthInfoParaTwo.setText(mHealthInfoParaTwo);
		
		mTvHealthInfoParaThree = (TextView) findViewById(R.id.tvHealthInfoParaThree);
		mTvHealthInfoParaThree.setText(mHealthInfoParaThree);
		
	}	
	
	//get data method
	protected void getStringData() {
		
		mHealthInfoParaOne = (ICareMySelfConstants.HealthInfoParaOne);
		
		mHealthInfoParaTwo = (ICareMySelfConstants.HealthInfoParaTwo);
		
		mHealthInfoParaThree = (ICareMySelfConstants.HealthInfoParaThree);
		
	}

}