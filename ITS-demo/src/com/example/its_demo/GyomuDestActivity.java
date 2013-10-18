package com.example.its_demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import jp.co.seino.sis.util.ActivityUtil;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class GyomuDestActivity extends Activity implements OnClickListener {
	private Button btn1;
	private ListView lv;
	private ArrayList<String> dataList = new ArrayList<String>();
	private ArrayList<String> addressList = new ArrayList<String>();
	private String item;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_gyomu_dest);
		//タイトル表示
		ActivityUtil.initTitleBarGyomu(R.layout.activity_gyomu_dest, R.string.title_activity_gyomu_dest, this);

		btn1 = (Button)findViewById(R.id.gdtbutton1); //ボタンの設定
		lv = (ListView)findViewById(R.id.gdtlistView1); //リストビューの設定
		
		btn1.setOnClickListener(this); //ボタンのリスナー設定
		setAdapters(); //リスト表示する内容をセット
		
		//リスト項目が選択されたとき
		lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自動生成されたメソッド・スタブ
				ListView listView = (ListView) parent;
                item = (String) listView.getItemAtPosition(position);
                System.out.println(item);
			}

			@Override //リスト項目が何も選択されていないとき
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO 自動生成されたメソッド・スタブ
			}
		});
		
		//リスト項目がクリックされたとき
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO 自動生成されたメソッド・スタブ
                String url = "http://maps.google.com/maps?daddr=" + addressList.get((int) id);//緯度,経度
				Intent intent = new Intent(
						    Intent.ACTION_VIEW,
						    Uri.parse(url));
				startActivity(intent); //intentでgoogle mapsを起動
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gyomu_dest, menu);
		return true;
	}
	
	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()) {
		case R.id.gdtbutton1: //業務画面へ
			Intent intent1 = new Intent(this, GyomuActivity.class);
			startActivity(intent1);
			break;
		}
	}
	
	public void setAdapters(){ //リスト表示
		
		//ファイル書き込み（テストデータ用）
//        try {
//            FileOutputStream fos = openFileOutput("destination.txt", MODE_PRIVATE);
//            String writeString1 = "35.70568044469603,139.75206971168518,東京ドーム,集荷お願いします";
//            String writeString2 = "35.71019317972326,139.811185598373,東京スカイツリー,集荷の依頼を受けました。確認の上、現地に向かうようお願いします。";
//            
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
//            bw.write(writeString1.toString());
//            bw.newLine(); //改行の挿入
//            bw.write(writeString2.toString());
//            bw.flush(); //まとめて記入
//            bw.close();
//        } catch (FileNotFoundException e) {
//        } catch (IOException e) {
//        }
		
		// ファイル読み込み
        try {
            FileInputStream fis;
			fis = openFileInput("destination.txt");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			
            String str;
            String[] spl;			
	        str = br.readLine();
	        while(str != null){
	        	spl = str.split(",",0);
	        	dataList.add(spl[2]); //地点名
	        	addressList.add(spl[0] + "," + spl[1]); //緯度、経度
	        	str = br.readLine();
	        }
        } catch (FileNotFoundException e){
			// TODO 自動生成された catch ブロック
			e.printStackTrace();        	
        }      catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        
		adapter = new ArrayAdapter<String>(
			      this, 
			      android.R.layout.simple_expandable_list_item_1, 
			      dataList);
		lv.setAdapter(adapter);
	}

}
