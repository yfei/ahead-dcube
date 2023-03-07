package cn.ahead.dcube.upload.dto;

import java.io.File;
import java.util.Date;

import cn.ahead.dcube.base.dto.IDTO;
import lombok.Data;

/**
 * 
 * @desc: 文件上传dto
 * @date: 2022年11月28日 上午10:05:58<br>
 * @author:yangfei<br>
 * @since 1.0.0
 */
@Data
public class UploadFileDTO implements IDTO {

    /**
     * 文件上传ID,一般为uuid
     */
    private String id;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件后缀
     */
    private String fileSuffix;

    /**
     * 文件大小 byte
     */
    private long fileSize;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件描述
     */
    private String fileDesc;
    
    /**
     * 业务类型
     */
    private String business;
    
    /**
     * 业务id
     */
    private String businessId;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 文件对象
     */
    private File file;
}
