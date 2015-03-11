package com.ftfl.icaremyself.database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.sql.SQLException;

import com.ftfl.icaremyself.util.MyDietChartModel;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyDietChartDBSource {
	
	//initialization
	private SQLiteDatabase mDB;
	private ICareMySelfDBHelper mHelper;
	
	List<MyDietChartModel> todayDietChart = new ArrayList<MyDietChartModel>();
	List<String> upcomingDates = new ArrayList<String>();
	List<String> allDates = new ArrayList<String>();
	public String mCurrentDate = "";
	
	//constructor
	public MyDietChartDBSource(Context context) {
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
	
	@SuppressLint("SimpleDateFormat") public void cDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy");
		Date date = new Date();
		mCurrentDate = dateFormat.format(date);
	}
	
	// Adding new data
	public boolean insertData(MyDietChartModel eMyDiet) {
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ContentValues values = new ContentValues();		
		
		values.put(ICareMySelfDBHelper.CO_DIET_DATE, eMyDiet.getmDietDate());
		values.put(ICareMySelfDBHelper.COL_DIET_TIME, eMyDiet.getmDietTime());
		values.put(ICareMySelfDBHelper.COL_DIET_FOOD_MENU, eMyDiet.getmDietFoodMenu());
		values.put(ICareMySelfDBHelper.COL_DIET_EVENT_NAME, eMyDiet.getmDietEventName());		
		values.put(ICareMySelfDBHelper.COL_DIET_ALARM, eMyDiet.getmDietAlarm());		
	
		Long inserted = mDB.insert(ICareMySelfDBHelper.DIET_CHART_TABLE_NAME, null, values);
		close();
		
		this.close();
		if(inserted <0)
			return false;
		else
			return true;
			
	}
	
	// delete data from database.
	public boolean deleteData(Integer eDietID) {
		try {
			this.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mDB.delete(ICareMySelfDBHelper.DIET_CHART_TABLE_NAME, ICareMySelfDBHelper.COL_DIET_ID + "=" + eDietID, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}
	
	// update database by Id
	public long updateData(Integer eDietID, MyDietChartModel eMyDiet) {
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ContentValues values = new ContentValues();
		
		values.put(ICareMySelfDBHelper.CO_DIET_DATE, eMyDiet.getmDietDate());
		values.put(ICareMySelfDBHelper.COL_DIET_TIME, eMyDiet.getmDietTime());
		values.put(ICareMySelfDBHelper.COL_DIET_FOOD_MENU, eMyDiet.getmDietFoodMenu());
		values.put(ICareMySelfDBHelper.COL_DIET_EVENT_NAME, eMyDiet.getmDietEventName());		
		values.put(ICareMySelfDBHelper.COL_DIET_ALARM, eMyDiet.getmDietAlarm());

		long updated = 0;
		try {
			updated = mDB.update(ICareMySelfDBHelper.DIET_CHART_TABLE_NAME, values, ICareMySelfDBHelper.COL_DIET_ID	+ "=" + eDietID, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}
	
	// Getting all profile list
	public ArrayList<MyDietChartModel> getDietList() {
		
		ArrayList<MyDietChartModel> mDietList = new ArrayList<MyDietChartModel>();
		
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Cursor mCursor = mDB.query(ICareMySelfDBHelper.DIET_CHART_TABLE_NAME, null, null, null, null, null, null);
	
		// looping through all rows and adding to list
		if (mCursor != null && mCursor.getCount() > 0) {
			mCursor.moveToFirst();
			
			for (int i = 0; i < mCursor.getCount(); i++) {
				
				int mDietID = mCursor.getInt(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_ID));
				String mDietDate = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.CO_DIET_DATE));
				String mDietTime = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_TIME));
				String mDietFoodMenu = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_FOOD_MENU));
				String mDietEventName = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_EVENT_NAME));
				String mDietAlarm = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_ALARM));
				
				MyDietChartModel mMyDiet = new MyDietChartModel( mDietID,  mDietDate,  mDietTime,
						mDietFoodMenu,  mDietEventName,  mDietAlarm);
				
				mDietList.add(mMyDiet);
				mCursor.moveToNext();
			}
		}
		mCursor.close();
		mDB.close();
	
		// return place list
		return mDietList;
	}
	
	// using cursor for display data from database.
	public ArrayList<MyDietChartModel> dailyDietChart(String eDate) {
		
		ArrayList<MyDietChartModel> mDailyDietList = new ArrayList<MyDietChartModel>();
		
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.cDate();
		
		Cursor mCursor = mDB.query(ICareMySelfDBHelper.DIET_CHART_TABLE_NAME, null, null, null, null, null, null);
	
		// looping through all rows and adding to list
		if (mCursor != null && mCursor.getCount() > 0) {
			mCursor.moveToFirst();
			
			for (int i = 0; i < mCursor.getCount(); i++) {
				
				int mDietID = mCursor.getInt(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_ID));
				String mDietDate = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.CO_DIET_DATE));
				String mDietTime = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_TIME));
				String mDietFoodMenu = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_FOOD_MENU));
				String mDietEventName = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_EVENT_NAME));
				String mDietAlarm = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_ALARM));
				
				MyDietChartModel mMyDiet = new MyDietChartModel( mDietID,  mDietDate,  mDietTime,
						mDietFoodMenu,  mDietEventName,  mDietAlarm);
				
				mDailyDietList.add(mMyDiet);
				mCursor.moveToNext();
			}
		}
		mCursor.close();
		mDB.close();
	
		// return place list
		return mDailyDietList;
	}
	
	// using cursor for display data from database.
	public ArrayList<MyDietChartModel> todayDietChart() {
		
		ArrayList<MyDietChartModel> mTodayDietList = new ArrayList<MyDietChartModel>();
		
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.cDate();
		
		Cursor mCursor = mDB.query(ICareMySelfDBHelper.DIET_CHART_TABLE_NAME, null, null, null, null, null, null);
	
		// looping through all rows and adding to list
		if (mCursor != null && mCursor.getCount() > 0) {
			mCursor.moveToFirst();
			
			for (int i = 0; i < mCursor.getCount(); i++) {
				
				int mDietID = mCursor.getInt(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_ID));
				String mDietDate = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.CO_DIET_DATE));
				String mDietTime = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_TIME));
				String mDietFoodMenu = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_FOOD_MENU));
				String mDietEventName = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_EVENT_NAME));
				String mDietAlarm = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_ALARM));
				
				MyDietChartModel mMyTodayDiet = new MyDietChartModel( mDietID,  mDietDate,  mDietTime,
						mDietFoodMenu,  mDietEventName,  mDietAlarm);
				
				mTodayDietList.add(mMyTodayDiet);
				mCursor.moveToNext();
			}
		}
		mCursor.close();
		mDB.close();
	
		// return place list
		return mTodayDietList;
	}

	
	/*
	 * using cursor for display data from database.
	 */
	public List<String> allDates( ) {
		try {
			this.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.cDate();

		Cursor mCursor = mDB.rawQuery("SELECT DISTINCT date FROM diet_chart_table", null);     

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {
					String mDietDate = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.CO_DIET_DATE));
					allDates.add(mDietDate);


				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return allDates;
	}
	
	// Getting all profile detail
	public MyDietChartModel getDetail(int eDietID) {
		
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String selectQuery = "SELECT  * FROM " + ICareMySelfDBHelper.DIET_CHART_TABLE_NAME+ " WHERE " + ICareMySelfDBHelper.COL_DIET_ID + "=" + eDietID;

		Cursor mCursor = mDB.rawQuery(selectQuery, null);
		
		mCursor.moveToFirst();
		
		int mDietID = mCursor.getInt(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_ID));
		String mDietDate = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.CO_DIET_DATE));
		String mDietTime = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_TIME));
		String mDietFoodMenu = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_FOOD_MENU));
		String mDietEventName = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_EVENT_NAME));
		String mDietAlarm = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_DIET_ALARM));
		
		MyDietChartModel mMyDietDetail = new MyDietChartModel( mDietID,  mDietDate,  mDietTime,
				mDietFoodMenu,  mDietEventName,  mDietAlarm);		

		mCursor.moveToNext();

		mCursor.close();
		mDB.close();

		// return place detail
		return mMyDietDetail;
	}
	
	//check for empty data
	public boolean isEmpty() {
		try {
			this.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cursor cursor = mDB.query(ICareMySelfDBHelper.DIET_CHART_TABLE_NAME, new String[] {
				ICareMySelfDBHelper.COL_DIET_ID, 
				ICareMySelfDBHelper.CO_DIET_DATE, 
				ICareMySelfDBHelper.COL_DIET_TIME,
				ICareMySelfDBHelper.COL_DIET_FOOD_MENU, 
				ICareMySelfDBHelper.COL_DIET_EVENT_NAME,
				ICareMySelfDBHelper.COL_DIET_ALARM, }, 
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
