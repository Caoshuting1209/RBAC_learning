package com.shuting.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuting.rbac.entity.User;
import com.shuting.rbac.service.UserService;
import com.shuting.rbac.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author caoshuting
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-12-16 13:35:24
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




