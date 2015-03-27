package com.sree.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseFunctions extends SQLiteOpenHelper {

	
		
		private static final int DATABASE_VERSION = 1;
	    private static final String TABLE_CATEGORY = "category";
	    private static final String TABLE_INCOME = "income";
	    private static final String TABLE_EXPENSE = "expense";
		private static String DATABASE_NAME = "finance";
	    private static final String CREATE_TABLE_CATEGORY =
	                "CREATE TABLE " + TABLE_CATEGORY +
		            "(_id integer primary key autoincrement," +
		            "name TEXT, type TEXT);";
	    private static final String CREATE_TABLE_INCOME =
                "CREATE TABLE " + TABLE_INCOME +
	            "(_id integer primary key autoincrement," +
	            "name TEXT, amount integer, date TEXT ,budget integer);";
	    private static final String CREATE_TABLE_EXPENSE =
                "CREATE TABLE " + TABLE_EXPENSE +
	            "(_id integer primary key autoincrement," +
	            "name TEXT, amount integer, date TEXT ,budget integer);";
	   

	    public DatabaseFunctions(Context context) {
	    	
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	        System.out.println("Inside constructor....");
	       
	    }

	    

		@Override
	    public void onCreate(SQLiteDatabase db) {
	    	 System.out.println("Inside database create....");
	    	 db.execSQL(CREATE_TABLE_CATEGORY);
	    	 System.out.println("Inside database create....");
//	        db.execSQL(CREATE_TABLE_INCOME);
//	        db.execSQL(CREATE_TABLE_EXPENSE);
//	        System.out.println("Inside database create....");
	    }

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}

}
