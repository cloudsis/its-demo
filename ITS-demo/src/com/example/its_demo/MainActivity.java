package com.example.its_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	private Button btn1, btn2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//タイトルにレイアウトを指定する
		//requestWindowFeature(Window.FEATURE_LEFT_ICON);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	    setContentView(R.layout.activity_main);
	    //カスタマイズしたレイアウトファイルを指定
	    getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	    
		//btn1 = (Button)findViewById(R.id.maibutton1); //ボタンの設定
		//btn2 = (Button)findViewById(R.id.maibutton2); //ボタンの設定
		btn1 = (Button)findViewById(R.id.titleGyomuBtn); //ボタンの設定
		btn2 = (Button)findViewById(R.id.titleEcoBtn); //ボタンの設定

		btn1.setOnClickListener(this); //ボタンのリスナー設定
		btn2.setOnClickListener(this); //ボタンのリスナー設定
		
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		
//		return true;
//	}
	
	@Override
	public void onClick(View v) {
		System.out.println("onClick");
		if (v.getId() == btn1.getId()) {
			Intent intent1 = new Intent(this, GyomuActivity.class);
			startActivity(intent1);
		} else if (v.getId() == btn2.getId()) {
			Intent intent2 = new Intent(this, DiagTopActivity.class);
			startActivity(intent2);
		}
	}

}
