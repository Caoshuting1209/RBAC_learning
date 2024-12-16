package com.shuting.rbac.constants;

import com.baomidou.mybatisplus.core.toolkit.StringPool;

public class CommonConstants {
    //默认每页的大小
    public static final Long DEFAULT_PAGE_SIZE = 10L;
    //默认当前第几页
    public static final Long DEFAULT_PAGE_NOW = 1L;

    public static final String DOT = StringPool.DOT;
    public static final String SLASH = StringPool.SLASH;
    public static final String COMMA = StringPool.COMMA;
    //路径前缀
    public static final String RESOURCES_PREFIX = "resources/";
    //文件按天生成目录进行存储
    public static final String UPLOAD_DATE_FORMAT = "yyyyMMdd";
    //顶层权限的parent_id
    public static final Long TOP_PARENT_ID = -1L;
}
