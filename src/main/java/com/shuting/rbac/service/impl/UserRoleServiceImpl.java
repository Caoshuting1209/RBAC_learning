package com.shuting.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuting.rbac.entity.UserRole;
import com.shuting.rbac.service.UserRoleService;
import com.shuting.rbac.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author caoshuting
* @description 针对表【user_role】的数据库操作Service实现
* @createDate 2024-12-16 13:35:24
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




