package com.ftfl.atm.uitl;

public class ProfileModel {
	
	public String mName;
	public String mAddress;
	public String mBankName;
	public String mLatitude;
	public String mLongitude;
	public String mDeposite;
	public String mContactPersoneName;
	public String mContactPersoneNumber;
	
	String id;
	
	public ProfileModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfileModel(String mName, String mAddress, String mBankName,
			String mLatitude, String mLongitude, String mDeposite,
			String mContactPersoneName, String mContactPersoneNumber, String id) {
		super();
		this.mName = mName;
		this.mAddress = mAddress;
		this.mBankName = mBankName;
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;
		this.mDeposite = mDeposite;
		this.mContactPersoneName = mContactPersoneName;
		this.mContactPersoneNumber = mContactPersoneNumber;
		this.id = id;
	}

	public ProfileModel(String mName, String mAddress, String mBankName,
			String mLatitude, String mLongitude, String mDeposite,
			String mContactPersoneName, String mContactPersoneNumber) {
		super();
		this.mName = mName;
		this.mAddress = mAddress;
		this.mBankName = mBankName;
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;
		this.mDeposite = mDeposite;
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

	public String getmDeposite() {
		return mDeposite;
	}

	public void setmDeposite(String mDeposite) {
		this.mDeposite = mDeposite;
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
