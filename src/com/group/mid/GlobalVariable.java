package com.group.mid;

import java.util.ArrayList;

import android.app.Application;

public class GlobalVariable extends Application {
	private ArrayList<StudentData> studentList;
	
	public ArrayList<StudentData> getStudentList(){
		return studentList;
	}
	
	public void setStudentList(ArrayList<StudentData> studentList){
		this.studentList = studentList;
	}
}
