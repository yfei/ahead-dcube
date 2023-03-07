package cn.ahead.dcube.system.upload.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import cn.ahead.dcube.base.entity.IEntity;
import lombok.Data;

/**
 * 
 * @desc:
 * @date: 2022年11月28日 上午10:25:46<br>
 * @author:yangfei<br>
 * @since 1.0.0
 */
@Table
@Entity(name = "tb_sys_upload_file")
@Data
public class UploadFileEntity implements IEntity {
    /**
     * 文件上传ID
     */
    @Id
    private String id;

    /**
     * 乐观锁
     */
    @Version
    private long version;

    /**
     * 文件名称
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件大小
     */
    @Column(name = "file_size")
    private long fileSize;

    /**
     * 文件大小
     */
    @Column(name = "file_suffix")
    private long fileSuffix;

    /**
     * 业务类型
     */
    private String business;

    /**
     * 业务id
     */
    @Column(name = "business_id")
    private String businessId;

    /**
     * 文件路径
     */
    @Column(name = "file_path")
    private String filePath;

    /**
     * 文件描述
     */
    @Column(name = "file_desc")
    private String fileDesc;

    /**
     * 创建用户
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

}
