package com.liushao.how.repository;

import java.util.List;

import com.liushao.how.domain.Article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String>,JpaSpecificationExecutor<Article> {
    
}