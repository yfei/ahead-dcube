package cn.ahead.dcube.upload.service;

import cn.ahead.dcube.upload.dto.UploadFileDTO;
import cn.ahead.dcube.upload.dto.UploadParam;
import cn.ahead.dcube.upload.dto.UploadResult;

/**
 * 
 * @desc:文件上传service
 * @date: 2022年11月28日 上午9:27:52<br>
 * @author:yangfei<br>
 * @since 1.0.0
 */
public interface UploadService {

    /**
     * 文件上传
     * 
     * @param file
     * @param param
     * @return
     */
    public UploadResult upload(UploadFileDTO file, UploadParam param);

}
