package com.spring.demo.Control.ResponseObj;

public class loginResponseObj {

    private String loginStatus;

    private String loginInfo;

    public loginResponseObj(){

    }

    public loginResponseObj(String status,String info){
        this.loginStatus = status;

        this.loginInfo = info;
    }

    public void setLoginStatus(String status){
        this.loginStatus = status;
    }

    public void setLoginInfo(String info){
        this.loginInfo = info;
    }

    public String getLoginStatus(){
        return this.loginStatus;
    }

    public String getLoginInfo(){
        return this.loginInfo;
    }

}
