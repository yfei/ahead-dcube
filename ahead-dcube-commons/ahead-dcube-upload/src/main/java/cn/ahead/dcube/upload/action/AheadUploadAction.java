package cn.ahead.dcube.upload.action;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.ahead.dcube.advice.annotation.NoResponseAdvice;
import cn.ahead.dcube.base.response.Response;
import cn.ahead.dcube.commons.file.FileUtil;
import cn.ahead.dcube.commons.util.StringUtils;
import cn.ahead.dcube.context.SpringContext;
import cn.ahead.dcube.log.annotation.Log;
import cn.ahead.dcube.log.enums.OperType;
import cn.ahead.dcube.security.util.SecurityUtil;
import cn.ahead.dcube.upload.config.UploadConfig;
import cn.ahead.dcube.upload.dto.UploadFileDTO;
import cn.ahead.dcube.upload.dto.UploadParam;
import cn.ahead.dcube.upload.dto.UploadResult;
import cn.ahead.dcube.upload.service.UploadService;

@RestController
@RequestMapping("/upload")
public class AheadUploadAction {

    /**
     * 配置信息
     */
    @Autowired
    private UploadConfig uploadConfig;
    
    @Autowired
    private UploadService service;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    @Log(module = "基础功能", function = "数据导入", details = true, oper = OperType.IMPORT)
    public Response upload(HttpServletRequest request,
	    @RequestParam(value = "param", required = true) UploadParam param, @RequestParam("file") MultipartFile file)
	    throws Exception {

	// 上传路径
	String rootPath = uploadConfig.getRootPath();
	String businessPath = null;
	if (!StringUtils.isEmpty(param.getBusiness())
		&& uploadConfig.getBusinessPath().containsKey(param.getBusiness())) {
	    businessPath = uploadConfig.getBusinessPath().get(param.getBusiness());
	}
	String uploadPath = rootPath + File.separator + businessPath;

	// 使用uuid+后缀名,避免文件名重复
	String fileName = file.getOriginalFilename(); // 原始文件名
	String fileId = StringUtils.getUUID();
	String fileNameOfUUID = fileId;
	String fileSuffix = FileUtil.getFileSuffix(fileName);
	if (fileSuffix != null) {
	    fileNameOfUUID = fileNameOfUUID + "." + fileSuffix;
	}
	File dstFile = new File(uploadPath, fileNameOfUUID);
	if (!dstFile.getParentFile().exists()) {
	    boolean rel = dstFile.getParentFile().mkdirs();
	    if (rel == false) {
		return Response.error("create folder error!");
	    }
	}
	try {
	    file.transferTo(dstFile);
	    // 构造dto
	    UploadFileDTO uploadFile = new UploadFileDTO();
	    uploadFile.setId(fileId);
	    uploadFile.setFilePath(businessPath);
	    uploadFile.setFileName(fileName);
	    uploadFile.setFileSize(dstFile.length());
	    uploadFile.setFileSuffix(fileSuffix);
	    uploadFile.setFileDesc(param.getFileDesc());
	    uploadFile.setBusiness(param.getBusiness());
	    uploadFile.setBusinessId(param.getBusinessId());
	    uploadFile.setCreateTime(new Date());
	    uploadFile.setCreateUser(SecurityUtil.getCurrentUser().getAccount());
	    uploadFile.setFile(dstFile);
	    
	    UploadResult result = service.upload(uploadFile, param);
	    if (result.isSuccess()) {
		return Response.success();
	    } else {
		return Response.error(result.getMessage());
	    }
	} catch (Exception e) {
	    return Response.error(e.getMessage());
	}
    }
}
