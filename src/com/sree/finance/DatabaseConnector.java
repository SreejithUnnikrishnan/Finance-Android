package com.sree.finance;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnector extends SQLiteOpenHelper {

	private static String DB_PATH = "/data/data/com.sree.finance/databases/";
	private static String DB_NAME = "finance";
	private SQLiteDatabase database;
	private final Context dbContext;

	public DatabaseConnector(Context context) {

		super(context, DB_NAME, null, 1);
		this.dbContext = context;
		System.out.println("Inside constructor....");

	}

	public void createDatabase() throws IOException {
		boolean check = checkDatabase();
		if (check) {
			System.out.println("Database Found");
		} else {
			this.getReadableDatabase();
			try {
				copyDatabase();
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
	}
	
	private boolean checkDatabase(){		 
    	SQLiteDatabase db = null; 
    	try{
    		String path = DB_PATH + DB_NAME;
    		db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY); 
    	}catch(SQLiteException e){ 
    		System.out.println("No database"); 
    	}
 
    	if(db != null){ 
    		db.close(); 
    		return true;
    	}
    	else{
    		return false;
    	}    	
    }
	
	private void copyDatabase() throws IOException{		 
    	InputStream input = dbContext.getAssets().open(DB_NAME);
     	String file = DB_PATH + DB_NAME;
    	OutputStream output = new FileOutputStream(file);
     	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = input.read(buffer))>0){
    		output.write(buffer, 0, length);
    	} 
    	output.flush();
    	output.close();
    	input.close(); 
    }
 
    public void openDatabase() throws SQLException{ 
        String path = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY); 
    }
 
    @Override
	public synchronized void close() { 
    	    if(database != null)
    	    	database.close(); 
    	    super.close(); 
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
