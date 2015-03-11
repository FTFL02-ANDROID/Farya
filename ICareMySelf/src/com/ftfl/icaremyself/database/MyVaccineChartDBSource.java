package com.ftfl.icaremyself.database;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ftfl.icaremyself.util.MyVaccineChartModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyVaccineChartDBSource {
	
	//initialization
	private SQLiteDatabase mDB;
	private ICareMySelfDBHelper mHelper;
	
	//constructor
	public MyVaccineChartDBSource(Context context) {
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
	public boolean insertData(MyVaccineChartModel eVaccineList) {
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ContentValues values = new ContentValues();		
		
		values.put(ICareMySelfDBHelper.CO_VACCINE_DATE, eVaccineList.getmVaccineDate());
		values.put(ICareMySelfDBHelper.COL_VACCINE_NAME, eVaccineList.getmVaccineName());
		values.put(ICareMySelfDBHelper.COL_VACCINE_TAKEN, eVaccineList.getmVaccineTaken());
		
		Long inserted = mDB.insert(ICareMySelfDBHelper.VACCINE_CHART_TABLE_NAME, null, values);
		close();
		
		this.close();
		if(inserted <0)
			return false;
		else
			return true;
			
	}
	
	// delete data from database.
	public boolean deleteData(Integer eVaccineId) {
		try {
			this.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mDB.delete(ICareMySelfDBHelper.VACCINE_CHART_TABLE_NAME, ICareMySelfDBHelper.COL_VACCINE_ID + "=" + eVaccineId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}
	
	// update database by Id
	public long updateData(Integer eVaccineId, MyVaccineChartModel eVaccineList) {
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ContentValues values = new ContentValues();
		
		values.put(ICareMySelfDBHelper.CO_VACCINE_DATE, eVaccineList.getmVaccineDate());
		values.put(ICareMySelfDBHelper.COL_VACCINE_NAME, eVaccineList.getmVaccineName());
		values.put(ICareMySelfDBHelper.COL_VACCINE_TAKEN, eVaccineList.getmVaccineTaken());
		
		long updated = 0;
		try {
			updated = mDB.update(ICareMySelfDBHelper.VACCINE_CHART_TABLE_NAME, values, ICareMySelfDBHelper.COL_VACCINE_ID	+ "=" + eVaccineId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}
	
	// Getting all profile list
	public ArrayList<MyVaccineChartModel> getVaccineList() {
		
		ArrayList<MyVaccineChartModel> mVaccineList = new ArrayList<MyVaccineChartModel>();
		
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Cursor mCursor = mDB.query(ICareMySelfDBHelper.VACCINE_CHART_TABLE_NAME, null, null, null, null, null, null);
	
		// looping through all rows and adding to list
		if (mCursor != null && mCursor.getCount() > 0) {
			mCursor.moveToFirst();
			
			for (int i = 0; i < mCursor.getCount(); i++) {
				
				int mVaccineID = mCursor.getInt(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_VACCINE_ID));
				String mVaccineName = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_VACCINE_NAME));
				String mVaccineDate = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.CO_VACCINE_DATE));
				String mVaccineTaken = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_VACCINE_TAKEN));
				
				MyVaccineChartModel mVaccine = new MyVaccineChartModel( mVaccineID,  mVaccineName,  mVaccineDate, mVaccineTaken);
				
				mVaccineList.add(mVaccine);
				mCursor.moveToNext();
			}
		}
		mCursor.close();
		mDB.close();
	
		// return place list
		return mVaccineList;
	}
	
	// Getting all profile detail
	public MyVaccineChartModel getVaccineDetail(int eVaccineId) {
		
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String selectQuery = "SELECT  * FROM " + ICareMySelfDBHelper.VACCINE_CHART_TABLE_NAME+ " WHERE " + ICareMySelfDBHelper.COL_VACCINE_ID + "=" + eVaccineId;

		Cursor mCursor = mDB.rawQuery(selectQuery, null);
		
		mCursor.moveToFirst();
		
		int mVaccineID = mCursor.getInt(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_VACCINE_ID));
		String mVaccineName = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_VACCINE_NAME));
		String mVaccineDate = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.CO_VACCINE_DATE));
		String mVaccineTaken = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_VACCINE_TAKEN));
		
		MyVaccineChartModel mVaccine = new MyVaccineChartModel( mVaccineID,  mVaccineName,  mVaccineDate, mVaccineTaken);
		

		mCursor.moveToNext();

		mCursor.close();
		mDB.close();

		// return place detail
		return mVaccine;
	}

}
