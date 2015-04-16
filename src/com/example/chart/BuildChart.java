package com.example.chart;

import java.util.List;
import com.example.group_projectmid.R;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.graphics.Color;

public class BuildChart {
	XYMultipleSeriesDataset dataset;
	XYMultipleSeriesRenderer renderer;

	public BuildChart(String title, int color, PointStyle style, boolean fill,
			String lineTitle, String xTitle, String yTitle, double xMin,
			double xMax, List<double[]> x, double yMin, double yMax,
			List<double[]> y, int axesColor) {
		// TODO Auto-generated constructor stub
		dataset = buildDatset(lineTitle, x, y); // 儲存座標值
		renderer = buildRenderer(color, style, fill);
		setChartSettings(renderer, title, xTitle, yTitle, xMin, xMax, yMin,
				yMax, axesColor);// 定義折線圖
	}
	
	public XYMultipleSeriesDataset getDataset() {
		return dataset;
	}

	public XYMultipleSeriesRenderer getRenderer() {
		return renderer;
	}
	
	// �w�q��u�ϦW��
	private void setChartSettings(XYMultipleSeriesRenderer renderer,
			String title, String xTitle, String yTitle, double xMin,
			double xMax, double yMin, double yMax, int axesColor) {
		renderer.setChartTitle(title); // 折線圖名稱
		renderer.setChartTitleTextSize(50); // 折線圖名稱字形大小
		renderer.setLabelsTextSize(30);
		renderer.setAxisTitleTextSize(25);
		renderer.setLegendTextSize(25);
		renderer.setXTitle(xTitle); // X軸名稱
		renderer.setYTitle(yTitle); // Y軸名稱
		renderer.setXAxisMin(xMin); // X軸顯示最小值
		renderer.setXAxisMax(xMax); // X軸顯示最大值
		renderer.setXLabelsColor(Color.BLACK); // X軸線顏色
		renderer.setYAxisMin(yMin); // Y軸顯示最小值
		renderer.setYAxisMax(yMax); // Y軸顯示最大值
		renderer.setAxesColor(axesColor); // 設定坐標軸顏色
		renderer.setYLabelsColor(0, Color.BLACK); // Y軸線顏色
		renderer.setLabelsColor(Color.BLACK); // 設定標籤顏色
		renderer.setMarginsColor(Color.WHITE); // 設定背景顏色
		renderer.setShowGrid(true); // 設定格線
	}

	// 定義折線圖的格式
	private XYMultipleSeriesRenderer buildRenderer(int color, PointStyle style,
			boolean fill) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		XYSeriesRenderer r = new XYSeriesRenderer();
		r.setColor(color);
		r.setPointStyle(style);
		r.setFillPoints(fill);
		r.setLineWidth(5);
		renderer.addSeriesRenderer(r); // �N�y���ܦ��u�[�J�Ϥ����
		return renderer;
	}

	// 資料處理
	private XYMultipleSeriesDataset buildDatset(String lineTitle,
			List<double[]> xValues, List<double[]> yValues) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

		// XYseries對象,用於提供繪製的點集合的資料
		XYSeries series = new XYSeries(lineTitle); // 依據每條線的名稱新增
		double[] xV = xValues.get(0); // 獲取第i條線的資料
		double[] yV = yValues.get(0);
		int seriesLength = xV.length; // 有幾個點

		for (int k = 0; k < seriesLength; k++) // 每條線裡有幾個點
		{
			series.add(xV[k], yV[k]);
		}
		dataset.addSeries(series);

		return dataset;
	}
	
	
}
