package com.spring.demo.Control;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.spring.demo.Control.ResponseObj.loginResponseObj;
import com.spring.demo.Entity.BlogItem;
import com.spring.demo.Entity.User;
import com.spring.demo.Service.Interface.IBlogService;
import com.spring.demo.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
//@ComponentScan("com.spring.demo.Service")
@RestController
public class loginControl {

    @Autowired
    private IUserService service;

    @Autowired
    private IBlogService blogService;

    @PostMapping("/api/login")
    public loginResponseObj login(
            @RequestParam(value="userName",required = true)String userName,
            @RequestParam(value="passWord",required = true)String passWord,
            HttpSession session
    ){
        User user = service.login(userName,passWord);
        loginResponseObj obj = new loginResponseObj();
        if(user == null){
            obj.setLoginStatus("fail");
            obj.setLoginInfo("账号名或密码不存在");
        }else{
            if(user.getPassWord().equals(passWord)){
                obj.setLoginInfo("success");
                obj.setLoginStatus("success");

                //登入成功后，直接将数据库中的所有归属数据全部读出，并保存到session中
                //避免频繁的对数据库进行读写操作
                //如果后期需要进行分布式，保存接口统一的情况下，替换HttpSession为Redis
                session.setAttribute("userName",user.getAccount());
                List<BlogItem> items = new ArrayList<BlogItem>();
                items = blogService.getBlogByOwner(userName);
                session.setAttribute("blogList",items);
            }else{
                obj.setLoginInfo("密码错误");
                obj.setLoginStatus("fail");
            }
        }
        return obj;
    }
}
