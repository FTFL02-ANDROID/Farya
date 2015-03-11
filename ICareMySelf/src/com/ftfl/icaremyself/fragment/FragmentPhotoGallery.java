package com.ftfl.icaremyself.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ftfl.icaremyself.R;
import com.ftfl.icaremyself.adapter.ImageAdapter;
import com.ftfl.icaremyself.util.ICareMySelfConstants;

public class FragmentPhotoGallery extends Fragment {
	
    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";
    
    List<String> mImagePathList = new ArrayList<String>();


    public FragmentPhotoGallery() {
		File mediaStorageDir = new File(Environment	.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				ICareMySelfConstants.IMAGE_DIRECTORY_NAME);
		for (File imageFile : mediaStorageDir.listFiles()) {
			if (imageFile.isFile()) {
				mImagePathList.add(imageFile.getAbsolutePath());
			}
		}
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

          View view = inflater.inflate(R.layout.fragment_layout_photo_gallery, container,false);
          
  		GridView gridview = (GridView) view.findViewById(R.id.gridview);
  		gridview.setAdapter(new ImageAdapter(getActivity(), mImagePathList));

          return view;
    }

}
