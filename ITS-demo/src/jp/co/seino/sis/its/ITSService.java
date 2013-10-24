package jp.co.seino.sis.its;

import jp.co.its.ecu.cangather.lib.CarInfo;
import jp.co.its.ecu.cangather.lib.ConnectStatus;
import jp.co.its.ecu.cangather.lib.ECUListener;
import jp.co.its.ecu.cangather.lib.IItsEcuService;
import jp.co.its.ecu.cangather.lib.SensorInfo;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;

public class ITSService {

	private IItsEcuService mECUServer; 
	private Handler mCalledHandler = new Handler();
	private ITSServiceListener listener;
	
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
	 * Service�̃o�C���h
	 */
	public void bindService(Activity activity){
		//service�̃o�C���h
		Intent intent = new Intent(IItsEcuService.class.getName());
		activity.bindService(intent, mItsEcuServiceConn, Activity.BIND_AUTO_CREATE);
		System.out.println("bindService intent mItsEcuServiceConn");
	}

	/**
	 * Listener�̐ݒ�
	 */
	private ECUListener ecuListener; 	

	/**
	 * ECUListener�̏�����
	 */
	private void initECUListener() {
		ecuListener = new ECUListener.Stub() {
			@Override
			public void OnCarInfoUpdated(CarInfo car, SensorInfo sensor)
					throws RemoteException {
				mCalledHandler.post(new Runnable(){
					public void run(){
						System.out.println("OnCarInfoUpdated");
						CANInfo info = new CANInfo(mECUServer);
						listener.OnCarInfoUpdate(info);
					}
				});

			}

			@Override
			public void OnConnectStatusChanged(ConnectStatus arg0)
					throws RemoteException {
				mCalledHandler.post(new Runnable(){
					public void run(){
						System.out.println("OnConnectStatusChanged");
						CANInfo info = new CANInfo(mECUServer);
						listener.OnConnectStatusChanged(info);
					}
				});
			}

		};
	}

		
	/**
	 * ���X�i�[�̊J�n
	 */
	public void register(ITSServiceListener listener) {
		try {
			if(mECUServer != null) {
				setListener(listener);
				initECUListener();
				mECUServer.registerECUListener(ecuListener);
			} else {
				System.out.println("mECUServer is null!");
			}
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ���X�i�[�̏I��
	 */
	public void unregister() {
		try {
			if (mECUServer != null) {
				mECUServer.unregisterECUListener(ecuListener);
			}
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		} 
	}

	public ITSServiceListener getListener() {
		return listener;
	}

	public void setListener(ITSServiceListener listener) {
		this.listener = listener;
	}

}
