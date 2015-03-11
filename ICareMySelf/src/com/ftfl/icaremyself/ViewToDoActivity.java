package com.ftfl.icaremyself;

import com.ftfl.icaremyself.database.ToDoListDBSource;
import com.ftfl.icaremyself.util.ToDoListModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewToDoActivity extends Activity  {
	
	// Widget GUI
	public TextView mTvDate = null;
	public TextView mTvToDo = null;

	
    //database initialization
	ToDoListDBSource mToDoDBSource;  	
	ToDoListModel mToDoList;
	
	private int mSelectedId = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_todo_list);
		
		mToDoDBSource = new ToDoListDBSource(this);	
		
		/* get Id from Intent */
		mSelectedId = getIntent().getExtras().getInt("selected_id");		
			
		mToDoList = mToDoDBSource.getToDoDetail(mSelectedId);
		
		//variable initialization
		mTvDate = (TextView) findViewById(R.id.tvViewDateOfTodo);		
		mTvDate.setText(mToDoList.getmDate());
		
		mTvToDo = (TextView) findViewById(R.id.tvViewWhatToDo);		
		mTvToDo.setText(mToDoList.getmWhatToDo());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_doc, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// Take appropriate action for each action item click
				switch (item.getItemId()) {				
					
				case R.id.back:
					back();
					return true;
					
				default:
					return super.onOptionsItemSelected(item);
		}
	}

	private void back() {
		Intent i = new Intent(this, HomeActivity.class);
		startActivity(i);
		// Remove activity
		finish(); // so that, it will not get back in the previous
					// file.
		
	}

}
