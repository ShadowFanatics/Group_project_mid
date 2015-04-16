package com.group.mid;

import com.example.group_projectmid.R;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.renderscript.Int2;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

public class BluetoothSeatActivity extends Activity {
	
	private GridView gridView;
	private ImageButton backButton;
	private String[] names;
	private int[] genderID;
	private String[] studentID;
	private int[] positionID;
	private int display_width, display_height, column;
	
	private GlobalVariable globalVariable;
	private ArrayList<StudentData> studentList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seat);
		
		//接收來自scan的資訊
		Bundle bundle = getIntent().getExtras();
		String receivedSeat[] = bundle.getStringArray("seat");
		//test
		//String receivedID = "100502531";
		
		//取得GlobalVariable裡的studentList
		getStudentData();
		
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		display_width = displayMetrics.widthPixels;
		display_height = displayMetrics.heightPixels;
		column = 6;
		
		backButton = (ImageButton) findViewById(R.id.blackboard);
		backButton.setOnClickListener(backButtonListener);
		
		gridView = (GridView) findViewById(R.id.GridLayout1);
		gridView.setNumColumns(column);//改行數
		BluetoothGridItemAdapter adapter = new BluetoothGridItemAdapter(names, genderID, studentID, receivedSeat, display_width, display_height, column, this);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(BluetoothSeatActivity.this, "item:" + (position+1), Toast.LENGTH_SHORT).show();
				
			}
		});
	}
	
	private void getStudentData() {
		//自訂的class GlobalVariable 繼承Application
		//為各個activity通用
		globalVariable = (GlobalVariable) getApplicationContext();
		studentList = globalVariable.getStudentList();
		
		names = new String[studentList.size()];
		genderID = new int[studentList.size()];
		studentID = new String[studentList.size()];
		//positionID = new int[studentList.size()];
		for (int i = 0; i < studentList.size(); i++) {
			names[i] = studentList.get(i).getName();
			genderID[i] = studentList.get(i).getGenderId();
			studentID[i] = studentList.get(i).getStudentID();
			//positionID[i] = studentList.set(i).getPosition();
		}
	}
	
	private Button.OnClickListener backButtonListener = new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			BluetoothSeatActivity.this.finish();
		}
	};
}
