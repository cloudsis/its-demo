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

	//ITS-ECUプロパティ
	private ITSService itsService;
	private CANInfo canInfo;

	//グラフ表示プロパティ
	private Handler handler = new Handler();
	private EcoChartView ecoChartView;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//タイトル表示
		ActivityUtil.initTitleBarEco(R.layout.activity_diagnosis, R.string.title_activity_diagnosis, this);
		
		//ITS-ECUのバインド
		//itsService = new ITSService();
		//itsService.bindService(this);
		
        // 初期化
		ecoChartView = (EcoChartView)findViewById(R.id.diaChartView);
        // グラフに表示するデータを生成
        //int[] dataList = {10, 5, 20, 8};
        //ecoChartView.createChart(dataList);
		double[] dataList = {12.2,13.4,20.0,10.5,25.5};
		double avg = 18.6;
	    ecoChartView.createEcoChart(dataList,avg);
	    
	    // タイマーを生成
	    Timer timer = new Timer(false);
	    // スケジュールを設定
	    timer.schedule(new TimerTask() {
	    	@Override
	    	public void run() {
	    		handler.post(new Runnable() {
	    			@Override
	    			public void run() {
	    				// 描画処理を指示
	    				drawChart();
	    			}
	    		});
	    	}
	    }, 10000, 10000); // 初回起動の遅延(5sec)と周期(1sec)指定
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.diagnosis, menu);
		return true;
	}
	

//	/**
//	 * 画面表示の更新
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
//	 * ログ出力の更新
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
//	// SDカードへのログ出力
//	private void logSd(final String log) {
//		// SDカードのマウントチェック、マウントされていない場合は何もしない
//		if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
//			return;
//		}
//
//		// ログファイルを生成。ファイル名はyyyyMMddhhmm.txt
//		File filePath = new File(Environment.getExternalStorageDirectory().getPath() + "/CanTest");
//		if (filePath.exists() == false){
//			filePath.mkdir();
//		}
//		Date date = new Date();
//		File file = new File(filePath + "/" + FILE_NAME_FORMAT.format(date) + ".txt");
//
//		// ログファイルに書き込み
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
	 * グラフ表示
	 */
	private void drawChart() {
		System.out.println("drawChart!!!!");
		double[]  dataList = {12.2,13.4};
		ecoChartView.addData(dataList);
		//再描画
		ecoChartView.refreshDrawableState();
	}
	
	/**
	 * ITS-ECU
	 * @param info
	 */
	@Override
	public void OnCarInfoUpdate(CANInfo info) {
		canInfo = info;
		// TODO ここに更新時のロジックを書く
	}

	@Override
	public void OnConnectStatusChanged(CANInfo info) {
		canInfo = info;
	}

}
