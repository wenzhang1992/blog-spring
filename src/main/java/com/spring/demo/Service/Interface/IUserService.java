package com.spring.demo.Service.Interface;

import com.spring.demo.Entity.User;

public interface IUserService {

    public User login(String userName, String passWord);

    public boolean register(String userName,String passWord,int level);

}
