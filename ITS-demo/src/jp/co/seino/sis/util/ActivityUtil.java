package jp.co.seino.sis.util;

import com.example.its_demo.DiagTopActivity;
import com.example.its_demo.GyomuActivity;
import com.example.its_demo.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class ActivityUtil {
	

	public static void initTitleBarGyomu(int layout, int title, Activity activity) {
		initTitleBar(layout,title,R.string.title_gyomu,activity);
	}
	public static void initTitleBarEco(int layout, int title, Activity activity) {
		initTitleBar(layout,title,R.string.title_eco,activity);
	}
	
	public static void initTitleBar(int layout, int title, int menu, Activity activity) {
		//�^�C�g���Ƀ��C�A�E�g���w�肷��
		//requestWindowFeature(Window.FEATURE_LEFT_ICON);
		activity.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		activity.setContentView(layout);
	    //�J�X�^�}�C�Y�������C�A�E�g�t�@�C�����w��
		activity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	    //�^�C�g���̕ύX
	    TextView titleText = (TextView)activity.findViewById(R.id.title_text);
	    titleText.setText(title);
	    //�{�^���̐ݒ�
		final Activity act = activity;
	    Button btn1 = (Button)activity.findViewById(R.id.titleGyomuBtn); //�{�^���̐ݒ�
		if (menu != R.string.title_gyomu) {
			btn1.setOnClickListener(new OnClickListener(){ 
				public void onClick(View v){
					Intent intent = new Intent(act, GyomuActivity.class);
					act.startActivity(intent);
				} 
			}); //�{�^���̃��X�i�[�ݒ�
		} else {
			btn1.setEnabled(false);//�{�^������
		}
	    Button btn2 = (Button)activity.findViewById(R.id.titleEcoBtn); //�{�^���̐ݒ�
		if (menu != R.string.title_eco) {
			btn2.setOnClickListener(new OnClickListener(){ 
				public void onClick(View v){
					Intent intent = new Intent(act, DiagTopActivity.class);
					act.startActivity(intent);
				} 
			}); //�{�^���̃��X�i�[�ݒ�
		} else {
			btn2.setEnabled(false);//�{�^������
		}
	}


}
