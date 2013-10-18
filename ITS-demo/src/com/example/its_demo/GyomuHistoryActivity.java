package com.example.its_demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jp.co.seino.sis.util.ActivityUtil;
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

public class GyomuHistoryActivity extends Activity implements OnClickListener {
	private Button btn1;
	private ListView lv;
	private ArrayList<String> dateList = new ArrayList<String>();
	private ArrayList<String> fullList = new ArrayList<String>();
	private String item;
	private ArrayAdapter<String> adapter;
	private SimpleDateFormat DATE_FORMAT1 = new SimpleDateFormat("yyyyMMddkkmm"); //24時間表示はhhではなくkkを使う
	private SimpleDateFormat DATE_FORMAT2 = new SimpleDateFormat("yyyy'年'MM'月'dd'日'kk'時'mm'分'");

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_gyomu_history);
		//タイトル表示
		ActivityUtil.initTitleBarGyomu(R.layout.activity_gyomu_history, R.string.title_activity_gyomu_history, this);

		btn1 = (Button)findViewById(R.id.ghsbutton1); //ボタンの設定
		lv = (ListView)findViewById(R.id.ghslistView1); //リストビューの設定

		btn1.setOnClickListener(this); //ボタンのリスナー設定
		setAdapters();
		
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
		
		//リスト項目がクリックされたとき 地図上の履歴表示 今はまだ設定せず
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO 自動生成されたメソッド・スタブ
				ListView listView = (ListView) parent;
                item = (String) listView.getItemAtPosition(position);
                System.out.println(item);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gyomu_history, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()) {
		case R.id.ghsbutton1: //業務画面へ
			Intent intent1 = new Intent(this, GyomuActivity.class);
			startActivity(intent1);
			break;
		}
	}
	
	public void setAdapters(){ //リスト表示
				//ファイル書き込み（テストデータ用）
//	      try {
//	          FileOutputStream fos = openFileOutput("history.txt", MODE_PRIVATE);
//	          String writeString1 = "201310171531,12.5,87";
//	          String writeString2 = "201310180908,10.7,79";
//	          String writeString3 = "201310181650,12.8,88";
//
//	          BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
//	          bw.write(writeString1.toString());
//	          bw.newLine(); //改行の挿入
//	          bw.write(writeString2.toString());
//	          bw.newLine(); //改行の挿入
//	          bw.write(writeString3.toString());          
//	          bw.flush(); //まとめて記入
//	          bw.close();
//	      } catch (FileNotFoundException e) {
//	      } catch (IOException e) {
//	      }
			
			// ファイル読み込み
	      try {
	          FileInputStream fis;
				fis = openFileInput("history.txt");
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader br = new BufferedReader(isr);
				
	          String str;
	          String[] spl;
	          Date dat;
		        str = br.readLine();
		        while(str != null){
		        	spl = str.split(",");
		        	dat = DATE_FORMAT1.parse(spl[0]);
		        	dateList.add(DATE_FORMAT2.format(dat)); //日付
		        	fullList.add(DATE_FORMAT2.format(dat) + "\r\n燃費:" + spl[1] + "  点数:" + spl[2]); //リスト表示用	        		

		        	str = br.readLine();
		        }
	      } catch (FileNotFoundException e){
				// TODO 自動生成された catch ブロック
				e.printStackTrace();        	
	      }      catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	      
			adapter = new ArrayAdapter<String>(
				      this,
				      android.R.layout.simple_expandable_list_item_1,
				      dateList);
			lv.setAdapter(adapter);
	}

}
