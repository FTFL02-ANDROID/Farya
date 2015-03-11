package com.ftfl.icaremyself.database;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ftfl.icaremyself.util.MyMedicalHistoryModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class MyMedicalHistoryDBSource {

	private SQLiteDatabase mDB;
	private ICareMySelfDBHelper mHelper;
	
	int countResult;
	
	public MyMedicalHistoryDBSource(Context context) {
		mHelper = new ICareMySelfDBHelper(context);
	}
	
	public void open() throws SQLException {
		mDB = mHelper.getWritableDatabase();
	}

	public void close() {
		mHelper.close();
	}
	
	// Adding new
	public boolean insertData(MyMedicalHistoryModel eHistory) {
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ContentValues values = new ContentValues();		
		
		values.put(ICareMySelfDBHelper.CO_HISTORY_DOCTOR_NAME, eHistory.getmMedicalHistoryDoctorName());
		values.put(ICareMySelfDBHelper.COL_HISTORY_VISITED_DATE, eHistory.getmVisitedDate());
		values.put(ICareMySelfDBHelper.COL_HISTORY_PERPOSE, eHistory.getmHistoryPerpose());
		values.put(ICareMySelfDBHelper.COL_HISTORY_PRESCRIBTION, eHistory.getmHistoryPrescribtion());	
	
		Long inserted = mDB.insert(ICareMySelfDBHelper.MEDICAL_HISTORY_TABLE_NAME, null, values);
		close();
		
		this.close();
		if(inserted <0)
			return false;
		else
			return true;
		
	}
	
	// delete data form database.
	public boolean deleteData(Integer eHistoryId) {
		try {
			this.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mDB.delete(ICareMySelfDBHelper.MEDICAL_HISTORY_TABLE_NAME, ICareMySelfDBHelper.COL_HISTORY_ID + "=" + eHistoryId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}
	
	// update database by Id
	public long updateData(Integer eHistoryId, MyMedicalHistoryModel eHistory) {
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ContentValues values = new ContentValues();
		
		values.put(ICareMySelfDBHelper.CO_HISTORY_DOCTOR_NAME, eHistory.getmMedicalHistoryDoctorName());
		values.put(ICareMySelfDBHelper.COL_HISTORY_VISITED_DATE, eHistory.getmVisitedDate());
		values.put(ICareMySelfDBHelper.COL_HISTORY_PERPOSE, eHistory.getmHistoryPerpose());
		values.put(ICareMySelfDBHelper.COL_HISTORY_PRESCRIBTION, eHistory.getmHistoryPrescribtion());	


		long updated = 0;
		try {
			updated = mDB.update(ICareMySelfDBHelper.MEDICAL_HISTORY_TABLE_NAME, values, ICareMySelfDBHelper.COL_HISTORY_ID	+ "=" + eHistoryId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}
		
	
	// Getting All place list
	public ArrayList<MyMedicalHistoryModel> getHistoryList() {
		
		ArrayList<MyMedicalHistoryModel> mHistoryList = new ArrayList<MyMedicalHistoryModel>();
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Cursor mCursor = mDB.query(ICareMySelfDBHelper.MEDICAL_HISTORY_TABLE_NAME, null, null, null, null, null, null);
	
		// looping through all rows and adding to list
		if (mCursor != null && mCursor.getCount() > 0) {
			mCursor.moveToFirst();
			
			for (int i = 0; i < mCursor.getCount(); i++) {
				
				int mDocID = mCursor.getInt(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_HISTORY_ID));
				String mDocName = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.CO_HISTORY_DOCTOR_NAME));
				String mVisitedDate = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_HISTORY_VISITED_DATE));
				String mPerpose = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_HISTORY_PERPOSE));
				String mImgPrescribtion = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_HISTORY_PRESCRIBTION));
				
				MyMedicalHistoryModel mHistory = new MyMedicalHistoryModel(mDocID, mDocName, mVisitedDate,
						mPerpose, mImgPrescribtion );
				
				mHistoryList.add(mHistory);
				mCursor.moveToNext();
			}
		}
		mCursor.close();
		mDB.close();
	
		// return place list
		return mHistoryList;
	}
	
	// Getting All place detail
		public MyMedicalHistoryModel getDetailHistory(int eHistoryId) {
			
			try {
				open();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String selectQuery = "SELECT  * FROM " + ICareMySelfDBHelper.MEDICAL_HISTORY_TABLE_NAME+ " WHERE " + ICareMySelfDBHelper.COL_HISTORY_ID + "=" + eHistoryId;

			Cursor mCursor = mDB.rawQuery(selectQuery, null);
			
			mCursor.moveToFirst();
			
			int mDocID = mCursor.getInt(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_HISTORY_ID));
			String mDocName = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.CO_HISTORY_DOCTOR_NAME));
			String mVisitedDate = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_HISTORY_VISITED_DATE));
			String mPerpose = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_HISTORY_PERPOSE));
			String mImgPrescribtion = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_HISTORY_PRESCRIBTION));
			
			MyMedicalHistoryModel mHistory = new MyMedicalHistoryModel(mDocID, mDocName, mVisitedDate,
					mPerpose, mImgPrescribtion );
			

			mCursor.moveToNext();

			mCursor.close();
			mDB.close();

			// return place detail
			return mHistory;
		}
		
		public boolean isEmpty() {
			try {
				this.open();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Cursor cursor = mDB.query(ICareMySelfDBHelper.MEDICAL_HISTORY_TABLE_NAME, new String[] {
					ICareMySelfDBHelper.COL_HISTORY_ID, 
					ICareMySelfDBHelper.CO_HISTORY_DOCTOR_NAME, 
					ICareMySelfDBHelper.COL_HISTORY_VISITED_DATE,
					ICareMySelfDBHelper.COL_HISTORY_PERPOSE, 
					ICareMySelfDBHelper.COL_HISTORY_PRESCRIBTION, }, null, null, null,
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

