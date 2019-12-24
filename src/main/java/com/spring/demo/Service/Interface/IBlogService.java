package com.spring.demo.Service.Interface;

import com.spring.demo.Entity.BlogItem;

import java.util.List;

public interface IBlogService {

    /*
    插入一条数据
     */
    public boolean insertBlog(BlogItem item);

    /*
    读取全部作者BLOG
     */
    public List<BlogItem> getBlogByOwner(String owner);

    /*
    读取全部的类型BLOG
     */
    public List<BlogItem> getBlogByType(String type);

    /*
    根据作者和类型进行数据读取
     */
    public List<BlogItem> getBlogByTypeAndOwner(String owner,String type);

    /*
    根据作者和链接进行数据读取
     */
    public List<BlogItem> getBlogByOwnerAndLink(String owner,String link);
}
