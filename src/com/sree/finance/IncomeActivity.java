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
	private DatabaseConnector connect = new DatabaseConnector(this);
	
	
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
        
        addIncomeDetailButton.setOnClickListener(addIncomeDetailButtonListener);
        
        Cursor income = connect.getIncome();
        if(income.moveToFirst()){
        	int count = 1;
        	do{
        		if(count == 1){
	        		incomeCatView1.setText(income.getString(1));
	        		incomeAmtView1.setText(income.getInt(2));
	        		incomeBudView1.setText(income.getInt(4));
	        		count++;
        		}
        		else if(count == 2){
	        		incomeCatView2.setText(income.getString(1));
	        		incomeAmtView2.setText(income.getInt(2));
	        		incomeBudView2.setText(income.getInt(4));
	        		count++;
        		}
        		else if(count == 3){
	        		incomeCatView3.setText(income.getString(1));
	        		incomeAmtView3.setText(income.getInt(2));
	        		incomeBudView3.setText(income.getInt(4));
	        		count++;
        		}
        		else if(count == 4){
	        		incomeCatView4.setText(income.getString(1));
	        		incomeAmtView4.setText(income.getInt(2));
	        		incomeBudView4.setText(income.getInt(4));
	        		count++;
        		}
        		else if(count == 5){
	        		incomeCatView5.setText(income.getString(1));
	        		incomeAmtView5.setText(income.getInt(2));
	        		incomeBudView5.setText(income.getInt(4));
	        		count++;
        		}
        		
			}while(income.moveToNext());
        }
        else{
        	incomeErrorTextView.setText("Add Income Details");
        }
	}
	
	private OnClickListener addIncomeDetailButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			startActivity(new Intent("com.sree.finance.AddIncomeActivity"));

		}

	};

}
