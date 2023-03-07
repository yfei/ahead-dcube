package cn.ahead.dcube.upload.dto;

import lombok.Data;

/**
 * 
 * @desc:上传结果
 * @date: 2022年11月28日 上午9:39:32<br>
 * @author:yangfei<br>
 * @since 1.0.0
 */
@Data
public class UploadResult {

    private boolean success;

    private String message;

}
