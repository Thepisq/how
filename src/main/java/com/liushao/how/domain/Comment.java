package com.liushao.how.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * 评论
 */
@Entity
@Data
public class Comment implements Serializable{
    
    private static final long serialVersionUID = 121869068929769947L;

    @Id
    @Column(length = 50)
    private String id;

    @Column(length = 50)
    private String userId;

    @Column(length = 50)
    private String articleId;

    @Column(length = 50)
    private String parentCommentId;

    private int likeCount;

    private int replyCount;

    private Date commentDate;

    private String commentContent;

    /**
     * 激活状态
     * 0
     * 1
     */
    @Column(length = 2)
    private int active;
}