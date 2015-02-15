package com.ftfl.mymeetingplace;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.ftfl.mymeetingplace.database.MyPlaceDBSource;
import com.ftfl.mymeetingplace.uitl.MyPlaceModel;

import android.app.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TakePhotoActivity extends Activity implements LocationListener {
	
	
	private Uri mFileUri = null; // file url to store image/video
	
	//initialization variable
	private Button mBtnTakePhoto = null;
	private Button mBtnSave = null;	
	private ImageView mImageView = null;
	
	private String mDate = "";
	private String mTime = "";
	
	private String eDate = "";
	private String eTime = "";
	
	private TextView mViewLatitude = null;
	private TextView mViewLongitude = null;
	private TextView mViewDate = null;
	private TextView mViewTime = null;
	
	private EditText mViewPlaces = null;
	private EditText mViewContactPerson = null;
	private EditText mViewContactEmail = null;
	private EditText mViewContactMobile = null;
	
	private String mLatitude = "";
	private String mLongitude = "";
	private String mMeetingPlaces = "";
	private String mContactPerson = "";
	private String mContactEmail = "";
	private String mContactMobile = "";
	
	private String eLatitude = "";
	private String eLongitude = "";
	
	private int mhour = 0;
	private int mminute = 0;
	private int mYear = 0;
	private int mMonth = 0;
	private int mDay = 0;
	
	private double mLat = 0.0;
	private double mLong = 0.0;
	
	private static String mPlacePhotoPath = "";
	
	Toast toast;
	
	MyPlaceDBSource mDBSource = new MyPlaceDBSource(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_take_photo);
		
		//Location method call for get latitude longitude
		getLocationData();
		//method call to get current date method
		getTodaysDate();
		//method call to get current time
		getCurrentTime();
		 
		//initialize variable find by id
		mViewLatitude = (TextView) findViewById(R.id.tvLatitude);
		mViewLatitude.setText(eLatitude);
		
		mViewLongitude = (TextView) findViewById(R.id.tvLongitude);
		mViewLongitude.setText(eLongitude);
		
		mViewDate = (TextView) findViewById(R.id.tvDate);
		mViewDate.setText(eDate);
		
		mViewTime = (TextView) findViewById(R.id.tvTime);
		mViewTime.setText(eTime);
		
		mViewPlaces = (EditText) findViewById(R.id.editRemarks);
		
		mViewContactPerson = (EditText) findViewById(R.id.editContactName);		
		mViewContactEmail = (EditText) findViewById(R.id.editContactEmail);		
		mViewContactMobile = (EditText) findViewById(R.id.editContactMobile);
		
		mImageView = (ImageView) findViewById(R.id.imgView);
		
		mBtnTakePhoto = (Button) findViewById(R.id.btnTakePhoto);
		//event listener for on click metnod
		mBtnTakePhoto.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//method call to intend camera
				captureImage();
				
			}			
		});
		
		// Checking camera availability
		if (!isDeviceSupportCamera()) {
			Toast.makeText(getApplicationContext(),	"Sorry! Your device doesn't support camera",Toast.LENGTH_LONG).show();
			// will close the app if the device does't have camera
			finish();
		}	
		
		mBtnSave = (Button) findViewById(R.id.btnSave);
		//event listener for on click metnod
		mBtnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//method call to get gata from form
				getDataFromForm();
				
				//condition to check validity
				if (   ( !mLatitude.equals("")) && ( !mLongitude.equals("")) &&
						( !mMeetingPlaces.equals("")) && ( !mPlacePhotoPath.equals("")) 
						&& ( !mDate.equals("")) && ( !mTime.equals(""))
						&& ( !mContactPerson.equals("")) && ( !mContactEmail.equals("")) 
						&& ( !mContactMobile.equals(""))){ 					
					
					// Assign values in the Profile
					MyPlaceModel mDataInsert = new MyPlaceModel();
					
					mDataInsert.setmLatitude(mLatitude);
					mDataInsert.setmLongitude(mLongitude);
					mDataInsert.setmImage(mPlacePhotoPath);
					mDataInsert.setmRemark(mMeetingPlaces);
					mDataInsert.setmDate(mDate);
					mDataInsert.setmTime(mTime);
					mDataInsert.setmContactName(mContactPerson);
					mDataInsert.setmContactEmail(mContactEmail);
					mDataInsert.setmContaceMobile(mContactMobile);
					
					if (mDBSource.insertData(mDataInsert) == true) {
						
						toast = Toast.makeText(TakePhotoActivity.this, "Successfully Saved.", Toast.LENGTH_LONG);
						toast.show();
						//communicate from take photo activeity to home activity
						startActivity(new Intent(TakePhotoActivity.this, ViewPlacesListActivity.class));
						
					} else {
						
						toast = Toast.makeText(TakePhotoActivity.this, "Not Saved.", Toast.LENGTH_LONG);
						toast.show();
					}
					
				}
				
			}			
		});
	}
	
	//Date format 
	protected String getTodaysDate() {	 
		
	    final Calendar c = Calendar.getInstance();
	    mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
	   
		eDate = mDay + "/" + (mMonth + 1) +"/"+ mYear;
	   
	    return eDate;
	 
	}
	 
	//Time format 
	protected String getCurrentTime() {
	 
	    final Calendar c = Calendar.getInstance();
	    mhour = c.get(Calendar.HOUR_OF_DAY);
		mminute = c.get(Calendar.MINUTE);
		
		eTime = mhour + ":" + mminute;	    
	   
	    return eTime;
	 
	}
	
	//get data
	protected void getDataFromForm() {		
		
		mDate = mViewDate.getText().toString();
		mTime = mViewTime.getText().toString();
		mLatitude = mViewLatitude.getText().toString();
		mLongitude = mViewLongitude.getText().toString();
		mMeetingPlaces = mViewPlaces.getText().toString();
		mContactPerson = mViewContactPerson.getText().toString();
		mContactEmail = mViewContactEmail.getText().toString();
		mContactMobile = mViewContactMobile.getText().toString();
		
	}
	
	//get location
	protected void getLocationData() {	
		
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		// Creating a criteria object to retrieve provider
		Criteria criteria = new Criteria();

		// Getting the name of the best provider
		String provider = locationManager.getBestProvider(criteria, true);

		// Getting Current Location
		Location location = locationManager.getLastKnownLocation(provider);

		if (location != null) {
			onLocationChanged(location);
		}
		
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
					
	}
	
	//get latitude and longitude
	@Override
	public void onLocationChanged(Location location) {
		
		try{
			mLat = location.getLatitude();
		}catch(Exception e)
        {
        	//by defoult 
        }

		// Getting longitude of the current location
		try{
			mLong = location.getLongitude();
		}catch(Exception e)
        {
        	//by defoult 
        }
		
		eLatitude =  Double.toString(mLat);
		eLongitude = Double.toString(mLong);		
	}
	
	//checking for camera support
	private boolean isDeviceSupportCamera() {
		if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			// this device has a camera
			return true;
		} else {
			// no camera on this device
			return false;
		}
	}	
	
	//Capturing Camera Image will launch camera app request image capture	 
	private void captureImage() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		mFileUri = getOutputMediaFileUri(1);

		intent.putExtra(MediaStore.EXTRA_OUTPUT, mFileUri);

		// start the image capture Intent
		startActivityForResult(intent, 100);

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// save file url in bundle as it will be null on screen orientation
		// changes
		outState.putParcelable("file_uri", mFileUri);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		// get the file url
		mFileUri = savedInstanceState.getParcelable("file_uri");
	}	
	
	// Receiving activity result method will be called after closing the camera	 
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// if the result is capturing Image
		if (requestCode == 100) {
			if (resultCode == RESULT_OK) {
				// successfully captured the image
				// display it in image view
				previewCapturedImage();			

			} else if (resultCode == RESULT_CANCELED) {
				// user cancelled Image capture
				Toast.makeText(getApplicationContext(),
						"User cancelled image capture", Toast.LENGTH_SHORT)
						.show();
			} else {
				// failed to capture image
				Toast.makeText(getApplicationContext(),
						"Sorry! Failed to capture image", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}
	
	//Display image from a path to ImageView	 
	private void previewCapturedImage() {
		try {
			// mImgPreview.setVisibility(View.VISIBLE);
			// bimatp factory
			BitmapFactory.Options options = new BitmapFactory.Options();
			/**
			 * downsizing image as it throws OutOfMemory Exception for larger
			 * images
			 */
			options.inSampleSize = 8; // should be power of 2.
			Bitmap bitmap = BitmapFactory.decodeFile(mFileUri.getPath(),options);

			mImageView.setImageBitmap(bitmap);

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	//Creating file uri to store image/video	
	public Uri getOutputMediaFileUri(int type) {
		return Uri.fromFile(getOutputMediaFile(type));
	}

	
	//returning image	
	private static File getOutputMediaFile(int type) {

		// External sdcard mLocation
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"My Meeting Place Gallery");

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("Public_university Gallery", "Oops! Failed create "+ "My Meeting Place Gallery" + " directory");
				return null;
			}
		}

		// Create a media file mName
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(new Date());

		File mediaFile;

		if (type == 1) {

			mediaFile = new File(mediaStorageDir.getPath() + File.separator+ "IMG_" + timeStamp + ".jpg");
			
			// Save a file: path for use with ACTION_VIEW intents
			mPlacePhotoPath = mediaFile.getAbsolutePath();
		} else {
			return null;
		}

		return mediaFile;
	}

	
	@Override
	public void onProviderDisabled(String provider) {
		//if Gps is Disable
		Toast.makeText( getApplicationContext(),"Gps Disabled",	Toast.LENGTH_SHORT ).show(); 		
	}

	@Override
	public void onProviderEnabled(String provider) {
		//if Gps is enable
		Toast.makeText( getApplicationContext(),"Gps Enabled",Toast.LENGTH_SHORT).show(); 
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}
