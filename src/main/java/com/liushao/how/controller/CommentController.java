package com.liushao.how.controller;

import com.liushao.how.domain.Comment;
import com.liushao.how.entity.PageResult;
import com.liushao.how.entity.Result;
import com.liushao.how.entity.StatusCode;
import com.liushao.how.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Comment comment){
        //TO DO 需要给定userID
        commentService.add(comment, "userId");
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id){
        commentService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @RequestMapping(value = "/{articleId}/{page}/{size}", method = RequestMethod.GET)
    public Result findParentComment(@PathVariable String articleId,@PathVariable int page,@PathVariable int size){
        Page<Comment> pageList = commentService.findParentComment(articleId,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Comment>(pageList.getTotalElements(),pageList.getContent()));
    }

    @RequestMapping(value = "/{parentCommentId}/{page}/{size}", method = RequestMethod.GET)
    public Result findComment(@PathVariable String parentCommentId,@PathVariable int page,@PathVariable int size){
        Page<Comment> pageList = commentService.findComment(parentCommentId,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Comment>(pageList.getTotalElements(),pageList.getContent()));
    }
}