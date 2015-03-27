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
	private DatabaseConnector connect = new DatabaseConnector(this);
	
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
        
        Cursor expense = connect.getExpense();
        if(expense.moveToFirst()){
        	int count = 1;
        	do{
        		if(count == 1){
	        		expenseCatView1.setText(expense.getString(1));
	        		expenseAmtView1.setText(expense.getInt(2));
	        		expenseBudView1.setText(expense.getInt(4));
	        		count++;
        		}
        		else if(count == 2){
	        		expenseCatView2.setText(expense.getString(1));
	        		expenseAmtView2.setText(expense.getInt(2));
	        		expenseBudView2.setText(expense.getInt(4));
	        		count++;
        		}
        		else if(count == 3){
	        		expenseCatView3.setText(expense.getString(1));
	        		expenseAmtView3.setText(expense.getInt(2));
	        		expenseBudView3.setText(expense.getInt(4));
	        		count++;
        		}
        		else if(count == 4){
	        		expenseCatView4.setText(expense.getString(1));
	        		expenseAmtView4.setText(expense.getInt(2));
	        		expenseBudView4.setText(expense.getInt(4));
	        		count++;
        		}
        		else if(count == 5){
	        		expenseCatView5.setText(expense.getString(1));
	        		expenseAmtView5.setText(expense.getInt(2));
	        		expenseBudView5.setText(expense.getInt(4));
	        		count++;
        		}
        		
			}while(expense.moveToNext());
        }
        else{
        	expenseErrorTextView.setText("Add expense Details");
        }
	}
	
	private OnClickListener addExpenseDetailButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			startActivity(new Intent("com.sree.finance.AddExpenseActivity"));

		}

	};

}
