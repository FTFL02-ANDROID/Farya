package com.ftfl.icaremyself.adapter;

import java.util.List;

import com.ftfl.icaremyself.R;
import com.ftfl.icaremyself.util.DoctorProfileModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DoctorProfileViewAdapter extends ArrayAdapter<DoctorProfileModel>{
	
	//initialization variable
	private static LayoutInflater inflater = null;
	private final Activity mContext;
	List<DoctorProfileModel> allDoctor;
	DoctorProfileModel mDoctorModel;
	
	//constractor of Adapter
	public DoctorProfileViewAdapter(Activity eContext,  List<DoctorProfileModel> allDoctor ) {
		super(eContext,R.layout.doctor_profile_list_view, allDoctor);
		this.mContext = eContext;
		this.allDoctor = allDoctor;		
		
		//Layout inflator to call external xml layout () 
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	//Create a holder Class to contain inflated xml file elements 
	public static class ViewHolder {
		
		public TextView mDoctorName;
		public TextView mDoctorSpecialization;		
		//public TextView mDoctorAddress;
		//public TextView mDoctorContact;		
		//public TextView mDoctorEmail;		
			
	}
	
	@SuppressLint("InflateParams") 
	public View getView(int position, View convertView, ViewGroup parent) {

		View rowView = convertView;
		ViewHolder holder;
		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			rowView = inflater.inflate(R.layout.doctor_profile_list_view, null);

			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			
			holder.mDoctorName = (TextView) rowView.findViewById(R.id.viewDocName);			
			holder.mDoctorSpecialization = (TextView) rowView.findViewById(R.id.viewDocSpecialization);	
			//holder.mDoctorAddress = (TextView) rowView.findViewById(R.id.viewMyFeastName);			
			//holder.mDoctorContact = (TextView) rowView.findViewById(R.id.viewMyFeastManu);
			//holder.mDoctorEmail = (TextView) rowView.findViewById(R.id.viewTimeOfDiet);					

			/************ Set holder with LayoutInflater ************/
			rowView.setTag(holder);
			
		} else
			holder = (ViewHolder) rowView.getTag();

		//get data from database
		mDoctorModel = allDoctor.get(position);		
			
		holder.mDoctorName.setText(mDoctorModel.getmDoctorName().toString());		
		holder.mDoctorSpecialization.setText(mDoctorModel.getmDoctorSpecialization().toString());	
		//holder.mDoctorAddress.setText(mDoctorModel.getmDoctorAddress().toString());		
		//holder.mDoctorContact.setText(mDoctorModel.getmDoctorContact().toString());
		//holder.mDoctorEmail.setText(mDoctorModel.getmDoctorEmail().toString());	
			
		return rowView;
	}

}
