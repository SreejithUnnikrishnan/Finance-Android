package com.sree.finance;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ExpenseActivity extends Activity {
	private Button addExpenseDetailButton;
	private TextView expenseErrorTextView;
	private TextView expenseCatView1;
	private TextView expenseAmtView1;
	private TextView expenseBudView1;
	private TextView expenseCatView2;
	private TextView expenseAmtView2;
	private TextView expenseBudView2;
	private TextView expenseCatView3;
	private TextView expenseAmtView3;
	private TextView expenseBudView3;
	private TextView expenseCatView4;
	private TextView expenseAmtView4;
	private TextView expenseBudView4;
	private TextView expenseCatView5;
	private TextView expenseAmtView5;
	private TextView expenseBudView5;
	private DatabaseConnector connect;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense);
        
        expenseErrorTextView = (TextView) findViewById(R.id.expenseErrorTextView);
        expenseCatView1 = (TextView) findViewById(R.id.expenseCatView1);
        expenseCatView2 = (TextView) findViewById(R.id.expenseCatView2);
        expenseCatView3 = (TextView) findViewById(R.id.expenseCatView3);
        expenseCatView4 = (TextView) findViewById(R.id.expenseCatView4);
        expenseCatView5 = (TextView) findViewById(R.id.expenseCatView5);
        
        expenseAmtView1 = (TextView) findViewById(R.id.expenseAmtView1);
        expenseAmtView2 = (TextView) findViewById(R.id.expenseAmtView2);
        expenseAmtView3 = (TextView) findViewById(R.id.expenseAmtView3);
        expenseAmtView4 = (TextView) findViewById(R.id.expenseAmtView4);
        expenseAmtView5 = (TextView) findViewById(R.id.expenseAmtView5);
        
        expenseBudView1 = (TextView) findViewById(R.id.expenseBudView1);
        expenseBudView2 = (TextView) findViewById(R.id.expenseBudView2);
        expenseBudView3 = (TextView) findViewById(R.id.expenseBudView3);
        expenseBudView4 = (TextView) findViewById(R.id.expenseBudView4);
        expenseBudView5 = (TextView) findViewById(R.id.expenseBudView5);
        
        addExpenseDetailButton = (Button) findViewById(R.id.addexpenseDetailButton);
        
        addExpenseDetailButton.setOnClickListener(addExpenseDetailButtonListener);
        
        
        connect = new DatabaseConnector(this);

        Cursor expense = connect.getExpense();;
		String category[] = new String[expense.getCount()];
		String amount[] = new String[expense.getCount()];
		String budget[] = new String[expense.getCount()];
		int i = 0;

		expense.moveToFirst();
		while (expense.isAfterLast() == false) {
			category[i] = expense.getString(1);
			amount[i] = expense.getString(2);
			budget[i] = expense.getString(4);
			i++;
			expense.moveToNext();
		}
		if (category.length == 0) {
			expenseErrorTextView.setText("Add Expense Details to start!!!");

		} else {
			for (int j = 0; j < category.length; j++) {
				System.out.println("Cat element: " + category[j]);
				System.out.println("amt element: " + amount[j]);
				System.out.println("budget element: " + budget[j]);
				if (j == 0) {
					expenseCatView1.setText(category[j]);
					expenseAmtView1.setText(amount[j]);
					expenseBudView1.setText(budget[j]);

				} else if (j == 1) {
					expenseCatView2.setText(category[j]);
					expenseAmtView2.setText(amount[j]);
					expenseBudView2.setText(budget[j]);

				} else if (j == 2) {
					expenseCatView3.setText(category[j]);
					expenseAmtView3.setText(amount[j]);
					expenseBudView3.setText(budget[j]);

				} else if (j == 3) {
					expenseCatView4.setText(category[j]);
					expenseAmtView4.setText(amount[j]);
					expenseBudView4.setText(budget[j]);

				} else if (j == 4) {
					expenseCatView5.setText(category[j]);
					expenseAmtView5.setText(amount[j]);
					expenseBudView5.setText(budget[j]);

				}
			}
		}
	}
	
	private OnClickListener addExpenseDetailButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			startActivity(new Intent("com.sree.finance.AddExpenseActivity"));

		}

	};

}
