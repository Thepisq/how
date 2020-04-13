package com.liushao.how.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * 标签
 */
@Entity
@Data
public class Label implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 50)
    private String id;

    @Column(length = 50)
    private String labelName;

    private String description;
}