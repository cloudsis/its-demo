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
		//タイトルにレイアウトを指定する
		//requestWindowFeature(Window.FEATURE_LEFT_ICON);
		activity.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		activity.setContentView(layout);
	    //カスタマイズしたレイアウトファイルを指定
		activity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	    //タイトルの変更
	    TextView titleText = (TextView)activity.findViewById(R.id.title_text);
	    titleText.setText(title);
	    //ボタンの設定
		final Activity act = activity;
	    Button btn1 = (Button)activity.findViewById(R.id.titleGyomuBtn); //ボタンの設定
		if (menu != R.string.title_gyomu) {
			btn1.setOnClickListener(new OnClickListener(){ 
				public void onClick(View v){
					Intent intent = new Intent(act, GyomuActivity.class);
					act.startActivity(intent);
				} 
			}); //ボタンのリスナー設定
		} else {
			btn1.setEnabled(false);//ボタン無効
		}
	    Button btn2 = (Button)activity.findViewById(R.id.titleEcoBtn); //ボタンの設定
		if (menu != R.string.title_eco) {
			btn2.setOnClickListener(new OnClickListener(){ 
				public void onClick(View v){
					Intent intent = new Intent(act, DiagTopActivity.class);
					act.startActivity(intent);
				} 
			}); //ボタンのリスナー設定
		} else {
			btn2.setEnabled(false);//ボタン無効
		}
	}


}
