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

@Controller
@ComponentScan("com.spring.demo.Service")
public class loginControl {

    @Autowired
    private IUserService service;

    @RequestMapping("/login")
    @ResponseBody
    public void login(
            @RequestParam(value="userName",required = true)String userName,
            @RequestParam(value="passWord",required = true)String passWord
    ){
        User user = service.login(userName,passWord);
        loginResponseObj obj = new loginResponseObj();
        if(user.equals(null)==true){
            obj.setLoginStatus("fail");
            obj.setLoginInfo("账号名或密码不存在");
        }else{
            if(user.getPassWord() == passWord){
                obj.setLoginInfo("success");
                obj.setLoginStatus("success");
            }else{
                obj.setLoginInfo("密码错误");
                obj.setLoginStatus("fail");
            }
        }
    }
}
