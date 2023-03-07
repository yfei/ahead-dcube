package cn.ahead.dcube.upload.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class UploadConfig {

    /**
     * 上传根路径
     */
    @Value("${system.upload.path.root}")
    private String rootPath;

    @Value("${system.upload.path.business}")
    private Map<String, String> businessPath;

}
