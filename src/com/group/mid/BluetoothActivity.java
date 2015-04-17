package com.group.mid;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import com.example.group_projectmid.DataBaseConnector;
import com.example.group_projectmid.R;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.bluetooth.BluetoothSocket;


import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.TextView;



public class BluetoothActivity extends Activity {
	static final String SPP_UUID = "0001101-0000-1000-8000-00805F9B34FB";
	static final UUID uuid = UUID.fromString(SPP_UUID);
	static final String tag = "BtSpp";
	private static final int REQUEST_ENABLE=0x1;
	
	//測試用UI
	private TextView seat1,textView3;
	private Button countSeat;
	private int currentSeat = 0;
	Timer timer;
	ArrayList<String> devices = new ArrayList<String>();
	ArrayAdapter<String> adapter1;
	//設備 MAC ADDRESS
	String devAddr[] = {"98:D3:31:B3:7C:75","00:15:FF:F3:2A:65"};
	BluetoothAdapter btAdapt;
	BluetoothSocket btSocket ;
	BluetoothDevice btDevice;
	SppReceiver sppReceiver;	
	InputStream btIn = null;
	OutputStream btOut = null;
	private String msg = "";
	//check if the seat have student
	String seatOn[] = {"",""};
		
	private GlobalVariable globalVariable;
	private ArrayList<StudentData> studentList;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        
        setView();
        setListener();   
        setupStudentData();
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
        
        //存到globalVariable
        globalVariable.setStudentList(studentList);
        
        // TODO 看要不要"另外存"一個亂數座位到資料庫        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void setView(){
    	seat1 = (TextView)findViewById(R.id.seat1);
    	textView3 = (TextView)findViewById(R.id.textView3);
    	countSeat = (Button)findViewById(R.id.countSeat);
    }
    
    public void setListener(){
    	countSeat.setOnClickListener(countSeat_listener);
    	
    	btAdapt = BluetoothAdapter.getDefaultAdapter();//initialize
    	if(!btAdapt.isEnabled()){
			Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(intent,REQUEST_ENABLE);  
		} 		
    }   
    
	//測試用UI
    
	private Button.OnClickListener countSeat_listener = new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			//TIMER 
			seat1.setText("waiting....");
			timer = new Timer(true);
			timer.schedule( new timerTask(), 500, 6000);
			
		}		
	};
    
    //藍芽設備轉換 下面為參數
    //timer.schedule( new timerTask(), 500, 4000);  
	public class timerTask extends TimerTask
	{
	    public void run()
	    {
	    	if(currentSeat<2){
		    	connectDevice();
		    	Log.i(tag,"hi");
	    	}
	    	else{
	    		currentSeat = 0;
	    		//Log.i(tag,"restart");
	    		//測試用UI
	    		//handlerUpdate.sendEmptyMessage(0);
	    		Bundle bundle = new Bundle();
	    		bundle.putStringArray("seat", seatOn);
	    		Intent seatIntent = new Intent();
	    		seatIntent.setClass(BluetoothActivity.this, BluetoothSeatActivity.class);
	    		seatIntent.putExtras(bundle);
	    		startActivity(seatIntent);	   		
	    		timer.cancel();
	    	}	    	
	    }
	};
	/*
	Handler handlerUpdate = new Handler(){
		public void handleMessage(Message m){
			for(int i=0;i<2;i++){
    			textView3.setText("waiting....");
    		}
		}		
	};	*/
	private  void connectDevice(){
		
		//取得藍芽設備
		btDevice = btAdapt.getRemoteDevice(devAddr[currentSeat]);
		//連接藍芽SOCKET
		try {
			btSocket = btDevice.createRfcommSocketToServiceRecord(uuid);
		}catch(IOException e){
			Log.e(tag,"btsoket fail");
		}
	
		try{
			
			btSocket.connect();
			Log.d(tag,"BT_Socket connect");
		}catch(IOException e){
			Log.e(tag,"btsoket connect fail");
			try {
				btSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				Log.e(tag,"btsoket connect fail closing..");
				e1.printStackTrace();
			}
			btSocket = null;
			//Toast.makeText(MainActivity.this,"�s�����`", Toast.LENGTH_SHORT).show();			
		}
		try{		
				btIn = btSocket.getInputStream();
				btOut = btSocket.getOutputStream();
				synchronized(this){	
					connected(); 
				}	
		}catch(IOException e){
			Log.e(tag,"btin connect fail closing..");
		}
		//Toast.makeText(MainActivity.this,"�Ū޸˸m�w�}��:" + devAddr[currentSeat],Toast.LENGTH_SHORT).show();
			
	}
	private void connected(){		
		sppReceiver = new SppReceiver(btIn);		
		sppReceiver.start();		
		Log.d(tag,"connected");
		Log.i(tag,"btIn: " + btIn + " btOut: " + btOut);
	}	 
	

	public class SppReceiver extends Thread{
		
		private InputStream input = null;	
		
		public SppReceiver(InputStream in){
			input = in;			
			Log.i(tag,"Sppreceiver current:" + currentSeat);
		}
		
		public void run(){
			
			byte[] data = new byte[1024];
			int length = 0;	
			String outPut = "1";
			byte[] outData = outPut.getBytes();			
			if(input == null){
				//Log.d(tag,"inputStream null");
				return;
			}					
			while(true){
				try{ 
					btOut.write(outData);
					length = input.read(data);
					Log.i(tag,"Spp receiver");
					if(length > 0 ){						
						
						msg = new String(data,0,length,"ASCII");																
						
						Log.d(tag,"msg: " + msg);
						if(msg.equals("y")||msg.equals("n")){
							btHandler.sendEmptyMessage(0);
							break;
						}
					}
				} catch(IOException e){
					Log.e(tag,"SppReceiver disconnect");
					disconnect();
				}
			}
								
		}
		public void cancel(){
			if(btSocket == null)
				return;
			try{
				btSocket.close();
			} catch (IOException e){
				e.printStackTrace();
				Log.e(tag,"close Socket failed");
			}
		}	
	}
	private void disconnect(){
		btIn = null;
		btOut = null;		
		sppReceiver.cancel();			
		msg = "";
		Log.e(tag,"disconnect");		
	}
	Handler btHandler = new Handler(){
		public void handleMessage(Message m){	
			seatOn[currentSeat] = msg;
			Log.i(tag,"seat:" + seatOn[currentSeat]);
			// 測試用UI
			seat1.setText("waiting...");	
			Log.i(tag,"seat1:" + seatOn[currentSeat]);	
			Log.i(tag,"btIn: " + btIn + " btOut: " + btOut);
			disconnect();		
			currentSeat++;			
		}
	};
	protected void onPause(){
		super.onPause();
		
	}
	protected void onStop(){
		super.onStop();
	}
	protected void onDestroy(){
		super.onDestroy();		
		if(btIn != null){
			try{
				btSocket.close();
				//btServerSocket.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
