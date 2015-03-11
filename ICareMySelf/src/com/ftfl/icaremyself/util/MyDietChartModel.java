package com.ftfl.icaremyself.util;

public class MyDietChartModel {
	
	//initialization
	Integer mDietID = 0;	
	String mDietDate = "";
	String mDietTime = "";
	String mDietFoodMenu = "";	
	String mDietEventName = "";	
	String mDietAlarm = "";
	public MyDietChartModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyDietChartModel(Integer mDietID, String mDietDate,
			String mDietTime, String mDietFoodMenu, String mDietEventName,
			String mDietAlarm) {
		super();
		this.mDietID = mDietID;
		this.mDietDate = mDietDate;
		this.mDietTime = mDietTime;
		this.mDietFoodMenu = mDietFoodMenu;
		this.mDietEventName = mDietEventName;
		this.mDietAlarm = mDietAlarm;
	}
	public MyDietChartModel(String mDietDate, String mDietTime,
			String mDietFoodMenu, String mDietEventName, String mDietAlarm) {
		super();
		this.mDietDate = mDietDate;
		this.mDietTime = mDietTime;
		this.mDietFoodMenu = mDietFoodMenu;
		this.mDietEventName = mDietEventName;
		this.mDietAlarm = mDietAlarm;
	}
	public Integer getmDietID() {
		return mDietID;
	}
	public void setmDietID(Integer mDietID) {
		this.mDietID = mDietID;
	}
	public String getmDietDate() {
		return mDietDate;
	}
	public void setmDietDate(String mDietDate) {
		this.mDietDate = mDietDate;
	}
	public String getmDietTime() {
		return mDietTime;
	}
	public void setmDietTime(String mDietTime) {
		this.mDietTime = mDietTime;
	}
	public String getmDietFoodMenu() {
		return mDietFoodMenu;
	}
	public void setmDietFoodMenu(String mDietFoodMenu) {
		this.mDietFoodMenu = mDietFoodMenu;
	}
	public String getmDietEventName() {
		return mDietEventName;
	}
	public void setmDietEventName(String mDietEventName) {
		this.mDietEventName = mDietEventName;
	}
	public String getmDietAlarm() {
		return mDietAlarm;
	}
	public void setmDietAlarm(String mDietAlarm) {
		this.mDietAlarm = mDietAlarm;
	}	

}
