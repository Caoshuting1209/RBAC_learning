package com.shuting.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuting.rbac.entity.Role;
import com.shuting.rbac.service.RoleService;
import com.shuting.rbac.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author caoshuting
* @description 针对表【role】的数据库操作Service实现
* @createDate 2024-12-16 13:35:24
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




