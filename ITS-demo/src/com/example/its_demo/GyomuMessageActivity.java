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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class GyomuMessageActivity extends Activity implements OnClickListener {
	private Button btn1;
	private ListView lv;
	private ArrayList<String> dateList = new ArrayList<String>();
	private ArrayList<String> messageList = new ArrayList<String>();
	private ArrayList<String> fullList = new ArrayList<String>();
	private String item;
	private ArrayAdapter<String> adapter;
	private SimpleDateFormat DATE_FORMAT1 = new SimpleDateFormat("yyyyMMddkkmm"); //24時間表示はhhではなくkkを使う
	private SimpleDateFormat DATE_FORMAT2 = new SimpleDateFormat("yyyy'年'MM'月'dd'日'kk'時'mm'分'");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_gyomu_message);
		//タイトル表示
		ActivityUtil.initTitleBarGyomu(R.layout.activity_gyomu_message, R.string.title_activity_gyomu_message, this);
		
		btn1 = (Button)findViewById(R.id.gmsbutton1); //ボタンの設定
		lv = (ListView)findViewById(R.id.gmslistView1); //リストビューの設定

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
//				String[] str = messageList.get((int) id).split(",");
				openAlert(dateList.get((int) id), messageList.get((int) id)); //ダイアログ表示
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gyomu_message, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()) {
		case R.id.gmsbutton1: //業務画面へ
			Intent intent1 = new Intent(this, GyomuActivity.class);
			startActivity(intent1);
			break;
		}
	}
	
	public void setAdapters(){ //リスト表示
		//ファイル書き込み（テストデータ用）
      try {
          FileOutputStream fos = openFileOutput("message.txt", MODE_PRIVATE);
          String writeString1 = "201310171526,東京ドーム,集荷お願いします";
          String writeString2 = "201310181157,東京スカイツリー,集荷の依頼を受けました。確認の上、現地に向かうようお願いします。";
          String writeString3 = "201310181314,,台風が近づいています。運転に気を付けてください";

          BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
          bw.write(writeString1.toString());
          bw.newLine(); //改行の挿入
          bw.write(writeString2.toString());
          bw.newLine(); //改行の挿入
          bw.write(writeString3.toString());          
          bw.flush(); //まとめて記入
          bw.close();
      } catch (FileNotFoundException e) {
      } catch (IOException e) {
      }
		
		// ファイル読み込み
      try {
          FileInputStream fis;
			fis = openFileInput("message.txt");
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
	        	if(spl[1].equals("")){ //目的地指定がないとき
	        		messageList.add(spl[2]); //メッセージ
	        		fullList.add(DATE_FORMAT2.format(dat) + "  " + spl[2]); //リスト表示用
	        	}else{ //目的地指定があるとき
		        	messageList.add(spl[1] + "  " + spl[2]); //目的地名、メッセージ
		        	fullList.add(DATE_FORMAT2.format(dat) + "  " + spl[1] + " " + spl[2]); //リスト表示用	        		
	        	}
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
	
	public void openAlert(String date, String mes){
		// アラートダイアログの設定
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
        // アラートダイアログのタイトルを設定
        adb.setTitle("メッセージ");
        // アラートダイアログのメッセージを設定
        adb.setMessage(date + "\r\n" + mes);
        // OKボタンがクリックされた時
        adb.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        // キャンセルボタンがクリックされた時
        adb.setNegativeButton("キャンセル",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
        });
        adb.setCancelable(true);
        AlertDialog aDialog = adb.create();
        aDialog.show();
	}
}
