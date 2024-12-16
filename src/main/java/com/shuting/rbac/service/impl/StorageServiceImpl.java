package com.shuting.rbac.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.shuting.rbac.common.CodeEnum;
import com.shuting.rbac.common.UploadFileReq;
import com.shuting.rbac.config.properties.UploadProperties;
import com.shuting.rbac.constants.CommonConstants;
import com.shuting.rbac.exception.GlobalException;
import com.shuting.rbac.service.StorageService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {
    @Resource
    private UploadProperties uploadProperties;

    @Override
    public String upload(UploadFileReq uploadFileReq) {
        MultipartFile file = uploadFileReq.getFile();
        if(file.isEmpty()){
            log.warn("upload file is empty");
            throw new GlobalException(CodeEnum.UPLOAD_EMPTY);
        }
        String originalFileName = file.getOriginalFilename();
        if(StringUtils.isBlank(originalFileName)){
            log.warn("File name is empty");
            throw new GlobalException(CodeEnum.FILENAME_ERROR);
        }
        String module = uploadFileReq.getModule();
        String relativePath = spliceRelativePath(originalFileName, module);
        doStoreFile(relativePath, file);
        return relativePath;
    }

    private void doStoreFile(String relativePath, MultipartFile file) {
        String loaclAbsPath = uploadProperties.getStaticDir() + relativePath;
        File destFile = new File(loaclAbsPath);
        try{
            FileUtils.forceMkdir(destFile.getParentFile());
        }catch (IOException e){
            log.error(e.getMessage(), e);
            throw new GlobalException(CodeEnum.MAKE_DIR_ERROR);
        }
        try{
            file.transferTo(destFile);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            throw new GlobalException(CodeEnum.STORAGE_ERROR);
        }
    }
    private String spliceRelativePath(String originalFileName, String module) {
        String patternDate = DateUtil.format(LocalDateTime.now(), CommonConstants.UPLOAD_DATE_FORMAT);
        String newFileName = reName(originalFileName);
        StringBuilder relativePath = new StringBuilder();
        relativePath.append(CommonConstants.RESOURCES_PREFIX);
        if(StringUtils.isNotBlank(module)){
            relativePath.append(CommonConstants.SLASH);
        }
        relativePath.append(patternDate).append(newFileName);
        return relativePath.toString();
    }
    private String reName(String originalFileName) {
        String baseName = IdUtil.fastSimpleUUID();
        String suffix = CommonConstants.DOT + FilenameUtils.getExtension(originalFileName);
        return baseName + suffix;
    }
}
