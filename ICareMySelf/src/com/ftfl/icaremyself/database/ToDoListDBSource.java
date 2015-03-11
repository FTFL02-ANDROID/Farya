package com.ftfl.icaremyself.database;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ftfl.icaremyself.util.ToDoListModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ToDoListDBSource {
	
	//initialization
	private SQLiteDatabase mDB;
	private ICareMySelfDBHelper mHelper;
	
	//constructor
	public ToDoListDBSource(Context context) {
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
	public boolean insertData(ToDoListModel eToDoList) {
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ContentValues values = new ContentValues();		
		
		values.put(ICareMySelfDBHelper.COL_TODO_LIST_WHAT_TODO, eToDoList.getmWhatToDo());
		values.put(ICareMySelfDBHelper.COL_TODO_LIST_DATE, eToDoList.getmDate());
		
		Long inserted = mDB.insert(ICareMySelfDBHelper.TODO_LIST_NAME, null, values);
		close();
		
		this.close();
		if(inserted <0)
			return false;
		else
			return true;
			
	}
	
	// delete data from database.
	public boolean deleteData(Integer eToDoId) {
		try {
			this.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mDB.delete(ICareMySelfDBHelper.TODO_LIST_NAME, ICareMySelfDBHelper.COL_TODO_LIST_ID + "=" + eToDoId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}
	
	// update database by Id
	public long updateData(Integer eToDoId, ToDoListModel eToDoList) {
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ContentValues values = new ContentValues();
		
		values.put(ICareMySelfDBHelper.COL_TODO_LIST_WHAT_TODO, eToDoList.getmWhatToDo());
		values.put(ICareMySelfDBHelper.COL_TODO_LIST_DATE, eToDoList.getmDate());
		
		long updated = 0;
		try {
			updated = mDB.update(ICareMySelfDBHelper.TODO_LIST_NAME, values, ICareMySelfDBHelper.COL_TODO_LIST_ID	+ "=" + eToDoId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}
	
	// Getting all profile list
	public ArrayList<ToDoListModel> getToDoList() {
		
		ArrayList<ToDoListModel> mToDoList = new ArrayList<ToDoListModel>();
		
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Cursor mCursor = mDB.query(ICareMySelfDBHelper.TODO_LIST_NAME, null, null, null, null, null, null);
	
		// looping through all rows and adding to list
		if (mCursor != null && mCursor.getCount() > 0) {
			mCursor.moveToFirst();
			
			for (int i = 0; i < mCursor.getCount(); i++) {
				
				int mToDoID = mCursor.getInt(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_TODO_LIST_ID));
				String mWhatToDo = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_TODO_LIST_WHAT_TODO));
				String mDate = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_TODO_LIST_DATE));
				
				ToDoListModel mToDo = new ToDoListModel( mToDoID,  mWhatToDo,  mDate);
				
				mToDoList.add(mToDo);
				mCursor.moveToNext();
			}
		}
		mCursor.close();
		mDB.close();
	
		// return place list
		return mToDoList;
	}
	
	// Getting all profile detail
	public ToDoListModel getToDoDetail(int eToDoId) {
		
		try {
			open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String selectQuery = "SELECT  * FROM " + ICareMySelfDBHelper.TODO_LIST_NAME+ " WHERE " + ICareMySelfDBHelper.COL_TODO_LIST_ID + "=" + eToDoId;

		Cursor mCursor = mDB.rawQuery(selectQuery, null);
		
		mCursor.moveToFirst();
		
		int mToDoID = mCursor.getInt(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_TODO_LIST_ID));
		String mWhatToDo = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_TODO_LIST_WHAT_TODO));
		String mDate = mCursor.getString(mCursor.getColumnIndex(ICareMySelfDBHelper.COL_TODO_LIST_DATE));
		
		ToDoListModel mToDo = new ToDoListModel( mToDoID,  mWhatToDo,  mDate);

		mCursor.moveToNext();

		mCursor.close();
		mDB.close();

		// return place detail
		return mToDo;
	}
	
	//check for empty data
	public boolean isEmpty() {
		try {
			this.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cursor cursor = mDB.query(ICareMySelfDBHelper.TODO_LIST_NAME, new String[] {
				ICareMySelfDBHelper.COL_TODO_LIST_ID, 
				ICareMySelfDBHelper.COL_TODO_LIST_WHAT_TODO, 			
				ICareMySelfDBHelper.COL_TODO_LIST_DATE,}, 
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
