package com.liushao.guangli.controller.backend;

import com.liushao.guangli.entity.AdminUser;
import com.liushao.guangli.entity.ServerResponse;
import com.liushao.guangli.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@RequestMapping("/manage")
public class BackendLoginController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String login(String username, String password) {
        System.out.println("username:" + username + ", password:" + password);
        AdminUser adminUser = adminService.findByName(username);
        if (adminUser != null) {
            if (adminUser.getPassword().equals(password)) {
//                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//                attributes.getRequest().getSession().setAttribute("adminUser", adminUser); //将登陆用户信息存入到session域对象中
                //return ServerResponse.createBySuccess("登录成功",adminUser.getUsername());
                return "backend_index";
            }
        }
        return "";
    }

    /**
     * 注销
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        attributes.getRequest().getSession().removeAttribute("adminUser");
        return "backend_login";
    }
}
