package com.ftfl.icaremyself;

import com.ftfl.icaremyself.util.ICareMySelfConstants;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class VaccinationInfoActivity extends Activity {
	
	// Widget GUI
	private TextView mTvVaccinationInfoParaOne = null;
	private TextView mTvVaccinationInfoParaTwo = null;
	private TextView mTvVaccinationInfoParaThree = null;
	
	//initialization string variable
	private String mVaccinationInfoParaOne = "";
	private String mVaccinationInfoParaTwo = "";
	private String mVaccinationInfoParaThree = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_vaccine_info);
		
		//get static string data
		getStringData();
		
		mTvVaccinationInfoParaOne = (TextView) findViewById(R.id.tvVaccinationInfoParaOne);	
		mTvVaccinationInfoParaOne.setText(mVaccinationInfoParaOne);
		
		mTvVaccinationInfoParaTwo = (TextView) findViewById(R.id.tvVaccinationInfoParaTwo);	
		mTvVaccinationInfoParaTwo.setText(mVaccinationInfoParaTwo);
		
		mTvVaccinationInfoParaThree = (TextView) findViewById(R.id.tvVaccinationInfoParaThree);
		mTvVaccinationInfoParaThree.setText(mVaccinationInfoParaThree);
		
	}	
	
	//get data method
	protected void getStringData() {
		
		mVaccinationInfoParaOne = (ICareMySelfConstants.VaccinationInfoParaOne);
		
		mVaccinationInfoParaTwo = (ICareMySelfConstants.VaccinationInfoParaTwo);
		
		mVaccinationInfoParaThree = (ICareMySelfConstants.VaccinationInfoParaThree);
		
	}

}
