package com.example.its_demo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DiagTopActivity extends Activity implements OnClickListener{
	private Button btn1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diag_top);
		btn1 = (Button)findViewById(R.id.dtpbutton1); //ボタンの設定

		btn1.setOnClickListener(this); //ボタンのリスナー設定
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.diag_top, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()) {
		case R.id.dtpbutton1:
			Intent intent1 = new Intent(this, DiagnosisActivity.class);
			startActivity(intent1);
			break;
		}
	}

}
