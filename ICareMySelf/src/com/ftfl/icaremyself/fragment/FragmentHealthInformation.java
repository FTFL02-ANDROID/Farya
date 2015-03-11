package com.ftfl.icaremyself.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ftfl.icaremyself.DietNutritionInfoActivity;
import com.ftfl.icaremyself.GrowthInfoActivity;
import com.ftfl.icaremyself.HealthInfoActivity;
import com.ftfl.icaremyself.R;
import com.ftfl.icaremyself.VaccinationInfoActivity;

public class FragmentHealthInformation extends Fragment implements OnClickListener {
	
	// Widget GUI
	private Button mGeneralInfo = null;
	private Button mVaccineInfo = null;
	private Button mGrowthInfo = null;
	private Button mNutritionInfo = null;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

    public FragmentHealthInformation() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

          View view = inflater.inflate(R.layout.fragment_layout_health_info, container,false);
          
          mGeneralInfo = (Button) view.findViewById(R.id.lvHealthInfo);
          mGeneralInfo.setOnClickListener(this);
      		
          mVaccineInfo = (Button) view.findViewById(R.id.lvVaccinationInfo);  
          mVaccineInfo.setOnClickListener(this);
      		
          mGrowthInfo = (Button) view.findViewById(R.id.lvGrowthInfo);
          mGrowthInfo.setOnClickListener(this);
      		
          mNutritionInfo = (Button) view.findViewById(R.id.lvDietNutritionInfo);
          mNutritionInfo.setOnClickListener(this);

          return view;
    }

	@Override
	public void onClick(View v) {
		
	
		switch (v.getId()) {
    	
	    	case R.id.lvHealthInfo:    		
					
					Intent intentDiet = new Intent(	getActivity(), HealthInfoActivity.class);							
					startActivity(intentDiet);
				
	    		break;
	    		
	    	case R.id.lvVaccinationInfo:    		
				
				Intent intentVaccine = new Intent(	getActivity(), VaccinationInfoActivity.class);							
				startActivity(intentVaccine);
			
			break;
			
	    	case R.id.lvGrowthInfo:    		
				
				Intent intentGrowth = new Intent(	getActivity(), GrowthInfoActivity.class);							
				startActivity(intentGrowth);
			
			break;
			
	    	case R.id.lvDietNutritionInfo:    		
				
				Intent intentDietNutrition = new Intent(	getActivity(), DietNutritionInfoActivity.class);							
				startActivity(intentDietNutrition);
			
			break;
    		
		}
		
	}

}
