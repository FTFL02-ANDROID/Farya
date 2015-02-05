package com.ftfl.photoviewer;

import java.util.List;


import com.ftfl.photoviewer.adapter.PVAdapter;
import com.ftfl.photoviewer.database.PVSource;
import com.ftfl.photoviewer.uitl.PVModel;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ViewPhotoListActivity extends Activity {
	
	ListView mListView = null;
	List<PVModel> allPhoto = null;
	PVModel mPhoto = null;
	PVSource mDBSource = null;
	PVAdapter arrayAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_photo_list);
		
		mDBSource = new PVSource(this);
		
		allPhoto = mDBSource.getPhotoList();
		arrayAdapter = new PVAdapter(this, allPhoto);
		
		// adding it to the list view.
		mListView = (ListView) findViewById(R.id.lv);
		mListView.setAdapter(arrayAdapter);
	}

	
}
