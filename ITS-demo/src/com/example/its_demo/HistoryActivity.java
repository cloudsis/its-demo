package com.example.its_demo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class HistoryActivity extends Activity implements OnClickListener {
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
		//タイトル表示
		ActivityUtil.initTitleBarEco(R.layout.activity_history, R.string.title_activity_history, this);
		//setContentView(R.layout.activity_history);
		btn1 = (Button)findViewById(R.id.hstbutton1); //ボタンの設定
		lv = (ListView)findViewById(R.id.hstlistView1); //リストビューの設定
		
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
		
		//リスト項目がクリックされたとき
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO 自動生成されたメソッド・スタブ
				ListView listView = (ListView) parent;
                item = (String) listView.getItemAtPosition(position);
    			Intent intent1 = new Intent(HistoryActivity.this, DiagnosisActivity.class);
    			intent1.putExtra("hist", dateList.get((int) id)); //日付を診断画面に渡す
    			startActivity(intent1);
                System.out.println(item);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()) {
		case R.id.hstbutton1: //診断結果画面へ
//			Intent intent1 = new Intent(this, DiagnosisActivity.class);
//			startActivity(intent1);
			break;
		}
	}
	
	public void setAdapters(){ //リスト表示
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
				      fullList);
			lv.setAdapter(adapter);
	}
	
	public void setData(){ //リスト表示するデータをセット
		//dataList.add = "";
	}

}
