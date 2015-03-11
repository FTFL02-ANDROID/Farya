package com.ftfl.icaremyself.util;

public class ToDoListModel {
	
	//initialization
	Integer mToDoID = 0;		
	String mWhatToDo = "";	
	String mDate = "";
	public ToDoListModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ToDoListModel(Integer mToDoID, String mWhatToDo, String mDate) {
		super();
		this.mToDoID = mToDoID;
		this.mWhatToDo = mWhatToDo;
		this.mDate = mDate;
	}
	public ToDoListModel(String mWhatToDo, String mDate) {
		super();
		this.mWhatToDo = mWhatToDo;
		this.mDate = mDate;
	}
	public Integer getmToDoID() {
		return mToDoID;
	}
	public void setmToDoID(Integer mToDoID) {
		this.mToDoID = mToDoID;
	}
	public String getmWhatToDo() {
		return mWhatToDo;
	}
	public void setmWhatToDo(String mWhatToDo) {
		this.mWhatToDo = mWhatToDo;
	}
	public String getmDate() {
		return mDate;
	}
	public void setmDate(String mDate) {
		this.mDate = mDate;
	}
	

}
