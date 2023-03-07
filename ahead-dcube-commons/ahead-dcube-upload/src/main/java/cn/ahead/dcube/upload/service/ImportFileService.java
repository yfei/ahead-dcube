package cn.ahead.dcube.upload.service;

import java.io.File;

import cn.ahead.dcube.upload.dto.UploadParam;
import cn.ahead.dcube.upload.dto.UploadResult;

/**
 * 
 * @desc:文件导入service
 * @date: 2022年11月28日 上午9:27:52<br>
 * @author:yangfei<br>
 * @since 1.0.0
 */
public interface ImportFileService {

    /**
     * 解析文件
     * 
     * @param file
     */
    public UploadResult importFile(File file, UploadParam param);

}
