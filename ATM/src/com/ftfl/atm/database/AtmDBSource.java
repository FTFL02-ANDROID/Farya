package com.ftfl.atm.database;

import java.util.ArrayList;
import java.util.List;

import com.ftfl.atm.uitl.ProfileModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AtmDBSource {
	
	// Database fields
	private SQLiteDatabase atmDatabase;	
	private AtmDBHelper atmDbHelper;
	List<ProfileModel> atmProfilesList = new ArrayList<ProfileModel>();
	
	public AtmDBSource(Context context) {
		atmDbHelper = new AtmDBHelper(context);
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

	public boolean insert(ProfileModel profile) {

		this.open();

		ContentValues cv = new ContentValues();

		cv.put(AtmDBHelper.COL_ATM_PROFILE_NAME, profile.getmName());

		cv.put(AtmDBHelper.COL_ATM_PROFILE_ADDRESS, profile.getmAddress());
		
		cv.put(AtmDBHelper.COL_ATM_PROFILE_BANK_NAME, profile.getmBankName());
		
		cv.put(AtmDBHelper.COL_ATM_PROFILE_LATITUDE, profile.getmLatitude());
		
		cv.put(AtmDBHelper.COL_ATM_PROFILE_LONGTITUDE, profile.getmLongitude());
		
		cv.put(AtmDBHelper.COL_ATM_PROFILE_DEPOSITE, profile.getmDeposite());
	
		cv.put(AtmDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NAME, profile.getmContactPersoneName());
		
		cv.put(AtmDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NUMBER, profile.getmContactPersoneNumber());


		Long check = atmDatabase.insert(AtmDBHelper.ATM_PROFILE, null, cv);
		atmDatabase.close();
	
		this.close();
		if(check <0)
			return false;
		else
			return true;
	}
	
	// Updating database by id
	public boolean updateData(long profileId, ProfileModel profile) {
		this.open();
		ContentValues cvUpdate = new ContentValues();

		cvUpdate.put(AtmDBHelper.COL_ATM_PROFILE_NAME, profile.getmName());
		
		cvUpdate.put(AtmDBHelper.COL_ATM_PROFILE_ADDRESS, profile.getmAddress());
		
		cvUpdate.put(AtmDBHelper.COL_ATM_PROFILE_BANK_NAME, profile.getmBankName());
		
		cvUpdate.put(AtmDBHelper.COL_ATM_PROFILE_LATITUDE, profile.getmLatitude());
		
		cvUpdate.put(AtmDBHelper.COL_ATM_PROFILE_LONGTITUDE, profile.getmLongitude());
		
		cvUpdate.put(AtmDBHelper.COL_ATM_PROFILE_DEPOSITE, profile.getmDeposite());
		
		cvUpdate.put(AtmDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NAME, profile.getmContactPersoneName());
		
		cvUpdate.put(AtmDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NUMBER, profile.getmContactPersoneNumber());

		
		int check =  atmDatabase.update(AtmDBHelper.ATM_PROFILE, cvUpdate,
				AtmDBHelper.COL_ATM_PROFILE_ID + "=" + profileId, null);
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
			atmDatabase.delete(AtmDBHelper.ATM_PROFILE, AtmDBHelper.COL_ATM_PROFILE_ID 
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
	public List<ProfileModel> atmProfilesList() {
		this.open();

		Cursor mCursor = atmDatabase.query(AtmDBHelper.ATM_PROFILE,
				new String[] { AtmDBHelper.COL_ATM_PROFILE_ID,
							AtmDBHelper.COL_ATM_PROFILE_NAME,
							AtmDBHelper.COL_ATM_PROFILE_ADDRESS,
							AtmDBHelper.COL_ATM_PROFILE_BANK_NAME,
							AtmDBHelper.COL_ATM_PROFILE_LATITUDE,
							AtmDBHelper.COL_ATM_PROFILE_LONGTITUDE,
							AtmDBHelper.COL_ATM_PROFILE_DEPOSITE,
							AtmDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NAME,
							AtmDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NUMBER}, 
							null, null, null, null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {

					String mId = mCursor.getString(mCursor.getColumnIndex("id"));
					
					String mName = mCursor.getString(mCursor.getColumnIndex("name"));
					
					String mAddress = mCursor.getString(mCursor.getColumnIndex("address"));
					
					String mBankName = mCursor.getString(mCursor.getColumnIndex("bank_name"));
					
					String mLatitude = mCursor.getString(mCursor.getColumnIndex("latitude"));
					
					String mLongitude = mCursor.getString(mCursor.getColumnIndex("longitude"));
					
					String mDeposite = mCursor.getString(mCursor.getColumnIndex("deposite"));
					
					String mContactName = mCursor.getString(mCursor.getColumnIndex("persone_name"));
					
					String mContactNumber = mCursor.getString(mCursor.getColumnIndex("person_number"));
					
					// long mmId = Long.parseLong(mId);
					atmProfilesList.add(new ProfileModel(mId, mName, mAddress, mBankName, mLatitude, 
							mLongitude, mDeposite, mContactName, mContactNumber));

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return atmProfilesList;
	}
	
	public boolean isEmpty(){
		this.open();
		Cursor mCursor = atmDatabase.query(AtmDBHelper.ATM_PROFILE,
				new String[] { AtmDBHelper.COL_ATM_PROFILE_ID,
							AtmDBHelper.COL_ATM_PROFILE_NAME,
							AtmDBHelper.COL_ATM_PROFILE_ADDRESS,
							AtmDBHelper.COL_ATM_PROFILE_BANK_NAME,
							AtmDBHelper.COL_ATM_PROFILE_LATITUDE,
							AtmDBHelper.COL_ATM_PROFILE_LONGTITUDE,
							AtmDBHelper.COL_ATM_PROFILE_DEPOSITE,
							AtmDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NAME,
							AtmDBHelper.COL_ATM_PROFILE_CONTACT_PERSON_NUMBER}, 
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
