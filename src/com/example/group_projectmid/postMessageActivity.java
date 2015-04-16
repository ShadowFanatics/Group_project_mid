package com.example.group_projectmid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class postMessageActivity extends Activity {
	private Button submit;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pushboard_post);
		submit = (Button) findViewById(R.id.postSubmit);
		submit.setOnClickListener(sendMessage);
	}
	
	private OnClickListener sendMessage = new OnClickListener() {
		public void onClick(View v) {
			String title = ((EditText) findViewById(R.id.post_title) ).getText().toString();
			String message = ((EditText) findViewById(R.id.post_message) ).getText().toString();
			String date = ((EditText) findViewById(R.id.post_date) ).getText().toString();
			if (title.isEmpty() ) {
				Toast.makeText(postMessageActivity.this, "請輸入標題", Toast.LENGTH_SHORT).show();
			}
			else if (message.isEmpty()) {
				Toast.makeText(postMessageActivity.this, "請輸入內容", Toast.LENGTH_SHORT).show();
			}
			else if (date.isEmpty()) {
				Toast.makeText(postMessageActivity.this, "請輸入期限", Toast.LENGTH_SHORT).show();
			}
			else {
				DataBaseConnector.insertMessage(title,message,date);
				Intent intent = new Intent();
				intent.setClass(postMessageActivity.this, pushBoardActivity.class);
				startActivity(intent);
				postMessageActivity.this.finish();
			}
		}
	};
}
