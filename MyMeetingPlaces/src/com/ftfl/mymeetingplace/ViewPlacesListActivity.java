package com.ftfl.mymeetingplace;

import java.util.List;

import com.ftfl.mymeetingplace.adapter.MyPlaceAdapter;
import com.ftfl.mymeetingplace.database.MyPlaceDBSource;
import com.ftfl.mymeetingplace.uitl.MyPlaceModel;

import android.app.Activity;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPlacesListActivity extends Activity implements LocationListener{
	
	//initialization variable
	ListView mListView;
	List<MyPlaceModel> allPlace;
	MyPlaceDBSource mDBSource;
	MyPlaceAdapter arrayAdapter;	
	
	MyPlaceModel mplace;
	
	private Button mBtnHome = null;
	
	private TextView mCurrentLatitude = null;
	private TextView mCurrentLongitude = null;
	
	
	private static String eCurrentLatitude = "";
	private static String eCurrentLongitude = "";
	
	public static String mDistance = "";
	
	
	private double currentLatitude = 0.0;
	private double currentLongitude = 0.0;
	
	private int selectedId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_places_list);
		
		mplace = new MyPlaceModel();
		
		//Location method call for get latitude longitude
		getLocationData();
		
		//initialize variable find by id
		mCurrentLatitude = (TextView) findViewById(R.id.tvCurrentLatitudeView);
		mCurrentLatitude.setText(eCurrentLatitude);
		
		mCurrentLongitude = (TextView) findViewById(R.id.tvCurrentLongitudeView);
		mCurrentLongitude.setText(eCurrentLongitude);
		
		
		
		mDBSource = new MyPlaceDBSource(this);		
		allPlace = mDBSource.getPlaceList();
		
		
		
		//object of MyPlace adapter
		arrayAdapter = new MyPlaceAdapter(this, allPlace, currentLatitude, currentLongitude);		
		
		// adding it to the list view.
		mListView = (ListView) findViewById(R.id.lv);
		mListView.setAdapter(arrayAdapter);
		
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

				AlertDialog.Builder alertDialog = new AlertDialog.Builder(ViewPlacesListActivity.this);
				// Setting Dialog Title
				final String[] menuList = { "View Profile", "Delete Profile"};
				
				alertDialog.setTitle("Options");
				alertDialog.setIcon(R.drawable.ic_launcher);
				
				selectedId = allPlace.get(position).getmID();
				
				alertDialog.setItems(menuList, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,	int item) {
						
						switch (item) {
						case 0:

								Intent intent = new Intent(	ViewPlacesListActivity.this, SingleProfileActivity.class);							
								intent.putExtra("selected_id",selectedId);
								startActivity(intent);
							
							break;
						case 1:
							
							Toast.makeText(getApplicationContext(),String.valueOf(selectedId),Toast.LENGTH_SHORT).show();

							AlertDialog.Builder alertDialog = new AlertDialog.Builder(ViewPlacesListActivity.this);

							// Setting Dialog Title
							alertDialog.setTitle("Do You Want to delete");

							// Setting Dialog Message
							alertDialog.setMessage("Are you sure you want delete this?");

							// Setting Icon to Dialog
							alertDialog.setIcon(R.drawable.ic_launcher);

							// Setting Positive "Yes" Button
							alertDialog.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,	int which) {
									
									mDBSource.deleteData(selectedId);
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
		
		mBtnHome = (Button) findViewById(R.id.btnHome);
		//event listener for on click metnod
		mBtnHome.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent mIntentOne = new Intent(ViewPlacesListActivity.this, SingleProfileActivity.class);
				startActivity(mIntentOne);
				
			}			
		});
	}	

	//get location
	protected void getLocationData() {	
		
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		// Creating a criteria object to retrieve provider
		Criteria criteria = new Criteria();

		// Getting the name of the best provider
		String provider = locationManager.getBestProvider(criteria, true);

		// Getting Current Location
		Location mLocation = locationManager.getLastKnownLocation(provider);

		if (mLocation != null) {
			onLocationChanged(mLocation);
		}
		
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
					
	}

	//get latitude and longitude
	@Override
	public void onLocationChanged(Location location) {
		
		
		try{
			currentLatitude = location.getLatitude();
		}catch(Exception e)
        {
        	//by defoult 
        }

		// Getting longitude of the current location
		try{
			currentLongitude = location.getLongitude();	
		}catch(Exception e)
        {
        	//by defoult 
        }
		
		eCurrentLatitude =  Double.toString(currentLatitude);
		eCurrentLongitude = Double.toString(currentLongitude);
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		//if Gps is Disable
		Toast.makeText( getApplicationContext(),"Gps Disabled",	Toast.LENGTH_SHORT ).show();		
	}

	@Override
	public void onProviderEnabled(String provider) {
		//if Gps is enable
		Toast.makeText( getApplicationContext(),"Gps Enabled",Toast.LENGTH_SHORT).show(); 		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_places_list, menu);
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
		Intent i = new Intent(ViewPlacesListActivity.this, TakePhotoActivity.class);
		startActivity(i);
		
	}
}
