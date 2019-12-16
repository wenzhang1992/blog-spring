package com.spring.demo.Service.Imp;

import com.spring.demo.DAO.UserDao;
import com.spring.demo.Entity.User;
import com.spring.demo.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("com.spring.demo.DAO")
public class UserServiceImp implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String userName,String passWord){

        try{
            User user = userDao.getUserByAccount(userName);

            return user;
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean register(String userName,String passWord,int level){
        return true;
    }
}
