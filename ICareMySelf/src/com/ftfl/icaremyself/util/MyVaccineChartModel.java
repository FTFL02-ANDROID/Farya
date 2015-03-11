package com.ftfl.icaremyself.util;

public class MyVaccineChartModel {
	
	//initialization
	Integer mVaccineID = 0;	
	String mVaccineName = "";		
	String mVaccineDate = "";
	String mVaccineTaken = "";
	public MyVaccineChartModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyVaccineChartModel(Integer mVaccineID, String mVaccineName,
			String mVaccineDate,  String mVaccineTaken) {
		super();
		this.mVaccineID = mVaccineID;
		this.mVaccineName = mVaccineName;
		this.mVaccineDate = mVaccineDate;
		this.mVaccineTaken = mVaccineTaken;
	}
	public MyVaccineChartModel(String mVaccineName, String mVaccineDate, String mVaccineTaken) {
		super();
		this.mVaccineName = mVaccineName;
		this.mVaccineDate = mVaccineDate;
		this.mVaccineTaken = mVaccineTaken;
	}
	public Integer getmVaccineID() {
		return mVaccineID;
	}
	public void setmVaccineID(Integer mVaccineID) {
		this.mVaccineID = mVaccineID;
	}
	public String getmVaccineName() {
		return mVaccineName;
	}
	public void setmVaccineName(String mVaccineName) {
		this.mVaccineName = mVaccineName;
	}
	public String getmVaccineDate() {
		return mVaccineDate;
	}
	public void setmVaccineDate(String mVaccineDate) {
		this.mVaccineDate = mVaccineDate;
	}
	public String getmVaccineTaken() {		
		return mVaccineTaken;
	}
	public void setmVaccineTaken(String mVaccineTaken) {
		this.mVaccineTaken = mVaccineTaken;
	}
	
	

}
