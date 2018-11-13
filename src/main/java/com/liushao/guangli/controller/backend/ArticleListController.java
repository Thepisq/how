package com.liushao.guangli.controller.backend;

import com.liushao.guangli.entity.ServerResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class ArticleListController {

    @RequestMapping("articleList/{id}")
    public ServerResponse articleList(Integer id){
        return null;
    }
}
