package com.ftfl.icaremyself.database;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ftfl.icaremyself.util.DoctorProfileModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DoctorProfileDBSource {
	
	//initialization
	private SQLiteDatabase mDB;
	private ICareMySelfDBHelper mHelper;
	
	//constructor
	public DoctorProfileDBSource(Context context) {
		mHelper = new ICareMySelfDBHelper(context);
	}
	
	//open database to read and write
	public void open() throws SQLException {
		mDB = mHelper.getWritableDatabase();
	}

	//close database
	public void close() {
		mHelper.close();
	}
	
	// Adding new data
	public boolean insertData(DoctorProfileModel eDoctorProfile) {
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ContentValues values = new ContentValues();		
		
		values.put(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_NAME, eDoctorProfile.getmDoctorName());
		values.put(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_SPECIALIZATION, eDoctorProfile.getmDoctorSpecialization());
		values.put(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_ADDRESS, eDoctorProfile.getmDoctorAddress());
		values.put(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_CONTACT_NUMBER, eDoctorProfile.getmDoctorContact());		
		values.put(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_EMAIL, eDoctorProfile.getmDoctorEmail());

		
		Long inserted = mDB.insert(ICareMySelfDBHelper.DOCTOR_PROFILE_TABLE_NAME, null, values);
		close();
		
		this.close();
		if(inserted <0)
			return false;
		else
			return true;
			
	}
	
	// delete data from database.
	public boolean deleteData(Integer eDoctorId) {
		try {
			this.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mDB.delete(ICareMySelfDBHelper.DOCTOR_PROFILE_TABLE_NAME, ICareMySelfDBHelper.COL_DOCTOR_PROFILE_ID + "=" + eDoctorId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}
	
	// update database by Id
	public long updateData(Integer eDoctorId, DoctorProfileModel eDoctorProfile) {
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ContentValues values = new ContentValues();
		
		values.put(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_NAME, eDoctorProfile.getmDoctorName());
		values.put(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_SPECIALIZATION, eDoctorProfile.getmDoctorSpecialization());
		values.put(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_ADDRESS, eDoctorProfile.getmDoctorAddress());
		values.put(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_CONTACT_NUMBER, eDoctorProfile.getmDoctorContact());		
		values.put(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_EMAIL, eDoctorProfile.getmDoctorEmail());

		
		long updated = 0;
		try {
			updated = mDB.update(ICareMySelfDBHelper.DOCTOR_PROFILE_TABLE_NAME, values, ICareMySelfDBHelper.COL_DOCTOR_PROFILE_ID	+ "=" + eDoctorId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}
	
	// Getting all profile list
	public ArrayList<DoctorProfileModel> getDoctorProfileList() {
		
		ArrayList<DoctorProfileModel> mDoctorProfileList = new ArrayList<DoctorProfileModel>();
		
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Cursor mCursor = mDB.query(ICareMySelfDBHelper.DOCTOR_PROFILE_TABLE_NAME, null, null, null, null, null, null);
	
		// looping through all rows and adding to list
		if (mCursor != null && mCursor.getCount() > 0) {
			mCursor.moveToFirst();
			
			for (int i = 0; i < mCursor.getCount(); i++) {
				
				int mDoctorID = mCursor.getInt(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_ID));
				String mDoctorName = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_NAME));
				String mDoctorSpecialization = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_SPECIALIZATION));
				String mDoctorAddress = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_ADDRESS));
				String mDoctorContact = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_CONTACT_NUMBER));
				String mDoctorEmail = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_EMAIL));

	
				DoctorProfileModel mDoctorProfile = new DoctorProfileModel( mDoctorID,  mDoctorName,  mDoctorSpecialization,
						mDoctorAddress,  mDoctorContact,  mDoctorEmail);
				
				mDoctorProfileList.add(mDoctorProfile);
				mCursor.moveToNext();
			}
		}
		mCursor.close();
		mDB.close();
	
		// return place list
		return mDoctorProfileList;
	}
	
	// Getting all profile detail
	public DoctorProfileModel getDoctorDetail(int eDoctorId) {
		
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String selectQuery = "SELECT  * FROM " + ICareMySelfDBHelper.DOCTOR_PROFILE_TABLE_NAME+ " WHERE " + ICareMySelfDBHelper.COL_DOCTOR_PROFILE_ID + "=" + eDoctorId;

		Cursor mCursor = mDB.rawQuery(selectQuery, null);
		
		mCursor.moveToFirst();
		
		int mDoctorID = mCursor.getInt(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_ID));
		String mDoctorName = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_NAME));
		String mDoctorSpecialization = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_SPECIALIZATION));
		String mDoctorAddress = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_ADDRESS));
		String mDoctorContact = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_CONTACT_NUMBER));
		String mDoctorEmail = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DOCTOR_PROFILE_EMAIL));


		DoctorProfileModel mDoctorProfile = new DoctorProfileModel( mDoctorID,  mDoctorName,  mDoctorSpecialization,
				mDoctorAddress,  mDoctorContact,  mDoctorEmail);
		

		mCursor.moveToNext();

		mCursor.close();
		mDB.close();

		// return place detail
		return mDoctorProfile;
	}
	
	//check for empty data
	public boolean isEmpty() {
		try {
			this.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cursor cursor = mDB.query(ICareMySelfDBHelper.DOCTOR_PROFILE_TABLE_NAME, new String[] {
				ICareMySelfDBHelper.COL_DOCTOR_PROFILE_ID, 
				ICareMySelfDBHelper.COL_DOCTOR_PROFILE_NAME, 
				ICareMySelfDBHelper.COL_DOCTOR_PROFILE_SPECIALIZATION,
				ICareMySelfDBHelper.COL_DOCTOR_PROFILE_ADDRESS, 
				ICareMySelfDBHelper.COL_DOCTOR_PROFILE_CONTACT_NUMBER,
				ICareMySelfDBHelper.COL_DOCTOR_PROFILE_EMAIL,}, 
				null, null, null, null, null);

		if (cursor.getCount() == 0) {
			this.close();
			return true;
		} else {
			this.close();
			return false;
		}
	}

}
