package com.example.chart;

import java.util.ArrayList;
import com.example.group_projectmid.R;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chart.TableAdapter.TableCell;
import com.example.chart.TableAdapter.TableRow;

public class TableActivity extends Activity {
	
	ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table);
		
		lv = (ListView) this.findViewById(R.id.ListView01);  
        ArrayList<TableRow> table = new ArrayList<TableRow>();  
        TableCell[] titles = new TableCell[5]; // 每行5個單元  
        int width = this.getWindowManager().getDefaultDisplay().getWidth()/titles.length;  
        // 定义标题  
        for (int i = 0; i < titles.length; i++) {  
            titles[i] = new TableCell("標題" + String.valueOf(i),   
                                    width + 8 * i,  
                                    LayoutParams.FILL_PARENT,   
                                    TableCell.STRING);
	}
        table.add(new TableRow(titles));  
        // 每行的數據  
        TableCell[] cells = new TableCell[5]; // 每行5個單元  
        for (int i = 0; i < cells.length - 1; i++) {  
            cells[i] = new TableCell("No." + String.valueOf(i),  
                                    titles[i].width,   
                                    LayoutParams.FILL_PARENT,   
                                    TableCell.STRING);  
        }  
        cells[cells.length - 1] = new TableCell(R.drawable.ic_launcher,  
                                                titles[cells.length - 1].width,   
                                                LayoutParams.WRAP_CONTENT,  
                                                TableCell.IMAGE);  
        // 把表格的行添加到表格  
        for (int i = 0; i < 12; i++)  
            table.add(new TableRow(cells));  
        TableAdapter tableAdapter = new TableAdapter(this, table);  
        lv.setAdapter(tableAdapter);
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
