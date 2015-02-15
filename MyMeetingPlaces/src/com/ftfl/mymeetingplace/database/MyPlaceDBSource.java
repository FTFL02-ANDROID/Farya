package com.ftfl.mymeetingplace.database;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ftfl.mymeetingplace.uitl.MyPlaceModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class MyPlaceDBSource {
	
	private SQLiteDatabase mDB;
	private MyPlaceDBHelper mHelper;
	
	int countResult;
	
	public MyPlaceDBSource(Context context) {
		mHelper = new MyPlaceDBHelper(context);
	}
	
	public void open() throws SQLException {
		mDB = mHelper.getWritableDatabase();
	}

	public void close() {
		mHelper.close();
	}
	
	// Adding new
	public boolean insertData(MyPlaceModel ePlace) {
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ContentValues values = new ContentValues();		
		
		values.put(MyPlaceDBHelper.COL_PLACE_IMAGE, ePlace.getmImage());
		values.put(MyPlaceDBHelper.COL_PLACE_LATITUDE, ePlace.getmLatitude());
		values.put(MyPlaceDBHelper.COL_PLACE_LONGTITUDE, ePlace.getmLongitude());
		values.put(MyPlaceDBHelper.COL_PLACE_DATE, ePlace.getmDate());		
		values.put(MyPlaceDBHelper.COL_PLACE_TIME, ePlace.getmTime());
		values.put(MyPlaceDBHelper.COL_PLACE_REMARKS, ePlace.getmRemark());
		values.put(MyPlaceDBHelper.COL_PLACE_CONTACT_PERSON, ePlace.getmContactName());
		values.put(MyPlaceDBHelper.COL_PLACE_CONTACT_EMAIL, ePlace.getmContactEmail());
		values.put(MyPlaceDBHelper.COL_PLACE_CONTACT_MOBILE, ePlace.getmContaceMobile());
	
		Long inserted = mDB.insert(MyPlaceDBHelper.PLACES_TABLE_NAME, null, values);
		close();
		
		this.close();
		if(inserted <0)
			return false;
		else
			return true;
		
	}
	
	// delete data form database.
	public boolean deleteData(Integer eId) {
		try {
			this.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mDB.delete(MyPlaceDBHelper.PLACES_TABLE_NAME, MyPlaceDBHelper.COL_PLACES_ID + "=" + eId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}
	
	// update database by Id
	public long updateData(Integer eId, MyPlaceModel ePlace) {
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ContentValues values = new ContentValues();
		
		values.put(MyPlaceDBHelper.COL_PLACE_IMAGE, ePlace.getmImage());
		values.put(MyPlaceDBHelper.COL_PLACE_LATITUDE, ePlace.getmLatitude());
		values.put(MyPlaceDBHelper.COL_PLACE_LONGTITUDE, ePlace.getmLongitude());
		values.put(MyPlaceDBHelper.COL_PLACE_DATE, ePlace.getmDate());		
		values.put(MyPlaceDBHelper.COL_PLACE_TIME, ePlace.getmTime());
		values.put(MyPlaceDBHelper.COL_PLACE_REMARKS, ePlace.getmRemark());
		values.put(MyPlaceDBHelper.COL_PLACE_CONTACT_PERSON, ePlace.getmContactName());
		values.put(MyPlaceDBHelper.COL_PLACE_CONTACT_EMAIL, ePlace.getmContactEmail());
		values.put(MyPlaceDBHelper.COL_PLACE_CONTACT_MOBILE, ePlace.getmContaceMobile());

		long updated = 0;
		try {
			updated = mDB.update(MyPlaceDBHelper.PLACES_TABLE_NAME, values, MyPlaceDBHelper.COL_PLACES_ID	+ "=" + eId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}
		
	
	// Getting All place list
	public ArrayList<MyPlaceModel> getPlaceList() {
		
		ArrayList<MyPlaceModel> mPhotoList = new ArrayList<MyPlaceModel>();
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Cursor mCursor = mDB.query(MyPlaceDBHelper.PLACES_TABLE_NAME, null, null, null, null, null, null);
	
		// looping through all rows and adding to list
		if (mCursor != null && mCursor.getCount() > 0) {
			mCursor.moveToFirst();
			
			for (int i = 0; i < mCursor.getCount(); i++) {
				
				int mID = mCursor.getInt(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACES_ID));
				String mLatitude = mCursor.getString(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACE_LATITUDE));
				String mLongitude = mCursor.getString(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACE_LONGTITUDE));
				String mRemarks = mCursor.getString(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACE_REMARKS));
				String mDate = mCursor.getString(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACE_DATE));
				String mTime = mCursor.getString(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACE_TIME));
				String mImage = mCursor.getString(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACE_IMAGE));
				String mContactName = mCursor.getString(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACE_CONTACT_PERSON));
				String mContactEmail = mCursor.getString(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACE_CONTACT_EMAIL));
				String mContaceMobile = mCursor.getString(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACE_CONTACT_MOBILE));
	
				MyPlaceModel mPlace = new MyPlaceModel(mID, mLatitude, mLongitude,mRemarks, mDate, mTime, mImage, mContactName, mContactEmail, mContaceMobile);
				
				mPhotoList.add(mPlace);
				mCursor.moveToNext();
			}
		}
		mCursor.close();
		mDB.close();
	
		// return place list
		return mPhotoList;
	}
	
	// Getting All place detail
		public MyPlaceModel getDetail(int id) {
			
			try {
				open();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String selectQuery = "SELECT  * FROM " + MyPlaceDBHelper.PLACES_TABLE_NAME+ " WHERE " + MyPlaceDBHelper.COL_PLACES_ID + "=" + id;

			Cursor mCursor = mDB.rawQuery(selectQuery, null);
			
			mCursor.moveToFirst();
			
			int mID = mCursor.getInt(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACES_ID));
			String mLatitude = mCursor.getString(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACE_LATITUDE));
			String mLongitude = mCursor.getString(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACE_LONGTITUDE));
			String mRemarks = mCursor.getString(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACE_REMARKS));
			String mDate = mCursor.getString(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACE_DATE));
			String mTime = mCursor.getString(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACE_TIME));
			String mImage = mCursor.getString(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACE_IMAGE));
			String mContactName = mCursor.getString(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACE_CONTACT_PERSON));
			String mContactEmail = mCursor.getString(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACE_CONTACT_EMAIL));
			String mContaceMobile = mCursor.getString(mCursor.getColumnIndex(MyPlaceDBHelper.COL_PLACE_CONTACT_MOBILE));

			MyPlaceModel mPlaceDetail = new MyPlaceModel(mID, mLatitude, mLongitude,mRemarks, mDate, mTime, mImage, mContactName, mContactEmail, mContaceMobile);
			

			mCursor.moveToNext();

			mCursor.close();
			mDB.close();

			// return place detail
			return mPlaceDetail;
		}
		
		public boolean isEmpty() {
			try {
				this.open();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Cursor cursor = mDB.query(MyPlaceDBHelper.PLACES_TABLE_NAME, new String[] {
					MyPlaceDBHelper.COL_PLACES_ID, MyPlaceDBHelper.COL_PLACE_LATITUDE, MyPlaceDBHelper.COL_PLACE_LONGTITUDE,
					MyPlaceDBHelper.COL_PLACE_REMARKS, MyPlaceDBHelper.COL_PLACE_DATE,
					MyPlaceDBHelper.COL_PLACE_TIME, MyPlaceDBHelper.COL_PLACE_IMAGE,
					MyPlaceDBHelper.COL_PLACE_CONTACT_PERSON, MyPlaceDBHelper.COL_PLACE_CONTACT_EMAIL,
					MyPlaceDBHelper.COL_PLACE_CONTACT_MOBILE, }, null, null, null,
					null, null);

			if (cursor.getCount() == 0) {
				this.close();
				return true;
			} else {
				this.close();
				return false;
			}
		}
}
