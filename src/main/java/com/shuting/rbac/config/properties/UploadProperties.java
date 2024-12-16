package com.shuting.rbac.config.properties;

import cn.hutool.core.util.StrUtil;
import com.shuting.rbac.constants.CommonConstants;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;
import java.io.IOException;

@ConfigurationProperties(prefix = "upload")
@Data
public class UploadProperties {
    private String staticDir;

    @PostConstruct
    public void init() {
        if(StrUtil.isBlank(staticDir)){
            throw new RuntimeException("Upload.staticDir not configured");
        }
        if(!staticDir.endsWith(CommonConstants.SLASH)){
            throw new RuntimeException("Upload.staticDir not ended with '/'");
        }
        File file = new File(staticDir);
        if(!file.isAbsolute()){
            throw new RuntimeException("Upload.staticDir is not absolute");
        }
        if(!file.exists()){
            try{
                FileUtils.forceMkdir(file);
            }catch(IOException e){
                throw new RuntimeException("upload.staticDir create failed");
            }
        }
    }
}
