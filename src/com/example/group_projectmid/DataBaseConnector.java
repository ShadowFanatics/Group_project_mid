package com.example.group_projectmid;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.group.mid.StudentData;
import com.inin.dataType.postDataFormat;
import com.inin.dataType.userData;

import android.util.Log;
import android.view.Gravity;
import android.widget.TableRow;
import android.widget.TextView;

public class DataBaseConnector {
	private static boolean DeBugMode = true;
	private static userData user = new userData();

	public static int logIn(String account, String password) {
		if (DeBugMode) {
			user.ID = "100502531";
			user.name = "陳映亦";
			user.type = 1;
			// -1 is error
			// 0 is student
			// 1 is teacher
			return user.type;
		} else {
			String result = "";
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(
						"http://10.0.2.2/android_sql/logIn.php");
				ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("account", account));
				params.add(new BasicNameValuePair("password", password));
				httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
				HttpResponse httpResponse = httpClient.execute(httpPost);
				// view_account.setText(httpResponse.getStatusLine().toString());
				HttpEntity httpEntity = httpResponse.getEntity();
				InputStream inputStream = httpEntity.getContent();

				BufferedReader bufReader = new BufferedReader(
						new InputStreamReader(inputStream, "utf-8"), 8);
				StringBuilder builder = new StringBuilder();
				String line = null;
				while ((line = bufReader.readLine()) != null) {
					builder.append(line + "\n");
				}
				inputStream.close();
				result = builder.toString();
			} catch (Exception e) {
				Log.e("log_tag_DB", e.toString());
			}
			try {
				JSONObject jsonData = new JSONObject(result);
				user.name = jsonData.getString("name");
				user.ID = jsonData.getString("school_ID");
				user.type = jsonData.getInt("type");
				return jsonData.getInt("type");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.e("JSONObject", e.toString());
				return -1;
			}
		}
	}

	public static postDataFormat[] getPosts() {
		postDataFormat value[] = null;
<<<<<<< HEAD
		if ( DeBugMode ) {
			value = new postDataFormat[5];
			for ( int i = 0; i < 5; i++ ) {
				value[i] = new postDataFormat();
	            value[i].id = i;
	            value[i].title = "標題";
	            value[i].message = "交作業喔!";
	            value[i].teacher = "陳國棟";
	            value[i].time = "2015-04-12 00:00:00";
	            value[i].date = "2015-04-14 00:00:00";
			}
			return value;
		}
=======
>>>>>>> origin/panda
		String result = "";
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(
					"http://10.0.2.2/android_sql/getPost.php");
			ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("classId", String.valueOf(1)));
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);

			HttpEntity httpEntity = httpResponse.getEntity();
			InputStream inputStream = httpEntity.getContent();

			BufferedReader bufReader = new BufferedReader(
					new InputStreamReader(inputStream, "utf-8"), 8);
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = bufReader.readLine()) != null) {
				builder.append(line + "\n");
			}
			inputStream.close();
			result = builder.toString();
		} catch (Exception e) {
			Log.e("log_tag_DB", e.toString());
		}
		Log.e("log_tag_DB", result);
		try {
			JSONArray jsonArray = new JSONArray(result);
			value = new postDataFormat[jsonArray.length()];
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonData = jsonArray.getJSONObject(i);
                value[i] = new postDataFormat();
                value[i].id = jsonData.getInt("id");
                value[i].title = jsonData.getString("title");
                value[i].message = jsonData.getString("message");
                value[i].teacher = "teacher";
                value[i].time = jsonData.getString("submit_time");
                value[i].date = jsonData.getString("date");
            }
		} catch (JSONException e) {
			Log.e("JSONObject", e.toString());
		}

		return value;
	}

	public static StudentData[] getSeats() {
		StudentData value[] = null;
		String result = "";
		if (DeBugMode) {
			value = new StudentData[8];
            for(int i = 0; i < 8; i++) {
                value[i] = new StudentData(i, "inin" + String.valueOf(i), "10050252" + String.valueOf(i), 1);
            }
			return value;
		} 
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(
					"http://10.0.2.2/android_sql/getSeat.php");
			ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("classId", String.valueOf(1)));
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);

			HttpEntity httpEntity = httpResponse.getEntity();
			InputStream inputStream = httpEntity.getContent();

			BufferedReader bufReader = new BufferedReader(
					new InputStreamReader(inputStream, "utf-8"), 8);
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = bufReader.readLine()) != null) {
				builder.append(line + "\n");
			}
			inputStream.close();
			result = builder.toString();
		} catch (Exception e) {
			Log.e("log_tag_DB", e.toString());
		}
		
		try {
			JSONArray jsonArray = new JSONArray(result);
			value = new StudentData[jsonArray.length()];
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonData = jsonArray.getJSONObject(i);
                value[i] = new StudentData(jsonData.getInt("seat"), jsonData.getString("name"), jsonData.getString("school_id"), jsonData.getInt("gender"));
            }
		} catch (JSONException e) {
			Log.e("JSONObject", e.toString());
		}
<<<<<<< HEAD
=======

>>>>>>> origin/panda
		return value;
	}
	
	public static userData getUserData() {
<<<<<<< HEAD
		/*if (DeBugMode) {
			user.ID = "100502521";
			user.name = "陳映亦";
			user.type = 1;
		}*/
=======
		if (DeBugMode) {
			user.ID = "100502521";
			user.name = "陳映亦";
			user.type = 0;
		}
>>>>>>> origin/panda
		return user;
	}

}
