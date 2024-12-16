package com.shuting.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuting.rbac.entity.Authority;
import com.shuting.rbac.service.AuthorityService;
import com.shuting.rbac.mapper.AuthorityMapper;
import org.springframework.stereotype.Service;

/**
* @author caoshuting
* @description 针对表【authority】的数据库操作Service实现
* @createDate 2024-12-16 13:35:24
*/
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority>
    implements AuthorityService{

}




