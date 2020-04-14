package com.liushao.how.repository;

import com.liushao.how.domain.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String>, JpaSpecificationExecutor<Comment> {
    Page<Comment> findAllByArticleIdAndParentCommentIdAndActive(String articleId,String parentCommentId,int active,Pageable pageable);
    Page<Comment> findAllByParentCommentIdAndActive(String parentCommentId,int active,Pageable pageable);
}