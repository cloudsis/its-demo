package com.example.its_demo;

import jp.co.seino.sis.chart.EcoChartView;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class EfficiencyActivity extends Activity {
	
	EcoChartView ecoChartView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_efficiency);
		//�X�V�e�X�g
		//����������
        // ������
		ecoChartView = (EcoChartView)findViewById(R.id.eco_chart);
        // �O���t�ɕ\������f�[�^�𐶐�
        //int[] dataList = {10, 5, 20, 8};
        //ecoChartView.createChart(dataList);
		double[] dataList = {12.2,13.4,20.0,10.5,25.5};
		double avg = 18.6;
	    ecoChartView.createEcoChart(dataList,avg);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.efficiency, menu);
		return true;
	}

}
