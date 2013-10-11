package com.example.its_demo;




import jp.co.its.ecu.cangather.lib.CarInfo;
import jp.co.its.ecu.cangather.lib.ConnectStatus;
import jp.co.its.ecu.cangather.lib.ECUListener;
import jp.co.its.ecu.cangather.lib.IItsEcuService;
import jp.co.its.ecu.cangather.lib.SensorInfo;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	private IItsEcuService mECUServer; 
	private Button btn1, btn2;
	private Handler mCalledHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setContentView(R.layout.activity_main);
		btn1 = (Button)findViewById(R.id.button1); //�{�^���̐ݒ�
		btn2 = (Button)findViewById(R.id.button2); //�{�^���̐ݒ�

		btn1.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
		btn2.setOnClickListener(this); //�{�^���̃��X�i�[�ݒ�
		
		//service�̃o�C���h
		Intent intent = new Intent(IItsEcuService.class.getName());
		bindService(intent, mItsEcuServiceConn, BIND_AUTO_CREATE);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
	
	/**
	 * ServiceConnection�̏�����
	 */
	private ServiceConnection mItsEcuServiceConn = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mECUServer = IItsEcuService.Stub.asInterface(service);
			System.out.println("connect");
			startConnection();
		}
		@Override
		public void onServiceDisconnected(ComponentName name) {
			unregister();
			System.out.println("connect");
			endConnection();
		}

		private void startConnection() {
			try {
				if (mECUServer != null) {
					int ret = mECUServer.communicationStart();
					System.out.println("communicationStart:"+ret);
				}
			} catch (RemoteException e) {
				throw new RuntimeException(e);
			}
		}
		private void endConnection() {
			try {
				if (mECUServer != null) {
					mECUServer.communicationEnd();
					System.out.println("communicationEnd");
				}
			} catch (RemoteException e) {
				throw new RuntimeException(e);
			}
			mECUServer = null;
		}
	};

	/**
	 * Listener�̐ݒ�
	 */
	private ECUListener listener = new ECUListener.Stub() {
		@Override
		public void OnCarInfoUpdated(CarInfo car, SensorInfo sensor)
				throws RemoteException {
			mCalledHandler.post(new Runnable(){
				public void run(){
					System.out.println("OnCarInfoUpdated");
					// ���X�i�[������L�q����
				}
			});

		}

		@Override
		public void OnConnectStatusChanged(ConnectStatus arg0)
				throws RemoteException {
			mCalledHandler.post(new Runnable(){
				public void run(){
					System.out.println("OnConnectStatusChanged");
				}
			});
		}

	};
	
	@Override
	public void onClick(View v) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		System.out.println("onClick");
		switch(v.getId()) {
		case R.id.button1:
			System.out.println("button1");
			register();
			break;

		case R.id.button2:
			System.out.println("button2");
			unregister();
			break;
		}	
	}
	
	/**
	 * ���X�i�[�̊J�n
	 */
	private void register() {
		try {
			if(mECUServer != null) {
				mECUServer.registerECUListener(listener);
				System.out.println("listener");
			} else {
				System.out.println("mECUServer is null!!!!!!!!!!!!!!!!!!!!");
			}
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ���X�i�[�̏I��
	 */
	private void unregister() {
		try {
			if (mECUServer != null) {
					mECUServer.unregisterECUListener(listener);
			}
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		} 
	}

}
