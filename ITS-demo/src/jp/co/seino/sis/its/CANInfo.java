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
				dist = dist / 100000; // 0.01m単位を1km単位に修正
				setTotalDist(dist);
				double eff = (double) mECUServer.getInstantaneousFuelEfficiency();//瞬間燃費
				eff = eff / 10; // 0.1km/Lを1km/Lに修正
				setInstantaneousFuelEfficiency(eff);
				double effA = (double) mECUServer.getAverageFuelEfficiency();//平均燃費
				effA = effA / 100; // 0.01km/Lを1km/Lに修正
				setAverageFuelEfficiency(effA);
				double angle = (double)mECUServer.getRudderAngle();//舵角
				angle = angle / 10; // 10°単位を1°単位に修正
				setRudderAngle(angle);
				double tFuel = (double)mECUServer.getTotalFuel();//積算燃料
				tFuel = tFuel / 100; // ml単位に修正
				setTotalFuel(tFuel);
				double vol = (double)mECUServer.getVoltage();//電圧
				vol = vol / 10; // V単位に修正
				setVoltage(vol);
			}
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String toCanInfoString(IItsEcuService mECUServer) throws RemoteException {
		double dist = (double) mECUServer.getTotalDist();
		dist = dist / 100000; // 0.01m単位を1km単位に修正
		double eff = (double) mECUServer.getInstantaneousFuelEfficiency();//瞬間燃費
		eff = eff / 10; // 0.1km/Lを1km/Lに修正
		double effA = (double) mECUServer.getAverageFuelEfficiency();//平均燃費
		effA = effA / 100; // 0.01km/Lを1km/Lに修正
		double angle = (double)mECUServer.getRudderAngle();//舵角
		angle = angle / 10; // 10°単位を1°単位に修正
		double tFuel = (double)mECUServer.getTotalFuel();//積算燃料
		tFuel = tFuel / 100; // ml単位に修正
		double vol = (double)mECUServer.getVoltage();//電圧
		vol = vol / 10; // V単位に修正
		StringBuffer updateText = new StringBuffer("--------車両情報--------");
		updateText.append("\r\n車速：" + mECUServer.getCarSpeed() + " km/h" + "\r\n積算距離：" + String.valueOf(dist) + " km");// 
		updateText.append("\r\n瞬間燃費：" + String.valueOf(eff) + " km/L" + "\r\n平均燃費：" + String.valueOf(effA) + " km/L");// 燃費
		updateText.append("\r\n舵角:" + String.valueOf(angle) + " °" + "\r\n水温:" + mECUServer.getWaterTemp() + " ℃");
		updateText.append("\r\nエンジン回転数:" + mECUServer.getEngineRPM() + " rpm" + "\r\nスロットル開度:" + mECUServer.getThrottleOpening() + " %");
		updateText.append("\r\n積算燃料:" + String.valueOf(tFuel) + " mL" + "\r\n電圧:"+String.valueOf(vol) + " V");
		updateText.append("\r\n室温:" + mECUServer.getTemperature() + " ℃");
		
		updateText.append("\r\nスイッチ情報");
		SwitchInfo sInfo = mECUServer.getSwitchInfo();
		updateText.append("\r\nブレーキ有効："+sInfo.getEnableBrakeSwitch()+"　スイッチ："+sInfo.getBrakeSwitch());
		updateText.append("\r\nシートベルト有効："+sInfo.getEnableSeatbeltSwitch()+"　スイッチ："+sInfo.getSeatbeltSwitch());
		updateText.append("\r\nABS作動有効："+sInfo.getEnableABSActuationSwitch()+"　スイッチ："+sInfo.getABSActuationSwitch());
		updateText.append("\r\nエアコン有効："+sInfo.getEnableAirConSwitch()+"　スイッチ："+sInfo.getAirConSwitch());
		updateText.append("\r\nワイパー有効："+sInfo.getEnableWiperSwitch()+"　スイッチ："+sInfo.getWiperSwitch());
		updateText.append("\r\nウィンカー有効："+sInfo.getEnableBlinkerSwitch()+"　スイッチ："+sInfo.getBlinkerSwitch());
		//ConnectStatus connectStatus = mECUServer.getCarInfo(cInfo, seInfo);
		CarInfo cInfo = new CarInfo();
		SensorInfo seInfo = new SensorInfo();
		mECUServer.getCarInfo(cInfo, seInfo);
		updateText.append("\r\n舵角:"+cInfo.getRudderAngle());
		updateText.append("\r\nseInfo:室温:"+seInfo.getTemperature());
		StatusInfo stInfo = mECUServer.getStatusInfo();
		updateText.append("\r\n車両通信状態:"+stInfo.getStatusCarConnect());
		updateText.append("\r\nseInfo:電圧:"+seInfo.getVoltage()+"\r\n");
		
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
