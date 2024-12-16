package com.shuting.rbac.controller;

import com.shuting.rbac.common.Result;
import com.shuting.rbac.common.UploadFileReq;
import com.shuting.rbac.service.StorageService;
import com.shuting.rbac.service.impl.StorageServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/stroage")
@Validated
public class StorageController {
    @Autowired
    private StorageService storageService;

    @PostMapping("/uploadFile")
    public Result<String> uploadFile(@Validated UploadFileReq uploadFileReq) {
        return Result.success(storageService.upload(uploadFileReq));
    }

}
