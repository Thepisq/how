package com.liushao.how.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User {
    @Id
    private long userId;

    @Column(length = 20)
    private String userIp;

    @Column(length = 30)
    private String username;

    @Column(length = 20)
    private String passworld;

    @Column(length = 30)
    private String userEmail;

    private String avatar;

    private Date regtime;

    private Date birthday;

    @Column(length = 20)
    private String mobilePhone;

    @Column(length = 30)
    private String nickname;
}