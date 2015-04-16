package com.example.group_projectmid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class postMessageActivity extends Activity {
	private Button submit;
	private TextView title;
	private TextView message;
	private TextView date;
	private EditText etitle;
	private EditText emessage;
	private EditText edate;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pushboard_post);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		int display_width = displayMetrics.widthPixels;
		int display_height = displayMetrics.heightPixels;
		
		title = (TextView) findViewById(R.id.post_titleView);
		title.setTextSize(display_width/50);
		title.getLayoutParams().width = display_width / 8;
		title.getLayoutParams().height = display_height / 6;
		message = (TextView) findViewById(R.id.post_messageView);
		message.setTextSize(display_width/50);
		message.getLayoutParams().width = display_width / 8;
		message.getLayoutParams().height = display_height / 6;
		date = (TextView) findViewById(R.id.post_dateView);
		date.setTextSize(display_width/50);
		date.getLayoutParams().width = display_width / 8;
		date.getLayoutParams().height = display_height / 6;
		
		etitle = (EditText) findViewById(R.id.post_title);
		etitle.setTextSize(display_width/30);
		etitle.getLayoutParams().width = display_width / 13;
		etitle.getLayoutParams().height = display_height / 17;
		emessage = (EditText) findViewById(R.id.post_message);
		emessage.setTextSize(display_width/30);
		emessage.getLayoutParams().width = display_width / 13;
		emessage.getLayoutParams().height = display_height / 17;
		edate = (EditText) findViewById(R.id.post_date);
		edate.setTextSize(display_width/30);
		edate.getLayoutParams().width = display_width / 13;
		edate.getLayoutParams().height = display_height / 17;
		
		
		
		submit = (Button) findViewById(R.id.postSubmit);
		submit.setOnClickListener(sendMessage);
		
		submit.setTextSize(display_width/40);
		submit.getLayoutParams().width = display_width / 3;
		submit.getLayoutParams().height = display_height / 15;
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
