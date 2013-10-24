package jp.co.seino.sis.its;

public interface ITSServiceListener {
	public void OnCarInfoUpdate(CANInfo info);
	public void OnConnectStatusChanged(CANInfo info);
}
