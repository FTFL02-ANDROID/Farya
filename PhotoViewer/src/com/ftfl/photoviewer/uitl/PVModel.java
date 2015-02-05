package com.ftfl.photoviewer.uitl;

public class PVModel {
	
	Integer mID = 0;	
	String mLatitude = "";	
	String mLongitude = "";	
	String mRemarks = "";	
	String mDate = "";	
	String mTime = "";	
	String mImage = "";
	public PVModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PVModel(Integer mID, String mLatitude, String mLongitude,
			String mRemarks, String mDate, String mTime, String mImage) {
		super();
		this.mID = mID;
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;
		this.mRemarks = mRemarks;
		this.mDate = mDate;
		this.mTime = mTime;
		this.mImage = mImage;
	}
	public PVModel(String mLatitude, String mLongitude, String mRemarks,
			String mDate, String mTime, String mImage) {
		super();
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;
		this.mRemarks = mRemarks;
		this.mDate = mDate;
		this.mTime = mTime;
		this.mImage = mImage;
	}
	public Integer getmID() {
		return mID;
	}
	public void setmID(Integer mID) {
		this.mID = mID;
	}
	public String getmLatitude() {
		return mLatitude;
	}
	public void setmLatitude(String mLatitude) {
		this.mLatitude = mLatitude;
	}
	public String getmLongitude() {
		return mLongitude;
	}
	public void setmLongitude(String mLongitude) {
		this.mLongitude = mLongitude;
	}
	public String getmRemarks() {
		return mRemarks;
	}
	public void setmRemarks(String mRemarks) {
		this.mRemarks = mRemarks;
	}
	public String getmDate() {
		return mDate;
	}
	public void setmDate(String mDate) {
		this.mDate = mDate;
	}
	public String getmTime() {
		return mTime;
	}
	public void setmTime(String mTime) {
		this.mTime = mTime;
	}
	public String getmImage() {
		return mImage;
	}
	public void setmImage(String mImage) {
		this.mImage = mImage;
	}

	
}
