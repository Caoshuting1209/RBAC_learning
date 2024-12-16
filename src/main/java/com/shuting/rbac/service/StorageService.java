package com.shuting.rbac.service;

import com.shuting.rbac.common.UploadFileReq;

//存储业务类
public interface StorageService {
    public String upload(UploadFileReq uploadFileReq);
}
