package com.sree.finance;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddExpenseActivity  extends Activity {
	private Button addExpenseDetailsAddButton;
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
        
        addExpenseDetailsAddButton = (Button) findViewById(R.id.addExpenseDetailsAddButton);
        expenseCategorySpinner = (Spinner) findViewById(R.id.expenseCategorySpinner);
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
	}
	
	private OnClickListener addExpenseDetailsAddButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			addExpenseAmountEditText = (EditText) findViewById(R.id.addExpenseAmountEditText);
	        addExpenseDateEditText = (EditText) findViewById(R.id.addExpenseDateEditText);
	        addExpenseBudgetEditText = (EditText) findViewById(R.id.addExpenseBudgetEditText);
	        expenseCategorySpinner = (Spinner) findViewById(R.id.expenseCategorySpinner);
			startActivity(new Intent("com.sree.finance.ExpenseActivity"));

		}

	};
	

}
