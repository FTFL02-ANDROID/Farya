package com.ftfl.atm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AtmDBHelper extends SQLiteOpenHelper{
	
	private static final String DATABASE_NAME = "atm.db";
	private static final int DATABASE_VERSION = 1;
	
    // ATM Profile Table
	public static final String ATM_PROFILE = "atmprofiles";
	public static final String COL_ATM_PROFILE_ID = "id";
	public static final String COL_ATM_PROFILE_NAME = "name";
	public static final String COL_ATM_PROFILE_ADDRESS = "address";
	public static final String COL_ATM_PROFILE_BANK_NAME = "bank_name";
	public static final String COL_ATM_PROFILE_LATITUDE = "latitude";
	public static final String COL_ATM_PROFILE_LONGTITUDE = "longitude";
	public static final String COL_ATM_PROFILE_DEPOSITE = "deposite";
	public static final String COL_ATM_PROFILE_CONTACT_PERSON_NAME = "persone_name";
	public static final String COL_ATM_PROFILE_CONTACT_PERSON_NUMBER = "person_number";
	
	// Database creation sql statement
	private static final String DATABASE_CREATE_PROFILE = "create table "
			+ ATM_PROFILE + "( " + COL_ATM_PROFILE_ID
			+ " integer primary key autoincrement, " + " "
			+ COL_ATM_PROFILE_NAME + " text not null," + " "
			+ COL_ATM_PROFILE_ADDRESS + " text not null," + " "
			+ COL_ATM_PROFILE_BANK_NAME + " text not null," + " "
			+ COL_ATM_PROFILE_LATITUDE + " text not null," + " "
			+ COL_ATM_PROFILE_LONGTITUDE + " text not null," + " "
			+ COL_ATM_PROFILE_DEPOSITE + " text not null," + " "
			+ COL_ATM_PROFILE_CONTACT_PERSON_NAME + " text not null," + " "
			+ COL_ATM_PROFILE_CONTACT_PERSON_NUMBER + " text not null);";
	
	public AtmDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE_PROFILE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.w(AtmDBHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
		
		database.execSQL("DROP TABLE IF EXISTS " + ATM_PROFILE);		
		onCreate(database);
		
	}
}
