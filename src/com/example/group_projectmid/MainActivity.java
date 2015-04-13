package com.example.group_projectmid;

import com.widget.radialmenu.semicircularmenu.SemiCircularRadialMenu;
import com.widget.radialmenu.semicircularmenu.SemiCircularRadialMenuItem;
import com.widget.radialmenu.semicircularmenu.SemiCircularRadialMenuItem.OnSemiCircularRadialMenuPressed;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private SemiCircularRadialMenu mMenu;
	private SemiCircularRadialMenuItem mContact, mDislike, mInfo, mRefresh, mSearch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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
		
		
		
		/*mContact.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {
			@Override
			public void onMenuItemPressed() {
				Toast.makeText(MainActivity.this, mContact.getText(), Toast.LENGTH_SHORT).show();
			}
		});
		
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

}
