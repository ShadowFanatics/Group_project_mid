package com.example.group_projectmid;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.inin.dataType.postDataFormat;
import com.inin.sqlite.postDataDAO;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;

public class checkService extends Service {
	private Handler handler = new Handler();
	private postDataDAO localData;
	int localLastId;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onStart(Intent intent, int startId) {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
				.build());
		localData = new postDataDAO(getApplicationContext());
		localLastId = localData.getCount();
		handler.postDelayed(showTime, 1000);
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		handler.removeCallbacks(showTime);
		super.onDestroy();
	}

	private Runnable showTime = new Runnable() {
		public void run() {
			// log目前時間
<<<<<<< HEAD
			//Log.e("time:", new Date().toString());
=======
			Log.e("time:", new Date().toString());
>>>>>>> origin/panda
			handler.postDelayed(this, 1000);

			postDataFormat messages[] = DataBaseConnector.getPosts();
			if (messages.length > localLastId) {
				for (int i = (int) (localLastId); i < messages.length; i++) {
					postDataFormat save = new postDataFormat(messages[i].id,
							messages[i].title, messages[i].message,
							messages[i].date, messages[i].time,
							messages[i].teacher);
					localData.insert(save);
					NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
					Notification msg = new Notification.Builder(
							getApplicationContext())
							.setSmallIcon(R.drawable.ic_launcher)
							.setContentTitle(messages[i].title).setContentText(messages[i].message)
							.build();
					manager.notify(0, msg);
				}
				localLastId = messages.length;
			}
		}
	};

}
