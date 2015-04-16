package com.example.chart;

import java.util.ArrayList;

import com.example.group_projectmid.R;

import android.R.integer;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chart.TableAdapter.TableCell;
import com.example.chart.TableAdapter.TableRow;
import com.inin.dataType.rollCallFormat;

public class TableActivity extends Activity {
	
	ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table);
		
		int tableColumnSize = 3; // 每行3個單元
		
		lv = (ListView) this.findViewById(R.id.ListView01);  
        ArrayList<TableRow> table = new ArrayList<TableRow>();  
        TableCell[] titles = new TableCell[tableColumnSize]; // 3個標題
        String[] titleStrings = {"時間", "簽到或點名", "是否有到"};
        int width = this.getWindowManager().getDefaultDisplay().getWidth()/3;
        
        // 定義標題  
        for (int i = 0; i < titles.length; i++) {  
            titles[i] = new TableCell(titleStrings[i],   
                                    width + 8 * i,
                                    LayoutParams.FILL_PARENT,   
                                    TableCell.STRING);
        }
        table.add(new TableRow(titles));
        
        // 每行的數據  
        /*--------------假資料--------------*/
        rollCallFormat[] cellsData = new rollCallFormat[4];
        cellsData[0].time = "2015/04/01";
        cellsData[1].time = "2015/04/08";
        cellsData[2].time = "2015/04/15";
        cellsData[3].time = "2015/04/22";
        cellsData[0].type = 0;
        cellsData[1].type = 1;
        cellsData[2].type = 1;
        cellsData[3].type = 0;
        cellsData[0].isCome = true;
        cellsData[1].isCome = true;
        cellsData[2].isCome = false;
        cellsData[3].isCome = true;
        /*--------------假資料--------------*/
        TableCell[] cells = new TableCell[tableColumnSize]; // 每行3個單元  
        for (int i = 0; i < cellsData.length; i++) {
        	// 日期
        	cells[0] = new TableCell(cellsData[i].time,  
                    titles[0].width,   
                    LayoutParams.FILL_PARENT,   
                    TableCell.STRING);
        	// 簽到或點名
        	cells[1] = new TableCell(cellsData[i].type,  
                    titles[1].width,   
                    LayoutParams.FILL_PARENT,   
                    TableCell.STRING);
        	// 是否有到
        	cells[2] = new TableCell(cellsData[i].isCome,  
                    titles[2].width,   
                    LayoutParams.FILL_PARENT,   
                    TableCell.STRING);
            // 把行添加到表格  
        	table.add(new TableRow(cells));
        }
        
        // 新增表格
        TableAdapter tableAdapter = new TableAdapter(this, table);  
        lv.setAdapter(tableAdapter);
        
        /*cells[cells.length - 1] = new TableCell(R.drawable.ic_launcher,  
                                                titles[cells.length - 1].width,   
                                                LayoutParams.WRAP_CONTENT,  
                                                TableCell.IMAGE);*/
        /*
        lv.setOnItemClickListener(new ItemClickEvent());  
    }
    class ItemClickEvent implements AdapterView.OnItemClickListener {  
        @Override  
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
                long arg3) {  
            Toast.makeText(TableActivity.this, "選中第"+String.valueOf(arg2)+"行", 500).show();  
        }
        */  
    }
}
