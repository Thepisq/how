package com.liushao.guangli.service;

import com.liushao.guangli.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 18-11-8.
 */
public interface ArticleService {
    public int addArticle(Article article);
    public int deleteArticle(int id);
    public List<Article> listArticleByUserid(int userid, String orderby);
    public List<Article> listAllArticleTitle(String orderby);
    public Article getArticleById(Integer id);
}
