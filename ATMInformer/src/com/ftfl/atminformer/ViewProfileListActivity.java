package com.ftfl.atminformer;


import java.util.List;

import com.ftfl.atminformer.adapter.ATMProfileListAdapter;
import com.ftfl.atminformer.database.ATMDBSource;
import com.ftfl.atminformer.uitl.ATMProfileModel;

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

public class ViewProfileListActivity extends Activity {
	
	ListView mListView = null;
	List<ATMProfileModel> allProfile = null;
	ATMProfileModel mProfile = null;
	ATMDBSource mDataSource = null;
	ATMProfileListAdapter arrayAdapter = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_profile);
		
		mDataSource = new ATMDBSource(this);
		
		allProfile = mDataSource.atmProfilesList();
		arrayAdapter = new ATMProfileListAdapter(this, allProfile);
		
		// adding it to the list view.
		mListView = (ListView) findViewById(R.id.lv);
		mListView.setAdapter(arrayAdapter);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			private long profileId;
			private String mID;

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(ViewProfileListActivity.this);
				// Setting Dialog Title

				final String[] menuList = {"view",  "delete", "map"};
				
				alertDialog.setTitle("Options");
				alertDialog.setIcon(R.drawable.ic_launcher);
				
				mID = allProfile.get(position).getId();
				profileId = Long.parseLong(mID);
				
				alertDialog.setItems(menuList, new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog,	int item) {
						
						switch (item) {
						
						case 0:

							Intent intent = new Intent(ViewProfileListActivity.this,ProfileActivity.class);
							
							intent.putExtra("selected_id",profileId);

							startActivity(intent);

							// startActivity(intent);
							/*
							 * Toast.makeText(
							 * getApplicationContext(),
							 * String.valueOf(selectedId),
							 * Toast.LENGTH_SHORT).show();
							 */
							break;
							
					/*	case 1:
					 *		// function 2 code here
					 *
					 *		Intent secondIntent = new Intent(ViewProfileListActivity.this,UpdateProfileActivity.class);
					 *		// intent.putExtra(RestaurantConstants.SELECTED_ID,(Parcelable)
					 *		// res_List.get(pos));
					 *		secondIntent.putExtra("selected_id",profileId);
					 *
					 *		startActivity(secondIntent);
					 *  	break;
					 */						
						case 1:							

							AlertDialog.Builder alertDialog = new AlertDialog.Builder( ViewProfileListActivity.this);

							// Setting Dialog Title
							alertDialog.setTitle("delete");

							// Setting Dialog Message
							alertDialog.setMessage("Are you sure you want delete this?");

							// Setting Icon to Dialog
							alertDialog.setIcon(R.drawable.ic_launcher);

							// Setting Positive "Yes" Button
							alertDialog.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,	int which) {

									// to invoke YES event
									mDataSource.deleteData(profileId);
									finish();
									startActivity(getIntent());
								}
							});
							// Showing Alert Message
							// Setting Negative "NO" Button
							alertDialog.setNegativeButton("No",	new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,	int which) {
									Toast.makeText(ViewProfileListActivity.this,"You clicked on No",Toast.LENGTH_SHORT).show();
									dialog.cancel();
								}	
							});
							alertDialog.show();
							break;
							
						case 2:							

							Intent thirdIntent = new Intent(ViewProfileListActivity.this, MapActivity.class);
							
							Bundle bundle = new Bundle();

							bundle.putString("latitude", mProfile.getmLatitude());
							bundle.putString("longitude", mProfile.getmLongitude());
							bundle.putString("bank_name", mProfile.getmBankName());
							thirdIntent.putExtras(bundle);							
							
							startActivity(thirdIntent);
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
		getMenuInflater().inflate(R.menu.view_profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// Take appropriate action for each action item click
				switch (item.getItemId()) {
				case R.id.add:
					add();
					return true;
				
				default:
					return super.onOptionsItemSelected(item);
		}
	}

	private void add() {
		Intent i = new Intent(ViewProfileListActivity.this, CreateProfileActivity.class);
		startActivity(i);
		
	}

	
}

