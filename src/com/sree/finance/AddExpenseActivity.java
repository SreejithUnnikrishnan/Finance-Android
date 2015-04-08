package com.sree.finance;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class AddExpenseActivity  extends Activity {
	private Button addExpenseDetailsAddButton;
	private Button expenseCancelButton;
	private ImageButton quitAddExpenseButton;
	private EditText addExpenseAmountEditText;
	private EditText addExpenseDateEditText;
	private EditText addExpenseBudgetEditText;
	private Spinner expenseCategorySpinner;
	private DatabaseConnector connect;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense);
        connect = new DatabaseConnector(this);
        
        addExpenseDateEditText = (EditText) findViewById(R.id.addExpenseDateEditText);
		addExpenseDetailsAddButton = (Button) findViewById(R.id.addExpenseDetailsAddButton);
		expenseCancelButton = (Button) findViewById(R.id.expenseCancelButton);
		quitAddExpenseButton = (ImageButton) findViewById(R.id.quitAddExpenseButton);
		quitAddExpenseButton.setOnClickListener(quitAddExpenseButtonListener);
        //expenseCategorySpinner = (Spinner) findViewById(R.id.expenseCategorySpinner);
        expenseCategorySpinner = (Spinner) findViewById(R.id.expenseCategorySpinner);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
		addExpenseDateEditText.setText(sdf.format(date));
        Cursor cursor = connect.getExpenseCategory();
        String category[] = new String[cursor.getCount()];
		int i = 0;
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			category[i] = cursor.getString(1);
			i++;
			cursor.moveToNext();
		}
		if (category.length == 0) {
			System.out.println("No Catgeory found");
		} else {
//			for (int j = 0; j < category.length; j++) {
//				System.out.println("Category" + j + ": " + category[j]);
//
//			}
			ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,category);
			categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			expenseCategorySpinner.setAdapter(categoryAdapter);
		}

        addExpenseDetailsAddButton.setOnClickListener(addExpenseDetailsAddButtonListener);
        expenseCancelButton.setOnClickListener(expenseCancelButtonListener);
	}
	
	private OnClickListener addExpenseDetailsAddButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			addExpenseAmountEditText = (EditText) findViewById(R.id.addExpenseAmountEditText);
	        addExpenseDateEditText = (EditText) findViewById(R.id.addExpenseDateEditText);
	        addExpenseBudgetEditText = (EditText) findViewById(R.id.addExpenseBudgetEditText);
	        expenseCategorySpinner = (Spinner) findViewById(R.id.expenseCategorySpinner);
			System.out.println("Before.....");
			String cat = expenseCategorySpinner.getSelectedItem().toString();
			double amt = Double.parseDouble(addExpenseAmountEditText.getText()
					.toString());
			System.out.println("mid.....");
			double bud = Double.parseDouble(addExpenseBudgetEditText.getText()
					.toString());
			String date = addExpenseDateEditText.getText().toString();
			System.out.println("Cat: " + cat + " amt: " + amt + " bud: " + bud
					+ " date: " + date);
		
			
			connect = new DatabaseConnector(AddExpenseActivity.this);
			boolean rs = connect.insertExpense(cat, amt, bud, date);
			if (rs) {
				startActivity(new Intent("com.sree.finance.ExpenseActivity"));
			} else {
				alertMessage("Oops!! Try Again");
			}

		}

	};
	
	private OnClickListener expenseCancelButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			startActivity(new Intent("com.sree.finance.ExpenseActivity"));

		}

	};
	
	private OnClickListener quitAddExpenseButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			finish();
			System.exit(0);

		}

	};
	
	public void alertMessage(String msg){
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
				AddExpenseActivity.this);
		alertBuilder.setMessage(msg);
		alertBuilder.setNeutralButton("Ok",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						dialog.dismiss();
					}
				});
		AlertDialog alert = alertBuilder.create();
		alert.show();
		
	}
	

}
