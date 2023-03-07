package cn.ahead.dcube.system.upload.dao;

import org.springframework.stereotype.Repository;

import cn.ahead.dcube.jpa.repository.AheadRepository;
import cn.ahead.dcube.system.upload.entity.UploadFileEntity;

@Repository
public interface UploadFileRepository extends AheadRepository<UploadFileEntity, Long> {

}
