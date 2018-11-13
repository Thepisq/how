package com.liushao.guangli.mapper;

import com.liushao.guangli.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();

    List<User> findById(Integer id);

    int create(User user);

    int delete(Integer id);

    int update(User user);

    User findByName(String name);

    int insertSelective(User user);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);

    int insert(User user);

    int deleteByPrimaryKey(Integer id);

    User selectByPrimaryKey(Integer id);

    int countByUsername(String username);

    int deleteBatch(List<Integer> user_ids);
}
