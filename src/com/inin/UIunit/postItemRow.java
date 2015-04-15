package com.inin.UIunit;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class postItemRow extends TableLayout {
	private TableRow upRow;
	private TextView title;
	private TextView date;
	private TextView teacher;

	private TableRow downRow;
	private TextView message;
	private TextView time;
	private TableLayout.LayoutParams row_layout;
	private TableRow.LayoutParams view_layout;
	
	public postItemRow(Context context) {
		super(context);
		upRow = new TableRow(context);
		downRow = new TableRow(context);
		title = new TextView(context);
		date = new TextView(context);
		teacher = new TextView(context);
		message = new TextView(context);
		time = new TextView(context);
		row_layout = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		view_layout = new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		
		upRow.setLayoutParams(row_layout);
		upRow.setGravity(Gravity.CENTER_HORIZONTAL);
		downRow.setLayoutParams(row_layout);
		downRow.setGravity(Gravity.CENTER_HORIZONTAL);
	}

	public void set(String title, String message, String date, String time, String teacher) {
		this.title.setText(title);
		this.title.setLayoutParams(view_layout);
		this.message.setText(message);
		this.message.setLayoutParams(view_layout);
		this.date.setText(date);
		this.date.setLayoutParams(view_layout);
		this.time.setText(time);
		this.time.setLayoutParams(view_layout);
		this.teacher.setText(teacher);
		this.teacher.setLayoutParams(view_layout);
		
		upRow.addView(this.title);
		upRow.addView(this.date);
		upRow.addView(this.teacher);
		downRow.addView(this.message);
		downRow.addView(this.time);
		
		this.addView(upRow);
		this.addView(downRow);
		/*this.addView(this.title);
		this.addView(this.date);
		this.addView(this.teacher);
		this.addView(this.message);
		this.addView(this.time);*/
	}
}
