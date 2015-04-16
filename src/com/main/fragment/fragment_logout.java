package com.main.fragment;

import com.example.group_projectmid.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class fragment_logout extends Fragment {
	private TextView textView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_logout, container, false);  
		textView = (TextView) view.findViewById(R.id.TextView_logout);
		String username = getArguments().getString("username");	
		textView.setText("歡迎" + username + "登入");
		return view;
	}
	
	
	
}
