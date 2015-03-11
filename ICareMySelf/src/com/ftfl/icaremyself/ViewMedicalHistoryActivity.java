package com.ftfl.icaremyself;

import com.ftfl.icaremyself.database.MyMedicalHistoryDBSource;
import com.ftfl.icaremyself.util.MyMedicalHistoryModel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class ViewMedicalHistoryActivity extends Activity  {
	
	// Widget GUI
	private ImageView mViewHistoryPrescription = null;
	
    //database initialization
	MyMedicalHistoryDBSource mHistoryDBSource;  	
	MyMedicalHistoryModel mPrescription;
	
	private int mSelectedId = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_history);
		
		mHistoryDBSource = new MyMedicalHistoryDBSource(this);	
		
		/* get Id from Intent */
		mSelectedId = getIntent().getExtras().getInt("selected_id");
		
			
		mPrescription = mHistoryDBSource.getDetailHistory(mSelectedId);
		
		mViewHistoryPrescription = (ImageView) findViewById(R.id.viewHistoryPrescription);
		
		BitmapFactory.Options options = new BitmapFactory.Options();		
		Bitmap image = BitmapFactory.decodeFile(mPrescription.getmHistoryPrescribtion(), options);		
		mViewHistoryPrescription.setImageBitmap(image);
		
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
