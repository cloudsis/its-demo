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
import java.util.List;

import jp.co.seino.sis.util.ActivityUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class RankActivity extends Activity implements OnClickListener {
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
		ActivityUtil.initTitleBarEco(R.layout.activity_rank, R.string.title_activity_rank, this);
		//setContentView(R.layout.activity_rank);
		btn1 = (Button)findViewById(R.id.rnkbutton1); //ボタンの設定
		lv = (ListView)findViewById(R.id.rnklistView1); //リストビューの設定
		
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
    			Intent intent1 = new Intent(RankActivity.this, DiagnosisActivity.class);
    			intent1.putExtra("rank", item);
    			startActivity(intent1);
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
    			Intent intent1 = new Intent(RankActivity.this, DiagnosisActivity.class);
    			intent1.putExtra("rank", item);
    			startActivity(intent1);
                System.out.println(item);
			}
		});
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rank, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()) {
		case R.id.rnkbutton1: //診断結果画面へ
			Intent intent1 = new Intent(this, DiagTopActivity.class);
			startActivity(intent1);
			break;
		}
	}

	public void setAdapters(){ //リスト表示
		//ファイル書き込み（テストデータ用）
//	      try {
//	          FileOutputStream fos = openFileOutput("ranking.txt", MODE_PRIVATE);
//	          String writeString1 = "201310181650,12.8,88,アレックス,seino";
//	          String writeString2 = "201310171531,12.5,87,アレックス,seino";
//	          String writeString3 = "201310180908,10.7,79,アレックス,SIS";
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

	      try { //ファイル読み込み
	          FileInputStream fis;
	          fis = openFileInput("ranking.txt");
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
		        	fullList.add("点数:" + spl[2] + "  燃費:" + spl[1] + "  車両:" + spl[3] + "  ユーザー:" + spl[4] + "\r\n  " + DATE_FORMAT2.format(dat)); //リスト表示用
//		        	fullList.add("点数:" + spl[2] + "  燃費:" + spl[1] + "  車両:" + spl[3] + "\r\n" + DATE_FORMAT2.format(dat)); //リスト表示用

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
