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
		//�^�C�g���\��
		ActivityUtil.initTitleBarEco(R.layout.activity_rank, R.string.title_activity_rank, this);
		//setContentView(R.layout.activity_rank);
		btn1 = (Button)findViewById(R.id.rnkbutton1); //�{�^���̐ݒ�
		lv = (ListView)findViewById(R.id.rnklistView1); //���X�g�r���[�̐ݒ�
		
		btn1.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
		setAdapters();
		
		//���X�g���ڂ��I�����ꂽ�Ƃ�
		lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO �����������ꂽ���\�b�h�E�X�^�u
				ListView listView = (ListView) parent;
                item = (String) listView.getItemAtPosition(position);
    			Intent intent1 = new Intent(RankActivity.this, DiagnosisActivity.class);
    			intent1.putExtra("rank", item);
    			startActivity(intent1);
                System.out.println(item);
			}

			@Override //���X�g���ڂ������I������Ă��Ȃ��Ƃ�
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO �����������ꂽ���\�b�h�E�X�^�u
				
			}
		});
		
		//���X�g���ڂ��N���b�N���ꂽ�Ƃ�
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO �����������ꂽ���\�b�h�E�X�^�u
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
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		switch(v.getId()) {
		case R.id.rnkbutton1: //�f�f���ʉ�ʂ�
//			Intent intent1 = new Intent(this, DiagnosisActivity.class);
//			startActivity(intent1);
			break;
		}
	}

	public void setAdapters(){ //���X�g�\��
		setData();
		adapter = new ArrayAdapter<String>(
			      this, 
			      android.R.layout.simple_expandable_list_item_1, 
			      dataList);
		lv.setAdapter(adapter);
	}
	
	public void setData(){ //���X�g�\������f�[�^���Z�b�g
		//dataList.add = "";
	}
	
}
