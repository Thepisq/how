package com.liushao.guangli.service;
import com.liushao.guangli.entity.User;

import java.util.List;

/**
 * @auther huangshen
 */
public interface UserService extends BaseService<User> {

    User findByName(String name);
    int deleteBatch(List<Integer> ids);
    int deleteUser(Integer id);
    int updateUser(User user);
    User getUser(Integer id);
    boolean checkUser(String username);
    int saveUser(User user);
}
