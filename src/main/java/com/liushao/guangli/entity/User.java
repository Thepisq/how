package com.liushao.guangli.entity;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer id; //编号

    @Pattern(regexp = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5}$)",message = "用户名必须是6-16位英文和数字或者2-5位中文的组合")
    private String username; //用户名

    private String password; //密码
    @Pattern(regexp = "(^1\\d{10}$)")
    private String tel;//电话
    private Date registerDate;//创建时间

    //查询用户的同时，查询用户拥有的文章数
    private Integer articleCount;

    public User(){

    }

    public User(Integer id, String username, String password, String tel, Date registerDate, Integer articleCount) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.registerDate = registerDate;
        this.articleCount = articleCount;
    }

    public User(Integer id, String username, String password, String tel, Date registerDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.registerDate = registerDate;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
