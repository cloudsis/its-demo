package com.example.its_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	private Button btn1, btn2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (Button)findViewById(R.id.maibutton1); //�{�^���̐ݒ�
		btn2 = (Button)findViewById(R.id.maibutton2); //�{�^���̐ݒ�

		btn1.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
		btn2.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
	
	@Override
	public void onClick(View v) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		System.out.println("onClick");
		switch(v.getId()) {
		case R.id.maibutton1:
			Intent intent1 = new Intent(this, GyomuActivity.class);
			startActivity(intent1);
			break;

		case R.id.maibutton2:
			Intent intent2 = new Intent(this, DiagTopActivity.class);
			startActivity(intent2);
			break;

		}
	}

}
