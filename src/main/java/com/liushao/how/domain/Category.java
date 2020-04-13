package com.liushao.how.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * 分类
 */
@Entity
@Data
public class Category implements Serializable{

    private static final long serialVersionUID = 2499238782914991517L;

    @Id
    @Column(length = 50)
    private String id;

    @Column(length = 50)
    private String categoryName;

    private String description;

    @Column(length = 50)
    private String parentCategoryId;
}