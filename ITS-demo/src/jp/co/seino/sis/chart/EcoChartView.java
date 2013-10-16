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
     * Ot¶¬
     */
    public void createChart(int[] dataList) {
        // X²Ìè`
        NumberAxis domainAxis = new NumberAxis("Ô");
        // Y²Ìè`
        NumberAxis rangeAxis = new NumberAxis("Rïkm/l");
        // ÜêüÌè`
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        // f[^ÌZbg
        XYSeries xy = new XYSeries("f[^");
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
     * Ot¶¬
     */
    public void createEcoChart(double[] dataList, double avg) {
    	ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());

    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int i = 0;
        for (double value : dataList) {
        	String cols = Integer.toString(i+1);
        	dataset.addValue(value, "Rï", cols);
        	dataset.addValue(avg, "½ÏRï", cols);
            i++;
        }
        AFreeChart chart = ChartFactory.createLineChart("RïOt", "Ô", "Rï(km/l)", dataset, PlotOrientation.VERTICAL, true, false, false);
        setChart(chart);
    }
}
