package com.liushao.guangli.entity;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    private Integer id;
    private String context;
    private Integer userID;
    private Integer likeCount;
    private Integer hot;
    private String type;
    private String title;
    private Date createDate;
    private Integer commentCount;

    public Article(Integer id, String context, Integer userID, Integer likeCount, Integer hot, String type, String title, Date createDate, Integer commentCount) {
        this.id = id;
        this.context = context;
        this.userID = userID;
        this.likeCount = likeCount;
        this.hot = hot;
        this.type = type;
        this.title = title;
        this.createDate = createDate;
        this.commentCount = commentCount;
    }

    public Article() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}
