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
		//�^�C�g���\��
		ActivityUtil.initTitleBarGyomu(R.layout.activity_gyomu, R.string.title_activity_gyomu, this);

		btn1 = (Button)findViewById(R.id.gymbutton1); //�{�^���̐ݒ�
		btn2 = (Button)findViewById(R.id.gymbutton2); //�{�^���̐ݒ�
		btn3 = (Button)findViewById(R.id.gymbutton3); //�{�^���̐ݒ�
		btn4 = (Button)findViewById(R.id.gymbutton4); //�{�^���̐ݒ�
		btn5 = (Button)findViewById(R.id.gymbutton5); //�{�^���̐ݒ�
		btn6 = (Button)findViewById(R.id.gymbutton6); //�{�^���̐ݒ�
		btn7 = (Button)findViewById(R.id.gymbutton7); //�{�^���̐ݒ�
		btn8 = (Button)findViewById(R.id.gymbutton8); //�{�^���̐ݒ�
		btn9 = (Button)findViewById(R.id.gymbutton9); //�{�^���̐ݒ�
		
		btn1.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
		btn2.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
		btn3.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
		btn4.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
		btn5.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
		btn6.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
		btn7.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
		btn8.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
		btn9.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gyomu, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		switch(v.getId()) {
		case R.id.gymbutton1: //�Ɩ��J�n
			break;
		case R.id.gymbutton2: //���ݒn
			Intent intent1 = new Intent(this, GyomuMapActivity.class);
			startActivity(intent1);
			break;
		case R.id.gymbutton3: //�ςݍ��݊���
			break;
		case R.id.gymbutton4: //�ړI�n
			Intent intent2 = new Intent(this, GyomuDestActivity.class);
			startActivity(intent2);
			break;
		case R.id.gymbutton5: //�Ɩ��I��
			break;
		case R.id.gymbutton6: //�����m�F
			Intent intent3 = new Intent(this, GyomuHistoryActivity.class);
			startActivity(intent3);
			break;
		case R.id.gymbutton7: //�z�B����
			break;
		case R.id.gymbutton8: //���b�Z�[�W��M
			Intent intent4 = new Intent(this, GyomuMessageActivity.class);
			startActivity(intent4);
			break;
		case R.id.gymbutton9: //�ݒ�
			Intent intent5 = new Intent(this, GyomuSettingActivity.class);
			startActivity(intent5);
			break;
		}
		
	}

}
