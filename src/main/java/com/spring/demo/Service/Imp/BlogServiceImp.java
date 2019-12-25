package com.spring.demo.Service.Imp;

import com.spring.demo.DAO.BlogDao;
import com.spring.demo.Entity.BlogItem;
import com.spring.demo.Service.Interface.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


@Service
@ComponentScan("com.spring.demo.DAO")
public class BlogServiceImp implements IBlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public int updateById(BlogItem item){

        ArrayList<BlogItem> items = (ArrayList<BlogItem>) blogDao.getBlogById(item);

        if(items.size() == 0){
            int ret = blogDao.insertBlog(item);
            return ret;
        }else{
            int ret = blogDao.updateBlog(item);
            return ret;
        }
    }

    @Override
    public List<BlogItem> getBlogByOwner(String owner){
        List<BlogItem> ret;
        try {
            ret = blogDao.getBlogByOwner(owner);
        }catch (Exception ex){
            ex.printStackTrace();
            ret =null;
        }
        return ret;
    }

    @Override
    public List<BlogItem> getBlogByType(String type){
        List<BlogItem> ret;
        try{
            ret = blogDao.getBlogByType(type);
        }catch(Exception ex){
            ex.printStackTrace();
            ret = null;
        }
        return ret;
    }

    @Override
    public List<BlogItem> getBlogByTypeAndOwner(String owner,String type){
        List<BlogItem> ret;
        try{
            ret = blogDao.getBlogByTypeAndOwner(owner,type);
        }catch(Exception ex){
            ex.printStackTrace();
            ret = null;
        }

        return ret;
    }

    @Override
    public List<BlogItem> getBlogByOwnerAndLink(String owner,String link){
        List<BlogItem> ret;
        try{
            ret = blogDao.getBlogByOwnerAndLink(owner,link);
        }catch (Exception ex){
            ex.printStackTrace();
            ret = null;
        }

        return ret;
    }
}
