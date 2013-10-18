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
	private SimpleDateFormat DATE_FORMAT1 = new SimpleDateFormat("yyyyMMddkkmm"); //24���ԕ\����hh�ł͂Ȃ�kk���g��
	private SimpleDateFormat DATE_FORMAT2 = new SimpleDateFormat("yyyy'�N'MM'��'dd'��'kk'��'mm'��'");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_gyomu_message);
		//�^�C�g���\��
		ActivityUtil.initTitleBarGyomu(R.layout.activity_gyomu_message, R.string.title_activity_gyomu_message, this);
		
		btn1 = (Button)findViewById(R.id.gmsbutton1); //�{�^���̐ݒ�
		lv = (ListView)findViewById(R.id.gmslistView1); //���X�g�r���[�̐ݒ�

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
//				String[] str = messageList.get((int) id).split(",");
				openAlert(dateList.get((int) id), messageList.get((int) id)); //�_�C�A���O�\��
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
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		switch(v.getId()) {
		case R.id.gmsbutton1: //�Ɩ���ʂ�
			Intent intent1 = new Intent(this, GyomuActivity.class);
			startActivity(intent1);
			break;
		}
	}
	
	public void setAdapters(){ //���X�g�\��
		//�t�@�C���������݁i�e�X�g�f�[�^�p�j
      try {
          FileOutputStream fos = openFileOutput("message.txt", MODE_PRIVATE);
          String writeString1 = "201310171526,�����h�[��,�W�ׂ��肢���܂�";
          String writeString2 = "201310181157,�����X�J�C�c���[,�W�ׂ̈˗����󂯂܂����B�m�F�̏�A���n�Ɍ������悤���肢���܂��B";
          String writeString3 = "201310181314,,�䕗���߂Â��Ă��܂��B�^�]�ɋC��t���Ă�������";

          BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
          bw.write(writeString1.toString());
          bw.newLine(); //���s�̑}��
          bw.write(writeString2.toString());
          bw.newLine(); //���s�̑}��
          bw.write(writeString3.toString());          
          bw.flush(); //�܂Ƃ߂ċL��
          bw.close();
      } catch (FileNotFoundException e) {
      } catch (IOException e) {
      }
		
		// �t�@�C���ǂݍ���
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
	        	dateList.add(DATE_FORMAT2.format(dat)); //���t
	        	if(spl[1].equals("")){ //�ړI�n�w�肪�Ȃ��Ƃ�
	        		messageList.add(spl[2]); //���b�Z�[�W
	        		fullList.add(DATE_FORMAT2.format(dat) + "  " + spl[2]); //���X�g�\���p
	        	}else{ //�ړI�n�w�肪����Ƃ�
		        	messageList.add(spl[1] + "  " + spl[2]); //�ړI�n���A���b�Z�[�W
		        	fullList.add(DATE_FORMAT2.format(dat) + "  " + spl[1] + " " + spl[2]); //���X�g�\���p	        		
	        	}
	        	str = br.readLine();
	        }
      } catch (FileNotFoundException e){
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();        	
      }      catch (IOException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		} catch (ParseException e) {
		// TODO �����������ꂽ catch �u���b�N
		e.printStackTrace();
	}
      
		adapter = new ArrayAdapter<String>(
			      this,
			      android.R.layout.simple_expandable_list_item_1,
			      fullList);
		lv.setAdapter(adapter);
	}
	
	public void openAlert(String date, String mes){
		// �A���[�g�_�C�A���O�̐ݒ�
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
        // �A���[�g�_�C�A���O�̃^�C�g����ݒ�
        adb.setTitle("���b�Z�[�W");
        // �A���[�g�_�C�A���O�̃��b�Z�[�W��ݒ�
        adb.setMessage(date + "\r\n" + mes);
        // OK�{�^�����N���b�N���ꂽ��
        adb.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        // �L�����Z���{�^�����N���b�N���ꂽ��
        adb.setNegativeButton("�L�����Z��",
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
