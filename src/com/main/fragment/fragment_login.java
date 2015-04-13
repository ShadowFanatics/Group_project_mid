package com.main.fragment;

import com.example.group_projectmid.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class fragment_login extends Fragment{
	
	
	private EditText mEdt_username;
	private EditText mEdt_password;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.fragment_login, container, false);
		return view;
		
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//getActivity().findViewById(R.id.frameLay).setVisibility(View.GONE);
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//getActivity().findViewById(R.id.frameLay).setVisibility(View.GONE);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//getActivity().findViewById(R.id.frameLay).setVisibility(View.GONE);
	}


	
}
