package com.ftfl.icaremyself.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ftfl.icaremyself.R;
import com.ftfl.icaremyself.util.MyVaccineChartModel;

public class VaccineViewAdapter  extends ArrayAdapter<MyVaccineChartModel> {
	
	//initialization variable
	private static LayoutInflater inflater = null;
	private final Activity mContext;
	List<MyVaccineChartModel> allVaccine;
	MyVaccineChartModel mVaccineModel;	
	
	//constractor of Adapter
	public VaccineViewAdapter(Activity eContext,  List<MyVaccineChartModel> allVaccine ) {
		super(eContext,R.layout.vaccine_list_view, allVaccine);
		this.mContext = eContext;
		this.allVaccine = allVaccine;		
		
		//Layout inflator to call external xml layout () 
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	//Create a holder Class to contain inflated xml file elements 
	public static class ViewHolder {
		
		public TextView mVaccineDate;	
		public TextView mVaccineTaken;
		public TextView mVaccineName;			
	}
	
	@SuppressLint("InflateParams") 
	public View getView(int position, View convertView, ViewGroup parent) {

		View rowView = convertView;
		ViewHolder holder;
		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			rowView = inflater.inflate(R.layout.vaccine_list_view, null);

			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			
			holder.mVaccineDate = (TextView) rowView.findViewById(R.id.viewDateOfVaccine);			
			holder.mVaccineName = (TextView) rowView.findViewById(R.id.viewVaccineName);	
			holder.mVaccineTaken = (TextView) rowView.findViewById(R.id.viewVaccineTaken);				

			/************ Set holder with LayoutInflater ************/
			rowView.setTag(holder);
			
		} else
			holder = (ViewHolder) rowView.getTag();

		//get data from database
		mVaccineModel = allVaccine.get(position);		
			
		holder.mVaccineDate.setText(mVaccineModel.getmVaccineDate().toString());		
		holder.mVaccineName.setText(mVaccineModel.getmVaccineName().toString());		
		
		String str = mVaccineModel.getmVaccineTaken().toString();
		
		if(str != null && str.equals("true")){
			 holder.mVaccineTaken.setVisibility(View.VISIBLE);
		}
		return rowView;
	}

}
