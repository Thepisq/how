package com.liushao.how.service;

import com.liushao.how.domain.Article;
import com.liushao.how.domain.Comment;
import com.liushao.how.repository.ArticleRepository;
import com.liushao.how.repository.CommentRepository;
import com.liushao.how.util.IdWorker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private IdWorker idWorker;

    /**
     * 新增评论
     * @param comment 评论
     * @param articleId 文章id
     * @param parentCommentId 父评论id
     */
    public void add(Comment comment,String userId){
        comment.setId(idWorker.nextId()+"");
        comment.setUserId(userId);
        comment.setActive(1);
        //设置文章评论数增加
        if(!comment.getArticleId().isEmpty()){
            Article article = articleRepository.findById(comment.getArticleId()).get();
            article.setCommentCount(article.getCommentCount()+1);
            articleRepository.save(article);
        }
        //设置父评论回复数增加
        if(!comment.getParentCommentId().isEmpty()){
            Comment parentComment = commentRepository.findById(comment.getParentCommentId()).get();
            parentComment.setReplyCount(parentComment.getReplyCount()+1);
            commentRepository.save(parentComment);
        }
        commentRepository.save(comment);
    }

    /**
     * 删除评论。只是吧active字段设置为0，并不真的删除数据
     * @param id 评论id
     */
    public void delete(String id){
        Comment comment = commentRepository.findById(id).get();
        comment.setActive(0);
        //设置文章和父评论回复数量-1
        if(!comment.getArticleId().isEmpty()){
            Article article = articleRepository.findById(comment.getArticleId()).get();
            article.setCommentCount(article.getCommentCount()-1);
            articleRepository.save(article);
        }
        if(!comment.getParentCommentId().isEmpty()){
            //设置父评论回复数增加
            Comment parentComment = commentRepository.findById(comment.getParentCommentId()).get();
            parentComment.setReplyCount(parentComment.getReplyCount()-1);
            commentRepository.save(parentComment);
        }
        commentRepository.save(comment);
    }

    /**
     * 分页查询父评论
     * @param articleId 文章id
     * @param page 页码
     * @param size 每页大小
     * @return 分页数据
     */
    public Page<Comment> findParentComment(String articleId, int page, int size){
		PageRequest pageRequest =  PageRequest.of(page-1, size);
        return commentRepository.findAllByArticleIdAndParentCommentIdAndActive(articleId, null, 1, pageRequest);
    }

    /**
     * 分页查询子评论
     * @param parentCommentId 父评论id
     * @param page 页码
     * @param size 每页大小
     * @return 分页数据
     */
    public Page<Comment> findComment(String parentCommentId, int page, int size){
		PageRequest pageRequest =  PageRequest.of(page-1, size);
        return commentRepository.findAllByParentCommentIdAndActive(parentCommentId, 1, pageRequest);
    }
}