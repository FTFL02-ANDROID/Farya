package com.ftfl.photoviewer;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import com.ftfl.photoviewer.database.PVSource;
import com.ftfl.photoviewer.uitl.PVModel;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PreviewPhotoActivity extends Activity implements LocationListener {
	
	private Uri mFileUri = null; // file url to store image
	
	private TextView mViewLatitude = null;
	private TextView mViewLongitude = null;
	private EditText mViewRemarks = null;
	private ImageView mViewImage = null;
	private Button mBtnSave = null;
	
	private String mLatitude = "";
	private String mLongitude = "";
	private String mRemarks = "";
	private String mDate = "";
	private String mTime = "";
	private String mPhtotPath = "";
	
	PVSource mDBSource = new PVSource(this);
	
	Toast toast = null;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview_photo);
		
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
		
		mViewLatitude = (TextView) findViewById(R.id.tvLatitude);
		mViewLatitude.setText(mLatitude);
		mViewLongitude = (TextView) findViewById(R.id.tvLongitude);
		mViewLongitude.setText(mLongitude);
		
		mViewRemarks = (EditText) findViewById(R.id.editRemarks);		
		mViewImage = (ImageView) findViewById(R.id.imgPreView);
		previewCapturedImage();
		
		mBtnSave = (Button) findViewById(R.id.btnSave);
		mBtnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getDate();				
				
				// Assign values in the Profile
				PVModel mDataInsert = new PVModel();
				
				mDataInsert.setmLatitude(mLatitude);
				mDataInsert.setmLongitude(mLongitude);
				mDataInsert.setmImage(mPhtotPath);
				mDataInsert.setmRemarks(mRemarks);
				mDataInsert.setmDate(mDate);
				mDataInsert.setmTime(mTime);
				
				if (mDBSource.insertData(mDataInsert) == true) {
					
					toast = Toast.makeText(PreviewPhotoActivity.this, "Successfully Saved.", Toast.LENGTH_LONG);
					toast.show();
					
					startActivity(new Intent(PreviewPhotoActivity.this, RegActivity.class));
					
				} else {
					
					toast = Toast.makeText(PreviewPhotoActivity.this, "Not Saved.", Toast.LENGTH_LONG);
					toast.show();
				}
			}			
		});
	}
	
	@Override
	public void onLocationChanged(Location location) {
		
		
		// TODO Auto-generated method stub
		double latitude = location.getLatitude();

		// Getting longitude of the current location
		double longitude = location.getLongitude();	
		
		mLatitude =  Double.toString(latitude);
		mLongitude = Double.toString(longitude);
		
	}

	
	
	protected void getDate() {
		
		mDate = new SimpleDateFormat("yyyy/MM/dd",Locale.getDefault()).format(new Date());
		mTime = DateFormat.getTimeInstance().format(new Date());
		mLatitude = mViewLatitude.getText().toString();
		mLongitude = mViewLongitude.getText().toString();
		mRemarks = mViewRemarks.getText().toString();
		
	}

	/**
	 * Display image from a path to ImageView
	 */
	private void previewCapturedImage() {
		try {
			
			// bimatp factory
			BitmapFactory.Options options = new BitmapFactory.Options();
			/**
			 * downsizing image as it throws OutOfMemory Exception for larger
			 * images
			 */
			mPhtotPath = mFileUri.getPath();
			options.inSampleSize = 8; // should be power of 2.
			Bitmap bitmap = BitmapFactory.decodeFile(mPhtotPath,options);
			
			mViewImage.setImageBitmap(bitmap);
			
			

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText( getApplicationContext(),"Gps Disabled",	Toast.LENGTH_SHORT ).show(); 
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText( getApplicationContext(),"Gps Enabled",Toast.LENGTH_SHORT).show(); 
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
