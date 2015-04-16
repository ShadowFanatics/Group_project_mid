<<<<<<< HEAD
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
=======
package com.group.mid;

import java.util.ArrayList;

import android.app.Application;

public class GlobalVariable extends Application {
	private static ArrayList<StudentData> studentList;
	
	public static ArrayList<StudentData> getStudentList(){
		return studentList;
	}
	
	public static void setStudentList(ArrayList<StudentData> List){
		studentList = List;
	}
}
>>>>>>> origin/panda
