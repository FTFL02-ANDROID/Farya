package com.ftfl.icaremyself.fragment;

import com.ftfl.icaremyself.R;
import com.ftfl.icaremyself.util.ICareMySelfConstants;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentAboutUs extends Fragment {
	
    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";
    
	// Widget GUI
	private TextView mTvMySelf = null;
	private TextView mTvAboutTrackTraineeOne = null;
	private TextView mTvAboutTrackTraineeTwo = null;
	private TextView mTvAboutTrackTraineeThree = null;
	private TextView mTvAboutFTFL = null;
	
	//initialization string variable
	private String mMySelf = "";
	private String mAboutTrackTraineeOne = "";
	private String mAboutTrackTraineeTwo = "";
	private String mAboutTrackTraineeThree = "";
	private String mAboutFTFL = "";

    public FragmentAboutUs() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
    	
		  //get static string data
		  getStringData();

          View view = inflater.inflate(R.layout.fragment_layout_about_us, container,false);
          
          mTvMySelf = (TextView) view.findViewById(R.id.tvMySelf);	
          mTvMySelf.setText(mMySelf);
	  		
          mTvAboutTrackTraineeOne = (TextView) view.findViewById(R.id.tvAboutTrackTraineeOne);	
          mTvAboutTrackTraineeOne.setText(mAboutTrackTraineeOne);
          mTvAboutTrackTraineeTwo = (TextView) view.findViewById(R.id.tvAboutTrackTraineeTwo);	
          mTvAboutTrackTraineeTwo.setText(mAboutTrackTraineeTwo);
          mTvAboutTrackTraineeThree = (TextView) view.findViewById(R.id.tvAboutTrackTraineeThree);	
          mTvAboutTrackTraineeThree.setText(mAboutTrackTraineeThree);
	  		
          mTvAboutFTFL = (TextView) view.findViewById(R.id.tvAboutFTFL);
          mTvAboutFTFL.setText(mAboutFTFL);

          return view;
    }

	private void getStringData() {
		
		mMySelf = (ICareMySelfConstants.MySelf);
		
		mAboutTrackTraineeOne = (ICareMySelfConstants.AboutTrackTrainerOne);
		mAboutTrackTraineeTwo = (ICareMySelfConstants.AboutTrackTrainerThree);
		mAboutTrackTraineeThree = (ICareMySelfConstants.AboutTrackTrainerTwo);
		
		mAboutFTFL = (ICareMySelfConstants.AboutFTFL);
		
	}
}
