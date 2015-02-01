package com.ftfl.atminformer.uitl;

public class ATMProfileModel {
	
	public String id;
	public String mName;
	public String mAddress;
	public String mBankName;
	public String mLatitude;
	public String mLongitude;	
	public String mContactPersoneName;
	public String mContactPersoneNumber;
	
	
	

	public ATMProfileModel(String mName, String mAddress, String mBankName,
			String mLatitude, String mLongitude, String mContactPersoneName,
			String mContactPersoneNumber,  String id) {
		super();
		this.mName = mName;
		this.mAddress = mAddress;
		this.mBankName = mBankName;
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;
		this.mContactPersoneName = mContactPersoneName;
		this.mContactPersoneNumber = mContactPersoneNumber;
		
		this.id = id;
	}

	public ATMProfileModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ATMProfileModel(String mName, String mAddress, String mBankName,
			String mLatitude, String mLongitude, String mContactPersoneName,
			String mContactPersoneNumber) {
		super();
		this.mName = mName;
		this.mAddress = mAddress;
		this.mBankName = mBankName;
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;
		this.mContactPersoneName = mContactPersoneName;
		this.mContactPersoneNumber = mContactPersoneNumber;
		
	}	

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmAddress() {
		return mAddress;
	}

	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}

	public String getmBankName() {
		return mBankName;
	}

	public void setmBankName(String mBankName) {
		this.mBankName = mBankName;
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

	public String getmContactPersoneName() {
		return mContactPersoneName;
	}

	public void setmContactPersoneName(String mContactPersoneName) {
		this.mContactPersoneName = mContactPersoneName;
	}

	public String getmContactPersoneNumber() {
		return mContactPersoneNumber;
	}

	public void setmContactPersoneNumber(String mContactPersoneNumber) {
		this.mContactPersoneNumber = mContactPersoneNumber;
	}	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
