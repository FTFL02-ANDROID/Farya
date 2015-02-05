package com.ftfl.photoviewer.database;

import java.util.ArrayList;

import com.ftfl.photoviewer.uitl.PVModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class PVSource {
	
	private SQLiteDatabase mDB;
	private PVHelper mPVHelper;
	
	public PVSource(Context context) {
		mPVHelper = new PVHelper(context);
	}
	
	public void open() throws SQLException {
		mDB = mPVHelper.getWritableDatabase();
	}

	public void close() {
		mPVHelper.close();
	}
	
	// Adding new
	public boolean insertData(PVModel ePhoto) {
		open();
		ContentValues values = new ContentValues();
		
		values.put(PVHelper.COL_PV_LATITUDE, ePhoto.getmLatitude());
		values.put(PVHelper.COL_PV_LONGTITUDE, ePhoto.getmLongitude());
		values.put(PVHelper.COL_PV_REMARKS, ePhoto.getmRemarks());
		values.put(PVHelper.COL_PV_TIME, ePhoto.getmTime());
		values.put(PVHelper.COL_PV_DATE, ePhoto.getmDate());		
		values.put(PVHelper.COL_PV_IMAGE, ePhoto.getmImage());

		Long inserted = mDB.insert(PVHelper.PV_TABLE_NAME, null, values);
		close();
		
		this.close();
		if(inserted <0)
			return false;
		else
			return true;
		
	}
	
	// delete data form database.
	public boolean deleteData(Integer eId) {
		this.open();
		try {
			mDB.delete(PVHelper.PV_TABLE_NAME, PVHelper.COL_PV_ID + "=" + eId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}
	
	// update database by Id
	public long updateData(Integer eId, PVModel ePhoto) {
		open();
		
		ContentValues values = new ContentValues();
		
		values.put(PVHelper.COL_PV_LATITUDE, ePhoto.getmLatitude());
		values.put(PVHelper.COL_PV_LONGTITUDE, ePhoto.getmLongitude());
		values.put(PVHelper.COL_PV_REMARKS, ePhoto.getmRemarks());
		values.put(PVHelper.COL_PV_TIME, ePhoto.getmTime());
		values.put(PVHelper.COL_PV_DATE, ePhoto.getmDate());		
		values.put(PVHelper.COL_PV_IMAGE, ePhoto.getmImage());

		long updated = 0;
		try {
			updated = mDB.update(PVHelper.PV_TABLE_NAME, values, PVHelper.COL_PV_ID	+ "=" + eId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}
	
	// Getting All place list
	public ArrayList<PVModel> getPhotoList() {
		ArrayList<PVModel> mPhotoList = new ArrayList<PVModel>();
		open();
		
		Cursor mCursor = mDB.query(PVHelper.PV_TABLE_NAME, null, null, null, null,
				null, null);
	
		// looping through all rows and adding to list
		if (mCursor != null && mCursor.getCount() > 0) {
			mCursor.moveToFirst();
			
			for (int i = 0; i < mCursor.getCount(); i++) {
				
				int mID = mCursor.getInt(mCursor.getColumnIndex(PVHelper.COL_PV_ID));
				String mLatitude = mCursor.getString(mCursor.getColumnIndex(PVHelper.COL_PV_LATITUDE));
				String mLongitude = mCursor.getString(mCursor.getColumnIndex(PVHelper.COL_PV_LONGTITUDE));
				String mRemarks = mCursor.getString(mCursor.getColumnIndex(PVHelper.COL_PV_REMARKS));
				String mDate = mCursor.getString(mCursor.getColumnIndex(PVHelper.COL_PV_DATE));
				String mTime = mCursor.getString(mCursor.getColumnIndex(PVHelper.COL_PV_TIME));
				String mImage = mCursor.getString(mCursor.getColumnIndex(PVHelper.COL_PV_IMAGE));								
	
				PVModel mPhoto = new PVModel(mID, mLatitude, mLongitude,
												mRemarks, mDate, mTime, mImage);
				
				mPhotoList.add(mPhoto);
				mCursor.moveToNext();
			}
		}
		mCursor.close();
		mDB.close();
	
		// return place list
		return mPhotoList;
	}
	
	// Getting All place detail
	public PVModel getData(int eID) {
		PVModel mPhotoData;
		open();

		String selectQuery = "SELECT  * FROM " + PVHelper.PV_TABLE_NAME
				+ " WHERE " + PVHelper.COL_PV_ID + "=" + eID;

		Cursor mCursor = mDB.rawQuery(selectQuery, null);
		mCursor.moveToFirst();
		
		String mLatitude = mCursor.getString(mCursor.getColumnIndex(PVHelper.COL_PV_LATITUDE));
		String mLongitude = mCursor.getString(mCursor.getColumnIndex(PVHelper.COL_PV_LONGTITUDE));
		String mRemarks = mCursor.getString(mCursor.getColumnIndex(PVHelper.COL_PV_REMARKS));
		String mDate = mCursor.getString(mCursor.getColumnIndex(PVHelper.COL_PV_DATE));
		String mTime = mCursor.getString(mCursor.getColumnIndex(PVHelper.COL_PV_TIME));
		String mImage = mCursor.getString(mCursor.getColumnIndex(PVHelper.COL_PV_IMAGE));

		mPhotoData = new PVModel(eID, mLatitude, mLongitude,
				mRemarks, mDate, mTime, mImage);

		mCursor.moveToNext();

		mCursor.close();
		mDB.close();

		// return place detail
		return mPhotoData;
	}
		
	public boolean isEmpty() {
		this.open();
		Cursor mCursor = mDB.query(PVHelper.PV_TABLE_NAME, new String[] {
													PVHelper.COL_PV_ID, 
													PVHelper.COL_PV_LATITUDE, 
													PVHelper.COL_PV_LONGTITUDE,
													PVHelper.COL_PV_REMARKS,
													PVHelper.COL_PV_DATE,
													PVHelper.COL_PV_TIME,
													PVHelper.COL_PV_IMAGE,}, 
													null, null, null,null, null);

		if (mCursor.getCount() == 0) {
			this.close();
			return true;
		} else {
			this.close();
			return false;
		}
	}

}
