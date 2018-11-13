package com.liushao.guangli.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liushao.guangli.entity.ServerResponse;
import com.liushao.guangli.entity.User;
import com.liushao.guangli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 单个批量二合一
     * 批量1-2-3
     * 单个1
     * @param ids
     * @return Msg
     */
    @RequestMapping(value = "/user/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public ServerResponse deleteUserById(@PathVariable("ids") String ids){
        if (ids.contains("-")){
            //批量删除
            List<Integer> user_ids=new ArrayList<Integer>();
            String[] str_ids = ids.split("-");
            for(String string:str_ids){
                user_ids.add(Integer.parseInt(string));
            }
            if (userService.deleteBatch(user_ids)>0){
                return ServerResponse.createBySuccessMessage("删除成功");
            }else {
                return ServerResponse.createByErrorMessage("删除失败");
            }
        }else {
            //单个删除
            Integer id=Integer.parseInt(ids);
            if (userService.deleteUser(id)>0){
                return ServerResponse.createBySuccessMessage("删除成功");
            }else {
                return ServerResponse.createByErrorMessage("删除失败");
            }
        }
    }


    @RequestMapping(value = "/user/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ServerResponse saveUser(User user){
        if (userService.updateUser(user)>0){
            return ServerResponse.createBySuccess("保存成功");
        }else {
            return ServerResponse.createByErrorMessage("保存失败");
        }
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getUser(@PathVariable Integer id){
        User user=userService.getUser(id);
        return ServerResponse.createBySuccess("获取成功",user);
    }

    @RequestMapping("/checkuser")
    @ResponseBody
    public ServerResponse checkUser(String username){
        //先判断用户名是否是合法的表达式
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5}$)";
        if (!(username.matches(regx))){
            return ServerResponse.createByErrorMessage("用户名可以是6-16位英文和数字或者2-5位中文的组合");
        }
        //数据库用户名重复校验
        boolean b=userService.checkUser(username);
        if (b){
            return ServerResponse.createBySuccess();
        }else {
            return ServerResponse.createByErrorMessage("用户名不可用");
        }
    }


    /**
     * 保存员工
     * @param user result
     * @return Msg.success
     */
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse saveUser(@Valid User user, BindingResult result){
        if (result.hasErrors()){
            //校验失败，应该返回失败，在模态框中显示失败信息
            Map<String,Object> map=new HashMap<String, Object>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError field :
                    fieldErrors) {
//                System.out.println("错误字段：" + field.getField());
//                System.out.println("错误信息：" + field.getDefaultMessage());
                map.put(field.getField(),field.getDefaultMessage());
            }
            return ServerResponse.createByErrorMessage("errorFields");
        }else {
            userService.saveUser(user);
            return ServerResponse.createBySuccess("保存成功");
        }
    }


    @RequestMapping("/users")
    @ResponseBody
    public ServerResponse getUsersWithArticleCount(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        //这不是分页查询
        //引入pageHelper插件
        //在查询之前只需要调用，传入页码和每一页的大小
        PageHelper.startPage(pn,5);
        //startPage紧跟的查询就是一个分页查询
        List<User> users=userService.findAll();
        //使用pageInfo包装查询的结果，只需要将pageInfo交给页面就好了
        //封装了详细的分页信息，包括我们查询出来的数据，传入连续显示的页数
        PageInfo<User> page=new PageInfo<User>(users,5);
        return ServerResponse.createBySuccess(page);
    }

    //@RequestMapping("/emps")
//    public String getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
//        //这不是分页查询
//        //引入pageHelper插件
//        //在查询之前只需要调用，传入页码和每一页的大小
//        PageHelper.startPage(pn,5);
//        //startPage紧跟的查询就是一个分页查询
//        List<Employee> emps=employeeService.getAll();
//        //使用pageInfo包装查询的结果，只需要将pageInfo交给页面就好了
//        //封装了详细的分页信息，包括我们查询出来的数据，传入连续显示的页数
//        PageInfo<Employee> page=new PageInfo<Employee>(emps,5);
//        model.addAttribute("pageInfo",page);
//        return "list";
//    }
}
