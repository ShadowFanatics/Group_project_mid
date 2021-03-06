package com.group.mid;

import java.util.ArrayList;
import java.util.Collections;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.example.group_projectmid.DataBaseConnector;
import com.example.group_projectmid.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ScanActivity extends Activity
{
	Button scan;
	TextView result;
	private Button exitButton;
	
	private GlobalVariable globalVariable;
	private ArrayList<StudentData> studentList;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        
        scan = (Button)findViewById(R.id.scan);
        result = (TextView)findViewById(R.id.result);
        exitButton = (Button) findViewById(R.id.button_exit);
        
        scan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentIntegrator scanIntegrator = new IntentIntegrator(ScanActivity.this);
				
				scanIntegrator.initiateScan(scanIntegrator.ONE_D_CODE_TYPES, 1);
			}
		});
        
        //結束簽到的按鈕
        exitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ScanActivity.this.finish();
			}
		});
        
        setupStudentData();
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
    	
    	if (scanningResult != null) {
    		String resultString = scanningResult.getContents();
    		String idNumber = resultString.substring(0, resultString.length()-1);
    		
    		boolean isFound = false;
    		for (int i = 0; i < studentList.size(); i++) {
				if (idNumber.equals(studentList.get(i).getStudentID())) {
					isFound = true;
					break;
				}
			}
    		
    		if (isFound) {
    			result.setText(idNumber+" 已經完成簽到");
    			//將掃描回傳的id值再傳給SeatActivity
        		Bundle bundle = new Bundle();
        		bundle.putString("id", idNumber);
        		Intent seatIntent = new Intent();
        		seatIntent.setClass(ScanActivity.this, SeatActivity.class);
        		seatIntent.putExtras(bundle);
        		startActivity(seatIntent);
			}
    		else {
    			result.setText("你沒有修這堂課");
    			showGetOutDialog();
			}
    	}
    	else{
    	    Toast toast = Toast.makeText(getApplicationContext(), 
    	        "No scan data received!", Toast.LENGTH_SHORT);
    	    toast.show();
    	}
	}
    
    private void setupStudentData() {
    	//自訂的class GlobalVariable 繼承Application
    	//為各個activity通用
        globalVariable = (GlobalVariable) getApplicationContext();
        studentList = new ArrayList<StudentData>();
        
        // TODO 從資料庫讀取學生資料
        StudentData studentData[] = DataBaseConnector.getSeats();
        for (int i = 0; i < studentData.length; i++) {
        	studentList.add(studentData[i]);
		}
        
        //接收來自RegisterActivity的資訊
        Bundle bundle = getIntent().getExtras();
        if(bundle.getBoolean("isRandom"))
        {
        	//亂數座位
        	Collections.shuffle(studentList);
        	for (int i = 0; i < studentList.size(); i++) {
				studentList.get(i).setPosition(i);
			}
        	
        }
        
        //存到globalVariable
        globalVariable.setStudentList(studentList);
        
        // TODO 看要不要"另外存"一個亂數座位到資料庫
        
    }
    
	private void showGetOutDialog() {		
		AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
		MyAlertDialog.setTitle(R.string.getout_string);
		MyAlertDialog.setIcon(android.R.drawable.ic_dialog_alert);
		MyAlertDialog.setMessage("同協你沒修這堂課喔～");
		MyAlertDialog.setNegativeButton("對不起", new DialogInterface.OnClickListener() {	
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		MyAlertDialog.create().show();			
	}
}
