package com.group.mid;

import java.util.ArrayList;
import java.util.Collections;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.example.group_projectmid.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScanActivity extends Activity
{
	Button scan;
	TextView result;
	
	private GlobalVariable globalVariable;
	private ArrayList<StudentData> studentList;
	//假資料
	private int[] positions = {
			0,1,2,3,4,5,6,7
	};
	private String[] names = new String[]{
			"三個字","林岳霖","inin","余達明","A-lin","Yui","Yuki","Alice"
	};
	private String[] studentID = new String[]{
			"100502529","100502531","100502521","100502068",
			"100503456","100504567","100505678","100506789"
	};
	private int[] genderID = new int[]{//男生1女生0
			1,1,1,1,0,0,0,0
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        
        scan = (Button)findViewById(R.id.scan);
        result = (TextView)findViewById(R.id.result);
        
        scan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentIntegrator scanIntegrator = new IntentIntegrator(ScanActivity.this);
				
				scanIntegrator.initiateScan(scanIntegrator.ONE_D_CODE_TYPES, 1);
			}
		});
        
        setupStudentData();
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
    	
    	if (scanningResult != null) {
    		String resultString = scanningResult.getContents();
    		String idNumber = resultString.substring(0, resultString.length()-1);
    		result.setText(idNumber);
    		
    		boolean isFound = false;
    		for (int i = 0; i < studentID.length; i++) {
				if (idNumber.equals(studentID[i])) {
					isFound = true;
					break;
				}
			}
    		
    		if (isFound) {
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
        
        //假資料
        StudentData studentData;
        for (int i = 0; i < 8; i++) {
        	studentData = new StudentData(positions[i], names[i], studentID[i], genderID[i]);
        	studentList.add(studentData);
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
}