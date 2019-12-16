package com.spring.demo.Entity;


import org.apache.ibatis.type.Alias;

@Alias(value = "user")
public class User {

    private Long id;

    private String account;

    private String passWord;

    private int level;

    public User(){

    }

    public void setId(Long id){
        this.id= id;
    }

    public Long getId(){
        return this.id;
    }

    public void setAccount(String account){
        this.account = account;
    }

    public String getAccount(){
        return this.account;
    }

    public void setPassWord(String passWord){
        this.passWord = passWord;
    }

    public String getPassWord(){
        return this.passWord;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getLevel(){
        return this.level;
    }
}
