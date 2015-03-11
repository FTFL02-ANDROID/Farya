package com.ftfl.icaremyself;

import java.util.Calendar;

import com.ftfl.icaremyself.database.MyDietChartDBSource;
import com.ftfl.icaremyself.util.MyDietChartModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat") 
public class CreateDietChartActivity extends Activity implements OnClickListener, OnDateSetListener, OnTimeSetListener{
	
	// Widget GUI
	private Button mBtnSaveDiet = null;	

	private CheckBox mCkDietAlarm = null;
	
	private EditText mEtFeastname = null;
	private EditText mEtFeastmenu = null;
	private EditText mEtDateOfDiet = null;
	private EditText mEtTimeOfDiet = null;
	
	//initialization string variable
	private String mDietDate = "";
	private String mDietTime = "";
	private String mDietFoodMenu = "";	
	private String mDietEventName = "";	
	private String mDietAlarm = "";
	
	private int mYear = 0;
	private int mDay = 0;
	private int mMonth = 0;
	public String mCurrentDate = "";
	
	private int mHour = 0;
	private int mMinute = 0;
	
	Integer mSetHour = 0;
	Integer mSetMinute = 0;
	
	private String mAlarm = "true";
	
    Toast toast;
	
    MyDietChartDBSource mDietDBSource = null;
    
    final Calendar mCalendar = Calendar.getInstance(); 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_diet_chart);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		mCurrentDate = dateFormat.format(date);		
		
		mDietDBSource = new MyDietChartDBSource(this);		
		
		//initialize variable find by id
		mEtFeastname = (EditText) findViewById(R.id.editFeastname);	
		mEtFeastmenu = (EditText) findViewById(R.id.editFeastmenu);	
		
		mEtTimeOfDiet = (EditText) findViewById(R.id.editTimeOfDiet);	
		mEtTimeOfDiet.setInputType(InputType.TYPE_NULL);
		mEtTimeOfDiet.setFocusable(true);
		mEtTimeOfDiet.setClickable(true);		
		mEtTimeOfDiet.setOnClickListener(this);	
				
		mEtDateOfDiet = (EditText) findViewById(R.id.editDateOfDiet);
		mEtDateOfDiet.setInputType(InputType.TYPE_NULL);
		mEtDateOfDiet.setFocusable(true);;
		mEtDateOfDiet.setClickable(true);		
		mEtDateOfDiet.setOnClickListener(this);
		mEtDateOfDiet.setText(mCurrentDate);
		
		mCkDietAlarm = (CheckBox) findViewById(R.id.editDietAlarm);
		mCkDietAlarm.setOnClickListener(this);	
		
		mBtnSaveDiet = (Button) findViewById(R.id.btnSaveDiet);		
		mBtnSaveDiet.setOnClickListener(this);	
	}
	
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		// TODO Auto-generated method stub
		mEtDateOfDiet.setText(new StringBuilder()
		// Month is 0 based so add 1
		.append(dayOfMonth).append("/").append(monthOfYear + 1).append("/").append(year));
	}
	
	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// TODO Auto-generated method stub
		
		mSetHour = hourOfDay;
		mSetMinute = minute;
		int hour = 0;
		String st = "";
		if (hourOfDay > 12) {
			hour = hourOfDay - 12;
			st = "PM";
		}

		else if (hourOfDay == 12) {
			hour = hourOfDay;
			st = "PM";
		}

		else if (hourOfDay == 0) {
			hour = hourOfDay + 12;
			st = "AM";
		} else {
			hour = hourOfDay;
			st = "AM";
		}
		mEtTimeOfDiet.setText(new StringBuilder().append(hour).append(" : ")
				.append(minute).append(" ").append(st));

	
	}
	
	//get data
	protected void getDataFromForm() {			
		 
		//get values to string
		mDietEventName = mEtFeastname.getText().toString();		
		mDietDate = mEtDateOfDiet.getText().toString();			
		mDietFoodMenu = mEtFeastmenu.getText().toString();
		mDietTime = mEtTimeOfDiet.getText().toString();	
		mDietAlarm = mCkDietAlarm.getText().toString();		
	}

	@Override
	public void onClick(View v) {
		
	
		switch (v.getId()) {
		
		case R.id.editDateOfDiet:
			
			mYear = mCalendar.get(Calendar.YEAR);
			mMonth = mCalendar.get(Calendar.MONTH);
			mDay = mCalendar.get(Calendar.DAY_OF_MONTH);

			DatePickerDialog dialog = new DatePickerDialog(this, this, mYear, mMonth, mDay);
			dialog.show();
			break;
			
		case R.id.editTimeOfDiet:
			// Process to get Current Time

			mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
			mMinute = mCalendar.get(Calendar.MINUTE);

			// Launch Time Picker Dialog
			TimePickerDialog tpd = new TimePickerDialog(this, this, mHour,
					mMinute, false);
			tpd.show();
			break;
			
		case R.id.editDietAlarm:			

			if (mCkDietAlarm.isChecked()) {				
				
				Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);           
	            alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, mSetHour);
	            alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, mSetMinute);
	            alarmIntent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
	            alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(alarmIntent);	            
	            mCkDietAlarm.setText(mAlarm);
			}
			
			break;
		
			
		case R.id.btnSaveDiet:
			
			//method call to get data from form
			getDataFromForm();
			
			//condition to check validity
			if (   ( !mDietEventName.equals("")) && ( !mDietDate.equals("")) &&
					( !mDietFoodMenu.equals("")) && ( !mDietTime.equals(""))					 
					&& ( !mDietAlarm.equals(" "))){ 					
				
				// Assign values in the Profile
				MyDietChartModel mDataInsert = new MyDietChartModel();
				
				mDataInsert.setmDietEventName(mDietEventName);				
				mDataInsert.setmDietFoodMenu(mDietFoodMenu);								
				mDataInsert.setmDietTime(mDietTime);
				mDataInsert.setmDietAlarm(mDietAlarm);			
				mDataInsert.setmDietDate(mDietDate);
			
				
				if (mDietDBSource.insertData(mDataInsert) == true) {
					
					toast = Toast.makeText(CreateDietChartActivity.this, "Successfully Saved.", Toast.LENGTH_LONG);
					toast.show();
					
					//communicate from activeity to activity
					startActivity(new Intent(CreateDietChartActivity.this, DietChartListActivity.class));
					// Remove activity
					finish(); // so that, it will not get back in the previous
								// file.
					
				} else {
					
					toast = Toast.makeText(CreateDietChartActivity.this, "Not svaed.", Toast.LENGTH_LONG);
					toast.show();
				}
				
			}else {
				
				toast = Toast.makeText(CreateDietChartActivity.this, "Please input all data.", Toast.LENGTH_LONG);
				toast.show();
			}
			
			break;
			
		}
		
	}

	
	
}
