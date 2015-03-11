package com.ftfl.icaremyself.util;

public class MyMedicalHistoryModel {
	
	//initialization
	Integer mMedicalHistoryID = 0;	
	String mMedicalHistoryDoctorName = "";		
	String mVisitedDate = "";
	String mHistoryPerpose = "";	
	String mHistoryPrescribtion = "";
	public MyMedicalHistoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyMedicalHistoryModel(Integer mMedicalHistoryID,
			String mMedicalHistoryDoctorName, String mVisitedDate,
			String mHistoryPerpose, String mHistoryPrescribtion) {
		super();
		this.mMedicalHistoryID = mMedicalHistoryID;
		this.mMedicalHistoryDoctorName = mMedicalHistoryDoctorName;
		this.mVisitedDate = mVisitedDate;
		this.mHistoryPerpose = mHistoryPerpose;
		this.mHistoryPrescribtion = mHistoryPrescribtion;
	}
	public MyMedicalHistoryModel(String mMedicalHistoryDoctorName,
			String mVisitedDate, String mHistoryPerpose,
			String mHistoryPrescribtion) {
		super();
		this.mMedicalHistoryDoctorName = mMedicalHistoryDoctorName;
		this.mVisitedDate = mVisitedDate;
		this.mHistoryPerpose = mHistoryPerpose;
		this.mHistoryPrescribtion = mHistoryPrescribtion;
	}
	public Integer getmMedicalHistoryID() {
		return mMedicalHistoryID;
	}
	public void setmMedicalHistoryID(Integer mMedicalHistoryID) {
		this.mMedicalHistoryID = mMedicalHistoryID;
	}
	public String getmMedicalHistoryDoctorName() {
		return mMedicalHistoryDoctorName;
	}
	public void setmMedicalHistoryDoctorName(String mMedicalHistoryDoctorName) {
		this.mMedicalHistoryDoctorName = mMedicalHistoryDoctorName;
	}
	public String getmVisitedDate() {
		return mVisitedDate;
	}
	public void setmVisitedDate(String mVisitedDate) {
		this.mVisitedDate = mVisitedDate;
	}
	public String getmHistoryPerpose() {
		return mHistoryPerpose;
	}
	public void setmHistoryPerpose(String mHistoryPerpose) {
		this.mHistoryPerpose = mHistoryPerpose;
	}
	public String getmHistoryPrescribtion() {
		return mHistoryPrescribtion;
	}
	public void setmHistoryPrescribtion(String mHistoryPrescribtion) {
		this.mHistoryPrescribtion = mHistoryPrescribtion;
	}
	

}
