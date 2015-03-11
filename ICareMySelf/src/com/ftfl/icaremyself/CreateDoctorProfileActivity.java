package com.ftfl.icaremyself;

import com.ftfl.icaremyself.database.DoctorProfileDBSource;
import com.ftfl.icaremyself.util.DoctorProfileModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateDoctorProfileActivity extends Activity implements OnClickListener{
	
	// Widget GUI
	private Button mBtnSaveDoc = null;
	
	private EditText mEtDocName = null;
	private EditText mEtSpecialization = null;	
	private EditText mEtAddress = null;
	private EditText mEtContact = null;
	private EditText mEtEmail = null ;

	
	//initialization string variable
	private String mDocName = "";
	private String mSpecialization = "";
	private String mAddress = "";
	private String mContact = "";
	private String mEmail = "";

	
	DoctorProfileDBSource mDocDBSource = null;	
	
	Toast toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_doctor_profile);
		
		mDocDBSource = new DoctorProfileDBSource(this);
		
		//initialize variable find by id
		mEtDocName = (EditText) findViewById(R.id.editDoctorName);		
		mEtSpecialization = (EditText) findViewById(R.id.editSpecialization);		
		mEtAddress = (EditText) findViewById(R.id.editDocAddress);
		mEtContact = (EditText) findViewById(R.id.editDocContact);
		mEtEmail = (EditText) findViewById(R.id.editDocEmail);

		
		mBtnSaveDoc = (Button) findViewById(R.id.btnSaveDocProfile);		
		mBtnSaveDoc.setOnClickListener(this);		
		
	}
	
	//get data
	protected void getDataFromForm() {	
     
		//get values to string
		mDocName = mEtDocName.getText().toString();
		mSpecialization = mEtSpecialization.getText().toString();		
		mAddress = mEtAddress.getText().toString();
		mContact = mEtContact.getText().toString();
		mEmail = mEtEmail.getText().toString();

		
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		case R.id.btnSaveDocProfile:
			
			//method call to get data from form
			getDataFromForm();
			
			//condition to check validity
			if (   ( !mDocName.equals("")) && ( !mSpecialization.equals("")) &&
					( !mAddress.equals("")) && ( !mContact.equals("")) 						 
					&& ( !mEmail.equals(""))){ 	
				
				String email = mEmail.trim();
				 final String emailPattern  = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
				 if (email.matches(emailPattern)){
					 
					 	Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
			
						// Assign values in the Profile
						DoctorProfileModel mDocDataInsert = new DoctorProfileModel();
						
						mDocDataInsert.setmDoctorName(mDocName);
						mDocDataInsert.setmDoctorSpecialization(mSpecialization);
						mDocDataInsert.setmDoctorAddress(mAddress);
						mDocDataInsert.setmDoctorContact(mContact);
						mDocDataInsert.setmDoctorEmail(mEmail);
				 
					
						if (mDocDBSource.insertData(mDocDataInsert) == true) {
							
							toast = Toast.makeText(CreateDoctorProfileActivity.this, " Successfully Saved. ", Toast.LENGTH_LONG);
							toast.show();		
							
						    Bundle b = new Bundle();
		                     
		                    // Storing data into bundle
		                    b.putInt("position", 1);
		                    
		                    // Creating Intent object
		                    Intent intent = new Intent(CreateDoctorProfileActivity.this, HomeActivity.class);

		                    // Storing bundle object into intent
		                    intent.putExtras(b);
		                    startActivity(intent);							
							
							// Remove activity
							finish(); // so that, it will not get back in the previous
										// file.
							
						} else {
							
							toast = Toast.makeText(CreateDoctorProfileActivity.this, "Not Saved.", Toast.LENGTH_LONG);
							toast.show();
						}
				
				 }
			}else {
				
				toast = Toast.makeText(CreateDoctorProfileActivity.this, "Enter all valid data.", Toast.LENGTH_LONG);
				toast.show();
			}
			
			break;
		
		}
	}

}
