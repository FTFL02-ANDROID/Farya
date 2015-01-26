package com.ftfl.atm;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.app.Activity;
import android.os.Bundle;

public class MapActivity extends Activity {
	
	static final LatLng BCC = new LatLng(23.778691, 90.374600);
	private GoogleMap map;

	@SuppressWarnings("static-access")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

		@SuppressWarnings("unused")
		Marker bcc = map.addMarker(new MarkerOptions().position(BCC).title("BCC"));

		// Move the camera instantly to bcc with a zoom of 15.
		 map.moveCamera(CameraUpdateFactory.newLatLngZoom(BCC, 15));

		// Zoom in, animating the camera.
		map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

		// change map style
		map.setMapType(map.MAP_TYPE_HYBRID);

		// show user’s current location on the map
		map.setMyLocationEnabled(true);

		// My location button will be used to move map to your current location
		// map.getUiSettings().setMyLocationButtonEnabled(true);
	}	
}
