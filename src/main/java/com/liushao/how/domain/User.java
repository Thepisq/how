package com.liushao.how.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * 用户，暂时为管理员
 */
@Entity
@Data
public class User implements Serializable{

    private static final long serialVersionUID = -5672299093275279481L;

    @Id
    @Column(length = 50)
    private String id;

    @Column(length = 20)
    private String ip;

    @Column(length = 30)
    private String username;

    @Column(length = 20)
    private String password;

    /**
     * M:F
     */
    @Column(length = 5)
    private String sex;//性别

    @Column(length = 30)
    private String email;

    private String avatar;

    private Date regtime;

    private Date birthday;

    @Column(length = 20)
    private String mobilePhone;

    @Column(length = 30)
    private String nickname;
}