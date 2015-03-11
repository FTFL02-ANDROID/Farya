package com.ftfl.icaremyself.fragment;

import java.util.List;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.ftfl.icaremyself.CreateDoctorProfileActivity;
import com.ftfl.icaremyself.R;
import com.ftfl.icaremyself.ViewDoctorProfileActivity;
import com.ftfl.icaremyself.adapter.DoctorProfileViewAdapter;
import com.ftfl.icaremyself.database.DoctorProfileDBSource;
import com.ftfl.icaremyself.util.DoctorProfileModel;

public class FragmentDoctorProfile extends Fragment {
	
   //initialization variable for list view
  	private ListView mListView = null;
  	
  	List<DoctorProfileModel> allDoctor;
  	DoctorProfileDBSource mDoctorProfileDBSource;
  	DoctorProfileViewAdapter arrayDoctorAdapter;	
  	
  	DoctorProfileModel mDoctorProfile;	
  	private int selectedId = 0;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

    public FragmentDoctorProfile() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
    	 mDoctorProfile = new DoctorProfileModel();
    	 mDoctorProfileDBSource = new DoctorProfileDBSource(getActivity());		
    	 allDoctor = mDoctorProfileDBSource.getDoctorProfileList();

        View view = inflater.inflate(R.layout.fragment_layout_doctor_profile, container,false);

        //object of MyPlace adapter
        arrayDoctorAdapter = new DoctorProfileViewAdapter( getActivity() , allDoctor);		
		
		// adding it to the list view.
		mListView = (ListView) view.findViewById(R.id.lvDoctor);
		mListView.setAdapter(arrayDoctorAdapter);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

				AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
				// Setting Dialog Title
				final String[] menuList = {"Delete Profile" ,  "View Profile"};
				
				alertDialog.setTitle("Doctor Profile");
				alertDialog.setIcon(R.drawable.ic_launcher);
				
				selectedId = allDoctor.get(position).getmDoctorID();
				
				
				
				alertDialog.setItems(menuList, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,	int item) {
						
						switch (item) {
						
						case 0:							

							AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

							// Setting Dialog Title
							alertDialog.setTitle("Do You Want to delete");

							// Setting Dialog Message
							alertDialog.setMessage("Are you sure you want delete this?");

							// Setting Icon to Dialog
							alertDialog.setIcon(R.drawable.ic_launcher);

							// Setting Positive "Yes" Button
							alertDialog.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,	int which) {									
									
									// Loop backwards, so you can remove the items from within the loop
									for ( int i = arrayDoctorAdapter.getCount() - 1; i >= 0; i--) {
									   
									        // This item is checked and can be removed
									    	mDoctorProfileDBSource.deleteData(selectedId);
									    	arrayDoctorAdapter.remove(arrayDoctorAdapter.getItem(i));
									   
									}
									
							        // This item is checked and can be removed
									//mDoctorProfileDBSource.deleteData(selectedId);
							    	//arrayDoctorAdapter.remove(arrayDoctorAdapter.getItem(selectedId));							 
									arrayDoctorAdapter.notifyDataSetChanged();
									
									getActivity().getFragmentManager().popBackStack();
									
								}
							});
							// Showing Alert Message
							// Setting Negative "NO" Button
							alertDialog.setNegativeButton("No",	new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,	int which) {
									Toast.makeText(	getActivity(),"You clicked on No",Toast.LENGTH_SHORT).show();
									dialog.cancel();
								}

							});
							alertDialog.show();
							break;
							
						case 1:
							
							//communicate from take photo activeity to activity
							Intent intent = new Intent(	getActivity(), ViewDoctorProfileActivity.class);							
							intent.putExtra("selected_id",selectedId);
							startActivity(intent);
							
							break;
						}
					}
				});
				
				AlertDialog menuDrop = alertDialog.create();
				menuDrop.show();
			}
		});
         
          return view;
    }
    
    @Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);		
		
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
			inflater.inflate(R.menu.doctor_profile, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		   // handle item selection
		   switch (item.getItemId()) {
		      case R.id.add:
		    	  add();
		         return true;
		      default:
		         return super.onOptionsItemSelected(item);
		}
	}

	private void add() {
		Intent intent = new Intent(getActivity(), CreateDoctorProfileActivity.class);
		startActivity(intent);
		
	}

}
