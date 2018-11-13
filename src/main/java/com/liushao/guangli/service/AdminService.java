package com.liushao.guangli.service;

import com.liushao.guangli.entity.AdminUser;

public interface AdminService {
    AdminUser findByName(String username);
}

