package com.group.mid;

import com.example.group_projectmid.R;

public class GridItem {
	private String name;
	private int genderID;
	private int image;
	private String studentID;
	private static int[] images = {
			R.drawable.boy_01,R.drawable.boy_02,R.drawable.boy_03,R.drawable.boy_04,
			R.drawable.girl_01,R.drawable.girl_02,R.drawable.girl_03,
			R.drawable.boy_01_choose,R.drawable.boy_02_choose,R.drawable.boy_03_choose,R.drawable.boy_04_choose,
			R.drawable.girl_01_choose,R.drawable.girl_02_choose,R.drawable.girl_03_choose
	};
		
	public GridItem(String name,int genderID,int image_identifier, String studentID){
		this.name = name;
		this.genderID = genderID;
		this.image = images[image_identifier];
		this.studentID = studentID;
	}
	
	/*
	public String getCoodinate(){
		return coordinate;
	}
	*/
	
	public String getName(){
		return name;
	}
	
	public int getGenderID(){
		return genderID;
	}
	
	public void setDrawable(int image) {
		this.image = image;
	}
	
	public int getDrawable(){
		return image;
	}
	
	public String getStudentID(){
		return studentID;
	}
}
