package com.spring.demo.Control;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.spring.demo.Control.ResponseObj.loginResponseObj;
import com.spring.demo.Entity.User;
import com.spring.demo.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@ComponentScan("com.spring.demo.Service")
@RestController
public class loginControl {

    @Autowired
    private IUserService service;

    @RequestMapping("/login")
    public loginResponseObj login(
            @RequestParam(value="userName",required = true)String userName,
            @RequestParam(value="passWord",required = true)String passWord
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
            }else{
                obj.setLoginInfo("密码错误");
                obj.setLoginStatus("fail");
            }
        }
        return obj;
    }
}
