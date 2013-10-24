package com.example.its_demo;

import java.util.Timer;
import java.util.TimerTask;

import jp.co.seino.sis.chart.EcoChartView;
import jp.co.seino.sis.its.CANInfo;
import jp.co.seino.sis.its.ITSService;
import jp.co.seino.sis.its.ITSServiceListener;
import jp.co.seino.sis.util.ActivityUtil;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class DiagnosisActivity extends Activity implements ITSServiceListener {

	//ITS-ECU�v���p�e�B
	private ITSService itsService;
	private CANInfo canInfo;

	//�O���t�\���v���p�e�B
	private Handler handler = new Handler();
	private EcoChartView ecoChartView;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//�^�C�g���\��
		ActivityUtil.initTitleBarEco(R.layout.activity_diagnosis, R.string.title_activity_diagnosis, this);
		
		//ITS-ECU�̃o�C���h
		//itsService = new ITSService();
		//itsService.bindService(this);
		
        // ������
		ecoChartView = (EcoChartView)findViewById(R.id.diaChartView);
        // �O���t�ɕ\������f�[�^�𐶐�
        //int[] dataList = {10, 5, 20, 8};
        //ecoChartView.createChart(dataList);
		double[] dataList = {12.2,13.4,20.0,10.5,25.5};
		double avg = 18.6;
	    ecoChartView.createEcoChart(dataList,avg);
	    
	    // �^�C�}�[�𐶐�
	    Timer timer = new Timer(false);
	    // �X�P�W���[����ݒ�
	    timer.schedule(new TimerTask() {
	    	@Override
	    	public void run() {
	    		handler.post(new Runnable() {
	    			@Override
	    			public void run() {
	    				// �`�揈�����w��
	    				drawChart();
	    			}
	    		});
	    	}
	    }, 10000, 10000); // ����N���̒x��(5sec)�Ǝ���(1sec)�w��
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.diagnosis, menu);
		return true;
	}
	

//	/**
//	 * ��ʕ\���̍X�V
//	 */
//	private void updateText() {
//		try {
//			String updateText = toCanInfo();
//			//text1.setText(updateText);
//			logSd(updateText);
//		} catch (RemoteException e) {
//			throw new RuntimeException(e);
//		}
//	}
//	

//	/**
//	 * ���O�o�͂̍X�V
//	 */
//	private void writeLog()  {
//		try {
//			System.out.println(toCanInfo());
//		} catch (RemoteException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//
//	// SD�J�[�h�ւ̃��O�o��
//	private void logSd(final String log) {
//		// SD�J�[�h�̃}�E���g�`�F�b�N�A�}�E���g����Ă��Ȃ��ꍇ�͉������Ȃ�
//		if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
//			return;
//		}
//
//		// ���O�t�@�C���𐶐��B�t�@�C������yyyyMMddhhmm.txt
//		File filePath = new File(Environment.getExternalStorageDirectory().getPath() + "/CanTest");
//		if (filePath.exists() == false){
//			filePath.mkdir();
//		}
//		Date date = new Date();
//		File file = new File(filePath + "/" + FILE_NAME_FORMAT.format(date) + ".txt");
//
//		// ���O�t�@�C���ɏ�������
//		try {
//			FileWriter writer = new FileWriter(file, true);
//			//            writer.write(LOG_PREFIX_FORMAT.format(date));
//			writer.write(log);
//			writer.close();
//		} catch (FileNotFoundException e) {
//			throw new RuntimeException(e);
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//	}

	/**
	 * �O���t�\��
	 */
	private void drawChart() {
		System.out.println("drawChart!!!!");
		double[]  dataList = {12.2,13.4};
		ecoChartView.addData(dataList);
		//�ĕ`��
		ecoChartView.refreshDrawableState();
	}
	
	/**
	 * ITS-ECU
	 * @param info
	 */
	@Override
	public void OnCarInfoUpdate(CANInfo info) {
		canInfo = info;
		// TODO �����ɍX�V���̃��W�b�N������
	}

	@Override
	public void OnConnectStatusChanged(CANInfo info) {
		canInfo = info;
	}

}
