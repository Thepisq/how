package com.liushao.guangli.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页控制器
 *
 * @auther huangshen
 */
@Controller
public class HomeController {


    @GetMapping(value = "/test")
    public String test() {
        return "backend_login";
    }

    /**
     * 后台首页
     *
     * @return
     */
    @GetMapping(value = "/manage")
    public String index() {
        return "backend_index";
    }

    /**
     * 前台首页
     *
     * @return
     */

    @GetMapping(value = {"/editor"})
    public String editor(){
        return "article_editor";
    }
    @GetMapping(value = {"/","/index"})
    public String ind(){
        return "major";
    }

}
