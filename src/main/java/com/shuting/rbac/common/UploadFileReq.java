package com.shuting.rbac.common;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadFileReq {
    @NotNull
    private MultipartFile file;
    private String module;
}
