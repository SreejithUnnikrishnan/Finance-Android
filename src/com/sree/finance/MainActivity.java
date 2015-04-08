package com.sree.finance;

import java.io.IOException;

import com.sree.database.DatabaseFunctions;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	private Button incomeButton;
	private Button expenseButton;
	private Button reportsButton;
	private ImageButton quitButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		incomeButton = (Button) findViewById(R.id.incomeButton);
		expenseButton = (Button) findViewById(R.id.expenseButton);
		reportsButton = (Button) findViewById(R.id.reportsButton);
		quitButton = (ImageButton) findViewById(R.id.quitButton);

		incomeButton.setOnClickListener(incomeButtonListener);
		expenseButton.setOnClickListener(expenseButtonListener);
		reportsButton.setOnClickListener(reportsButtonListener);
		quitButton.setOnClickListener(quitButtonListener);
		// DatabaseFunctions db = new DatabaseFunctions(this);
		DatabaseConnector connect = new DatabaseConnector(this);

		try {

			connect.createDatabase();

		} catch (IOException ioe) {

			throw new Error("Unable to create database");

		}

		try {

			connect.openDatabase();

		} catch (SQLException sqle) {

			throw sqle;

		}

	}

	private OnClickListener incomeButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			startActivity(new Intent("com.sree.finance.IncomeActivity"));

		}

	};

	private OnClickListener expenseButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			startActivity(new Intent("com.sree.finance.ExpenseActivity"));

		}

	};

	private OnClickListener reportsButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			startActivity(new Intent("com.sree.finance.ReportActivity"));

		}

	};

	private OnClickListener quitButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			finish();
			System.exit(0);

		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
