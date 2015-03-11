package com.ftfl.icaremyself;

import com.ftfl.icaremyself.util.ICareMySelfConstants;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class GrowthInfoActivity extends Activity {
	
	// Widget GUI
	private TextView mTvGrowthInfoParaOne = null;
	private TextView mTvGrowthInfoParaTwo = null;
	private TextView mTvGrowthInfoParaThree = null;
	
	//initialization string variable
	private String mGrowthInfoParaOne = "";
	private String mGrowthInfoParaTwo = "";
	private String mGrowthInfoParaThree = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_growth_info);
		
		//get static string data
		getStringData();
		
		mTvGrowthInfoParaOne = (TextView) findViewById(R.id.tvGrowthInfoParaOne);	
		mTvGrowthInfoParaOne.setText(mGrowthInfoParaOne);
		
		mTvGrowthInfoParaTwo = (TextView) findViewById(R.id.tvGrowthInfoParaTwo);	
		mTvGrowthInfoParaTwo.setText(mGrowthInfoParaTwo);
		
		mTvGrowthInfoParaThree = (TextView) findViewById(R.id.tvGrowthInfoParaThree);
		mTvGrowthInfoParaThree.setText(mGrowthInfoParaThree);
		
	}	
	
	//get data method
	protected void getStringData() {
		
		mGrowthInfoParaOne = (ICareMySelfConstants.GrowthInfoParaOne);
		
		mGrowthInfoParaTwo = (ICareMySelfConstants.GrowthInfoParaTwo);
		
		mGrowthInfoParaThree = (ICareMySelfConstants.GrowthInfoParaThree);
		
	}


}
