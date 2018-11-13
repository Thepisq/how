package com.liushao.guangli.service.imp;

import com.liushao.guangli.entity.Article;
import com.liushao.guangli.mapper.ArticleMapper;
import com.liushao.guangli.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 18-11-8.
 */
@Service
public class ArticleServiceImp implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    public List<Article> listArticleByUserid(int userid,String orderby){
        return articleMapper.selectByUserid(userid, orderby);
    }

    public List<Article> listAllArticleTitle(String orderby){
        return articleMapper.selectAllTitle(orderby);
    }

    public Article getArticleById(Integer id){
        return articleMapper.selectById(id);
    }

    public int addArticle(Article article){
        return articleMapper.insert(article);
    }

    public int deleteArticle(int id){
        return articleMapper.deleteById(id);
    }
}
