package com.example.its_demo;

import java.util.Timer;
import java.util.TimerTask;

import jp.co.seino.sis.chart.EcoChartView;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;

public class DiagnosisActivity extends Activity {


	Handler handler = new Handler();
	EcoChartView ecoChartView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diagnosis);
		//更新テスト
		//あいうえお
        // 初期化
		ecoChartView = (EcoChartView)findViewById(R.id.diaChartView);
        // グラフに表示するデータを生成
        //int[] dataList = {10, 5, 20, 8};
        //ecoChartView.createChart(dataList);
		double[] dataList = {12.2,13.4,20.0,10.5,25.5};
		double avg = 18.6;
	    ecoChartView.createEcoChart(dataList,avg);
	    
	    // タイマーを生成
	    Timer timer = new Timer(false);
	    // スケジュールを設定
	    timer.schedule(new TimerTask() {
	    	@Override
	    	public void run() {
	    		handler.post(new Runnable() {
	    			@Override
	    			public void run() {
	    				// 描画処理を指示
	    				drawChart();
	    			}
	    		});
	    	}
	    }, 10000, 10000); // 初回起動の遅延(5sec)と周期(1sec)指定
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.diagnosis, menu);
		return true;
	}
	
	private void drawChart() {
		System.out.println("drawChart!!!!");
		double[]  dataList = {12.2,13.4};
		ecoChartView.addData(dataList);
	}

}
