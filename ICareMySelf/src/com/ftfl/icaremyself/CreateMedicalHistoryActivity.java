package com.ftfl.icaremyself;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.ftfl.icaremyself.database.MyMedicalHistoryDBSource;
import com.ftfl.icaremyself.util.MyMedicalHistoryModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateMedicalHistoryActivity extends Activity implements OnClickListener, OnDateSetListener {
	
	// Widget GUI
	private Button mBtnSaveMedical = null;
	private Button mBtnTakePhoto = null;
	
	private ImageView mImgPrecribPhoto = null;
	
	private EditText mEtDateVisited = null;
	private EditText mEtDocName = null;
	private EditText mEtPerpose = null;
	
	//initialization string variable
	private String mDateVisited = "";
	private String mDocName = "";
	private String mPerpose = "";
	
	// file url to store image
	private Uri mFileUri = null; 
	
	private static String mPlacePhotoPath = "";
	
	private int mYear = 0;
	private int mDay = 0;
	private int mMonth = 0;
	public String mCurrentDate = "";
	
    Toast toast;
	
    MyMedicalHistoryDBSource mMyMedicalDBSource = null;
    
    final Calendar mCalendar = Calendar.getInstance(); 
    
	@SuppressLint("SimpleDateFormat") @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_medical_history);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		mCurrentDate = dateFormat.format(date);		
		
		mMyMedicalDBSource = new MyMedicalHistoryDBSource(this);		
		
		//initialize variable find by id
		mEtDocName = (EditText) findViewById(R.id.editDocName);		
	
		mEtPerpose = (EditText) findViewById(R.id.editPerpose);	
				
		mEtDateVisited = (EditText) findViewById(R.id.editDateVisited);
		mEtDateVisited.setInputType(InputType.TYPE_NULL);
		mEtDateVisited.setFocusable(true);;
		mEtDateVisited.setClickable(true);		
		mEtDateVisited.setOnClickListener(this);
		mEtDateVisited.setText(mCurrentDate);	
		
		mImgPrecribPhoto = (ImageView) findViewById(R.id.imgPrecribPhoto);
		
		mBtnTakePhoto = (Button) findViewById(R.id.btnTakePhoto);		
		mBtnTakePhoto.setOnClickListener(this);	
		
		mBtnSaveMedical = (Button) findViewById(R.id.btnSaveMedical);		
		mBtnSaveMedical.setOnClickListener(this);	
		
		// Checking camera availability
		if (!isDeviceSupportCamera()) {
			Toast.makeText(getApplicationContext(),	"Sorry! Your device doesn't support camera",Toast.LENGTH_LONG).show();
			// will close the app if the device does't have camera
			finish();
		}	
	}

	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		// TODO Auto-generated method stub
		mEtDateVisited.setText(new StringBuilder()
		// Month is 0 based so add 1
		.append(dayOfMonth).append("/").append(monthOfYear + 1).append("/").append(year));
	}
	
	//get data
	protected void getDataFromForm() {			
		 
		//get values to string
		mDateVisited = mEtDateVisited.getText().toString();		
		mDocName = mEtDocName.getText().toString();			
		mPerpose = mEtPerpose.getText().toString();
	
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

			mImgPrecribPhoto.setImageBitmap(bitmap);

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
				"ICare MySelf Gallery");

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
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.editDateVisited:
			
			mYear = mCalendar.get(Calendar.YEAR);
			mMonth = mCalendar.get(Calendar.MONTH);
			mDay = mCalendar.get(Calendar.DAY_OF_MONTH);

			DatePickerDialog dialog = new DatePickerDialog(this, this, mYear, mMonth, mDay);
			dialog.show();
			break;
			
		case R.id.btnTakePhoto:
			
			//method call to intend camera
			captureImage();
			
			break;
			
		case R.id.btnSaveMedical:
			
			//method call to get data from form
			getDataFromForm();
			
			//condition to check validity
			if (   ( !mDateVisited.equals("")) && ( !mDocName.equals("")) &&
					( !mPerpose.equals("")) && ( !mPlacePhotoPath.equals(" "))){ 					
				
				// Assign values in the Profile
				MyMedicalHistoryModel mDataInsert = new MyMedicalHistoryModel();
				
				mDataInsert.setmVisitedDate(mDateVisited);				
				mDataInsert.setmMedicalHistoryDoctorName(mDocName);								
				mDataInsert.setmHistoryPerpose(mPerpose);
				mDataInsert.setmHistoryPrescribtion(mPlacePhotoPath);
				
				if (mMyMedicalDBSource.insertData(mDataInsert) == true) {
					
					toast = Toast.makeText(CreateMedicalHistoryActivity.this, "Successfully Saved.", Toast.LENGTH_LONG);
					toast.show();
					
					//communicate from activeity to activity
					startActivity(new Intent(CreateMedicalHistoryActivity.this, MedicalHistoryListActivity.class));
					// Remove activity
					finish(); // so that, it will not get back in the previous
								// file.
					
				} else {
					
					toast = Toast.makeText(CreateMedicalHistoryActivity.this, "Not saved.", Toast.LENGTH_LONG);
					toast.show();
				}
				
			} else {
				
				toast = Toast.makeText(CreateMedicalHistoryActivity.this, "Please input all data.", Toast.LENGTH_LONG);
				toast.show();
			}
			
			break;
			
		}
		
	}

}
