package cn.ahead.dcube.base.constant;

public class AheadSysConstant {

	// sesson中的当前用户
	public static final String SESSION_USER = "CURRENTUSER";

	public static final String SESSION_CAPTCHA = "CAPTCHA_TEXT";
	
	public static final String SNS_FLAG = "SNS_LOGIN";


	// session中的token
	public static final String SESSION_TOKEN = "AHEAD-TOKEN";

	// ----- 用户constant--------
	public static final Integer USER_TYPE_COMMON = 0;

	public static final Integer USER_TYPE_ADMIN = 1;

	public static final Integer USER_SEX_MALE = 1;

	public static final Integer USER_SEX_FFMALE = 2;

	public static final Integer USER_SEX_UNKOWN = 0;

	public static final Integer USER_STATUS_NORMAL = 0;

	public static final Integer USER_STATUS_DISABLED = 1;

	// ------org constant

	public static final Integer ORG_STATUS_NORMAL = 0;

	public static final Integer ORG_STATUS_DISABLED = 1;
}
