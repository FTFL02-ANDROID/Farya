package com.ftfl.atminformer;



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import com.ftfl.atminformer.database.ATMDBSource;
import com.ftfl.atminformer.uitl.ATMProfileModel;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateProfileActivity extends Activity  {
	
	Button btnSave = null;
	
	@SuppressWarnings("unused")
	private static String mCurrentPhotoPath = null;

	private Uri mFileUri = null; // file url to store image/video
	private Uri mCropUri = null; // file url to store Crop Image

	private ImageView mImgPreview = null;
	private Button mBtnCapturePicture = null;
	
	EditText etName = null;
	EditText etAddress = null;
	EditText etBankName = null;
	EditText etLatitude = null;
	EditText etLongitude = null;	
	EditText etContactPersonName = null;
	EditText etContactPersonNumber = null;
	
	Toast toast = null;
	
	
	String mName = "";
	String mAddress = "";
	String mBankName = "";
	String mLatitude = "";
	String mLongitude = "";	
	String mContactName = "";
	String mContactNumber = "";
	
	
	
	ATMDBSource mDBSource = new ATMDBSource(this);
	

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
		etContactPersonName = (EditText) findViewById(R.id.createPersonName);
		etContactPersonNumber = (EditText) findViewById(R.id.createPersonNumber);
		mImgPreview = (ImageView) findViewById(R.id.imgPreview);
		
		mBtnCapturePicture = (Button) findViewById(R.id.btnCapturePicture);
		mBtnCapturePicture.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				captureImage();
				
			}			
		});
		
		// Checking camera availability
			if (!isDeviceSupportCamera()) {
				Toast.makeText(getApplicationContext(),
						"Sorry! Your device doesn't support camera",
						Toast.LENGTH_LONG).show();
				// will close the app if the device does't have camera
				finish();
			}
		
		btnSave = (Button) findViewById(R.id.btnSave);
		btnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mName = etName.getText().toString();
				mAddress = etAddress.getText().toString();
				mBankName = etBankName.getText().toString();
				mLatitude = etLatitude.getText().toString();
				mLongitude = etLongitude.getText().toString();				
				mContactName = etContactPersonName.getText().toString();
				mContactNumber = etContactPersonNumber.getText().toString();
				
				// Assign values in the Profile
				ATMProfileModel profileDataInsert = new ATMProfileModel();
				
				profileDataInsert.setmName(mName);
				profileDataInsert.setmAddress(mAddress);
				profileDataInsert.setmBankName(mBankName);
				profileDataInsert.setmLatitude(mLatitude);
				profileDataInsert.setmLongitude(mLongitude);				
				profileDataInsert.setmContactPersoneName(mContactName);
				profileDataInsert.setmContactPersoneNumber(mContactNumber);
							
			    
				
				if (mDBSource.insert(profileDataInsert) == true) {
					
					toast = Toast.makeText(CreateProfileActivity.this, "Successfully Saved.", Toast.LENGTH_LONG);
					toast.show();
					
					startActivity(new Intent(CreateProfileActivity.this, ViewProfileListActivity.class));
					
				} else {
					
					toast = Toast.makeText(CreateProfileActivity.this, "Not Saved.", Toast.LENGTH_LONG);
					toast.show();
				}
				
			}			
		});
	}
	
	private boolean isDeviceSupportCamera() {
		if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			// this device has a camera
			return true;
		} else {
			// no camera on this device
			return false;
		}
	}
	
	/**
	 * Capturing Camera Image will launch camera app request image capture
	 */
	private void captureImage() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		mFileUri = getOutputMediaFileUri(1);

		intent.putExtra(MediaStore.EXTRA_OUTPUT, mFileUri);

		// start the image capture Intent
		startActivityForResult(intent, 100);

	}
	
	/**
	 * Here we store the file url as it will be null after returning from camera
	 * app
	 */
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
	
	/**
	 * Receiving activity result method will be called after closing the camera
	 * */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// if the result is capturing Image
		if (requestCode == 100) {
			if (resultCode == RESULT_OK) {
				// successfully captured the image
				// display it in image view
				previewCapturedImage();
				// carry out the crop operation
				performCrop();
			} // user is returning from cropping the image
			else if (requestCode == 200) {
				previewCropped();

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

	/**
	 * Display image from a path to ImageView
	 */
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

			mImgPreview.setImageBitmap(bitmap);

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	private void previewCropped() {

		try {
			// bitmap factory
			BitmapFactory.Options options = new BitmapFactory.Options();
			/**
			 * down sizing image as it throws OutOfMemory Exception for larger
			 * images
			 **/
			options.inSampleSize = 8; // should always be, power of 2

			final Bitmap bitmap = BitmapFactory.decodeFile(mCropUri.getPath(),
					options);

			mImgPreview.setImageBitmap(bitmap);

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	private void performCrop() {

		try {
			/**
			 * call the standard crop action intent (the user device may not
			 * support it)
			 */
			Intent cropIntent = new Intent("com.android.camera.action.CROP");
			// indicate image type and Uri
			cropIntent.setDataAndType(mFileUri, "image/*");
			// set crop properties
			cropIntent.putExtra("crop", "true");
			// indicate aspect of desired crop
			cropIntent.putExtra("aspectX", 0);
			cropIntent.putExtra("aspectY", 0);
			// indicate output X and Y
			cropIntent.putExtra("outputX", 200);
			cropIntent.putExtra("outputY", 150);
			// retrieve data on return, true for bitmap type
			cropIntent.putExtra("return-data", false);

			mCropUri = getOutputMediaFileUri(2);
			// save output image in uri
			cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCropUri);
			// start the activity - we handle returning in onActivityResult
			startActivityForResult(cropIntent, 200);

		} catch (ActivityNotFoundException anfe) {
			// display an error message
			String errorMessage = "Whoops - your device doesn't support the crop action!";
			Toast toast = Toast
					.makeText(this, errorMessage, Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	/**
	 * ------------ Helper Methods ----------------------
	 * */

	/**
	 * Creating file uri to store image/video
	 */
	public Uri getOutputMediaFileUri(int type) {
		return Uri.fromFile(getOutputMediaFile(type));
	}

	/**
	 * returning image / video
	 */
	private static File getOutputMediaFile(int type) {

		// External sdcard mLocation
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"ATM Gallery");

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("Public_university Gallery", "Oops! Failed create "+ "ATM Gallery" + " directory");
				return null;
			}
		}

		// Create a media file mName
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(new Date());

		File mediaFile;

		if (type == 1) {

			mediaFile = new File(mediaStorageDir.getPath() + File.separator+ "IMG_" + timeStamp + ".jpg");

		} else if (type == 2) {

			mediaFile = new File(mediaStorageDir.getPath() + File.separator+ "IMG_" + "CROPPED_" + timeStamp + ".jpg");

			// Save a file: path for use with ACTION_VIEW intents
			mCurrentPhotoPath = mediaFile.getAbsolutePath();
		} else {
			return null;
		}

		return mediaFile;
	}
}
