package com.ftfl.icaremyself.fragment;

import com.ftfl.icaremyself.HomeActivity;
import com.ftfl.icaremyself.R;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class FragmentEmergencyCall extends Fragment implements SensorEventListener {
	
		// Widget GUI
		public EditText mEtPhoneNo = null;
		
		public Button mBtnSet = null;
		
		Toast toast;
	
		SensorManager mSensorManager = null;
		Sensor mAccelerometer = null;
		long mLastUpdate = 0;
		float mLastX = 0f;
		float mLastY = 0f;
		float mLastZ = 0f;
		static final int SHAKE_THRESHOLD = 600;
		
		String phoneNumber = "";
	
		public static final String MYPREFERENCES = "MyPrefs";
		public static final String PHONE_NO = "phone_number";
		
		SharedPreferences mSharedpreferences  = null; 
 
      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
 
      public FragmentEmergencyCall() {
 
      }
 
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
            View view = inflater.inflate(R.layout.fragment_layout_emergency_call, container,false);
            
    		mBtnSet = (Button) view.findViewById(R.id.buttonSet);
    		mEtPhoneNo = (EditText) view.findViewById(R.id.emergencyPhoneNo);
    		
    		mSharedpreferences = this.getActivity().getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
    		
    		if (!mSharedpreferences.contains(PHONE_NO)) {
    			mBtnSet = (Button) view.findViewById(R.id.buttonSet);
    			mEtPhoneNo = (EditText) view.findViewById(R.id.emergencyPhoneNo);
    			phoneNumber = mEtPhoneNo.getText().toString();
    			Editor editor = mSharedpreferences.edit();
    			editor.putString(PHONE_NO, phoneNumber);
    			editor.commit();
    			
    			toast = Toast.makeText(getActivity(), " Successfully Saved. ", Toast.LENGTH_LONG);
				toast.show();	
				
				//communicate from take photo activeity to activity
				startActivity(new Intent(getActivity(), HomeActivity.class));
				getActivity().finish();
    		}

    		else {
    			mBtnSet.setVisibility(View.GONE);
    			mEtPhoneNo.setVisibility(View.GONE);
    		}

    		mSensorManager = (SensorManager) this.getActivity().getSystemService(Context.SENSOR_SERVICE);
    		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    		mSensorManager.registerListener(this, mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
 
            return view;
      }
  	@Override
  	public void onSensorChanged(SensorEvent sensorEvent) {
  		// TODO Auto-generated method stub
  		Sensor mySensor = sensorEvent.sensor;
  		if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
  			float x = sensorEvent.values[0];
  			float y = sensorEvent.values[1];
  			float z = sensorEvent.values[2];

  			long currentTime = System.currentTimeMillis();

  			if ((currentTime - mLastUpdate) > 100) {
  				long diffTime = (currentTime - mLastUpdate);
  				mLastUpdate = currentTime;

  				float speed = Math.abs(x + y + z - mLastX - mLastY - mLastZ)
  						/ diffTime * 10000;

  				if (speed > SHAKE_THRESHOLD) {

  					makeCall();
  				}
  				mLastX = x;
  				mLastY = y;
  				mLastZ = z;
  			}
  		}
  	}

  	@Override
  	public void onAccuracyChanged(Sensor sensor, int accuracy) {
  		// TODO Auto-generated method stub

  	}

  	@Override
	public void onResume() {
  		super.onResume();
  		mSensorManager.registerListener(this, mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
  	}

  	@Override
	public void onPause() {
  		super.onPause();
  		mSensorManager.unregisterListener(this);
  	}

  	public void makeCall() {

  		if (mSharedpreferences.contains(PHONE_NO)) {
  			String phnum = mSharedpreferences.getString(PHONE_NO, "");
  			Intent callIntent = new Intent(Intent.ACTION_CALL);
  			callIntent.setData(Uri.parse("tel:" + phnum));
  			startActivity(callIntent);
  		}
  	}

	
}

