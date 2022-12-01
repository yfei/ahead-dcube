package cn.ahead.dcube.base.response.code;

import lombok.Getter;

/**
 * 
 * @desc: 响应代码。一般的响应代码同HTTP RESPONSE。业务响应代码1000+
 * @date: 2022年11月28日 上午9:22:52<br>
 * @author:yangfei<br>
 * @since 1.0.0
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
