package cn.ahead.dcube.sns;

public interface ISNSService {
	
	/**
	 * SNS的业务处理
	 * @param type
	 * @param openid
	 * @return
	 */
	public Object business(String type, String openid);

}
