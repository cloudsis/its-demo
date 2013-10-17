package com.example.its_demo;

import java.util.ArrayList;
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
	private String[] dataList = {"201310171412 12.7 87", "201310150948 10.5 78"};
	private String item;
	private ArrayAdapter<String> adapter;
	
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
//			Intent intent1 = new Intent(this, DiagnosisActivity.class);
//			startActivity(intent1);
			break;
		}
	}

	public void setAdapters(){ //リスト表示
		setData();
		adapter = new ArrayAdapter<String>(
			      this, 
			      android.R.layout.simple_expandable_list_item_1, 
			      dataList);
		lv.setAdapter(adapter);
	}
	
	public void setData(){ //リスト表示するデータをセット
		//dataList.add = "";
	}
	
}
