package com.ftfl.atm;

import com.ftfl.atm.database.AtmDBSource;
import com.ftfl.atm.uitl.ProfileModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateProfileActivity extends Activity {
	
	Button btnSave = null;
	
	EditText etName = null;
	EditText etAddress = null;
	EditText etBankName = null;
	EditText etLatitude = null;
	EditText etLongitude = null;
	EditText etDeposite = null;
	EditText etContactPersonName = null;
	EditText etContactPersonNumber = null;
	
	Toast toast = null;
	
	String mName = "";
	String mAddress = "";
	String mBankName = "";
	String mLatitude = "";
	String mLongitude = "";
	String mDeposite = "";
	String mContactName = "";
	String mContactNumber = "";
	
	AtmDBSource atmDBSource = new AtmDBSource(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_profile);
		
		// set the text field id to the variable.
		etName = (EditText) findViewById(R.id.createName);
		etAddress = (EditText) findViewById(R.id.createAddress);
		etBankName = (EditText) findViewById(R.id.createBankName);
		etLatitude = (EditText) findViewById(R.id.createLatitude);
		etLongitude = (EditText) findViewById(R.id.createLongitude);
		etDeposite = (EditText) findViewById(R.id.createDeposite);
		etContactPersonName = (EditText) findViewById(R.id.createPersonName);
		etContactPersonNumber = (EditText) findViewById(R.id.createPersonNumber);
		
		btnSave = (Button) findViewById(R.id.btnSave);
		btnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mName = etName.getText().toString();
				mAddress = etAddress.getText().toString();
				mBankName = etBankName.getText().toString();
				mLatitude = etLatitude.getText().toString();
				mLongitude = etLongitude.getText().toString();
				mDeposite = etDeposite.getText().toString();
				mContactName = etContactPersonName.getText().toString();
				mContactNumber = etContactPersonNumber.getText().toString();
				
				// Assign values in the Profile
				ProfileModel profileDataInsert = new ProfileModel();
				
				profileDataInsert.setmName(mName);
				profileDataInsert.setmAddress(mAddress);
				profileDataInsert.setmBankName(mBankName);
				profileDataInsert.setmLatitude(mLatitude);
				profileDataInsert.setmLongitude(mLongitude);
				profileDataInsert.setmDeposite(mDeposite);
				profileDataInsert.setmContactPersoneName(mContactName);
				profileDataInsert.setmContactPersoneNumber(mContactNumber);
							
			    //if update is needed then update otherwise submit
				
				if (atmDBSource.insert(profileDataInsert) == true) {
					
					toast = Toast.makeText(CreateProfileActivity.this, "Successfully Saved.", Toast.LENGTH_LONG);
					toast.show();
					
					startActivity(new Intent(CreateProfileActivity.this, ViewProfileActivity.class));
					
				} else {
					
					toast = Toast.makeText(CreateProfileActivity.this, "Not Saved.", Toast.LENGTH_LONG);
					toast.show();
				}
				
			}			
		});
	}

}
