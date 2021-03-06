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
				dist = dist / 100000; // 0.01mPÊð1kmPÊÉC³
				setTotalDist(dist);
				double eff = (double) mECUServer.getInstantaneousFuelEfficiency();//uÔRï
				eff = eff / 10; // 0.1km/Lð1km/LÉC³
				setInstantaneousFuelEfficiency(eff);
				double effA = (double) mECUServer.getAverageFuelEfficiency();//½ÏRï
				effA = effA / 100; // 0.01km/Lð1km/LÉC³
				setAverageFuelEfficiency(effA);
				double angle = (double)mECUServer.getRudderAngle();//Çp
				angle = angle / 10; // 10PÊð1PÊÉC³
				setRudderAngle(angle);
				double tFuel = (double)mECUServer.getTotalFuel();//ÏZR¿
				tFuel = tFuel / 100; // mlPÊÉC³
				setTotalFuel(tFuel);
				double vol = (double)mECUServer.getVoltage();//d³
				vol = vol / 10; // VPÊÉC³
				setVoltage(vol);
			}
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String toCanInfoString(IItsEcuService mECUServer) throws RemoteException {
		double dist = (double) mECUServer.getTotalDist();
		dist = dist / 100000; // 0.01mPÊð1kmPÊÉC³
		double eff = (double) mECUServer.getInstantaneousFuelEfficiency();//uÔRï
		eff = eff / 10; // 0.1km/Lð1km/LÉC³
		double effA = (double) mECUServer.getAverageFuelEfficiency();//½ÏRï
		effA = effA / 100; // 0.01km/Lð1km/LÉC³
		double angle = (double)mECUServer.getRudderAngle();//Çp
		angle = angle / 10; // 10PÊð1PÊÉC³
		double tFuel = (double)mECUServer.getTotalFuel();//ÏZR¿
		tFuel = tFuel / 100; // mlPÊÉC³
		double vol = (double)mECUServer.getVoltage();//d³
		vol = vol / 10; // VPÊÉC³
		StringBuffer updateText = new StringBuffer("--------Ô¼îñ--------");
		updateText.append("\r\nÔ¬F" + mECUServer.getCarSpeed() + " km/h" + "\r\nÏZ£F" + String.valueOf(dist) + " km");// 
		updateText.append("\r\nuÔRïF" + String.valueOf(eff) + " km/L" + "\r\n½ÏRïF" + String.valueOf(effA) + " km/L");// Rï
		updateText.append("\r\nÇp:" + String.valueOf(angle) + " " + "\r\n·:" + mECUServer.getWaterTemp() + " ");
		updateText.append("\r\nGWñ]:" + mECUServer.getEngineRPM() + " rpm" + "\r\nXbgJx:" + mECUServer.getThrottleOpening() + " %");
		updateText.append("\r\nÏZR¿:" + String.valueOf(tFuel) + " mL" + "\r\nd³:"+String.valueOf(vol) + " V");
		updateText.append("\r\nº·:" + mECUServer.getTemperature() + " ");
		
		updateText.append("\r\nXCb`îñ");
		SwitchInfo sInfo = mECUServer.getSwitchInfo();
		updateText.append("\r\nu[LLøF"+sInfo.getEnableBrakeSwitch()+"@XCb`F"+sInfo.getBrakeSwitch());
		updateText.append("\r\nV[gxgLøF"+sInfo.getEnableSeatbeltSwitch()+"@XCb`F"+sInfo.getSeatbeltSwitch());
		updateText.append("\r\nABSì®LøF"+sInfo.getEnableABSActuationSwitch()+"@XCb`F"+sInfo.getABSActuationSwitch());
		updateText.append("\r\nGARLøF"+sInfo.getEnableAirConSwitch()+"@XCb`F"+sInfo.getAirConSwitch());
		updateText.append("\r\nCp[LøF"+sInfo.getEnableWiperSwitch()+"@XCb`F"+sInfo.getWiperSwitch());
		updateText.append("\r\nEBJ[LøF"+sInfo.getEnableBlinkerSwitch()+"@XCb`F"+sInfo.getBlinkerSwitch());
		//ConnectStatus connectStatus = mECUServer.getCarInfo(cInfo, seInfo);
		CarInfo cInfo = new CarInfo();
		SensorInfo seInfo = new SensorInfo();
		mECUServer.getCarInfo(cInfo, seInfo);
		updateText.append("\r\nÇp:"+cInfo.getRudderAngle());
		updateText.append("\r\nseInfo:º·:"+seInfo.getTemperature());
		StatusInfo stInfo = mECUServer.getStatusInfo();
		updateText.append("\r\nÔ¼ÊMóÔ:"+stInfo.getStatusCarConnect());
		updateText.append("\r\nseInfo:d³:"+seInfo.getVoltage()+"\r\n");
		
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
