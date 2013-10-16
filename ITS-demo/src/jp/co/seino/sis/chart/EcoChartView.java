package jp.co.seino.sis.chart;

import org.afree.chart.AFreeChart;
import org.afree.chart.ChartFactory;
import org.afree.chart.StandardChartTheme;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.plot.CategoryPlot;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.plot.ValueMarker;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.afree.data.category.DefaultCategoryDataset;
import org.afree.data.xy.XYSeries;
import org.afree.data.xy.XYSeriesCollection;
import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;
import org.afree.util.PaintTypeUtilities;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

public class EcoChartView extends ChartView {

	public EcoChartView(Context context, AttributeSet attrs) {
		super(context,attrs);
	}

	private DefaultCategoryDataset dataset;

    /**
     * ƒOƒ‰ƒt¶¬
     */
    public void createEcoChart(double[] dataList, double avg) {
    	
    	ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());

    	dataset = new DefaultCategoryDataset();
        int i = 0;
        for (double value : dataList) {
        	String cols = Integer.toString(i+1);
        	dataset.addValue(value, "”R”ï", cols);
            i++;
        }
        AFreeChart chart = ChartFactory.createBarChart("”R”ïƒOƒ‰ƒt", "ŠÔ", "”R”ï(km/l)", dataset, PlotOrientation.VERTICAL, true, false, false);
        setChart(chart);
        
        CategoryPlot plot = chart.getCategoryPlot();
        
        // ŠO˜g‚Ìİ’è
        plot.setOutlineVisible(false);
        
        //Šî€ü‚ğˆø‚­
        ValueMarker marker = new ValueMarker(avg);
        marker.setPaintType(new SolidColor(Color.GREEN));
        marker.setStroke(2.0F);
        marker.setLabel("•½‹Ï”R”ï");
        plot.addRangeMarker(marker);
        
    }
    
    public void addData(double[] dataList) {
    	if (dataset == null) return;
        int i = dataset.getColumnCount();
        for (double value : dataList) {
        	String cols = Integer.toString(i+1);
        	dataset.addValue(value, "”R”ï", cols);
            i++;
        }
    }
}
