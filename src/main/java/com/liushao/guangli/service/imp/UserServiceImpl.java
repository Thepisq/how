package com.liushao.guangli.service.imp;
import com.liushao.guangli.entity.User;
import com.liushao.guangli.mapper.UserMapper;
import com.liushao.guangli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther huangshen
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public List<User> findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public int create(User user) {
        return userMapper.create(user);
    }

    @Override
    public int delete(Integer... ids) {
        for (Integer id : ids) {
            userMapper.delete(id);
        }
        return 0;
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }



//    public List<User> getAll() {
//        return userMapper.selectByExampleWithDept(null);
//    }

    public int saveUser(User user) {
        return userMapper.insertSelective(user);
    }

    /**
     * 检查当前用户名是否可用
     * true代表可用，false代表不可用
     * @param username
     * @return count
     */
    public boolean checkUser(String username) {
        long count=userMapper.countByUsername(username);
        return count==0;
    }

    /**
     * @param id
     * @return Employee
     */
    public User getUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public int deleteUser(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    public int deleteBatch(List<Integer> user_ids) {
        //delete from xxx where emp_id in(1,2,3)
        return userMapper.deleteBatch(user_ids);
    }
}
