package jp.co.seino.sis.chart;

import org.afree.chart.AFreeChart;
import org.afree.chart.ChartFactory;
import org.afree.chart.StandardChartTheme;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.afree.data.category.DefaultCategoryDataset;
import org.afree.data.xy.XYSeries;
import org.afree.data.xy.XYSeriesCollection;

import android.content.Context;
import android.util.AttributeSet;

public class EcoChartView extends ChartView {

	public EcoChartView(Context context, AttributeSet attrs) {
		super(context,attrs);
	}

    /**
     * グラフ生成
     */
    public void createChart(int[] dataList) {
        // X軸の定義
        NumberAxis domainAxis = new NumberAxis("時間");
        // Y軸の定義
        NumberAxis rangeAxis = new NumberAxis("燃費km/l");
        // 折れ線の定義
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        // データのセット
        XYSeries xy = new XYSeries("データ");
        int i = 0;
        for (int value : dataList) {
            xy.add(i + 1, value);
            i++;
        }
        XYSeriesCollection dataset = new XYSeriesCollection(xy);
        XYPlot plot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);

        AFreeChart aFreeChart = new AFreeChart(plot);
        setChart(aFreeChart);
    }

    /**
     * グラフ生成
     */
    public void createEcoChart(double[] dataList, double avg) {
    	ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());

    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int i = 0;
        for (double value : dataList) {
        	String cols = Integer.toString(i+1);
        	dataset.addValue(value, "燃費", cols);
        	dataset.addValue(avg, "平均燃費", cols);
            i++;
        }
        AFreeChart chart = ChartFactory.createLineChart("燃費グラフ", "時間", "燃費(km/l)", dataset, PlotOrientation.VERTICAL, true, false, false);
        setChart(chart);
    }
}
