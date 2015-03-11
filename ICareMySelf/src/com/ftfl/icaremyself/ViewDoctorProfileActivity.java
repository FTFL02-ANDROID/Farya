package com.ftfl.icaremyself;

import java.util.ArrayList;

import com.ftfl.icaremyself.database.DoctorProfileDBSource;
import com.ftfl.icaremyself.util.DoctorProfileModel;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewDoctorProfileActivity extends Activity  {
	
	// Widget GUI
	public TextView mDoctorName = null;
	public TextView mDoctorSpecialization = null;		
	public TextView mDoctorAddress = null;
	public TextView mDoctorContact = null;		
	public TextView mDoctorEmail = null;
	
	private ImageView mBtnViewMap = null;
	private ImageView mBtnViewCall = null;
	private ImageView mBtnViewSendSMS = null;
	private ImageView mBtnViewEmail = null;
	private ImageView mBtnViewAddContact = null;
	
	private String mEmail = "";
	private String mNumber = "";
	private String mName = "";
	private String mAddress = "";	
	
    //database initialization
	DoctorProfileDBSource mDocDBSource;  	
	DoctorProfileModel mDocProfile;
	
	private int mSelectedId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_doctor_profile);
		
		mDocDBSource = new DoctorProfileDBSource(this);	
		
		/* get Id from Intent */
		mSelectedId = getIntent().getExtras().getInt("selected_id");
		
			
		mDocProfile = mDocDBSource.getDoctorDetail(mSelectedId);
		
        //variable initialization
		mDoctorName = (TextView) findViewById(R.id.tvViewDocName);
		mName = mDocProfile.getmDoctorName();
		mDoctorName.setText(mName);
		
		mDoctorSpecialization = (TextView) findViewById(R.id.tvViewSpecialization);	
		
		mDoctorAddress = (TextView) findViewById(R.id.tvViewDocAddress);
		mAddress = mDocProfile.getmDoctorAddress();
		mDoctorAddress.setText(mAddress);
		
		mDoctorContact = (TextView) findViewById(R.id.tvViewDocContact);
		mNumber = mDocProfile.getmDoctorContact();
		mDoctorContact.setText(mNumber);
		
		mDoctorEmail = (TextView) findViewById(R.id.tvDocEmail);
		mEmail = mDocProfile.getmDoctorEmail();
		mDoctorEmail.setText(mEmail);		
			
		mDoctorSpecialization.setText(mDocProfile.getmDoctorSpecialization());	
		
		mBtnViewEmail = (ImageView) findViewById(R.id.viewEmail);
		mBtnViewEmail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {				
				
				Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mEmail, null));
				startActivity(Intent.createChooser(emailIntent, "Send email..."));
				
				// Remove activity
				finish(); // so that, it will not get back in the previous
							// file.
								
			}
		});
		
		mBtnViewCall = (ImageView) findViewById(R.id.viewCall);
		mBtnViewCall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mNumber));
				startActivity(intent);

				// Remove activity
				finish(); // so that, it will not get back in the previous
							// file.
			}
		});		
		
		mBtnViewSendSMS = (ImageView) findViewById(R.id.viewSendSMS);
		mBtnViewSendSMS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {

					Intent smsIntent = new Intent(Intent.ACTION_VIEW);
					smsIntent.putExtra("address", mNumber);
					smsIntent.setType("vnd.android-dir/mms-sms");
					startActivity(smsIntent);
					// Remove activity
					finish(); // so that, it will not get back in the previous
								// file.

				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "SMS faild!",Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});
		
		mBtnViewAddContact = (ImageView) findViewById(R.id.viewAddContact);
		mBtnViewAddContact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
				int rawContactInsertIndex = ops.size();

				ops.add(ContentProviderOperation
						.newInsert(RawContacts.CONTENT_URI)
						.withValue(RawContacts.ACCOUNT_TYPE, null)
						.withValue(RawContacts.ACCOUNT_NAME, null).build());
				ops.add(ContentProviderOperation
						.newInsert(Data.CONTENT_URI)
						.withValueBackReference(Data.RAW_CONTACT_ID,
								rawContactInsertIndex)
						.withValue(Data.MIMETYPE,
								StructuredName.CONTENT_ITEM_TYPE)
						.withValue(StructuredName.DISPLAY_NAME, mName) // Name
																		// of
																		// the
																		// person
						.build());
				ops.add(ContentProviderOperation
						.newInsert(Data.CONTENT_URI)
						.withValueBackReference(
								ContactsContract.Data.RAW_CONTACT_ID,
								rawContactInsertIndex)
						.withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
						.withValue(Phone.NUMBER, mNumber) // Number of the person
						.withValue(Phone.TYPE, Phone.TYPE_MOBILE).build()); // Type
																			// of
																			// mobile
																			// number
				try {
					@SuppressWarnings("unused")
					ContentProviderResult[] res = getContentResolver()
							.applyBatch(ContactsContract.AUTHORITY, ops);

					Toast.makeText(getApplicationContext(),
							"Successfully  Contract Added !!!!!!!",
							Toast.LENGTH_LONG).show();
				} catch (RemoteException e) {
					// error
				} catch (OperationApplicationException e) {
					// error
				}
			}
		});
		
		mBtnViewMap = (ImageView) findViewById(R.id.viewMap);
		mBtnViewMap.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent mMapIntent = new Intent(getApplicationContext(),GoogleMapActivity.class);
				mMapIntent.putExtra("selected_id",mSelectedId);
				startActivity(mMapIntent);
				// Remove activity
				finish(); // so that, it will not get back in the previous
							// file.
				
			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_doc, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// Take appropriate action for each action item click
				switch (item.getItemId()) {				
					
				case R.id.back:
					back();
					return true;
					
				default:
					return super.onOptionsItemSelected(item);
		}
	}

	private void back() {
		Intent i = new Intent(this, HomeActivity.class);
		startActivity(i);
		// Remove activity
		finish(); // so that, it will not get back in the previous
					// file.
		
	}

}
