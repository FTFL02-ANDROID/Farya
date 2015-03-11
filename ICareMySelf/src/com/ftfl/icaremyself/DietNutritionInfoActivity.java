package com.ftfl.icaremyself;

import com.ftfl.icaremyself.util.ICareMySelfConstants;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DietNutritionInfoActivity extends Activity {
	
	// Widget GUI
	private TextView mTvDietNutritionInfoParaOne = null;
	private TextView mTvDietNutritionInfoParaTwo = null;
	private TextView mTvDietNutritionInfoParaThree = null;
	
	//initialization string variable
	private String mDietNutritionInfoParaOne = "";
	private String mDietNutritionInfoParaTwo = "";
	private String mDietNutritionInfoParaThree = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_diet_nitrition_info);
		
		//get static string data
		getStringData();
		
		mTvDietNutritionInfoParaOne = (TextView) findViewById(R.id.tvDietNutritionInfoParaOne);	
		mTvDietNutritionInfoParaOne.setText(mDietNutritionInfoParaOne);
		
		mTvDietNutritionInfoParaTwo = (TextView) findViewById(R.id.tvDietNutritionInfoParaTwo);	
		mTvDietNutritionInfoParaTwo.setText(mDietNutritionInfoParaTwo);
		
		mTvDietNutritionInfoParaThree = (TextView) findViewById(R.id.tvDietNutritionInfoParaThree);
		mTvDietNutritionInfoParaThree.setText(mDietNutritionInfoParaThree);
		
	}	
	
	//get data method
	protected void getStringData() {
		
		mDietNutritionInfoParaOne = (ICareMySelfConstants.DietNutritionInfoParaOne); 
		
		mDietNutritionInfoParaTwo = (ICareMySelfConstants.DietNutritionInfoParaTwo);
		
		mDietNutritionInfoParaThree = (ICareMySelfConstants.DietNutritionInfoParaThree);
		
	}

}
