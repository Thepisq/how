package com.liushao.how.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Entity
@Data
public class Article {
    @Id
    private long articleId;

    /**
     * 作者id
     */
    private long userId;

    @Column(length = 30)
    private String articleTital;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String articleContent;

    @Column(length = 20)
    private int views;

    @Column(length = 20)
    private int commentCount;

    /**
     * 点赞数
     */
    @Column(length = 20)
    private int likeCount;

    private Date createtime;

    private Date updateTime;
}