package com.example.chart;

import java.util.ArrayList;
import java.util.List;

import com.example.group_projectmid.R;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class ChartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);
		
		String title = "TeacherChart", xTitle = "Date", yTitle = "Attendtion";
		String lineTitle = "出席率"; // 定義折線的名稱
		int color = Color.GREEN, axesColor = Color.BLACK;
		PointStyle style = PointStyle.DIAMOND; // 折線點的形狀
		boolean fill = true;
		
		List<double[]> x = new ArrayList<double[]>(); // 點的x坐標
		List<double[]> y = new ArrayList<double[]>(); // 點的y坐標
		// 數值X,Y坐標值輸入
		x.add(new double[] { 1, 2, 3, 4, 5, 6 });
		y.add(new double[] { 3, 14, 8, 13, 16, 18 });
		int xMin = 0, xMax = 8, yMin = 0, yMax = 20;
		
		BuildChart buildChart = new BuildChart(title, color, style,
				fill, lineTitle, xTitle, yTitle, xMin, xMax, x, yMin,
				yMax, y, axesColor);
		drawChart(buildChart);
	}

	private void drawChart(BuildChart buildChart) {
		View chart = ChartFactory.getLineChartView(this,
				buildChart.getDataset(), buildChart.getRenderer());
		setContentView(chart);
	}
	/*
	public boolean onKeyDown(int keyCode, KeyEvent event) {//������^��
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {   
            ChartActivity.this.finish();;//����^��A�h����h�X�T�{
            return true;   
        }   
        return super.onKeyDown(keyCode, event);
	}
	*/
}
