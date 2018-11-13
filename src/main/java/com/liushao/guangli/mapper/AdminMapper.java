package com.liushao.guangli.mapper;

import com.liushao.guangli.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    AdminUser findByName(String username);
}
