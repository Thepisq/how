package com.liushao.guangli.service.imp;

import com.liushao.guangli.entity.AdminUser;
import com.liushao.guangli.mapper.AdminMapper;
import com.liushao.guangli.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    public AdminUser findByName(String username){
        return adminMapper.findByName(username);
    }
}
