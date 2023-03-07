package cn.ahead.dcube.upload.dto;

import java.util.Map;

import lombok.Data;

/**
 * 
 * @desc:上传参数
 * @date: 2022年11月28日 上午9:17:24<br>
 * @author:yangfei<br>
 * @since 1.0.0
 */
@Data
public class UploadParam {
    /**
     * service名称
     */
    private String serviceName;

    /**
     * 是否解析
     */
    private boolean analysis = false;

    /**
     * 是否保存uploadFileEntity
     */
    private boolean save = true;

    /**
     * 业务信息,根据业务信息映射上传路径
     */
    private String business;

    /**
     * 对应的业务id
     */
    private String businessId;

    /**
     * 对应的文件描述
     */
    private String fileDesc;

    /**
     * 其他业务参数
     */
    private Map<String, Object> params;

}
