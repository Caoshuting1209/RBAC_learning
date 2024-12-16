package com.shuting.rbac.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public enum CodeEnum{
    SUCCESS("200", "success"),
    ERROR("500", "error"),
    BINDING_ERROR("800", "binding error"),

    //
    AUTH_ERROR("300", "auth error"),

    //文件上传错误
    UPLOAD_EMPTY("301", "upload is empty"),
    FILENAME_ERROR("302", "filename error"),
    MAKE_DIR_ERROR("303", "make dir error"),
    STORAGE_ERROR("304", "stage error"),
    ;

    private String code;
    private String msg;
}
