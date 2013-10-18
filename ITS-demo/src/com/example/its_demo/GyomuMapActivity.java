package com.example.its_demo;

import jp.co.seino.sis.util.ActivityUtil;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GyomuMapActivity extends Activity implements OnClickListener {
	private Button btn1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_gyomu_map);
		//タイトル表示
		ActivityUtil.initTitleBarGyomu(R.layout.activity_gyomu_map, R.string.title_activity_gyomu_map, this);

		btn1 = (Button)findViewById(R.id.gmpbutton1); //ボタンの設定

		btn1.setOnClickListener(this); //ボタンのリスナー設定
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gyomu_map, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()) {
		case R.id.gmpbutton1: //業務画面へ
			Intent intent1 = new Intent(this, GyomuActivity.class);
			startActivity(intent1);
			break;
		}
	}

}
