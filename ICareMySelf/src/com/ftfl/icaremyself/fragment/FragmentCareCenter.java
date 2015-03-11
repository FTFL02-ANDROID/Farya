package com.ftfl.icaremyself.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ftfl.icaremyself.CareCenterGoogleMapActivity;
import com.ftfl.icaremyself.R;

public class FragmentCareCenter extends Fragment implements OnClickListener  {
	
	// Widget GUI
	private Button mBtnDhakaMedicalInfo = null;
	private Button mBtnGovtHomiopathyMedicalInfo = null;
	private Button mBtnDhakaDentalInfo = null;
	private Button mBtnGovtAyurvedicMedicalInfo = null;
	
	private String mAddress = "";
	private String mLatitude = "";
	private String mLongitude = "";

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

    public FragmentCareCenter() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

          View view = inflater.inflate(R.layout.fragment_layout_care_center, container,false);
          
          mBtnDhakaMedicalInfo = (Button) view.findViewById(R.id.btnDhakaMedicalInfo);
          mBtnDhakaMedicalInfo.setOnClickListener(this);
      		
          mBtnGovtHomiopathyMedicalInfo = (Button) view.findViewById(R.id.btnGovtHomiopathyMedicalInfo);  
          mBtnGovtHomiopathyMedicalInfo.setOnClickListener(this);
      		
          mBtnDhakaDentalInfo = (Button) view.findViewById(R.id.btnDhakaDentalInfo);
          mBtnDhakaDentalInfo.setOnClickListener(this);
      		
          mBtnGovtAyurvedicMedicalInfo = (Button) view.findViewById(R.id.btnGovtAyurvedicMedicalInfo);
          mBtnGovtAyurvedicMedicalInfo.setOnClickListener(this);

          return view;
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
    	
    	case R.id.btnDhakaMedicalInfo:    		
				
				Intent intentDhakaMedical = new Intent( getActivity(), CareCenterGoogleMapActivity.class);
				intentDhakaMedical.putExtra( mAddress , "Dhaka Medical");
				intentDhakaMedical.putExtra( mLatitude , "23.725214300000000000");
				intentDhakaMedical.putExtra( mLongitude , "90.397499599999950000");
				startActivity(intentDhakaMedical);
			
    		break;
    		
    	case R.id.btnGovtHomiopathyMedicalInfo:    		
			
				Intent intentGovtHomiopathyMedical = new Intent( getActivity(), CareCenterGoogleMapActivity.class);	
				intentGovtHomiopathyMedical.putExtra( mAddress ,"Govt Homiopathy Medical");
				intentGovtHomiopathyMedical.putExtra( mLatitude , "23.803514900000000000");
				intentGovtHomiopathyMedical.putExtra( mLongitude , "90.388520700000070000");
				startActivity(intentGovtHomiopathyMedical);
			
			break;
		
    	case R.id.btnDhakaDentalInfo:    		
			
				Intent intentDhakaDental = new Intent( getActivity(), CareCenterGoogleMapActivity.class);
				intentDhakaDental.putExtra( mAddress ,"Dhaka Dental");
				intentDhakaDental.putExtra( mLatitude ,"23.799054300000000000");
				intentDhakaDental.putExtra( mLongitude ,"90.387931999999980000");
				startActivity(intentDhakaDental);
			
			break;
		
    	case R.id.btnGovtAyurvedicMedicalInfo:    		
			
				Intent intentGovtAyurvedicMedical = new Intent( getActivity(), CareCenterGoogleMapActivity.class);		
				intentGovtAyurvedicMedical.putExtra( mAddress ,"Govt Ayurvedic Medical");
				intentGovtAyurvedicMedical.putExtra( mLatitude ,"23.804657");
				intentGovtAyurvedicMedical.putExtra( mLongitude ,"90.37794469999994");
				startActivity(intentGovtAyurvedicMedical);
			
			break;
			
	}
		
	}

}