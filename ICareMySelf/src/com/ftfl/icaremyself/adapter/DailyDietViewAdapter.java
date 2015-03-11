package com.ftfl.icaremyself.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftfl.icaremyself.R;
import com.ftfl.icaremyself.util.MyDietChartModel;


public class DailyDietViewAdapter extends ArrayAdapter<MyDietChartModel> {
	
	//initialization variable
	private static LayoutInflater inflater = null;
	private final Activity mContext;
	List<MyDietChartModel> allDiet;
	MyDietChartModel mDietModel;	
	
	//constractor of Adapter
	public DailyDietViewAdapter(Activity eContext,  List<MyDietChartModel> allDiet ) {
		super(eContext,R.layout.diet_list_view, allDiet);
		this.mContext = eContext;
		this.allDiet = allDiet;		
		
		//Layout inflator to call external xml layout () 
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	//Create a holder Class to contain inflated xml file elements 
	public static class ViewHolder {
		
		public TextView mDietDate;
		public TextView mDietTime;		
		public TextView mDietFoodMenu;
		public TextView mDietEventName;		
		public ImageView mDietAlarm;		
	}
	
	@SuppressLint("InflateParams") 
	public View getView(int position, View convertView, ViewGroup parent) {

		View rowView = convertView;
		ViewHolder holder;
		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			rowView = inflater.inflate(R.layout.diet_list_view, null);

			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			
			holder.mDietDate = (TextView) rowView.findViewById(R.id.viewDateOfDiet);			
			holder.mDietTime = (TextView) rowView.findViewById(R.id.viewTimeOfDiet);	
			holder.mDietFoodMenu = (TextView) rowView.findViewById(R.id.viewMyFeastName);			
			holder.mDietEventName = (TextView) rowView.findViewById(R.id.viewMyFeastManu);			
			holder.mDietAlarm = (ImageView) rowView.findViewById(R.id.viewAlarmDiet);			

			/************ Set holder with LayoutInflater ************/
			rowView.setTag(holder);
			
		} else
			holder = (ViewHolder) rowView.getTag();

		//get data from database
		mDietModel = allDiet.get(position);		
			
		holder.mDietDate.setText(mDietModel.getmDietDate().toString());		
		holder.mDietTime.setText(mDietModel.getmDietTime().toString());	
		holder.mDietFoodMenu.setText(mDietModel.getmDietFoodMenu().toString());		
		holder.mDietEventName.setText(mDietModel.getmDietEventName().toString());	
		
		String mDietAlarm = mDietModel.getmDietAlarm().toString();
		if(!mDietAlarm.equals(" ")){
		
			holder.mDietAlarm.setImageResource(R.drawable.alarm);			
	
		}
		return rowView;
	}

}
