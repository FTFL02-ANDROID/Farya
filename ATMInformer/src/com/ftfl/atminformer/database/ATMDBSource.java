package com.ftfl.atminformer.database;

import java.util.ArrayList;
import java.util.List;

import com.ftfl.atminformer.uitl.ATMProfileModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;



public class ATMDBSource {
	
	// Database fields
	private SQLiteDatabase atmDatabase;	
	private ATMDBHelper atmDbHelper;
	List<ATMProfileModel> atmProfilesList = new ArrayList<ATMProfileModel>();
	
	public ATMDBSource(Context context) {
		atmDbHelper = new ATMDBHelper(context);
	}
	
	
	//open a method for writable database
	 
	public void open() throws SQLException {
		atmDatabase = atmDbHelper.getWritableDatabase();
	}
	
	//close database connection
	 
	public void close() {
		atmDbHelper.close();
	}
	
	/*
	 * insert data into the database.
	 */

	public boolean insert(ATMProfileModel profile) {

		this.open();

		ContentValues cv = new ContentValues();
		
		//cv.put(ATMDBHelper.COL_ATM_PROFILE_IMAGE, profile.getmImage());

		cv.put(ATMDBHelper.COL_ATM_PROFILE_NAME, profile.getmName());

		cv.put(ATMDBHelper.COL_ATM_PROFILE_ADDRESS, profile.getmAddress());
		
		cv.put(ATMDBHelper.COL_ATM_PROFILE_BANK_NAME, profile.getmBankName());
		
		cv.put(ATMDBHelper.COL_ATM_PROFILE_LATITUDE, profile.getmLatitude());
		
		cv.put(ATMDBHelper.COL_ATM_PROFILE_LONGTITUDE, profile.getmLongitude());
		
		//cv.put(ATMDBHelper.COL_ATM_PROFILE_DEPOSITE, profile.getmDeposite());
	
		cv.put(ATMDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NAME, profile.getmContactPersoneName());
		
		cv.put(ATMDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NUMBER, profile.getmContactPersoneNumber());


		Long check = atmDatabase.insert(ATMDBHelper.ATM_PROFILE_TABLE, null, cv);
		atmDatabase.close();
	
		this.close();
		if(check <0)
			return false;
		else
			return true;
	}
	
	// Updating database by id
	public boolean updateData(long profileId, ATMProfileModel profile) {
		this.open();
		ContentValues cvUpdate = new ContentValues();
		
		//cvUpdate.put(ATMDBHelper.COL_ATM_PROFILE_IMAGE, profile.getmImage());

		cvUpdate.put(ATMDBHelper.COL_ATM_PROFILE_NAME, profile.getmName());
		
		cvUpdate.put(ATMDBHelper.COL_ATM_PROFILE_ADDRESS, profile.getmAddress());
		
		cvUpdate.put(ATMDBHelper.COL_ATM_PROFILE_BANK_NAME, profile.getmBankName());
		
		cvUpdate.put(ATMDBHelper.COL_ATM_PROFILE_LATITUDE, profile.getmLatitude());
		
		cvUpdate.put(ATMDBHelper.COL_ATM_PROFILE_LONGTITUDE, profile.getmLongitude());
		
		//cvUpdate.put(ATMDBHelper.COL_ATM_PROFILE_DEPOSITE, profile.getmDeposite());
		
		cvUpdate.put(ATMDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NAME, profile.getmContactPersoneName());
		
		cvUpdate.put(ATMDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NUMBER, profile.getmContactPersoneNumber());

		
		int check =  atmDatabase.update(ATMDBHelper.ATM_PROFILE_TABLE, cvUpdate,
				ATMDBHelper.COL_ATM_PROFILE_ID + "=" + profileId, null);
		atmDatabase.close();
	
		this.close();
		
		if(check ==0)
			return false;
		else
			return true;
	}
	
	// delete data form database.
	public boolean deleteData(long profileId) {
		this.open();
		try {
			atmDatabase.delete(ATMDBHelper.ATM_PROFILE_TABLE, ATMDBHelper.COL_ATM_PROFILE_ID 
					+ "=" + profileId,	null);
			
		} catch (Exception ex) {
			Log.e("ERROR", "data insertion problem");
			return false;
		}
		
		this.close();
		return true;
	}
	
