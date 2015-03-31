package com.sree.finance;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

	private boolean checkDatabase() {
		SQLiteDatabase db = null;
		try {
			String path = DB_PATH + DB_NAME;
			db = SQLiteDatabase.openDatabase(path, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (SQLiteException e) {
			System.out.println("No database");
		}

		if (db != null) {
			db.close();
			return true;
		} else {
			return false;
		}
	}

	private void copyDatabase() throws IOException {
		InputStream input = dbContext.getAssets().open(DB_NAME);
		String file = DB_PATH + DB_NAME;
		OutputStream output = new FileOutputStream(file);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = input.read(buffer)) > 0) {
			output.write(buffer, 0, length);
		}
		output.flush();
		output.close();
		input.close();
	}

	public void openDatabase() throws SQLException {
		String path = DB_PATH + DB_NAME;
		database = SQLiteDatabase.openDatabase(path, null,
				SQLiteDatabase.OPEN_READONLY);
	}

	@Override
	public synchronized void close() {
		if (database != null)
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

	public Cursor getIncomeCategory() {
		String query = "SELECT * from category where type = 'Income'";
		System.out.println("Inside get income category start");
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor result = db.rawQuery(query, null);
		System.out.println("Inside get category after cursor");
		return result;
	}

	public Cursor getExpenseCategory() {
		String query = "SELECT * from category where type = 'Expense'";
		System.out.println("Inside get Expense category start");
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor result = db.rawQuery(query, null);
		System.out.println("Inside get category after cursor");
		return result;
	}

	public Map<String, String> getCategory() {
		String query = "SELECT * from category";
		String key = "";
		String val = "";
		Map<String, String> map = new HashMap<String, String>();
		System.out.println("Inside get category start");
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor result = db.rawQuery(query, null);
		System.out.println("Inside get category after cursor");
		if (result.moveToFirst()) {
			do {
				key = result.getString(1);
				val = result.getString(2);
				map.put(key, val);
			} while (result.moveToNext());

		}
		System.out.println("Inside get category after all");
		return map;
	}

	public Cursor getIncome() {
		Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        String from = year+"-0"+(month+1)+"-01";
        String to = year+"-0"+(month+1)+"-31";
        System.out.println("from: " +from+ " to: "+to);
		String query = "SELECT * from income where date >= '"+from+"' and date <= '"+to+"'";
       // String query = "SELECT * from income";
		System.out.println("Inside get income start");
		System.out.println("Query: "+query);
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor result = db.rawQuery(query, null);
		System.out.println("Inside get income after cursor");
		return result;
	}

	public Cursor getExpense() {
		Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        String from = year+"-0"+(month+1)+"-01";
        String to = year+"-0"+(month+1)+"-31";
        System.out.println("from: " +from+ " to: "+to);
		String query = "SELECT * from expense where date >= '"+from+"' and date <= '"+to+"'";
		System.out.println("Inside get expense start");
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor result = db.rawQuery(query, null);
		System.out.println("Inside get expense after cursor");
		return result;
	}

	public boolean insertIncome(String cat, double amt, double bud, String date) {
		SQLiteDatabase database = this.getWritableDatabase();
		long id = -1;
		int count = 0;
		// String query =
		// "INSERT INTO income (name, amount, date, budget) VALUES("+cat+","+amt+","+date+","+bud+")";
		// database.execSQL(query);
		String check = checkCategory(cat, "income");
		if (check == "true") {
			ContentValues newIncome = new ContentValues();
			newIncome.put("name", cat);
			newIncome.put("amount", amt);
			newIncome.put("date", date);
			newIncome.put("budget", bud);
			id = database.insert("income", null, newIncome);
			close();
			if (id == -1) {
				return false;
			} else {
				return true;
			}
		} else {
			ContentValues newIncome = new ContentValues();
			double toAdd = getCategoryAmount(check, "income");
			amt = amt + toAdd;
			newIncome.put("name", cat);
			newIncome.put("amount", amt);
			newIncome.put("date", date);
			newIncome.put("budget", bud);
			count = database.update("income", newIncome, "_id=" + Integer.parseInt(check), null);
			close();
			if (count == 0) {
				return false;
			} else {
				return true;
			}
		}

	}

	public String checkCategory(String cat, String tab) {
		String query = "SELECT * from " + tab + " where name='" + cat + "'";
		System.out.println("Inside checkCategory start");
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor result = db.rawQuery(query, null);
		System.out.println("Inside checkCategory after cursor");
		result.moveToFirst();
		if (result.isAfterLast() == true) {
			return "true";
		} else {
			String id = result.getString(0);
			return id;
		}

	}

	public boolean insertExpense(String cat, double amt, double bud, String date) {
		SQLiteDatabase database = this.getWritableDatabase();
		long id = -1;
		int count = 0;
		String check = checkCategory(cat, "expense");
		if (check == "true") {
			ContentValues newIncome = new ContentValues();
			newIncome.put("name", cat);
			newIncome.put("amount", amt);
			newIncome.put("date", date);
			newIncome.put("budget", bud);
			id = database.insert("expense", null, newIncome);
			close();
			if (id == -1) {
				return false;
			} else {
				return true;
			}
		} else {
			ContentValues newIncome = new ContentValues();
			double toAdd = getCategoryAmount(check, "expense");
			amt = amt + toAdd;
			newIncome.put("name", cat);
			newIncome.put("amount", amt);
			newIncome.put("date", date);
			newIncome.put("budget", bud);
			count = database.update("expense", newIncome, "_id=" + Integer.parseInt(check), null);
			close();
			if (count == 0) {
				return false;
			} else {
				return true;
			}
		}

	}
	
	public double getCategoryAmount(String id, String tab){
		double amount = 0.00;
		String query = "SELECT * from " + tab + " where _id=" + id;
		System.out.println("Inside getCategoryAmount start");
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor result = db.rawQuery(query, null);
		System.out.println("Inside getCategoryAmount after cursor");
		result.moveToFirst();
		while (result.isAfterLast() == false) {
			amount = result.getDouble(2);
			result.moveToNext();
		}
		return amount;
		
	}
	
	public double getTotalAmount(String tab){
		double total = 0.00;
		Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        String from = year+"-0"+(month+1)+"-01";
        String to = year+"-0"+(month+1)+"-31";
        System.out.println("from: " +from+ " to: "+to);
		String query = "select SUM(amount) as \"total\" from "+tab+ " where date >= '"+from+"' and date <= '"+to+"'";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor result = db.rawQuery(query, null);		
		System.out.println("Inside get total after cursor");
		result.moveToFirst();
		while (result.isAfterLast() == false) {
			total = result.getDouble(0);
			result.moveToNext();
		}
		System.out.println("Total income: " + total);
		
		return total;
		
	}
	
	public String getHighCategory(String tab){
		String category = "";
		Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        String from = year+"-0"+(month+1)+"-01";
        String to = year+"-0"+(month+1)+"-31";
        System.out.println("from: " +from+ " to: "+to);
		String query = "select name from "+tab+ " where date >= '"+from+"' and date <= '"+to+"' and amount > (select amount from "+tab+ " where date >= '"+from+"' and date <= '"+to+"')";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor result = db.rawQuery(query, null);		
		System.out.println("Inside get total after cursor");
		result.moveToFirst();
		while (result.isAfterLast() == false) {
			category = result.getString(0);
			result.moveToNext();
		}
		System.out.println("high : " + category);
		return category;
	}

}
