package com.liushao.guangli.mapper;

import com.liushao.guangli.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 18-11-7.
 */

@Component
@Mapper
public interface ArticleMapper{
    List<Article> selectAll(@Param("orderby") String orderby);
    List<Article> selectAllTitle(@Param("orderby") String orderby);
    List<Article> selectByUserid(@Param("userid") Integer userid, @Param("orderby") String orderby);
    Article selectById(@Param("id") Integer id);
    int insert(Article article);
    int deleteById(Integer id);
}
