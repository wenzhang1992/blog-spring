package com.spring.demo.DAO;

import com.spring.demo.Entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    public User[] getAllUser();

    public User getUserByAccount(String account);

    public User[] getUserByPassword(String password);

    public User[] getUserByLevel(int level);

    public int insertUser(User item);

    public int deleteUserByAccount(String account);

    public int deleteUserByPassword(String password);

    public int deleteUserByLevel(int level);

}
