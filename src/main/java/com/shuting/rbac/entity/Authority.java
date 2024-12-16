package com.shuting.rbac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName authority
 */
@TableName(value ="authority")
@Data
public class Authority{
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Integer type;

    /**
     * 
     */
    private Integer showInMenu;

    /**
     * 
     */
    private String authorityName;

    /**
     * 
     */
    private String path;

    /**
     * 
     */
    private String reletaBackUris;

    /**
     * 
     */
    private String componentPath;

    /**
     * 
     */
    private Integer orderNo;

    /**
     * 
     */
    private String icon;

    /**
     * 
     */
    private Long parentId;
}