package jp.co.seino.sis.its;

import android.os.RemoteException;
import jp.co.its.ecu.cangather.lib.CarInfo;
import jp.co.its.ecu.cangather.lib.IItsEcuService;
import jp.co.its.ecu.cangather.lib.SensorInfo;
import jp.co.its.ecu.cangather.lib.StatusInfo;
import jp.co.its.ecu.cangather.lib.SwitchInfo;

public class CANInfo {

	private SwitchInfo switchiInfo;
	private CarInfo carInfo;
	private SensorInfo sensorInfo;
	private StatusInfo statustInfo;
	
	private double totalDist;
	private double instantaneousFuelEfficiency;
	private double averageFuelEfficiency;
	private double rudderAngle;
	private double totalFuel;
	private double voltage;
		
	public CANInfo() {
	}
	
	public CANInfo(IItsEcuService mECUServer) {
		try {
			if (mECUServer != null) {
				setSwitchiInfo(mECUServer.getSwitchInfo());
				CarInfo cInfo = new CarInfo();
				SensorInfo seInfo = new SensorInfo();
				mECUServer.getCarInfo(cInfo, seInfo);
				setCarInfo(cInfo);
				setSensorInfo(seInfo);
				setStatustInfo(mECUServer.getStatusInfo());
				//
				double dist = (double) mECUServer.getTotalDist();
				dist = dist / 100000; // 0.01m�P�ʂ�1km�P�ʂɏC��
				setTotalDist(dist);
				double eff = (double) mECUServer.getInstantaneousFuelEfficiency();//�u�ԔR��
				eff = eff / 10; // 0.1km/L��1km/L�ɏC��
				setInstantaneousFuelEfficiency(eff);
				double effA = (double) mECUServer.getAverageFuelEfficiency();//���ϔR��
				effA = effA / 100; // 0.01km/L��1km/L�ɏC��
				setAverageFuelEfficiency(effA);
				double angle = (double)mECUServer.getRudderAngle();//�Ǌp
				angle = angle / 10; // 10���P�ʂ�1���P�ʂɏC��
				setRudderAngle(angle);
				double tFuel = (double)mECUServer.getTotalFuel();//�ώZ�R��
				tFuel = tFuel / 100; // ml�P�ʂɏC��
				setTotalFuel(tFuel);
				double vol = (double)mECUServer.getVoltage();//�d��
				vol = vol / 10; // V�P�ʂɏC��
				setVoltage(vol);
			}
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String toCanInfoString(IItsEcuService mECUServer) throws RemoteException {
		double dist = (double) mECUServer.getTotalDist();
		dist = dist / 100000; // 0.01m�P�ʂ�1km�P�ʂɏC��
		double eff = (double) mECUServer.getInstantaneousFuelEfficiency();//�u�ԔR��
		eff = eff / 10; // 0.1km/L��1km/L�ɏC��
		double effA = (double) mECUServer.getAverageFuelEfficiency();//���ϔR��
		effA = effA / 100; // 0.01km/L��1km/L�ɏC��
		double angle = (double)mECUServer.getRudderAngle();//�Ǌp
		angle = angle / 10; // 10���P�ʂ�1���P�ʂɏC��
		double tFuel = (double)mECUServer.getTotalFuel();//�ώZ�R��
		tFuel = tFuel / 100; // ml�P�ʂɏC��
		double vol = (double)mECUServer.getVoltage();//�d��
		vol = vol / 10; // V�P�ʂɏC��
		StringBuffer updateText = new StringBuffer("--------�ԗ����--------");
		updateText.append("\r\n�ԑ��F" + mECUServer.getCarSpeed() + " km/h" + "\r\n�ώZ�����F" + String.valueOf(dist) + " km");// 
		updateText.append("\r\n�u�ԔR��F" + String.valueOf(eff) + " km/L" + "\r\n���ϔR��F" + String.valueOf(effA) + " km/L");// �R��
		updateText.append("\r\n�Ǌp:" + String.valueOf(angle) + " ��" + "\r\n����:" + mECUServer.getWaterTemp() + " ��");
		updateText.append("\r\n�G���W����]��:" + mECUServer.getEngineRPM() + " rpm" + "\r\n�X���b�g���J�x:" + mECUServer.getThrottleOpening() + " %");
		updateText.append("\r\n�ώZ�R��:" + String.valueOf(tFuel) + " mL" + "\r\n�d��:"+String.valueOf(vol) + " V");
		updateText.append("\r\n����:" + mECUServer.getTemperature() + " ��");
		
		updateText.append("\r\n�X�C�b�`���");
		SwitchInfo sInfo = mECUServer.getSwitchInfo();
		updateText.append("\r\n�u���[�L�L���F"+sInfo.getEnableBrakeSwitch()+"�@�X�C�b�`�F"+sInfo.getBrakeSwitch());
		updateText.append("\r\n�V�[�g�x���g�L���F"+sInfo.getEnableSeatbeltSwitch()+"�@�X�C�b�`�F"+sInfo.getSeatbeltSwitch());
		updateText.append("\r\nABS�쓮�L���F"+sInfo.getEnableABSActuationSwitch()+"�@�X�C�b�`�F"+sInfo.getABSActuationSwitch());
		updateText.append("\r\n�G�A�R���L���F"+sInfo.getEnableAirConSwitch()+"�@�X�C�b�`�F"+sInfo.getAirConSwitch());
		updateText.append("\r\n���C�p�[�L���F"+sInfo.getEnableWiperSwitch()+"�@�X�C�b�`�F"+sInfo.getWiperSwitch());
		updateText.append("\r\n�E�B���J�[�L���F"+sInfo.getEnableBlinkerSwitch()+"�@�X�C�b�`�F"+sInfo.getBlinkerSwitch());
		//ConnectStatus connectStatus = mECUServer.getCarInfo(cInfo, seInfo);
		CarInfo cInfo = new CarInfo();
		SensorInfo seInfo = new SensorInfo();
		mECUServer.getCarInfo(cInfo, seInfo);
		updateText.append("\r\n�Ǌp:"+cInfo.getRudderAngle());
		updateText.append("\r\nseInfo:����:"+seInfo.getTemperature());
		StatusInfo stInfo = mECUServer.getStatusInfo();
		updateText.append("\r\n�ԗ��ʐM���:"+stInfo.getStatusCarConnect());
		updateText.append("\r\nseInfo:�d��:"+seInfo.getVoltage()+"\r\n");
		
		return updateText.toString();
	}

	public CANInfo(final CarInfo car, final SensorInfo sensor){
		setCarInfo(car);
		setSensorInfo(sensor);
	}

	public SwitchInfo getSwitchiInfo() {
		return switchiInfo;
	}

	public void setSwitchiInfo(SwitchInfo switchiInfo) {
		this.switchiInfo = switchiInfo;
	}

	public CarInfo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	public SensorInfo getSensorInfo() {
		return sensorInfo;
	}

	public void setSensorInfo(SensorInfo sensorInfo) {
		this.sensorInfo = sensorInfo;
	}

	public StatusInfo getStatustInfo() {
		return statustInfo;
	}

	public void setStatustInfo(StatusInfo statustInfo) {
		this.statustInfo = statustInfo;
	}

	public double getTotalDist() {
		return totalDist;
	}

	public void setTotalDist(double totalDist) {
		this.totalDist = totalDist;
	}

	public double getInstantaneousFuelEfficiency() {
		return instantaneousFuelEfficiency;
	}

	public void setInstantaneousFuelEfficiency(double instantaneousFuelEfficiency) {
		this.instantaneousFuelEfficiency = instantaneousFuelEfficiency;
	}

	public double getAverageFuelEfficiency() {
		return averageFuelEfficiency;
	}

	public void setAverageFuelEfficiency(double averageFuelEfficiency) {
		this.averageFuelEfficiency = averageFuelEfficiency;
	}

	public double getRudderAngle() {
		return rudderAngle;
	}

	public void setRudderAngle(double rudderAngle) {
		this.rudderAngle = rudderAngle;
	}

	public double getTotalFuel() {
		return totalFuel;
	}

	public void setTotalFuel(double totalFuel) {
		this.totalFuel = totalFuel;
	}

	public double getVoltage() {
		return voltage;
	}

	public void setVoltage(double voltage) {
		this.voltage = voltage;
	}
	

	
}
