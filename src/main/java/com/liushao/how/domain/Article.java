package com.liushao.how.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

/**
 * 文章
 */
@Entity
@Data
public class Article implements Serializable {
    
    private static final long serialVersionUID = -7102972758096410686L;

    @Id
    @Column(length = 50)
    private String id;

    /**
     * 作者id
     */
    @Column(length = 50)
    private String userId;

    @Column(length = 30)
    private String articleTital;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String articleContent;

    /**
     * 浏览量
     */
    private int views;

    /**
     * 评论数
     */
    private int commentCount;

    /**
     * 点赞数
     */
    private int likeCount;

    /**
     * 激活状态
     * 0
     * 1
     */
    @Column(length = 2)
    private int active;

    private Date createDate;

    private Date updateDate;
}