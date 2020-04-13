package com.liushao.how.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * 文章标签
 */
@Entity
@Data
public class ArticleLabel implements Serializable{

    private static final long serialVersionUID = 4671189495431941200L;

    @Id
    private String id;
    
    @Column(length = 50)
    private String articleId;

    @Column(length = 50)
    private String lebelId;
}