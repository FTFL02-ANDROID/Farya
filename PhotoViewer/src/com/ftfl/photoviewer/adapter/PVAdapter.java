package com.ftfl.photoviewer.adapter;

import java.util.List;

import com.ftfl.photoviewer.R;
import com.ftfl.photoviewer.uitl.PVModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class PVAdapter extends ArrayAdapter<PVModel>{
	
private static LayoutInflater inflater = null;
	
	
	@SuppressWarnings("unused")
	private final Activity context;
	List<PVModel> allPhoto;
	
	public PVAdapter(Activity context,  List<PVModel> allProfile) {
		super(context,R.layout.simple_list_view, allProfile);
		this.context = context;
		this.allPhoto = allProfile;
		
		/*********** Layout inflator to call external xml layout () ***********/
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	/********* Create a holder Class to contain inflated xml file elements *********/
	public static class ViewHolder {
		
		public ImageView viewImage;
		public TextView tvLatitude;		
		public TextView tvLongitude;
		public TextView tvRemarks;		
		public TextView tvDistance;
		
	}
	
	@SuppressLint("InflateParams") @Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;
		ViewHolder holder;
		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			v = inflater.inflate(R.layout.simple_list_view, null);

			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			
			holder.viewImage = (ImageView) v.findViewById(R.id.viewImage);
			holder.tvLatitude = (TextView) v.findViewById(R.id.tvLatitude);			
			holder.tvLongitude = (TextView) v.findViewById(R.id.tvLongitude);	
			holder.tvRemarks = (TextView) v.findViewById(R.id.tvRemarks);			
				
			

			/************ Set holder with LayoutInflater ************/
			v.setTag(holder);
			
		} else
			holder = (ViewHolder) v.getTag();

		PVModel mPhoto = allPhoto.get(position);
		
			
		//holder.viewImage.setImageBitmap(bitmap);		
		holder.tvLatitude.setText(mPhoto.getmLatitude().toString());
		holder.tvLongitude.setText(mPhoto.getmLongitude().toString());		
		holder.tvRemarks.setText(mPhoto.getmRemarks().toString());
						

		return v;
	}

}
