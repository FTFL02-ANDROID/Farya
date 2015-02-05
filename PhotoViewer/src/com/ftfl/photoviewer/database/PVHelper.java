package com.ftfl.photoviewer.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PVHelper extends SQLiteOpenHelper{
	
	// Database Name
	private static final String DATABASE_NAME = "pv.db";
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;
	//table name
	public static final String PV_TABLE_NAME = "pv_table";
	// Table Columns names
	public static final String COL_PV_ID = "id";
	public static final String COL_PV_LATITUDE = "latitude";
	public static final String COL_PV_LONGTITUDE = "longitude";
	public static final String COL_PV_REMARKS = "remarks";
	public static final String COL_PV_DATE = "date";
	public static final String COL_PV_TIME = "time";
	public static final String COL_PV_IMAGE = "image";
	// table information
	private static final String DATABASE_PV_TABLE = "create table "+ PV_TABLE_NAME + 
			"( " + COL_PV_ID + " integer primary key autoincrement, " + " "
			//+ COL_ATM_PROFILE_IMAGE + " text not null," + " "
			+ COL_PV_LATITUDE + " text not null," + " "
			+ COL_PV_LONGTITUDE + " text not null," + " "
			+ COL_PV_REMARKS + " text not null," + " "
			+ COL_PV_DATE + " text not null," + " "
			+ COL_PV_TIME + " text not null," + " "			
			+ COL_PV_IMAGE + " text not null);";
	
	public PVHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_PV_TABLE);		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(PVHelper.class.getName(),	"Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");		
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_PV_TABLE);		
		onCreate(db);
	}

}
