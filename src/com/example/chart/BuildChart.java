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
		dataset = buildDatset(lineTitle, x, y); // �x�s�y�Э�
		renderer = buildRenderer(color, style, fill);
		setChartSettings(renderer, title, xTitle, yTitle, xMin, xMax, yMin,
				yMax, axesColor);// �w�q��u��
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
		renderer.setChartTitle(title); // ��u�ϦW��
		renderer.setChartTitleTextSize(50); // ��u�ϦW�٦r�Τj�p
		renderer.setLabelsTextSize(30);
		renderer.setAxisTitleTextSize(25);
		renderer.setLegendTextSize(25);
		renderer.setXTitle(xTitle); // X�b�W��
		renderer.setYTitle(yTitle); // Y�b�W��
		renderer.setXAxisMin(xMin); // X�b��̤ܳp��
		renderer.setXAxisMax(xMax); // X�b��̤ܳj��
		renderer.setXLabelsColor(Color.BLACK); // X�b�u�C��
		renderer.setYAxisMin(yMin); // Y�b��̤ܳp��
		renderer.setYAxisMax(yMax); // Y�b��̤ܳj��
		renderer.setAxesColor(axesColor); // �]�w���жb�C��
		renderer.setYLabelsColor(0, Color.BLACK); // Y�b�u�C��
		renderer.setLabelsColor(Color.BLACK); // �]�w�����C��
		renderer.setMarginsColor(Color.WHITE); // �]�w�I���C��
		renderer.setShowGrid(true); // �]�w��u
	}

	// �w�q��u�Ϫ��榡
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

	// ��ƳB�z
	private XYMultipleSeriesDataset buildDatset(String lineTitle,
			List<double[]> xValues, List<double[]> yValues) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

		// XYseries��H,�Ω󴣨�ø�s���I���X�����
		XYSeries series = new XYSeries(lineTitle); // �̾ڨC���u���W�ٷs�W
		double[] xV = xValues.get(0); // �����i���u�����
		double[] yV = yValues.get(0);
		int seriesLength = xV.length; // ���X���I

		for (int k = 0; k < seriesLength; k++) // �C���u�̦��X���I
		{
			series.add(xV[k], yV[k]);
		}
		dataset.addSeries(series);

		return dataset;
	}
	
	
}
