package com.group.mid;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group_projectmid.R;

public class BluetoothGridItemAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	private List<GridItem> gridItemList;
	private String[] coordinate;
	private int[] image_identifier;
	
	private int display_width;
	private int display_height;
	private int column;
	private class ViewHolder
	{
		public TextView coordinate;
		public TextView name;
		public ImageView image;
	}
	
	public BluetoothGridItemAdapter(String[] names,int[] genderID,String[] studentID,String receivedSeat[],
			int width, int height, int column, Context context){
		super();
		gridItemList = new ArrayList<GridItem>();
		inflater = LayoutInflater.from(context);
		display_width = width;
		display_height = height;
		this.column = column;
		
		iniCoordinate(names);
		generate_identifier(genderID);
		
		for(int i = 0;i < names.length; i++)
		{
			GridItem item;
			//把現在刷學生證的人換成框起來的圖片(有框跟沒框的index差7)
			if(i>=0 && i<2){
				if(receivedSeat[i].equals("y"))
				{
					item = new GridItem(names[i], genderID[i], image_identifier[i]+7, studentID[i]);
				}
				else
				{
					item = new GridItem(names[i], genderID[i], image_identifier[i], studentID[i]);
				}
			}
			else{
				item = new GridItem(names[i], genderID[i], image_identifier[i], studentID[i]);
			}
			gridItemList.add(item);
		}
		
	}
	
	public void iniCoordinate(String[] names){//產生座標
		coordinate = new String[names.length];
		int j =1 ,k = 1;//j為column k為row
		for(int i=0 ; i< names.length ; i++){
			coordinate[i] = Integer.toString(j) + "-" + Integer.toString(k);
			if(j < column){
				j++;
			}
			else{
				j = 1;
				k++;
			}
		}
	}
	
	public void generate_identifier (int[] genderID) {//根據男女性別 給不同的男女圖片 圖片在griditem裏
		image_identifier = new int[genderID.length];
		for(int i=0; i < genderID.length; i++){
			
			if(genderID[i] == 0){
				//女生圖片編號4~7
				int j = (int)(Math.random()*3+4);
				image_identifier[i] = j;
			}
			else {
				//男生圖片編號0~3
				int j = (int)(Math.random()*4);
				image_identifier[i] = j;
			}
		}
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		if(gridItemList != null)
			return gridItemList.size();
		else
			return 0;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return gridItemList.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.grid_item, null);
			viewHolder = new ViewHolder();
			viewHolder.coordinate = (TextView) convertView.findViewById(R.id.textView_rowcolumn);
			viewHolder.name = (TextView) convertView.findViewById(R.id.textView_name);
			viewHolder.image = (ImageView) convertView.findViewById(R.id.imageView1);
			convertView.setTag(viewHolder);
		}
		else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.coordinate.setText(coordinate[position]);
		viewHolder.name.setText(gridItemList.get(position).getName());
		viewHolder.image.setImageResource(gridItemList.get(position).getDrawable());
		viewHolder.image.getLayoutParams().width = display_width / column;
		viewHolder.image.getLayoutParams().height = 200;
//		viewHolder.image.getLayoutParams().height = 5*display_width / column /4;
		return convertView;
	}
}
