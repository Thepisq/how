package com.liushao.guangli.controller.portal;

import com.liushao.guangli.entity.Article;
import com.liushao.guangli.entity.ServerResponse;
import com.liushao.guangli.service.ArticleService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.net.URLEncoder;

/**
 * Created by Administrator on 18-11-7.
 */
//@RestController
@Controller
@RequestMapping(value = "/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ServerResponse addArticle(Article article,HttpServletRequest request){

        //获取用户id
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        int userId = 1;
        try{
            userId = (int) attributes.getRequest().getSession().getAttribute("userid");
        }catch (Exception e){
            e.printStackTrace();
        }
        article.setUserID(userId);
        String ctx = article.getContext();
        try {
            ctx = URLEncoder.encode(ctx,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ctx = ctx.replaceAll("\\+","%20");
        article.setContext(ctx); System.out.println("ctx: "+ctx);

        if(articleService.addArticle(article) > 0) {
            return ServerResponse.createBySuccessMessage("发布成功");
        }else{
            return ServerResponse.createByErrorMessage("发布失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ServerResponse allArticleTitle(@RequestParam(value = "orderby",defaultValue = "hot desc") String orderby,HttpServletRequest request){
        List<Article> articleList = articleService.listAllArticleTitle(orderby);
        return ServerResponse.createBySuccess("success",articleList);
    }

    @ResponseBody
    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    public ServerResponse delArticle(int id){
        if(articleService.deleteArticle(id) >0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }else{
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String getArticle(@PathVariable("id") Integer id,Model model){
        Article article = articleService.getArticleById(id);
        System.out.println("----------读取到新请求----------");
        System.out.println("-------------内容为-------------");
        System.out.println("标题:   "+article.getTitle());
        System.out.println("hot :   "+article.getHot());
        System.out.println("赞  :   "+article.getLikeCount());
        System.out.println("作者:   "+article.getUserID());
        System.out.println("内容:   "+article.getContext());
        System.out.println("------------读取完毕------------");
        model.addAttribute(article);
        return "article_main";
    }
}
