package com.liushao.how.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * 文章分类
 */
@Entity
@Data
public class ArticleCategory implements Serializable{

    private static final long serialVersionUID = -8305614844813576981L;

    @Id
    private String id;

    @Column(length = 50)
    private String articleId;

    @Column(length = 50)
    private String categoryId;
}