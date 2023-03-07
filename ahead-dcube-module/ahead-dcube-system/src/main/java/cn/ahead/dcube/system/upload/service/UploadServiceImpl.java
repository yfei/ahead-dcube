//package cn.ahead.dcube.system.upload.service;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import cn.ahead.dcube.base.exception.AheadServiceException;
//import cn.ahead.dcube.context.SpringContext;
//import cn.ahead.dcube.jpa.service.AbstractAheadService;
//import cn.ahead.dcube.system.upload.dao.UploadFileRepository;
//import cn.ahead.dcube.system.upload.entity.UploadFileEntity;
//import cn.ahead.dcube.upload.dto.UploadFileDTO;
//import cn.ahead.dcube.upload.dto.UploadParam;
//import cn.ahead.dcube.upload.dto.UploadResult;
//import cn.ahead.dcube.upload.service.ImportFileService;
//import cn.ahead.dcube.upload.service.UploadService;
//
///**
// * 
// * @desc: 文件上传service
// * @date: 2022年11月28日 上午10:33:34<br>
// * @author:yangfei<br>
// * @since 1.0.0
// */
//@Service
//public class UploadServiceImpl extends AbstractAheadService implements UploadService {
//
//    @Autowired
//    private UploadFileRepository repository;
//
//    @Transactional
//    @Override
//    public UploadResult upload(UploadFileDTO fileDto, UploadParam param) {
//	if (param.isSave()) {
//	    UploadFileEntity uploadFile = new UploadFileEntity();
//	    BeanUtils.copyProperties(fileDto, uploadFile, "file");
//	    repository.save(uploadFile);
//	}
//	if (param.isAnalysis()) {
//	    String serviceName = param.getServiceName();
//	    ImportFileService service = SpringContext.getBean(serviceName);
//	    if (service == null) {
//		throw new AheadServiceException("cannot find the business service!");
//	    }
//	    return service.importFile(fileDto.getFile(), param);
//	}
//
//	UploadResult result = new UploadResult();
//	result.setSuccess(true);
//	return result;
//
//    }
//
//}
