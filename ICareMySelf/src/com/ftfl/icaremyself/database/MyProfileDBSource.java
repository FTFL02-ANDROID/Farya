package com.ftfl.icaremyself.database;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ftfl.icaremyself.util.MyProfileModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyProfileDBSource {
	
	//initialization
	private SQLiteDatabase mDB;
	private ICareMySelfDBHelper mHelper;
	
	//constructor
	public MyProfileDBSource(Context context) {
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
	public boolean insertData(MyProfileModel eMyProfile) {
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ContentValues values = new ContentValues();		
		
		values.put(ICareMySelfDBHelper.COL_PROFILE_NAME, eMyProfile.getmName());
		values.put(ICareMySelfDBHelper.COL_PROFILE_BIRTH_DATE, eMyProfile.getmDateOfBirth());
		values.put(ICareMySelfDBHelper.COL_PROFILE_BLOOD_GROUP, eMyProfile.getmBloodGroup());
		values.put(ICareMySelfDBHelper.COL_PROFILE_GENDER, eMyProfile.getmGender());		
		values.put(ICareMySelfDBHelper.COL_PROFILE_HEIGHT, eMyProfile.getmHeight());
		values.put(ICareMySelfDBHelper.COL_PROFILE_WEIGHT, eMyProfile.getmWeight());
		values.put(ICareMySelfDBHelper.COL_PROFILE_IMPORTANT_NOTE, eMyProfile.getmImportantNote());
	
		Long inserted = mDB.insert(ICareMySelfDBHelper.PROFILE_TABLE_NAME, null, values);
		close();
		
		this.close();
		if(inserted <0)
			return false;
		else
			return true;
			
	}
	
	// delete data from database.
	public boolean deleteData(Integer eId) {
		try {
			this.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mDB.delete(ICareMySelfDBHelper.PROFILE_TABLE_NAME, ICareMySelfDBHelper.COL_PROFILE_ID + "=" + eId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}
	
	// update database by Id
	public long updateData(Integer eId, MyProfileModel eMyProfile) {
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ContentValues values = new ContentValues();
		
		values.put(ICareMySelfDBHelper.COL_PROFILE_NAME, eMyProfile.getmName());
		values.put(ICareMySelfDBHelper.COL_PROFILE_BIRTH_DATE, eMyProfile.getmDateOfBirth());
		values.put(ICareMySelfDBHelper.COL_PROFILE_BLOOD_GROUP, eMyProfile.getmBloodGroup());
		values.put(ICareMySelfDBHelper.COL_PROFILE_GENDER, eMyProfile.getmGender());		
		values.put(ICareMySelfDBHelper.COL_PROFILE_HEIGHT, eMyProfile.getmHeight());
		values.put(ICareMySelfDBHelper.COL_PROFILE_WEIGHT, eMyProfile.getmWeight());
		values.put(ICareMySelfDBHelper.COL_PROFILE_IMPORTANT_NOTE, eMyProfile.getmImportantNote());

		long updated = 0;
		try {
			updated = mDB.update(ICareMySelfDBHelper.PROFILE_TABLE_NAME, values, ICareMySelfDBHelper.COL_PROFILE_ID	+ "=" + eId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}
	
	// Getting all profile list
	public ArrayList<MyProfileModel> getProfileList() {
		
		ArrayList<MyProfileModel> mProfileList = new ArrayList<MyProfileModel>();
		
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Cursor mCursor = mDB.query(ICareMySelfDBHelper.PROFILE_TABLE_NAME, null, null, null, null, null, null);
	
		// looping through all rows and adding to list
		if (mCursor != null && mCursor.getCount() > 0) {
			mCursor.moveToFirst();
			
			for (int i = 0; i < mCursor.getCount(); i++) {
				
				int mID = mCursor.getInt(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_PROFILE_ID));
				String mName = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_PROFILE_NAME));
				String mDateOfBirth = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_PROFILE_BIRTH_DATE));
				String mBloodGroup = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_PROFILE_BLOOD_GROUP));
				String mGender = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_PROFILE_GENDER));
				String mHeight = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_PROFILE_HEIGHT));
				String mWeight = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_PROFILE_WEIGHT));
				String mImportantNote = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_PROFILE_IMPORTANT_NOTE));

	
				MyProfileModel mMyProfile = new MyProfileModel( mID,  mName,  mDateOfBirth,
						 mBloodGroup,  mGender,  mHeight,  mWeight,
						 mImportantNote);
				
				mProfileList.add(mMyProfile);
				mCursor.moveToNext();
			}
		}
		mCursor.close();
		mDB.close();
	
		// return place list
		return mProfileList;
	}
	

	// Getting all profile detail
	public MyProfileModel getDetail() {
		MyProfileModel mMyProfileDetail;
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		Cursor mCursor = mDB.query(
				ICareMySelfDBHelper.PROFILE_TABLE_NAME, new String[] {
						ICareMySelfDBHelper.COL_PROFILE_ID,
						ICareMySelfDBHelper.COL_PROFILE_NAME,
						ICareMySelfDBHelper.COL_PROFILE_BIRTH_DATE,
						ICareMySelfDBHelper.COL_PROFILE_BLOOD_GROUP,
						ICareMySelfDBHelper.COL_PROFILE_GENDER,
						ICareMySelfDBHelper.COL_PROFILE_HEIGHT,
						ICareMySelfDBHelper.COL_PROFILE_HEIGHT,
						ICareMySelfDBHelper.COL_PROFILE_WEIGHT,
						ICareMySelfDBHelper.COL_PROFILE_IMPORTANT_NOTE, },
						null, null, null, null, null);
		
		mCursor.moveToFirst();
		
		int mID = mCursor.getInt(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_PROFILE_ID));
		String mName = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_PROFILE_NAME));
		String mDateOfBirth = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_PROFILE_BIRTH_DATE));
		String mBloodGroup = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_PROFILE_BLOOD_GROUP));
		String mGender = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_PROFILE_GENDER));
		String mHeight = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_PROFILE_HEIGHT));
		String mWeight = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_PROFILE_WEIGHT));
		String mImportantNote = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_PROFILE_IMPORTANT_NOTE));

		mMyProfileDetail = new MyProfileModel( mID,  mName,  mDateOfBirth,
				 mBloodGroup,  mGender,  mHeight,  mWeight,
				 mImportantNote);
		

		mCursor.moveToNext();

		mCursor.close();
		mDB.close();

		// return place detail
		return mMyProfileDetail;
	}
	
	//check for empty data
	public boolean isEmpty() {
		try {
			this.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cursor cursor = mDB.query(ICareMySelfDBHelper.PROFILE_TABLE_NAME, new String[] {
				ICareMySelfDBHelper.COL_PROFILE_ID, 
				ICareMySelfDBHelper.COL_PROFILE_NAME, 
				ICareMySelfDBHelper.COL_PROFILE_BIRTH_DATE,
				ICareMySelfDBHelper.COL_PROFILE_BLOOD_GROUP, 
				ICareMySelfDBHelper.COL_PROFILE_GENDER,
				ICareMySelfDBHelper.COL_PROFILE_HEIGHT, 
				ICareMySelfDBHelper.COL_PROFILE_WEIGHT,
				ICareMySelfDBHelper.COL_PROFILE_IMPORTANT_NOTE, }, 
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
