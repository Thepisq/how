package com.liushao.how.service;

import com.liushao.how.domain.User;
import com.liushao.how.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByMobilePhone(String mobilePhone){
        return userRepository.findByMobilePhone(mobilePhone);
    }
}