package com.ftfl.icaremyself;

import java.util.List;

import com.ftfl.icaremyself.adapter.VaccineViewAdapter;
import com.ftfl.icaremyself.database.MyVaccineChartDBSource;
import com.ftfl.icaremyself.util.MyVaccineChartModel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class VaccineChartListActivity extends Activity {
	
	//initialization variable
	private ListView mListView;
	
	List<MyVaccineChartModel> AllVaccine;
	MyVaccineChartDBSource mVaccineDBSource;
	VaccineViewAdapter arrayVaccineAdapter;	
	MyVaccineChartModel mVaccine;	
	
	private int selectedId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_vaccine_chart);
		
		mVaccine = new MyVaccineChartModel();
		mVaccineDBSource = new MyVaccineChartDBSource(this);		
		AllVaccine = mVaccineDBSource.getVaccineList();
		
		arrayVaccineAdapter = new VaccineViewAdapter(this, AllVaccine);
		// adding it to the list view.
		mListView = (ListView) findViewById(R.id.lvVaccine);
		mListView.setAdapter(arrayVaccineAdapter);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

				AlertDialog.Builder alertDialog = new AlertDialog.Builder(VaccineChartListActivity.this);
				// Setting Dialog Title
				final String[] menuList = {"Delete Profile"};
				
				alertDialog.setTitle("I Care MySelf");
				alertDialog.setIcon(R.drawable.ic_launcher);
				
				selectedId = AllVaccine.get(position).getmVaccineID();
				
				alertDialog.setItems(menuList, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,	int item) {
						
						switch (item) {
						
						case 0:							

							AlertDialog.Builder alertDialog = new AlertDialog.Builder(VaccineChartListActivity.this);

							// Setting Dialog Title
							alertDialog.setTitle("Do You Want to delete");

							// Setting Dialog Message
							alertDialog.setMessage("Are you sure you want delete this?");

							// Setting Icon to Dialog
							alertDialog.setIcon(R.drawable.ic_launcher);

							// Setting Positive "Yes" Button
							alertDialog.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,	int which) {
									
									mVaccineDBSource.deleteData(selectedId);
									finish();
									startActivity(getIntent());
								}
							});
							// Showing Alert Message
							// Setting Negative "NO" Button
							alertDialog.setNegativeButton("No",	new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,	int which) {
									Toast.makeText(	getApplicationContext(),"You clicked on No",Toast.LENGTH_SHORT).show();
									dialog.cancel();
								}

							});
							alertDialog.show();
							break;
						}
					}
				});
				
				AlertDialog menuDrop = alertDialog.create();
				menuDrop.show();
			}
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_diet, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// Take appropriate action for each action item click
				switch (item.getItemId()) {
				case R.id.add:
					add();
					return true;
					
				case R.id.back:
					back();
					return true;
					
				default:
					return super.onOptionsItemSelected(item);
		}
	}

	private void back() {
		Intent i = new Intent( VaccineChartListActivity.this, HomeActivity.class);
		startActivity(i);
		// Remove activity
		finish(); // so that, it will not get back in the previous
					// file.
		
	}

	private void add() {
		Intent i = new Intent( VaccineChartListActivity.this, CreateVaccineChartActivity.class);
		startActivity(i);
		// Remove activity
		finish(); // so that, it will not get back in the previous
					// file.
		
	}	

}
