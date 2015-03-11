package com.ftfl.icaremyself.fragment;

import com.ftfl.icaremyself.DietChartListActivity;
import com.ftfl.icaremyself.MedicalHistoryListActivity;
import com.ftfl.icaremyself.VaccineChartListActivity;
import com.ftfl.icaremyself.ViewProfileActivity;

import com.ftfl.icaremyself.R;
import com.ftfl.icaremyself.database.MyProfileDBSource;
import com.ftfl.icaremyself.util.MyProfileModel;

import android.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.Button;

import android.widget.TextView;
 
public class FragmentMyProfile   extends Fragment implements OnClickListener { 
	
	// Widget GUI
	public TextView mName = null;
	public TextView mDateOfBirth = null;		
	public TextView mBloodGroup = null;
	public TextView mGender = null;		
	public TextView mHeight = null;
	public TextView mWeight = null;		
	public TextView mImportantNote = null;
	
	private Button mLayoutViewDiet = null;
	private Button mLayoutViewVaccin = null;
	private Button mLayoutViewMedicalHistory = null; 
    
    //database initialization
 	MyProfileDBSource mDBSource;  	
  	MyProfileModel mProfile;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

    public FragmentMyProfile()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {    	
    	
    	mDBSource = new MyProfileDBSource(getActivity());		
    	mProfile = mDBSource.getDetail();

        View view =inflater.inflate(R.layout.fragment_layout_my_profile,container, false);
        
        //variable initialization
        mName = (TextView) view.findViewById(R.id.viewName);		
		mImportantNote = (TextView) view.findViewById(R.id.viewNote);	
		
		mName.setText(mProfile.getmName());		
		mImportantNote.setText(mProfile.getmImportantNote());	
  		
  		mLayoutViewDiet = (Button) view.findViewById(R.id.layoutViewDiet);
  		mLayoutViewDiet.setOnClickListener(this);
  		
  		mLayoutViewVaccin = (Button) view.findViewById(R.id.layoutViewVaccin);  
  		mLayoutViewVaccin.setOnClickListener(this);
  		
  		mLayoutViewMedicalHistory = (Button) view.findViewById(R.id.layoutViewMedicalHistory);
  		mLayoutViewMedicalHistory.setOnClickListener(this);

        return view;
    }

    @Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);		
	}	
    
	@Override
	public void onClick(View v) {    	
    
    	switch (v.getId()) {
    	
    	case R.id.layoutViewDiet:    		
			
				Intent intentDiet = new Intent(	getActivity(), DietChartListActivity.class);							
				startActivity(intentDiet);
			
    		break;   
    		
    	case R.id.layoutViewVaccin:    		
				
				Intent intentVaccin = new Intent(	getActivity(), VaccineChartListActivity.class);							
				startActivity(intentVaccin);
    		break; 
    		
    	case R.id.layoutViewMedicalHistory:    		
			
				Intent intentMedicalHistory = new Intent(	getActivity(), MedicalHistoryListActivity.class);							
				startActivity(intentMedicalHistory);
			
    		break;   
    		
    		
    	}		
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
			inflater.inflate(R.menu.my_profile, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		   // handle item selection
		   switch (item.getItemId()) {
		      case R.id.view:
		    	  view();
		         return true;
		      default:
		         return super.onOptionsItemSelected(item);
		}
	}

	private void view() {
		Intent intent = new Intent(getActivity(), ViewProfileActivity.class);
		startActivity(intent);
		
	}
}