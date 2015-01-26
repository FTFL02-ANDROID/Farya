package com.ftfl.atm.adapter;

import java.util.List;

import com.ftfl.atm.R;
import com.ftfl.atm.uitl.ProfileModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ProfileListAdapter extends ArrayAdapter<ProfileModel>{	
	
	private static LayoutInflater inflater = null;

	@SuppressWarnings("unused")
	private final Activity context;
	List<ProfileModel> allProfile;
	
	public ProfileListAdapter(Activity context,List<ProfileModel> allProfile) {
		
		super(context, R.layout.activity_list_view, allProfile);
		this.context = context;
		this.allProfile = allProfile;
		
		/*********** Layout inflator to call external xml layout () ***********/
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	/********* Create a holder Class to contain inflated xml file elements *********/
	public static class ViewHolder {

		public TextView tvId;
		public TextView tvName;
		public TextView tvAddress;
		public TextView tvBankName;
		public TextView tvLatitude;
		public TextView tvLongitude;
		public TextView tvDeposite;
		public TextView tvPersonName;
		public TextView tvPersonNumber;

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
			
			holder.tvId = (TextView) v.findViewById(R.id.viewId);
			holder.tvName = (TextView) v.findViewById(R.id.viewName);
			holder.tvAddress = (TextView) v.findViewById(R.id.viewAddress);
			holder.tvBankName = (TextView) v.findViewById(R.id.viewBankName);
			holder.tvLatitude = (TextView) v.findViewById(R.id.viewLatitude);
			holder.tvLongitude = (TextView) v.findViewById(R.id.viewLongitude);
			holder.tvDeposite = (TextView) v.findViewById(R.id.viewDeposite);
			holder.tvPersonName = (TextView) v.findViewById(R.id.viewPersonName);
			holder.tvPersonNumber = (TextView) v.findViewById(R.id.viewPersonNumber);

			/************ Set holder with LayoutInflater ************/
			v.setTag(holder);
			
		} else
			holder = (ViewHolder) v.getTag();

		ProfileModel mProfile;
		
		mProfile = allProfile.get(position);

		holder.tvId.setText(mProfile.getId().toString());		
		holder.tvName.setText(mProfile.getmName().toString());
		holder.tvAddress.setText(mProfile.getmAddress().toString());
		holder.tvBankName.setText(mProfile.getmBankName().toString());
		holder.tvLatitude.setText(mProfile.getmLatitude().toString());		
		holder.tvLongitude.setText(mProfile.getmLongitude().toString());
		holder.tvDeposite.setText(mProfile.getmDeposite().toString());
		holder.tvPersonName.setText(mProfile.getmContactPersoneName().toString());
		holder.tvPersonNumber.setText(mProfile.getmContactPersoneNumber().toString());

		return v;
	}

}
