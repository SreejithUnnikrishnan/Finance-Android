package com.sree.finance;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ReportActivity extends Activity {
	private Button reportToIncomes;
	private Button reportToExpense;
	private TextView reportErrorTextView;
	private TextView incomeTotalAmountTextView;
	private TextView expenseTotalAmountTextView;
	private TextView reportHighestIncomeCategory;
	private TextView reporthighestExpenseCategory;
	private DatabaseConnector connect;
	
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        
        reportErrorTextView = (TextView) findViewById(R.id.reportErrorTextView);
        incomeTotalAmountTextView = (TextView) findViewById(R.id.incomeTotalAmountTextView);
        expenseTotalAmountTextView = (TextView) findViewById(R.id.expenseTotalAmountTextView);
        reportHighestIncomeCategory = (TextView) findViewById(R.id.reportHighestIncomeCategory);
        reporthighestExpenseCategory = (TextView) findViewById(R.id.reporthighestExpenseCategory);
        
        reportToIncomes = (Button) findViewById(R.id.reportToIncomes);
        reportToExpense = (Button) findViewById(R.id.reportToExpense);
		reportToIncomes.setOnClickListener(reportToIncomesButtonListener);
		reportToExpense.setOnClickListener(reportToExpenseButtonListener);
		
		connect = new DatabaseConnector(this);
		double incomeTotal = connect.getTotalAmount("income");
		double expenseTotal = connect.getTotalAmount("expense");
		String incomeCatgeory = connect.getHighCategory("income");
		String expenseCatgeory = connect.getHighCategory("expense");
		
		if (incomeTotal == 0.00 && expenseTotal == 0.00) {
			reportErrorTextView.setText("Add Income/Expense Details to See Report!!!");
		} else {
			incomeTotalAmountTextView.setText(Double.toString(incomeTotal));
			expenseTotalAmountTextView.setText(Double.toString(expenseTotal));
			reportHighestIncomeCategory.setText(incomeCatgeory);
			reporthighestExpenseCategory.setText(expenseCatgeory);
		}
		
	}
	
	
	private OnClickListener reportToIncomesButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			startActivity(new Intent("com.sree.finance.IncomeActivity"));

		}

	};
	
	private OnClickListener reportToExpenseButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			startActivity(new Intent("com.sree.finance.ExpenseActivity"));

		}

	};

}
