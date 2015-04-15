package com.example.group_projectmid;

import com.inin.dataType.userData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViews();
		setListeners();
	}

	private Button button_get_record;
	private Button button_to_push_activity;

	private void findViews() {
		button_get_record = (Button) findViewById(R.id.get_record);
		button_to_push_activity = (Button) findViewById(R.id.to_push_activity);
	}

	private void setListeners() {
		button_get_record.setOnClickListener(getDBRecord);
		button_to_push_activity.setOnClickListener(toPushActivity);
	}

	private Button.OnClickListener getDBRecord = new Button.OnClickListener() {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			userData user = new userData();
			int userType = DataBaseConnector.logIn("inin", "1234");
			Log.e("type_tag", String.valueOf(userType));
			if (userType != -1) {
				user = DataBaseConnector.getUserData();
			}
		}
	};
	
	private Button.OnClickListener toPushActivity = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, pushBoardActivity.class);
			startActivity(intent); 
			MainActivity.this.finish();
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
