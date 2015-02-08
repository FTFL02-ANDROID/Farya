package com.ftfl.mymeetingplace.adapter;

import java.util.List;

import com.ftfl.mymeetingplace.R;
import com.ftfl.mymeetingplace.uitl.MyPlaceModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyPlaceAdapter extends ArrayAdapter<MyPlaceModel> {
	
	//initialization variable
	private static LayoutInflater inflater = null;	
	
	String mDistance = "";
	
	@SuppressWarnings("unused")
	private final Activity context;
	List<MyPlaceModel> allPlace;
	MyPlaceModel mPlaceModel;	
	
	private double currentLatitude;
	private double currentLongitude;
	private double oldLatitude;
	private double oldLongitude;
	
	//constractor of Adapter
	public MyPlaceAdapter(Activity context,  List<MyPlaceModel> allPlace, double eCurrentLatitude, double eCurrentLongitude) {
		super(context,R.layout.simple_row_view, allPlace);
		this.context = context;
		this.allPlace = allPlace;
		this.currentLatitude = eCurrentLatitude;
		this.currentLongitude = eCurrentLongitude;
		
		//Layout inflator to call external xml layout () 
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	//Create a holder Class to contain inflated xml file elements 
	public static class ViewHolder {
		
		public ImageView mViewImage;
		
		public TextView mTvLatitude;		
		public TextView mTvLongitude;
		public TextView mTvDate;		
		public TextView mTvTime;
		public TextView mTvPlace;		
		public TextView mTvDistance;
		
	}	
	
	@SuppressLint("InflateParams")
	public View getView(int position, View convertView, ViewGroup parent) {

		View rowView = convertView;
		ViewHolder holder;
		
		if (convertView == null) {

			//Inflate tabitem.xml file for each row ( Defined below ) 
			rowView = inflater.inflate(R.layout.simple_row_view, null);

			//View Holder Object to contain tabitem.xml file elements 

			holder = new ViewHolder();
			
			//initialize variable find by id
			holder.mViewImage = (ImageView) rowView.findViewById(R.id.viewImageView);			
			holder.mTvLatitude = (TextView) rowView.findViewById(R.id.tvLatitudeView);			
			holder.mTvLongitude = (TextView) rowView.findViewById(R.id.tvLongitudeView);	
			holder.mTvPlace = (TextView) rowView.findViewById(R.id.tvPlaceView);			
			holder.mTvDate = (TextView) rowView.findViewById(R.id.tvDateView);			
			holder.mTvTime = (TextView) rowView.findViewById(R.id.tvTimeView);	
			holder.mTvDistance = (TextView) rowView.findViewById(R.id.tvDistenceView);

			//Set holder with LayoutInflater
			rowView.setTag(holder);
			
		} else
			holder = (ViewHolder) rowView.getTag();

		//get data form database
		mPlaceModel = allPlace.get(position);	
		
		String eLatitude = mPlaceModel.getmLatitude();		
		try {
			oldLatitude = Double.parseDouble(eLatitude);
		} catch (NumberFormatException e) {
			oldLatitude = 0; // default value
		}
		
		String eLongititude = mPlaceModel.getmLongitude();		
		try {
			oldLongitude = Double.parseDouble(eLongititude);
		} catch (NumberFormatException e) {
			oldLatitude = 0; // default value
		}
		
		//get location from point a
		Location locationA = new Location("point A");     
		locationA.setLatitude(currentLatitude); 
		locationA.setLongitude(currentLongitude);
		
		//get location from point b
		Location locationB = new Location("point B");
		locationB.setLatitude(oldLatitude); 
		locationB.setLongitude(oldLongitude);
		
		//get distance from point a to point b
		float distance = locationA.distanceTo(locationB) ;
		mDistance = String.valueOf(distance);
		
		//get image from sdcard of device	
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 8;
		Bitmap image = BitmapFactory.decodeFile(mPlaceModel.getmImage(), options);
		
		holder.mViewImage.setImageBitmap(image);
		holder.mTvLatitude.setText(mPlaceModel.getmLatitude().toString());
		holder.mTvLongitude.setText(mPlaceModel.getmLongitude().toString());		
		holder.mTvPlace.setText(mPlaceModel.getmRemark().toString());
		holder.mTvDate.setText(mPlaceModel.getmDate().toString());
		holder.mTvTime.setText(mPlaceModel.getmTime().toString());
		holder.mTvDistance.setText(mDistance);

		return rowView;
	}

}
