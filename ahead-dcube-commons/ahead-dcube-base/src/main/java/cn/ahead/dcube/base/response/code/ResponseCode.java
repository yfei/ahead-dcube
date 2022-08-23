package cn.ahead.dcube.base.response.code;

import lombok.Getter;

/**
 * 响应代码。一般的响应代码通HTTP RESPONSE。业务响应代码1000+
 * 
 * @author yangfei
 *
 */
@Getter
public enum ResponseCode implements StatusCode {

	SUCCESS(200, "请求成功"),
	
    ERROR(500, "请求失败");


    private int code;
	
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