	/*
	 * using cursor for display data from database.
	 */
	public List<ATMProfileModel> atmProfilesList() {
		this.open();

		Cursor mCursor = atmDatabase.query(ATMDBHelper.ATM_PROFILE_TABLE,
				new String[] { ATMDBHelper.COL_ATM_PROFILE_ID,
				//ATMDBHelper.COL_ATM_PROFILE_IMAGE,
				ATMDBHelper.COL_ATM_PROFILE_NAME,
				ATMDBHelper.COL_ATM_PROFILE_ADDRESS,
				ATMDBHelper.COL_ATM_PROFILE_BANK_NAME,
				ATMDBHelper.COL_ATM_PROFILE_LATITUDE,
				ATMDBHelper.COL_ATM_PROFILE_LONGTITUDE,
				//ATMDBHelper.COL_ATM_PROFILE_DEPOSITE,
				ATMDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NAME,
				ATMDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NUMBER}, 
							null, null, null, null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {

					String mId = mCursor.getString(mCursor.getColumnIndex("id"));
					
					//String mImage = mCursor.getString(mCursor.getColumnIndex("image"));
					
					String mName = mCursor.getString(mCursor.getColumnIndex("name"));
					
					String mAddress = mCursor.getString(mCursor.getColumnIndex("address"));
					
					String mBankName = mCursor.getString(mCursor.getColumnIndex("bank_name"));
					
					String mLatitude = mCursor.getString(mCursor.getColumnIndex("latitude"));
					
					String mLongitude = mCursor.getString(mCursor.getColumnIndex("longitude"));
					
					//String mDeposite = mCursor.getString(mCursor.getColumnIndex("deposite"));
					
					String mContactName = mCursor.getString(mCursor.getColumnIndex("persone_name"));
					
					String mContactNumber = mCursor.getString(mCursor.getColumnIndex("person_number"));
					
					// long mmId = Long.parseLong(mId);
					atmProfilesList.add(new ATMProfileModel(  mName, mAddress, mBankName, 
							mLatitude, mLongitude, mContactName, mContactNumber, mId));

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return atmProfilesList;
	}
	
	/*
	 * create a profile of ICareProfile. Here the data of the database according
	 * to the given id is set to the profile and return a profile.
	 */
	public ATMProfileModel singleProfileData(long mProfileId) {
		this.open();
		
		ATMProfileModel mProfile;
		String mId;
		//String mImage;
		String mName;		
		String mAddress;
		String mBankName;
		String mLatitude;
		String mLongitude;
		String mContactName;
		String mContactNumber;		

		Cursor mUpdateCursor = atmDatabase.query(ATMDBHelper.ATM_PROFILE_TABLE,
				new String[] { ATMDBHelper.COL_ATM_PROFILE_ID,
				//ATMDBHelper.COL_ATM_PROFILE_IMAGE,
				ATMDBHelper.COL_ATM_PROFILE_NAME,
				ATMDBHelper.COL_ATM_PROFILE_ADDRESS,
				ATMDBHelper.COL_ATM_PROFILE_BANK_NAME,
				ATMDBHelper.COL_ATM_PROFILE_LATITUDE,
				ATMDBHelper.COL_ATM_PROFILE_LONGTITUDE,
				//ATMDBHelper.COL_ATM_PROFILE_DEPOSITE,
				ATMDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NAME,
				ATMDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NUMBER},				
				ATMDBHelper.COL_ATM_PROFILE_ID + "=" + mProfileId, null,
				null, null, null);

		mUpdateCursor.moveToFirst();

		mId = mUpdateCursor.getString(0);
		
		mName = mUpdateCursor.getString(mUpdateCursor.getColumnIndex(ATMDBHelper.COL_ATM_PROFILE_NAME));
		
		//mImage = mUpdateCursor.getString(mUpdateCursor.getColumnIndex(ATMDBHelper.COL_ATM_PROFILE_IMAGE));
		
		mAddress = mUpdateCursor.getString(mUpdateCursor.getColumnIndex(ATMDBHelper.COL_ATM_PROFILE_ADDRESS));
		
		mBankName = mUpdateCursor.getString(mUpdateCursor.getColumnIndex(ATMDBHelper.COL_ATM_PROFILE_BANK_NAME));
		
		mLatitude = mUpdateCursor.getString(mUpdateCursor.getColumnIndex(ATMDBHelper.COL_ATM_PROFILE_LATITUDE));
		
		mLongitude = mUpdateCursor.getString(mUpdateCursor.getColumnIndex(ATMDBHelper.COL_ATM_PROFILE_LONGTITUDE));
		
		mContactName = mUpdateCursor.getString(mUpdateCursor.getColumnIndex(ATMDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NAME));
		
		mContactNumber = mUpdateCursor.getString(mUpdateCursor.getColumnIndex(ATMDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NUMBER));
		
		mUpdateCursor.close();
		
		mProfile = new ATMProfileModel(  mName, mAddress, mBankName, 
				mLatitude, mLongitude, mContactName, mContactNumber, mId);
		
		this.close();
		return mProfile;
	}

	
	
	public boolean isEmpty(){
		this.open();
		Cursor mCursor = atmDatabase.query(ATMDBHelper.ATM_PROFILE_TABLE,
				new String[] { ATMDBHelper.COL_ATM_PROFILE_ID,
				//ATMDBHelper.COL_ATM_PROFILE_IMAGE,
				ATMDBHelper.COL_ATM_PROFILE_NAME,
				ATMDBHelper.COL_ATM_PROFILE_ADDRESS,
				ATMDBHelper.COL_ATM_PROFILE_BANK_NAME,
				ATMDBHelper.COL_ATM_PROFILE_LATITUDE,
				ATMDBHelper.COL_ATM_PROFILE_LONGTITUDE,
				//ATMDBHelper.COL_ATM_PROFILE_DEPOSITE,
				ATMDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NAME,
				ATMDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NUMBER}, 
							null, null, null, null, null, null);
		
        if(mCursor.getCount() == 0) {
        	this.close();
        	return true;
        }
        
        else
        {
        	this.close();
        	return false;
        }
    }
}


