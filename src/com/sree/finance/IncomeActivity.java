package com.sree.finance;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class IncomeActivity extends Activity {
	private Button addIncomeDetailButton;
	//private Button incomeBackButton;
	private Button incomeToExpense;
	private Button incomeToReport;
	private TextView incomeErrorTextView;
	private TextView incomeCatView1;
	private TextView incomeAmtView1;
	private TextView incomeBudView1;
	private TextView incomeCatView2;
	private TextView incomeAmtView2;
	private TextView incomeBudView2;
	private TextView incomeCatView3;
	private TextView incomeAmtView3;
	private TextView incomeBudView3;
	private TextView incomeCatView4;
	private TextView incomeAmtView4;
	private TextView incomeBudView4;
	private TextView incomeCatView5;
	private TextView incomeAmtView5;
	private TextView incomeBudView5;
	private DatabaseConnector connect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.income);

		incomeErrorTextView = (TextView) findViewById(R.id.incomeErrorTextView);
		incomeCatView1 = (TextView) findViewById(R.id.incomeCatView1);
		incomeCatView2 = (TextView) findViewById(R.id.incomeCatView2);
		incomeCatView3 = (TextView) findViewById(R.id.incomeCatView3);
		incomeCatView4 = (TextView) findViewById(R.id.incomeCatView4);
		incomeCatView5 = (TextView) findViewById(R.id.incomeCatView5);

		incomeAmtView1 = (TextView) findViewById(R.id.incomeAmtView1);
		incomeAmtView2 = (TextView) findViewById(R.id.incomeAmtView2);
		incomeAmtView3 = (TextView) findViewById(R.id.incomeAmtView3);
		incomeAmtView4 = (TextView) findViewById(R.id.incomeAmtView4);
		incomeAmtView5 = (TextView) findViewById(R.id.incomeAmtView5);

		incomeBudView1 = (TextView) findViewById(R.id.incomeBudView1);
		incomeBudView2 = (TextView) findViewById(R.id.incomeBudView2);
		incomeBudView3 = (TextView) findViewById(R.id.incomeBudView3);
		incomeBudView4 = (TextView) findViewById(R.id.incomeBudView4);
		incomeBudView5 = (TextView) findViewById(R.id.incomeBudView5);

		addIncomeDetailButton = (Button) findViewById(R.id.addIncomeDetailButton);
		incomeToExpense = (Button) findViewById(R.id.incomeToExpense);
		incomeToReport = (Button) findViewById(R.id.incomeToReport);

		addIncomeDetailButton.setOnClickListener(addIncomeDetailButtonListener);
		incomeToExpense.setOnClickListener(incomeToExpenseButtonListener);
		incomeToReport.setOnClickListener(incomeToReportButtonListener);

		connect = new DatabaseConnector(this);

		Cursor income = connect.getIncome();
		String category[] = new String[income.getCount()];
		String amount[] = new String[income.getCount()];
		String budget[] = new String[income.getCount()];
		int i = 0;

		income.moveToFirst();
		while (income.isAfterLast() == false) {
			category[i] = income.getString(1);
			amount[i] = income.getString(2);
			budget[i] = income.getString(4);
			i++;
			income.moveToNext();
		}
		if (category.length == 0) {
			incomeErrorTextView.setText("Add Income Details to start!!!");

		} else {
			for (int j = 0; j < category.length; j++) {
				System.out.println("Cat element: " + category[j]);
				System.out.println("amt element: " + amount[j]);
				System.out.println("budget element: " + budget[j]);
				if (j == 0) {
					incomeCatView1.setText(category[j]);
					incomeAmtView1.setText(amount[j]);
					incomeBudView1.setText(budget[j]);

				} else if (j == 1) {
					incomeCatView2.setText(category[j]);
					incomeAmtView2.setText(amount[j]);
					incomeBudView2.setText(budget[j]);

				} else if (j == 2) {
					incomeCatView3.setText(category[j]);
					incomeAmtView3.setText(amount[j]);
					incomeBudView3.setText(budget[j]);

				} else if (j == 3) {
					incomeCatView4.setText(category[j]);
					incomeAmtView4.setText(amount[j]);
					incomeBudView4.setText(budget[j]);

				} else if (j == 4) {
					incomeCatView5.setText(category[j]);
					incomeAmtView5.setText(amount[j]);
					incomeBudView5.setText(budget[j]);

				}
			}
		}
	}

	private OnClickListener addIncomeDetailButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			startActivity(new Intent("com.sree.finance.AddIncomeActivity"));

		}

	};
	
	
	private OnClickListener incomeToExpenseButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			startActivity(new Intent("com.sree.finance.ExpenseActivity"));

		}

	};
	
	private OnClickListener incomeToReportButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			startActivity(new Intent("com.sree.finance.ReportActivity"));

		}

	};

}
