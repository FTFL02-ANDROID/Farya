package com.ftfl.icaremyself.util;

public class DoctorProfileModel {
	
	//initialization
	Integer mDoctorID = 0;		
	String mDoctorName = "";	
	String mDoctorSpecialization = "";		
	String mDoctorAddress = "";
	String mDoctorContact = "";
	String mDoctorEmail = "";

	public DoctorProfileModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DoctorProfileModel(Integer mDoctorID, String mDoctorName,
			String mDoctorSpecialization, String mDoctorAddress,
			String mDoctorContact, String mDoctorEmail) {
		super();
		this.mDoctorID = mDoctorID;
		this.mDoctorName = mDoctorName;
		this.mDoctorSpecialization = mDoctorSpecialization;
		this.mDoctorAddress = mDoctorAddress;
		this.mDoctorContact = mDoctorContact;
		this.mDoctorEmail = mDoctorEmail;

	}
	public DoctorProfileModel(String mDoctorName, String mDoctorSpecialization,
			String mDoctorAddress, String mDoctorContact, String mDoctorEmail) {
		super();
		this.mDoctorName = mDoctorName;
		this.mDoctorSpecialization = mDoctorSpecialization;
		this.mDoctorAddress = mDoctorAddress;
		this.mDoctorContact = mDoctorContact;
		this.mDoctorEmail = mDoctorEmail;

	}
	public Integer getmDoctorID() {
		return mDoctorID;
	}
	public void setmDoctorID(Integer mDoctorID) {
		this.mDoctorID = mDoctorID;
	}
	public String getmDoctorName() {
		return mDoctorName;
	}
	public void setmDoctorName(String mDoctorName) {
		this.mDoctorName = mDoctorName;
	}
	public String getmDoctorSpecialization() {
		return mDoctorSpecialization;
	}
	public void setmDoctorSpecialization(String mDoctorSpecialization) {
		this.mDoctorSpecialization = mDoctorSpecialization;
	}
	public String getmDoctorAddress() {
		return mDoctorAddress;
	}
	public void setmDoctorAddress(String mDoctorAddress) {
		this.mDoctorAddress = mDoctorAddress;
	}
	public String getmDoctorContact() {
		return mDoctorContact;
	}
	public void setmDoctorContact(String mDoctorContact) {
		this.mDoctorContact = mDoctorContact;
	}
	public String getmDoctorEmail() {
		return mDoctorEmail;
	}
	public void setmDoctorEmail(String mDoctorEmail) {
		this.mDoctorEmail = mDoctorEmail;
	}

	
}
