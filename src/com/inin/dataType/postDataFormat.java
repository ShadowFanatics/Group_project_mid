package com.inin.dataType;

public class postDataFormat {
	public long id;
	public String title;
	public String message;
	public String date;
	public String time;
	public String teacher;
	
	public postDataFormat() {
		this.id = 0;
		this.title = "TEST";
		this.message = "123";
		this.date = "123";
		this.time = "123";
		this.teacher = "teacher";
	}
	public postDataFormat(long id, String title, String message, String date,
			String time, String teacher) {
		this.id = id;
		this.title = title;
		this.message = message;
		this.date = date;
		this.time = time;
		this.teacher = teacher;
	}
}
