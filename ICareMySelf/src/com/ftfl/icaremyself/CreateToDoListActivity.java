package com.ftfl.icaremyself;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ftfl.icaremyself.database.ToDoListDBSource;
import com.ftfl.icaremyself.util.ToDoListModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class CreateToDoListActivity extends Activity implements OnClickListener, OnDateSetListener{
	
	// Widget GUI
	private Button btnSaveToDo = null;
	
	private EditText mEtDateOfTodo = null;
	private EditText mEtWhatToDo = null;
	
	//initialization string variable
	private String mDate = "";
	private String mToDo = "";
	
	private int mYear = 0;
	private int mDay = 0;
	private int mMonth = 0;
	public String mCurrentDate = "";
	
    Toast toast;
	
	ToDoListDBSource  mToDoDBSource = null;
	
    final Calendar mCalendar = Calendar.getInstance(); 

	@SuppressLint("SimpleDateFormat") @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_todo_list);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		mCurrentDate = dateFormat.format(date);
		
		mToDoDBSource = new ToDoListDBSource(this);
		
		mEtWhatToDo = (EditText) findViewById(R.id.editWhatToDo);	
		
		mEtDateOfTodo = (EditText) findViewById(R.id.editDateOfTodo);	
		mEtDateOfTodo.setInputType(InputType.TYPE_NULL);
		mEtDateOfTodo.setFocusable(true);
		mEtDateOfTodo.setClickable(true);		
		mEtDateOfTodo.setOnClickListener(this);	
		mEtDateOfTodo.setText(mCurrentDate);
		
		btnSaveToDo = (Button) findViewById(R.id.btnSaveToDo);		
		btnSaveToDo.setOnClickListener(this);
	}

	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		// TODO Auto-generated method stub
		mEtDateOfTodo.setText(new StringBuilder()
		// Month is 0 based so add 1
		.append(dayOfMonth).append("/").append(monthOfYear + 1).append("/").append(year));
	}
	
	//get data
	protected void getDataFromForm() {			
		 
		//get values to string
		mToDo = mEtWhatToDo.getText().toString();		
		mDate = mEtDateOfTodo.getText().toString();			
	
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		case R.id.editDateOfTodo:
			
			mYear = mCalendar.get(Calendar.YEAR);
			mMonth = mCalendar.get(Calendar.MONTH);
			mDay = mCalendar.get(Calendar.DAY_OF_MONTH);

			DatePickerDialog dialog = new DatePickerDialog(this, this, mYear, mMonth, mDay);
			dialog.show();
			break;
			
		case R.id.btnSaveToDo:
			
			//method call to get data from form
			getDataFromForm();
			
			//condition to check validity
			if (   ( !mDate.equals("")) && ( !mToDo.equals(" "))){ 					
				
				// Assign values in the Profile
				ToDoListModel mDataInsert = new ToDoListModel();
				
				mDataInsert.setmDate(mDate);				
				mDataInsert.setmWhatToDo(mToDo);
			
				
				if (mToDoDBSource.insertData(mDataInsert) == true) {
					
					toast = Toast.makeText(CreateToDoListActivity.this, "Successfully Saved.", Toast.LENGTH_LONG);
					toast.show();
					
				    Bundle b = new Bundle();
                     
                    // Storing data into bundle
                    b.putInt("position", 2);
                    
                    // Creating Intent object
                    Intent intent = new Intent(CreateToDoListActivity.this, HomeActivity.class);

                    // Storing bundle object into intent
                    intent.putExtras(b);
                    startActivity(intent);
					
					// Remove activity
					finish(); // so that, it will not get back in the previous
								// file.
					
				} else {
					
					toast = Toast.makeText(CreateToDoListActivity.this, "Not saved.", Toast.LENGTH_LONG);
					toast.show();
				}
				
			}else{
				
				toast = Toast.makeText(CreateToDoListActivity.this, "Not Saved.", Toast.LENGTH_LONG);
				toast.show();
			}
			
			break;
			
		}	
	}	
}
