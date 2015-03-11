package com.ftfl.icaremyself.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftfl.icaremyself.R;
import com.ftfl.icaremyself.util.MyMedicalHistoryModel;

public class MadicalHistoryViewAdapter extends ArrayAdapter<MyMedicalHistoryModel> {
	
	//initialization variable
	private static LayoutInflater inflater = null;
	private final Activity mContext;
	List<MyMedicalHistoryModel> allDoc;
	MyMedicalHistoryModel mDocModel;	
	
	//constractor of Adapter
	public MadicalHistoryViewAdapter(Activity eContext,  List<MyMedicalHistoryModel> allDoc ) {
		super(eContext,R.layout.medical_history_list_view, allDoc);
		this.mContext = eContext;
		this.allDoc = allDoc;		
		
		//Layout inflator to call external xml layout () 
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	//Create a holder Class to contain inflated xml file elements 
	public static class ViewHolder {
		
		public TextView mViewDocName;
		public TextView mViewVisitedDate;		
		public TextView mViewPerpose;
	
		public ImageView mViewPrecribPhoto;		
	}
	
	@SuppressLint("InflateParams") 
	public View getView(int position, View convertView, ViewGroup parent) {

		View rowView = convertView;
		ViewHolder holder;
		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			rowView = inflater.inflate(R.layout.medical_history_list_view, null);

			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			
			holder.mViewDocName = (TextView) rowView.findViewById(R.id.viewDocName);			
			holder.mViewVisitedDate = (TextView) rowView.findViewById(R.id.viewVisitedDate);	
			holder.mViewPerpose = (TextView) rowView.findViewById(R.id.viewPerpose);
			
			holder.mViewPrecribPhoto = (ImageView) rowView.findViewById(R.id.viewPrecribPhoto);			

			/************ Set holder with LayoutInflater ************/
			rowView.setTag(holder);
			
		} else
			holder = (ViewHolder) rowView.getTag();

		//get data from database
		mDocModel = allDoc.get(position);		
			
		holder.mViewDocName.setText(mDocModel.getmMedicalHistoryDoctorName().toString());		
		holder.mViewVisitedDate.setText(mDocModel.getmVisitedDate().toString());	
		holder.mViewPerpose.setText(mDocModel.getmHistoryPerpose().toString());	
		
		//get image from sdcard of device	
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 8;
		Bitmap image = BitmapFactory.decodeFile(mDocModel.getmHistoryPrescribtion(), options);		
		holder.mViewPrecribPhoto.setImageBitmap(image);
		

		return rowView;
	}

}
