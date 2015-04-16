package com.example.group_projectmid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.inin.sqlite.MyDBHelper;
import com.inin.sqlite.postDataDAO;
import com.inin.UIunit.postItemRow;
import com.inin.dataType.postDataFormat;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;

public class pushBoardActivity extends ListActivity {
	private final int userType = DataBaseConnector.getUserData().type;
	private ListView user_list;
	private TableLayout.LayoutParams row_layout;
	private TableRow.LayoutParams view_layout;
	private long localLastId = 0;
	private boolean shouldRefresh = true, isRefreshing = false;
	private boolean shouldLoadData = true, isLoadingData = false;
	ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private SimpleAdapter listAdapter;
	private View head, foot, teacher;

	// private MydataAdapter mydataAdapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.pushboard_student); //use will error!

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
				.build());
		loadMessages();
		initVeiw();
		Intent intent = new Intent(pushBoardActivity.this, checkService.class);
		startService(intent);

	}

	private void initVeiw() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		user_list = this.getListView();
		// user_list.setStretchAllColumns(true);
		row_layout = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		view_layout = new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		
		head = LayoutInflater.from(this).inflate(R.layout.pushboard_refresh,
				null);
		foot = LayoutInflater.from(this).inflate(R.layout.pushboard_load, null);
		Log.e("Height", String.valueOf(metrics.heightPixels));
		foot.setMinimumHeight(metrics.heightPixels);
		// 將head.xml轉為view
		// TextView headtext = (TextView) head.findViewById(R.id.load);
		user_list.addHeaderView(head, null, false);
		user_list.addFooterView(foot, null, false);
		foot.setVisibility(View.GONE);
		
		if (DataBaseConnector.getUserData().type == 1) {
			Log.e("teacher","true");
			teacher = LayoutInflater.from(this).inflate(
					R.layout.pushboard_teacher, null);
			Button postButton = (Button) teacher.findViewById(R.id.teacherPost);
			postButton.setOnClickListener(postMessage);
			user_list.addHeaderView(teacher, null, false);
		}
		
		listAdapter = new SimpleAdapter(this, list, R.layout.pushboard_item,
				new String[] { "title", "message", "date", "time", "teacher" },
				new int[] { R.id.postTitle, R.id.postMessage, R.id.postDate,
						R.id.postTime, R.id.postTeacher });
		setListAdapter(listAdapter);
		user_list.setOnScrollListener(listScrollListener);
		user_list.setSelection(1);
	}

	private void loadMessages() {
		postDataDAO localData = new postDataDAO(getApplicationContext());

		/*
		 * if (localData.getCount() == 0) { localData.sample(); }
		 */
		List<postDataFormat> messages = localData.getAll();
		localLastId = messages.size();
		for (int i = 0; i < messages.size(); i++) {
			HashMap<String, String> item = new HashMap<String, String>();
			item.put("title", messages.get(i).title);
			item.put("message", messages.get(i).message);
			item.put("date", "期限：" + messages.get(i).date);
			item.put("time", "發佈時間：" + messages.get(i).time);
			item.put("teacher", messages.get(i).teacher);
			list.add(item);
		}
		localData.close();
	}

	private OnScrollListener listScrollListener = new OnScrollListener() {
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			shouldLoadData = false;
			shouldRefresh = false;
			Log.e("firstVisibleItem", String.valueOf(firstVisibleItem));
			Log.e("visibleItemCount", String.valueOf(visibleItemCount));
			Log.e("totalItemCount", String.valueOf(totalItemCount));
			if (firstVisibleItem == 0) {// 拉到頂時
				shouldRefresh = true;
			} else if (firstVisibleItem + visibleItemCount == totalItemCount) {
				if (totalItemCount < 13) {
					user_list.setSelection(1);
				} else if (visibleItemCount < 13) {
					user_list.setSelection(totalItemCount - 13);
				}
			}
		}

		public void onScrollStateChanged(AbsListView view, int scrollState) {
			if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
				// 當ListView拉到頂或底時
				if (shouldRefresh) {// 當ListView拉到頂
					if (!isRefreshing) {
						Refresh();// 沒在更新資料時
					}
					user_list.setSelection(1);// 不管更不更新，都移到第一項
				}
				if (shouldLoadData && !isLoadingData) {
					// LoadData();
				}
				// 當ListView拉到底,且沒在載入資料時
			}
		}

	};

	private void Refresh() {
		Log.e("refresh", "yes");
		postDataFormat messages[] = DataBaseConnector.getPosts();
		if (messages.length > localLastId) {
			postDataDAO localData = new postDataDAO(getApplicationContext());
			for (int i = (int) (localLastId); i < messages.length; i++) {
				HashMap<String, String> item = new HashMap<String, String>();
				item.put("title", messages[i].title);
				item.put("message", messages[i].message);
				item.put("date", "期限：" + messages[i].date);
				item.put("time", "發佈時間：" + messages[i].time);
				item.put("teacher", messages[i].teacher);
				list.add(0, item);
				postDataFormat save = new postDataFormat(messages[i].id,
						messages[i].title, messages[i].message,
						messages[i].date, messages[i].time, messages[i].teacher);
				localData.insert(save);
			}
			localData.close();
			localLastId = messages.length;
			// have new data
			user_list.setAdapter(listAdapter);
		}
	}
	
	private OnClickListener postMessage = new OnClickListener() {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.e("CLICK","CCCC");
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
