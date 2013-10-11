package com.example.its_demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class EfficiencyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_efficiency);
		//更新テスト
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.efficiency, menu);
		return true;
	}

}
