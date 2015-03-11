package com.ftfl.icaremyself;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ftfl.icaremyself.database.MyVaccineChartDBSource;
import com.ftfl.icaremyself.util.MyVaccineChartModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class CreateVaccineChartActivity extends Activity implements OnClickListener, OnDateSetListener {
	
	// Widget GUI
	private Button mBtnSaveVaccin = null;
	
	private CheckBox mCkVaccineTaken = null;
	
	private EditText mEtDateOfVaccine = null;
	private EditText mEtVaccineName = null;
	
	private String mDateOfVaccine = "";	
	private String mVaccineName = "";	
	private String mVaccineTaken = "false";
	
	private int mYear = 0;
	private int mDay = 0;
	private int mMonth = 0;
	public String mCurrentDate = "";
	
    Toast toast;
    
    MyVaccineChartDBSource mVaccineDBSource = null;
    
    final Calendar mCalendar = Calendar.getInstance(); 
	
	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_vaccination_chart);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		mCurrentDate = dateFormat.format(date);		
		
		mVaccineDBSource = new MyVaccineChartDBSource(this);	
		
		//initialize variable find by id
		mEtVaccineName = (EditText) findViewById(R.id.editVaccineName);	
		
		mEtDateOfVaccine = (EditText) findViewById(R.id.editDateOfVaccine);
		mEtDateOfVaccine.setInputType(InputType.TYPE_NULL);
		mEtDateOfVaccine.setFocusable(true);;
		mEtDateOfVaccine.setClickable(true);		
		mEtDateOfVaccine.setOnClickListener(this);
		mEtDateOfVaccine.setText(mCurrentDate);
		
		mCkVaccineTaken = (CheckBox) findViewById(R.id.editVaccineTaken);
		mCkVaccineTaken.setOnClickListener(this);	
		
		mBtnSaveVaccin = (Button) findViewById(R.id.btnSaveVaccin);		
		mBtnSaveVaccin.setOnClickListener(this);	
	}
	
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		// TODO Auto-generated method stub
		mEtDateOfVaccine.setText(new StringBuilder()
		// Month is 0 based so add 1
		.append(dayOfMonth).append("/").append(monthOfYear + 1).append("/").append(year));
	}
	
	//get data
	protected void getDataFromForm() {			
		 
		//get values to string
		mDateOfVaccine = mEtDateOfVaccine.getText().toString();		
		mVaccineName = mEtVaccineName.getText().toString();			
//		mVaccineTaken = mCkVaccineTaken.getText().toString();	
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.editDateOfVaccine:
			
			mYear = mCalendar.get(Calendar.YEAR);
			mMonth = mCalendar.get(Calendar.MONTH);
			mDay = mCalendar.get(Calendar.DAY_OF_MONTH);

			DatePickerDialog dialog = new DatePickerDialog(this, this, mYear, mMonth, mDay);
			dialog.show();
			break;
			
		case R.id.editVaccineTaken:	
			
			if (mCkVaccineTaken.isChecked()) {
				
				mVaccineTaken = "true";
				
			} 
			
			break;
			
		case R.id.btnSaveVaccin:
			
			//method call to get data from form
			getDataFromForm();
			
			//condition to check validity
			if (   ( !mDateOfVaccine.equals("")) && ( !mVaccineName.equals("")) &&					 
					( !mVaccineTaken.equals(""))){ 					
				
				// Assign values in the Profile
				MyVaccineChartModel mDataInsert = new MyVaccineChartModel();
				
				mDataInsert.setmVaccineDate(mDateOfVaccine);				
				mDataInsert.setmVaccineName(mVaccineName);								
				mDataInsert.setmVaccineTaken(mVaccineTaken);			
				
				if (mVaccineDBSource.insertData(mDataInsert) == true) {
					
					toast = Toast.makeText(CreateVaccineChartActivity.this, "Successfully Saved.", Toast.LENGTH_LONG);
					toast.show();
					
					//communicate from activeity to activity
					startActivity(new Intent(CreateVaccineChartActivity.this, VaccineChartListActivity.class));
					// Remove activity
					finish(); // so that, it will not get back in the previous
								// file.
					
				} else {
					
					toast = Toast.makeText(CreateVaccineChartActivity.this, "Please input all data.", Toast.LENGTH_LONG);
					toast.show();
				}
				
			}
			
			break;
			
		}
		
	}

}
