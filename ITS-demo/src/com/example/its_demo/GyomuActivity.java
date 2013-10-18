package com.example.its_demo;

import jp.co.seino.sis.util.ActivityUtil;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GyomuActivity extends Activity implements OnClickListener {
	private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_gyomu);
		//タイトル表示
		ActivityUtil.initTitleBarGyomu(R.layout.activity_gyomu, R.string.title_activity_gyomu, this);

		btn1 = (Button)findViewById(R.id.gymbutton1); //ボタンの設定
		btn2 = (Button)findViewById(R.id.gymbutton2); //ボタンの設定
		btn3 = (Button)findViewById(R.id.gymbutton3); //ボタンの設定
		btn4 = (Button)findViewById(R.id.gymbutton4); //ボタンの設定
		btn5 = (Button)findViewById(R.id.gymbutton5); //ボタンの設定
		btn6 = (Button)findViewById(R.id.gymbutton6); //ボタンの設定
		btn7 = (Button)findViewById(R.id.gymbutton7); //ボタンの設定
		btn8 = (Button)findViewById(R.id.gymbutton8); //ボタンの設定
		btn9 = (Button)findViewById(R.id.gymbutton9); //ボタンの設定
		
		btn1.setOnClickListener(this); //ボタンのリスナー設定
		btn2.setOnClickListener(this); //ボタンのリスナー設定
		btn3.setOnClickListener(this); //ボタンのリスナー設定
		btn4.setOnClickListener(this); //ボタンのリスナー設定
		btn5.setOnClickListener(this); //ボタンのリスナー設定
		btn6.setOnClickListener(this); //ボタンのリスナー設定
		btn7.setOnClickListener(this); //ボタンのリスナー設定
		btn8.setOnClickListener(this); //ボタンのリスナー設定
		btn9.setOnClickListener(this); //ボタンのリスナー設定
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gyomu, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()) {
		case R.id.gymbutton1: //業務開始
			break;
		case R.id.gymbutton2: //現在地
			Intent intent1 = new Intent(this, GyomuMapActivity.class);
			startActivity(intent1);
			break;
		case R.id.gymbutton3: //積み込み完了
			break;
		case R.id.gymbutton4: //目的地
			Intent intent2 = new Intent(this, GyomuDestActivity.class);
			startActivity(intent2);
			break;
		case R.id.gymbutton5: //業務終了
			break;
		case R.id.gymbutton6: //履歴確認
			Intent intent3 = new Intent(this, GyomuHistoryActivity.class);
			startActivity(intent3);
			break;
		case R.id.gymbutton7: //配達完了
			break;
		case R.id.gymbutton8: //メッセージ受信
			Intent intent4 = new Intent(this, GyomuMessageActivity.class);
			startActivity(intent4);
			break;
		case R.id.gymbutton9: //設定
			Intent intent5 = new Intent(this, GyomuSettingActivity.class);
			startActivity(intent5);
			break;
		}
		
	}

}
