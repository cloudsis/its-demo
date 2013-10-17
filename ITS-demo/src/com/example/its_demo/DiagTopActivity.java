package com.example.its_demo;

import jp.co.seino.sis.util.ActivityUtil;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DiagTopActivity extends Activity implements OnClickListener{
	private Button btn1, btn2, btn3, btn4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//�^�C�g���\��
		ActivityUtil.initTitleBarEco(R.layout.activity_diag_top, R.string.title_activity_diag_top, this);
		//setContentView(R.layout.activity_diag_top);
		btn1 = (Button)findViewById(R.id.dtpbutton1); //�{�^���̐ݒ�
		btn2 = (Button)findViewById(R.id.dtpbutton2); //�{�^���̐ݒ�
		btn3 = (Button)findViewById(R.id.dtpbutton3); //�{�^���̐ݒ�
		btn4 = (Button)findViewById(R.id.dtpbutton4); //�{�^���̐ݒ�

		btn1.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
		btn2.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
		btn3.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
		btn4.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.diag_top, menu);
//		return true;
//	}

	@Override //�{�^���������ꂽ�Ƃ��̓���
	public void onClick(View v) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		switch(v.getId()) {
		case R.id.dtpbutton1:
			Intent intent1 = new Intent(this, DiagnosisActivity.class);
			startActivity(intent1);
			break;
		case R.id.dtpbutton2:
			Intent intent2 = new Intent(this, HistoryActivity.class);
			startActivity(intent2);
			break;
		case R.id.dtpbutton3:
			Intent intent3 = new Intent(this, RankActivity.class);
			startActivity(intent3);
			break;
		case R.id.dtpbutton4:
			Intent intent4 = new Intent(this, PanelActivity.class);
			startActivity(intent4);
			break;
		}
	}

}
