package com.spring.demo.Service.Interface;

import com.spring.demo.Entity.BlogItem;

public interface IBlogService {

    /*
    插入一条数据
     */
    public boolean insertBlog(BlogItem item);

    /*
    读取全部作者BLOG
     */
    public BlogItem[] getBlogByOwner(String owner);

    /*
    读取全部的类型BLOG
     */
    public BlogItem[] getBlogByType(String type);

    /*
    根据作者和类型进行数据读取
     */
    public BlogItem[] getBlogByTypeAndOwner(String owner,String type);


}
