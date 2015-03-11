package com.ftfl.icaremyself.util;

public class MyProfileModel {
	
	//initialization
	Integer mID = 0;		
	String mName = "";	
	String mDateOfBirth = "";		
	String mBloodGroup = "";
	String mGender = "";
	String mHeight = "";
	String mWeight = "";
	String mImportantNote = "";
	
	//constructor 
	public MyProfileModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyProfileModel(Integer mID, String mName, String mDateOfBirth,
			String mBloodGroup, String mGender, String mHeight, String mWeight,
			String mImportantNote) {
		super();
		this.mID = mID;
		this.mName = mName;
		this.mDateOfBirth = mDateOfBirth;
		this.mBloodGroup = mBloodGroup;
		this.mGender = mGender;
		this.mHeight = mHeight;
		this.mWeight = mWeight;
		this.mImportantNote = mImportantNote;
	}
	public MyProfileModel(String mName, String mDateOfBirth,
			String mBloodGroup, String mGender, String mHeight, String mWeight,
			String mImportantNote) {
		super();
		this.mName = mName;
		this.mDateOfBirth = mDateOfBirth;
		this.mBloodGroup = mBloodGroup;
		this.mGender = mGender;
		this.mHeight = mHeight;
		this.mWeight = mWeight;
		this.mImportantNote = mImportantNote;
	}
	
	//getter and setter
	public Integer getmID() {
		return mID;
	}
	public void setmID(Integer mID) {
		this.mID = mID;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmDateOfBirth() {
		return mDateOfBirth;
	}
	public void setmDateOfBirth(String mDateOfBirth) {
		this.mDateOfBirth = mDateOfBirth;
	}
	public String getmBloodGroup() {
		return mBloodGroup;
	}
	public void setmBloodGroup(String mBloodGroup) {
		this.mBloodGroup = mBloodGroup;
	}
	public String getmGender() {
		return mGender;
	}
	public void setmGender(String mGender) {
		this.mGender = mGender;
	}
	public String getmHeight() {
		return mHeight;
	}
	public void setmHeight(String mHeight) {
		this.mHeight = mHeight;
	}
	public String getmWeight() {
		return mWeight;
	}
	public void setmWeight(String mWeight) {
		this.mWeight = mWeight;
	}
	public String getmImportantNote() {
		return mImportantNote;
	}
	public void setmImportantNote(String mImportantNote) {
		this.mImportantNote = mImportantNote;
	}
	

}
