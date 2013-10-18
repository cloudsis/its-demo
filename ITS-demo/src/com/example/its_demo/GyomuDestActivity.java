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
		//�^�C�g���\��
		ActivityUtil.initTitleBarGyomu(R.layout.activity_gyomu_dest, R.string.title_activity_gyomu_dest, this);

		btn1 = (Button)findViewById(R.id.gdtbutton1); //�{�^���̐ݒ�
		lv = (ListView)findViewById(R.id.gdtlistView1); //���X�g�r���[�̐ݒ�
		
		btn1.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
		setAdapters(); //���X�g�\��������e���Z�b�g
		
		//���X�g���ڂ��I�����ꂽ�Ƃ�
		lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO �����������ꂽ���\�b�h�E�X�^�u
				ListView listView = (ListView) parent;
                item = (String) listView.getItemAtPosition(position);
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
                String url = "http://maps.google.com/maps?daddr=" + addressList.get((int) id);//�ܓx,�o�x
				Intent intent = new Intent(
						    Intent.ACTION_VIEW,
						    Uri.parse(url));
				startActivity(intent); //intent��google maps���N��
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
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		switch(v.getId()) {
		case R.id.gdtbutton1: //�Ɩ���ʂ�
			Intent intent1 = new Intent(this, GyomuActivity.class);
			startActivity(intent1);
			break;
		}
	}
	
	public void setAdapters(){ //���X�g�\��
		
		//�t�@�C���������݁i�e�X�g�f�[�^�p�j
//        try {
//            FileOutputStream fos = openFileOutput("destination.txt", MODE_PRIVATE);
//            String writeString1 = "35.70568044469603,139.75206971168518,�����h�[��,�W�ׂ��肢���܂�";
//            String writeString2 = "35.71019317972326,139.811185598373,�����X�J�C�c���[,�W�ׂ̈˗����󂯂܂����B�m�F�̏�A���n�Ɍ������悤���肢���܂��B";
//            
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
//            bw.write(writeString1.toString());
//            bw.newLine(); //���s�̑}��
//            bw.write(writeString2.toString());
//            bw.flush(); //�܂Ƃ߂ċL��
//            bw.close();
//        } catch (FileNotFoundException e) {
//        } catch (IOException e) {
//        }
		
		// �t�@�C���ǂݍ���
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
	        	dataList.add(spl[2]); //�n�_��
	        	addressList.add(spl[0] + "," + spl[1]); //�ܓx�A�o�x
	        	str = br.readLine();
	        }
        } catch (FileNotFoundException e){
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();        	
        }      catch (IOException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
        
		adapter = new ArrayAdapter<String>(
			      this, 
			      android.R.layout.simple_expandable_list_item_1, 
			      dataList);
		lv.setAdapter(adapter);
	}

}
