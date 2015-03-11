package com.ftfl.icaremyself.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ftfl.icaremyself.R;
import com.ftfl.icaremyself.util.ToDoListModel;

public class ToDoListViewAdapter extends ArrayAdapter<ToDoListModel>{
	
	//initialization variable
	private static LayoutInflater inflater = null;
	private final Activity mContext;
	List<ToDoListModel> allToDo;
	ToDoListModel mToDoModel;
	
	//constractor of Adapter
	public ToDoListViewAdapter(Activity eContext,  List<ToDoListModel> allToDo ) {
		super(eContext,R.layout.todo_list_view, allToDo);
		this.mContext = eContext;
		this.allToDo = allToDo;		
		
		//Layout inflator to call external xml layout () 
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	//Create a holder Class to contain inflated xml file elements 
	public static class ViewHolder {
		
		public TextView mToDoDate;
		
	}
	
	@SuppressLint("InflateParams") 
	public View getView(int position, View convertView, ViewGroup parent) {

		View rowView = convertView;
		ViewHolder holder;
		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			rowView = inflater.inflate(R.layout.todo_list_view, null);

			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			
			holder.mToDoDate = (TextView) rowView.findViewById(R.id.viewListDateOfTodo);						

			/************ Set holder with LayoutInflater ************/
			rowView.setTag(holder);
			
		} else
			holder = (ViewHolder) rowView.getTag();

		//get data from database
		mToDoModel = allToDo.get(position);		
			
		holder.mToDoDate.setText(mToDoModel.getmDate().toString());		
	
			
		return rowView;
	}

}
