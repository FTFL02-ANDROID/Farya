package com.ftfl.icaremyself.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ICareMySelfDBHelper extends SQLiteOpenHelper{
	// All Static variables	

	// Database Name
	private static final String DATABASE_NAME = "icare.db";	
	// Database Version
	private static final int DATABASE_VERSION = 1;
	
	
	//Profile table name
	public static final String PROFILE_TABLE_NAME = "icare_table";
	
	// Profile Table Columns names
	public static final String COL_PROFILE_ID = "id";
	public static final String COL_PROFILE_NAME = "name";
	public static final String COL_PROFILE_BIRTH_DATE = "birth_date";
	public static final String COL_PROFILE_BLOOD_GROUP = "blood_group";
	public static final String COL_PROFILE_GENDER = "gender";
	public static final String COL_PROFILE_HEIGHT = "height";
	public static final String COL_PROFILE_WEIGHT = "weight";
	public static final String COL_PROFILE_IMPORTANT_NOTE = "important_note";
	
	// Profile table information
	private static final String DATABASE_PROFILE_TABLE = "create table "+ PROFILE_TABLE_NAME + 
			"( " + COL_PROFILE_ID + " integer primary key autoincrement, " + " "			
			+ COL_PROFILE_NAME + " text not null," + " "
			+ COL_PROFILE_BIRTH_DATE + " text not null," + " "
			+ COL_PROFILE_BLOOD_GROUP + " text not null," + " "
			+ COL_PROFILE_GENDER + " text not null," + " "
			+ COL_PROFILE_HEIGHT + " text not null," + " "
			+ COL_PROFILE_WEIGHT + " text not null," + " "			
			+ COL_PROFILE_IMPORTANT_NOTE + " text not null);";
	
	
	//Diet table name
	public static final String DIET_CHART_TABLE_NAME = "diet_chart_table";
	
	// Diet Chart Table Columns names	
	public static final String COL_DIET_ID = "diet_id";
	public static final String CO_DIET_DATE = "diet_date";
	public static final String COL_DIET_TIME = "diet_time";
	public static final String COL_DIET_FOOD_MENU = "diet_food_menu";
	public static final String COL_DIET_EVENT_NAME = "diet_event_name";
	public static final String COL_DIET_ALARM = "diet_set_alarm";
	
	// Diet table information
	private static final String DATABASE_DIET_TABLE = "create table "+ DIET_CHART_TABLE_NAME + 
			"( " + COL_DIET_ID + " integer primary key autoincrement, " + " "			
			+ CO_DIET_DATE + " text not null," + " "
			+ COL_DIET_TIME + " text not null," + " "
			+ COL_DIET_FOOD_MENU + " text not null," + " "
			+ COL_DIET_EVENT_NAME + " text not null," + " "		
			+ COL_DIET_ALARM + " text not null);";
	
	
	//Vaccine table name
	public static final String VACCINE_CHART_TABLE_NAME = "vaccine_chart_table";
	
	// Vaccine Chart Table Columns names	
	public static final String COL_VACCINE_ID = "vaccine_id";
	public static final String CO_VACCINE_DATE = "vaccine_date";	
	public static final String COL_VACCINE_NAME = "vaccine_name";
	public static final String COL_VACCINE_TAKEN = "vaccine_set_taken";
	
	// Vaccine table information
	private static final String DATABASE_VACCINE_TABLE = "create table "+ VACCINE_CHART_TABLE_NAME + 
			"( " + COL_VACCINE_ID + " integer primary key autoincrement, " + " "			
			+ CO_VACCINE_DATE + " text not null," + " "
			+ COL_VACCINE_NAME + " text not null," + " "				
			+ COL_VACCINE_TAKEN + " text not null);";
	
	
	//Medical History table name
	public static final String MEDICAL_HISTORY_TABLE_NAME = "medical_history_table";
	
	//Medical History Table Columns names	
	public static final String COL_HISTORY_ID = "medical_id";
	public static final String CO_HISTORY_DOCTOR_NAME = "medical_doctor_name";
	public static final String COL_HISTORY_VISITED_DATE = "medical_visited_date";	
	public static final String COL_HISTORY_PERPOSE = "medical_perpose";
	public static final String COL_HISTORY_PRESCRIBTION = "medical_prescribtion";
	
	// Vaccine table information
	private static final String DATABASE_MEDICAL_HISTORY_TABLE = "create table "+ MEDICAL_HISTORY_TABLE_NAME + 
			"( " + COL_HISTORY_ID + " integer primary key autoincrement, " + " "			
			+ CO_HISTORY_DOCTOR_NAME + " text not null," + " "
			+ COL_HISTORY_VISITED_DATE + " text not null," + " "
			+ COL_HISTORY_PERPOSE + " text not null," + " "				
			+ COL_HISTORY_PRESCRIBTION + " text not null);";
	
	
	//Doctor Profile table name
	public static final String DOCTOR_PROFILE_TABLE_NAME = "doctor_table";
	
	//Doctor Profile Table Columns names
	public static final String COL_DOCTOR_PROFILE_ID = "doctor_id";
	public static final String COL_DOCTOR_PROFILE_NAME = "doctor_name";
	public static final String COL_DOCTOR_PROFILE_SPECIALIZATION = "doctor_specialization";
	public static final String COL_DOCTOR_PROFILE_ADDRESS = "doctor_address";
	public static final String COL_DOCTOR_PROFILE_CONTACT_NUMBER = "doctor_number";
	public static final String COL_DOCTOR_PROFILE_EMAIL = "doctor_email";

	
	//Doctor Profile table information
	private static final String DATABASE_DOCTOR_PROFILE_TABLE = "create table "+ DOCTOR_PROFILE_TABLE_NAME + 
			"( " + COL_DOCTOR_PROFILE_ID + " integer primary key autoincrement, " + " "			
			+ COL_DOCTOR_PROFILE_NAME + " text not null," + " "
			+ COL_DOCTOR_PROFILE_SPECIALIZATION + " text not null," + " "
			+ COL_DOCTOR_PROFILE_ADDRESS + " text not null," + " "
			+ COL_DOCTOR_PROFILE_CONTACT_NUMBER + " text not null," + " "
			+ COL_DOCTOR_PROFILE_EMAIL + " text not null);";
	
	//ToDo List table name
	public static final String TODO_LIST_NAME = "todo_table";
	
	//ToDo List Table Columns names
	public static final String COL_TODO_LIST_ID = "todo_id";
	public static final String COL_TODO_LIST_WHAT_TODO = "what_todo";
	public static final String COL_TODO_LIST_DATE = "date_todo";
	
	//ToDo List table information
	private static final String DATABASE_TODO_LIST_TABLE = "create table "+ TODO_LIST_NAME + 
			"( " + COL_TODO_LIST_ID + " integer primary key autoincrement, " + " "			
			+ COL_TODO_LIST_WHAT_TODO + " text not null," + " "			
			+ COL_TODO_LIST_DATE + " text not null);";
	
	
	//constractor
	public ICareMySelfDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	//Create Databasw
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_PROFILE_TABLE);
		db.execSQL(DATABASE_DIET_TABLE);
		db.execSQL(DATABASE_VACCINE_TABLE);
		db.execSQL(DATABASE_MEDICAL_HISTORY_TABLE);
		db.execSQL(DATABASE_DOCTOR_PROFILE_TABLE);
		db.execSQL(DATABASE_TODO_LIST_TABLE);
	}

	//Update database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(ICareMySelfDBHelper.class.getName(),	"Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");		
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_PROFILE_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_DIET_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_VACCINE_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_MEDICAL_HISTORY_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_DOCTOR_PROFILE_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TODO_LIST_TABLE);
		onCreate(db);
		
	}


}
