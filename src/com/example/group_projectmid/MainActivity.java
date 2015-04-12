package com.example.group_projectmid;

import android.app.Activity;
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

	private void findViews() {
		button_get_record = (Button) findViewById(R.id.get_record);
	}

	private void setListeners() {
		button_get_record.setOnClickListener(getDBRecord);
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
