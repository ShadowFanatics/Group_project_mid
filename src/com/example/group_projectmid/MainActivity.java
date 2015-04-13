package com.example.group_projectmid;

import com.main.fragment.fragment_login;
import com.main.fragment.fragment_logout;
import com.widget.radialmenu.semicircularmenu.SemiCircularRadialMenu;
import com.widget.radialmenu.semicircularmenu.SemiCircularRadialMenuItem;
import com.widget.radialmenu.semicircularmenu.SemiCircularRadialMenuItem.OnSemiCircularRadialMenuPressed;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
	
	private SemiCircularRadialMenu mMenu;
	private SemiCircularRadialMenuItem mContact, mDislike, mInfo, mRefresh, mSearch;
	private Button login_button, logout_button;
	private fragment_login frLogin;
	private fragment_logout frLogout;
	private String username,password;
	//private FragmentManager fragmentManager = getFragmentManager();
	private FragmentTransaction fragmentTransaction;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		

		
		
		
		iniFragmentView();
		iniSemiCircularRadialMenu();
		
	}
	
	public void iniFragmentView(){
		login_button = (Button) findViewById(R.id.button_login);
		logout_button = (Button) findViewById(R.id.button_logout);
		logout_button.setEnabled(false);
		login_button.setOnClickListener(login_listener);
		logout_button.setOnClickListener(logout_listener);
		
		frLogin = new fragment_login();
		
		fragmentTransaction = getFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.frameLay, frLogin);
		fragmentTransaction.commit();
	}
	
	public void iniSemiCircularRadialMenu() {
		mContact = new SemiCircularRadialMenuItem("Contact", getResources().getDrawable(R.drawable.ic_action_camera), "簽到");
		mDislike = new SemiCircularRadialMenuItem("Dislike", getResources().getDrawable(R.drawable.ic_action_dislike), "點名");
		mInfo = new SemiCircularRadialMenuItem("info", getResources().getDrawable(R.drawable.ic_action_info), "Info");
		mRefresh = new SemiCircularRadialMenuItem("Refresh", getResources().getDrawable(R.drawable.ic_action_refresh), "出席率");
		mSearch = new SemiCircularRadialMenuItem("Search", getResources().getDrawable(R.drawable.ic_action_search), "推播");
		
		mMenu = (SemiCircularRadialMenu) findViewById(R.id.radial_menu);
		mMenu.addMenuItem(mInfo.getMenuID(), mInfo);//最左
		mMenu.addMenuItem(mSearch.getMenuID(), mSearch);//最右
		mMenu.addMenuItem(mContact.getMenuID(), mContact);//中間
		mMenu.addMenuItem(mRefresh.getMenuID(), mRefresh);//右2
		mMenu.addMenuItem(mDislike.getMenuID(), mDislike);//左2
		
		mContact.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {
			@Override
			public void onMenuItemPressed() {
				Toast.makeText(MainActivity.this, mContact.getText(), Toast.LENGTH_SHORT).show();
			}
		});
		/*
		mDislike.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {
			@Override
			public void onMenuItemPressed() {
				Toast.makeText(MainActivity.this, mDislike.getText(), Toast.LENGTH_SHORT).show();
			}
		});
		
		mInfo.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {
			@Override
			public void onMenuItemPressed() {
				Toast.makeText(MainActivity.this, mInfo.getText(), Toast.LENGTH_SHORT).show();
			}
		});
		
		mRefresh.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {
			@Override
			public void onMenuItemPressed() {
				Toast.makeText(MainActivity.this, mRefresh.getText(), Toast.LENGTH_SHORT).show();
			}
		});
		
		mSearch.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {
			@Override
			public void onMenuItemPressed() {
				Toast.makeText(MainActivity.this, mSearch.getText(), Toast.LENGTH_SHORT).show();
				mMenu.dismissMenu();
			}
		});*/
	}


	private Button.OnClickListener login_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			frLogout = new fragment_logout();
			EditText login_username = (EditText) findViewById(R.id.username_login);
			EditText login_password = (EditText) findViewById(R.id.password_login);
			username = login_username.getText().toString();
			password = login_password.getText().toString();
			//檢查資料庫 看帳密是否吻合且判斷是學生或老師
			//
			//
			//
			//符合 元件SemiCircularRadialMenu is unclocked
			mMenu.set_unLocked();
			logout_button.setEnabled(true);
			login_button.setEnabled(false);
			
			
			fragmentTransaction = getFragmentManager().beginTransaction();
			Bundle args = new Bundle();
			args.putString("username", username);
			frLogout.setArguments(args);
			
			fragmentTransaction.replace(R.id.frameLay, frLogout);
			fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
		}
	};
	
	private Button.OnClickListener logout_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			mMenu.set_Locked();
			logout_button.setEnabled(false);
			login_button.setEnabled(true);
			
			fragmentTransaction = getFragmentManager().beginTransaction();
			fragmentTransaction.remove(frLogout);
			fragmentTransaction.add(R.id.frameLay, frLogin);
			fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
			
		}
	};
	
	

}
