package com.ftfl.mymeetingplace.uitl;

public class MyPlaceModel {
	
	Integer mID = 0;		
	String mLatitude = "";	
	String mLongitude = "";		
	String mRemark = "";
	String mDate = "";
	String mTime = "";
	String mImage = "";
	String mContactName = "";
	String mContactEmail = "";
	String mContaceMobile = "";
	public MyPlaceModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyPlaceModel(Integer mID, String mLatitude, String mLongitude,
			String mRemark, String mDate, String mTime, String mImage,
			String mContactName, String mContactEmail, String mContaceMobile) {
		super();
		this.mID = mID;
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;
		this.mRemark = mRemark;
		this.mDate = mDate;
		this.mTime = mTime;
		this.mImage = mImage;
		this.mContactName = mContactName;
		this.mContactEmail = mContactEmail;
		this.mContaceMobile = mContaceMobile;
	}
	public MyPlaceModel(String mLatitude, String mLongitude, String mRemark,
			String mDate, String mTime, String mImage, String mContactName,
			String mContactEmail, String mContaceMobile) {
		super();
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;
		this.mRemark = mRemark;
		this.mDate = mDate;
		this.mTime = mTime;
		this.mImage = mImage;
		this.mContactName = mContactName;
		this.mContactEmail = mContactEmail;
		this.mContaceMobile = mContaceMobile;
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
	public String getmRemark() {
		return mRemark;
	}
	public void setmRemark(String mRemark) {
		this.mRemark = mRemark;
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
	public String getmContactName() {
		return mContactName;
	}
	public void setmContactName(String mContactName) {
		this.mContactName = mContactName;
	}
	public String getmContactEmail() {
		return mContactEmail;
	}
	public void setmContactEmail(String mContactEmail) {
		this.mContactEmail = mContactEmail;
	}
	public String getmContaceMobile() {
		return mContaceMobile;
	}
	public void setmContaceMobile(String mContaceMobile) {
		this.mContaceMobile = mContaceMobile;
	}
	
}
