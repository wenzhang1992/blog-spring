package com.spring.demo.DAO;

import com.spring.demo.Entity.BlogItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogDao {
    public BlogItem[] getBlogByOwner(String owner);

    public BlogItem[] getBlogByType(String type);

    //mybatis多参数传参，使用Param注解
    public BlogItem[] getBlogByTypeAndOwner(@Param("owner") String owner, @Param("type") String type);

    //mybatis多参数传参，使用Param注解
    public BlogItem[] getBlogByOwnerAndTitle(@Param("owner") String owner,@Param("title") String title);

    public BlogItem[] getAllBlog();

    public BlogItem getBlogByTitle(String title);

    public int insertBlog(BlogItem item);

    public int deleteBlogByTitle(String title);

    public int deleteBlogByOwner(String owner);

    public int deleteBlogByType(String type);

}
