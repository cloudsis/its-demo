package jp.co.seino.sis.chart;

import org.afree.chart.AFreeChart;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.xy.XYLineAndShapeRenderer;
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
}
