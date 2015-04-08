package com.sree.finance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
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
import android.widget.TextView;

public class AddIncomeActivity extends Activity {
	private Button addIncomeDetailsAddButton;
	private Button incomeCancelButton;
	private ImageButton quitAddIncomeButton;
	private EditText addIncomeAmountEditText;
	private EditText addIncomeDateEditText;
	private EditText addIncomeBudgetEditText;
	private Spinner incomeCategorySpinner;
	private DatabaseConnector connect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_income);
		connect = new DatabaseConnector(this);
		addIncomeDateEditText = (EditText) findViewById(R.id.addIncomeDateEditText);
		addIncomeDetailsAddButton = (Button) findViewById(R.id.addIncomeDetailsAddButton);
		incomeCancelButton = (Button) findViewById(R.id.incomeCancelButton);
		incomeCategorySpinner = (Spinner) findViewById(R.id.incomeCategorySpinner);
		quitAddIncomeButton = (ImageButton) findViewById(R.id.quitAddIncomeButton);
		quitAddIncomeButton.setOnClickListener(quitAddIncomeButtonListener);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
		addIncomeDateEditText.setText(sdf.format(date));
		Cursor cursor = connect.getIncomeCategory();

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
			// for (int j = 0; j < category.length; j++) {
			// System.out.println("Category" + j + ": " + category[j]);
			//
			// }
			ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(
					this, android.R.layout.simple_spinner_item, category);
			categoryAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			incomeCategorySpinner.setAdapter(categoryAdapter);

		}

		addIncomeDetailsAddButton
				.setOnClickListener(addIncomeDetailsAddButtonListener);
		
		incomeCancelButton
			.setOnClickListener(incomeCancelButtonListener);
	}

	private OnClickListener addIncomeDetailsAddButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			addIncomeAmountEditText = (EditText) findViewById(R.id.addIncomeAmountEditText);
			addIncomeDateEditText = (EditText) findViewById(R.id.addIncomeDateEditText);
			addIncomeBudgetEditText = (EditText) findViewById(R.id.addIncomeBudgetEditText);
			incomeCategorySpinner = (Spinner) findViewById(R.id.incomeCategorySpinner);
			System.out.println("Before.....");
			String cat = incomeCategorySpinner.getSelectedItem().toString();
			double amt = Double.parseDouble(addIncomeAmountEditText.getText()
					.toString());
			System.out.println("mid.....");
			double bud = Double.parseDouble(addIncomeBudgetEditText.getText()
					.toString());
			String date = addIncomeDateEditText.getText().toString();
			System.out.println("Cat: " + cat + " amt: " + amt + " bud: " + bud
					+ " date: " + date);
			
//			Date dt = new Date();
//			
//			SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
//			System.out.println("after.....");
//			try {
//				Date sys = sdf.parse(dt.toString());
//				Date userDate = sdf.parse(date);
//				System.out.println("system: " + sys + " user date: " + userDate);
//				if(sys.after(userDate)){
//					alertMessage("Enter date less than or equal to todays date");
//				}
//			} catch (ParseException e) {
//				System.out.println("Inside exception");
//				e.printStackTrace();
//			}
			
			connect = new DatabaseConnector(AddIncomeActivity.this);
			boolean rs = connect.insertIncome(cat, amt, bud, date);
			if (rs) {
				startActivity(new Intent("com.sree.finance.IncomeActivity"));
			} else {
				alertMessage("Oops!! Try Again");
			}

		}

	};
	
	private OnClickListener incomeCancelButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			startActivity(new Intent("com.sree.finance.IncomeActivity"));

		}

	};
	
	private OnClickListener quitAddIncomeButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			finish();
			System.exit(0);

		}

	};
	
	public void alertMessage(String msg){
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
				AddIncomeActivity.this);
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
