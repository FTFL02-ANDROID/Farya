package com.ftfl.icaremyself;

import java.util.Calendar;

import com.ftfl.icaremyself.database.MyProfileDBSource;
import com.ftfl.icaremyself.util.MyProfileModel;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateProfileActivity extends Activity implements OnClickListener, OnDateSetListener{
	
	// Widget GUI
	private Button mBtnUpdate = null;
	
	private Spinner mSpBlood = null;	
	
	private RadioGroup mRgGender = null;
	private RadioButton mRbGender = null;	
	
	private EditText mEtName = null;
	private EditText mEtDateBirth = null;	
	private EditText mEtHeight = null;
	private EditText mEtWeight = null;
	private EditText mEtNote = null ;	
	
	//initialization string variable
	private String mName = "";
	private String mDateOfBirth = "";
	private String mBloodGroup = "";
	private String mGender = "";
	private String mHeight = "";
	private String mWeight = "";	
	private String mImportantNote = "";
	
	private int mYear = 0;
	private int mDay = 0;
	private int mMonth = 0;
	
	// Variable for selected radio button from radioGroup
	private int selectedId = 0;
	
	private int mProfileId = 0;
	    
    Toast toast;    
    
    final Calendar mCalendar = Calendar.getInstance(); 
    
    //database initialization
    MyProfileDBSource mDBSource;
  	MyProfileModel mProfile;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_profile);		
		
		mDBSource = new MyProfileDBSource(this);
    	mProfile = mDBSource.getDetail();
    	
		/* get Id from model class */
    	mProfileId = mProfile.getmID();
		
		//initialize variable find by id
		mEtName = (EditText) findViewById(R.id.editName);		
		mEtDateBirth = (EditText) findViewById(R.id.editdateOfBirth);
		mEtDateBirth.setInputType(InputType.TYPE_NULL);
		mEtDateBirth.requestFocus();
		mEtDateBirth.setClickable(true);		
		mEtDateBirth.setOnClickListener(this);			
		mEtHeight = (EditText) findViewById(R.id.editHeight);
		mEtWeight = (EditText) findViewById(R.id.editWeight);
		mEtNote = (EditText) findViewById(R.id.editNote);		
		mSpBlood = (Spinner) findViewById(R.id.editSpinner);		
		mRgGender = (RadioGroup) findViewById(R.id.editRadioGender);
		
		setData();
		
		mBtnUpdate = (Button) findViewById(R.id.btnSave);		
		mBtnUpdate.setOnClickListener(this);
		mBtnUpdate.setText("Update");

	}  
	
	private void setData() {
		
		mEtName.setText(mProfile.getmName());		
		mEtNote.setText(mProfile.getmImportantNote());		
		mEtHeight.setText(mProfile.getmHeight());		
		mEtWeight.setText(mProfile.getmWeight());	
		mEtDateBirth.setText(mProfile.getmDateOfBirth());
	}

	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		// TODO Auto-generated method stub
		mEtDateBirth.setText(new StringBuilder()
		// Month is 0 based so add 1
		.append(dayOfMonth).append("/").append(monthOfYear + 1).append("/").append(year));
	}
	
	//get data method
	protected void getDataFromForm() {	
        
		// get selected radio button from radioGroup
		selectedId = mRgGender.getCheckedRadioButtonId();
 
		// find the radiobutton by returned id
		mRbGender = (RadioButton) findViewById(selectedId);
		 
		//get values to string
		mName = mEtName.getText().toString();
		mDateOfBirth = mEtDateBirth.getText().toString();
		mBloodGroup = mSpBlood.getSelectedItem().toString();
		mGender = mRbGender.getText().toString();
		mHeight = mEtHeight.getText().toString();
		mWeight = mEtWeight.getText().toString();
		mImportantNote = mEtNote.getText().toString();
	}

	//event listener
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		case R.id.editdateOfBirth:
			
			mYear = mCalendar.get(Calendar.YEAR);
			mMonth = mCalendar.get(Calendar.MONTH);
			mDay = mCalendar.get(Calendar.DAY_OF_MONTH);

			DatePickerDialog dialog = new DatePickerDialog(this, this, mYear, mMonth, mDay);
			dialog.show();
			break;
			
		case R.id.btnSave:
			
			//method call to get data from form
			getDataFromForm();
			
			//condition to check validity
			if (   ( !mName.equals("")) && ( !mDateOfBirth.equals("")) &&
					( !mBloodGroup.equals("")) && ( !mGender.equals("")) 
					&& ( !mHeight.equals("")) && ( !mWeight.equals(""))						 
					&& ( !mImportantNote.equals(""))){ 					
				
				// Assign values in the Profile
				MyProfileModel mDataInsert = new MyProfileModel();
				
				mDataInsert.setmName(mName);
				mDataInsert.setmDateOfBirth(mDateOfBirth);
				mDataInsert.setmBloodGroup(mBloodGroup);
				mDataInsert.setmGender(mGender);
				mDataInsert.setmHeight(mHeight + " " + "feet");
				mDataInsert.setmWeight(mWeight + " " +"kg");
				mDataInsert.setmImportantNote(mImportantNote);
				
				
				
				mDBSource.updateData( mProfileId, mDataInsert);
					
				toast = Toast.makeText(UpdateProfileActivity.this, "Successfully Saved.", Toast.LENGTH_LONG);
				toast.show();
				
				//communicate from take photo activeity to home activity
				startActivity(new Intent(UpdateProfileActivity.this, HomeActivity.class));
				// Remove activity
				finish(); // so that, it will not get back in the previous
								// file.
					
								
			}else {
				
				toast = Toast.makeText(UpdateProfileActivity.this, "Enter all data.", Toast.LENGTH_LONG);
				toast.show();
			}
			
			break;
			
		}
		
	}
	

	

}
