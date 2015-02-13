package com.ftfl.mymeetingplace;

import java.util.ArrayList;

import com.ftfl.mymeetingplace.database.MyPlaceDBSource;
import com.ftfl.mymeetingplace.uitl.MyPlaceModel;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageButton;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ImageView;
import android.widget.TextView;


public class SingleProfileActivity extends Activity{
	
	private TextView mMeetingPlaceText = null;
	private TextView mLongText = null;
	private TextView mLatText = null;
	private TextView mDateText = null;
	private TextView mTimeText = null;
	private TextView mContactNameText = null;
	private TextView mEmailText = null;
	private TextView mMobileText = null;
	
	private String mPlace = "";
	private String mLong = "";
	private String mLat = "";
	private String mDate = "";
	private String mTime = "";
	private String mName = "";
	private String mEmail = "";
	private String mNumber = "";
	
	private ImageView mImagePreView = null;
	
	private ImageButton mBtnSendEmail = null;
	private ImageButton mBtnCall = null;
	private ImageButton mBtnSendSMS = null;
	private ImageButton mBtnAddContact = null;
	
	private MyPlaceModel mPlaceModel = null;
	private MyPlaceDBSource mDBSource = null;
	
	private int mSelectedId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_profile);		
		
		/* get Id from Intent */
		mSelectedId = getIntent().getExtras().getInt("selected_id");
		/* create instance of Data Source class */
		mDBSource = new MyPlaceDBSource(this);
		/*
		 * receive restrant information object by calling a method from Data
		 * Source class and sentd selected id aas parameter
		 */
		mPlaceModel = mDBSource.getDetail(mSelectedId);		
		 
		mMeetingPlaceText = (TextView) findViewById(R.id.tvViewPlaceName);
		mPlace = mPlaceModel.getmRemark();
		mMeetingPlaceText.setText(mPlace);
		
		mLongText = (TextView) findViewById(R.id.tvViewLongitude);
		mLong = mPlaceModel.getmLongitude();
		mLongText.setText(mLong);
		
		mLatText = (TextView) findViewById(R.id.tvViewLatitude);
		mLat = mPlaceModel.getmLatitude();
		mLatText.setText(mLat);
		
		mDateText = (TextView) findViewById(R.id.tvViewDate);
		mDate = mPlaceModel.getmDate();
		mDateText.setText(mDate);
		
		mTimeText = (TextView) findViewById(R.id.tvViewTime);
		mTime = mPlaceModel.getmTime();
		mTimeText.setText(mTime);
		
		mContactNameText = (TextView) findViewById(R.id.tvViewContactName);
		mName = mPlaceModel.getmContactName();
		mContactNameText.setText(mName);
		
		mEmailText = (TextView) findViewById(R.id.tvViewMail);
		mEmail = mPlaceModel.getmContactEmail();
		mEmailText.setText(mEmail);
		
		mMobileText = (TextView) findViewById(R.id.tvViewPhone);
		mNumber = mPlaceModel.getmContaceMobile();
		mMobileText.setText(mNumber);
		
		mImagePreView = (ImageView) findViewById(R.id.imgPreView);
		//get image from sdcard of device	
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4;
		Bitmap mImage = BitmapFactory.decodeFile(mPlaceModel.getmImage(), options);
		mImagePreView.setImageBitmap(mImage);
		
		mBtnSendEmail = (ImageButton) findViewById(R.id.btnViewEmail);
		mBtnSendEmail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {				
				
				Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mEmail, null));

				startActivity(Intent.createChooser(emailIntent, "Send email..."));

			}
		});
		
		mBtnCall = (ImageButton) findViewById(R.id.btnViewCall);
		mBtnCall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mNumber));
				startActivity(intent);

			}
		});		
		
		mBtnSendSMS = (ImageButton) findViewById(R.id.btnViewSMS);
		mBtnSendSMS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {

					Intent smsIntent = new Intent(Intent.ACTION_VIEW);
					smsIntent.putExtra("address", mNumber);
					smsIntent.setType("vnd.android-dir/mms-sms");
					startActivity(smsIntent);

				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "SMS faild!",Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});
		
		mBtnAddContact = (ImageButton) findViewById(R.id.btnViewAdd);
		mBtnAddContact.setOnClickListener(new OnClickListener() {

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
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// Take appropriate action for each action item click
				switch (item.getItemId()) {
				case R.id.edit:
					edit();
					return true;
				
				default:
					return super.onOptionsItemSelected(item);
		}
	}

	private void edit() {
		Intent i = new Intent(SingleProfileActivity.this, UpdateProfileActivity.class);
		startActivity(i);
		
	}

}
