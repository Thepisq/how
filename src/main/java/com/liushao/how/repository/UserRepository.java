package com.liushao.how.repository;

import com.liushao.how.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>,JpaSpecificationExecutor<User> {
    /**
     * 根据手机号查询用户
     *
     * @param mobilePhone
     * @return User
     */
    public User findByMobilePhone(String mobilePhone);
}