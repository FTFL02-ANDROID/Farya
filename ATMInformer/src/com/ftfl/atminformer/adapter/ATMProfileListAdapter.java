package com.ftfl.atminformer.adapter;

import java.util.List;


import com.ftfl.atminformer.R;
import com.ftfl.atminformer.uitl.ATMProfileModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class ATMProfileListAdapter extends ArrayAdapter<ATMProfileModel>{

	
	private static LayoutInflater inflater = null;
	
	
	@SuppressWarnings("unused")
	private final Activity context;
	List<ATMProfileModel> allProfile;
	
	public ATMProfileListAdapter(Activity context,  List<ATMProfileModel> allProfile) {
		super(context,R.layout.activity_list_view, allProfile);
		this.context = context;
		this.allProfile = allProfile;
		
		/*********** Layout inflator to call external xml layout () ***********/
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	/********* Create a holder Class to contain inflated xml file elements *********/
	public static class ViewHolder {
		
		public ImageView ivImage;
		public TextView tvName;		
		public TextView tvBankName;		
		
	}
	
	@SuppressLint("InflateParams") @Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;
		ViewHolder holder;
		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			v = inflater.inflate(R.layout.activity_list_view, null);

			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			
			holder.ivImage = (ImageView) v.findViewById(R.id.viewImage);
			holder.tvName = (TextView) v.findViewById(R.id.viewName);			
			holder.tvBankName = (TextView) v.findViewById(R.id.viewBankName);			
			

			/************ Set holder with LayoutInflater ************/
			v.setTag(holder);
			
		} else
			holder = (ViewHolder) v.getTag();

		ATMProfileModel mProfile = allProfile.get(position);
		
		//holder.ivImage.setImageBitmap(bitmap);	
		holder.tvName.setText(mProfile.getmName().toString());		
		holder.tvBankName.setText(mProfile.getmBankName().toString());		

		return v;
	}

}


