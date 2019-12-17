package com.spring.demo.Service.Imp;

import com.spring.demo.DAO.BlogDao;
import com.spring.demo.Entity.BlogItem;
import com.spring.demo.Service.Interface.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;


@Service
@ComponentScan("com.spring.demo.DAO")
public class BlogServiceImp implements IBlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public boolean insertBlog(BlogItem item){
        try{
            int ret = blogDao.insertBlog(item);

            if(ret == 0){
                return false;
            }else{
                return true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }


    @Override
    public BlogItem[] getBlogByOwner(String owner){
        BlogItem[] ret;
        try {
            ret = blogDao.getBlogByOwner(owner);
        }catch (Exception ex){
            ex.printStackTrace();
            ret =null;
        }
        return ret;
    }

    @Override
    public BlogItem[] getBlogByType(String type){
        BlogItem[] ret;
        try{
            ret = blogDao.getBlogByType(type);
        }catch(Exception ex){
            ex.printStackTrace();
            ret = null;
        }
        return ret;
    }

    @Override
    public BlogItem[] getBlogByTypeAndOwner(String owner,String type){
        BlogItem[] ret;
        try{
            ret = blogDao.getBlogByTypeAndOwner(owner,type);
        }catch(Exception ex){
            ex.printStackTrace();
            ret = null;
        }

        return ret;
    }
}
