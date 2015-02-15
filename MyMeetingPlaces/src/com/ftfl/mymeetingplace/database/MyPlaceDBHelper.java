package com.ftfl.mymeetingplace.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyPlaceDBHelper extends SQLiteOpenHelper{
	
	// Database Name
	private static final String DATABASE_NAME = "place.db";
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 2;
	//table name
	public static final String PLACES_TABLE_NAME = "place_table";
	
	// Table Columns names
	public static final String COL_PLACES_ID = "id";
	public static final String COL_PLACE_IMAGE = "image";
	public static final String COL_PLACE_LATITUDE = "latitude";
	public static final String COL_PLACE_LONGTITUDE = "longitude";
	public static final String COL_PLACE_DATE = "date";
	public static final String COL_PLACE_TIME = "time";
	public static final String COL_PLACE_REMARKS = "remarks";
	public static final String COL_PLACE_CONTACT_PERSON = "person";
	public static final String COL_PLACE_CONTACT_EMAIL = "email";
	public static final String COL_PLACE_CONTACT_MOBILE = "mobile";
	
	// table information
	private static final String DATABASE_PLACE_TABLE = "create table "+ PLACES_TABLE_NAME + 
			"( " + COL_PLACES_ID + " integer primary key autoincrement, " + " "			
			+ COL_PLACE_IMAGE + " text not null," + " "
			+ COL_PLACE_LATITUDE + " text not null," + " "
			+ COL_PLACE_LONGTITUDE + " text not null," + " "
			+ COL_PLACE_DATE + " text not null," + " "
			+ COL_PLACE_TIME + " text not null," + " "
			+ COL_PLACE_REMARKS + " text not null," + " "
			+ COL_PLACE_CONTACT_PERSON + " text not null," + " "
			+ COL_PLACE_CONTACT_EMAIL + " text not null," + " "
			+ COL_PLACE_CONTACT_MOBILE + " text not null);";
	
	public MyPlaceDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_PLACE_TABLE);		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MyPlaceDBHelper.class.getName(),	"Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");		
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_PLACE_TABLE);		
		onCreate(db);
	}

}
