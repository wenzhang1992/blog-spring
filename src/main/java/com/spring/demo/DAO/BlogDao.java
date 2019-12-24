package com.spring.demo.DAO;

import com.spring.demo.Entity.BlogItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {
    public List<BlogItem> getBlogByOwner(String owner);

    public List<BlogItem> getBlogByType(String type);

    //mybatis多参数传参，使用Param注解
    public List<BlogItem> getBlogByTypeAndOwner(@Param("owner") String owner, @Param("type") String type);

    //mybatis多参数传参，使用Param注解
    public List<BlogItem> getBlogByOwnerAndTitle(@Param("owner") String owner,@Param("title") String title);

    public List<BlogItem> getBlogByOwnerAndLink(@Param("owner")String owner,@Param("link")String link);

    public List<BlogItem> getBlogByLink(String link);

    public List<BlogItem> getAllBlog();

    public List<BlogItem> getBlogByTitle(String title);

    public int updateBlog(BlogItem item);

    public int insertBlog(BlogItem item);

    public int deleteBlogByTitle(String title);

    public int deleteBlogByOwner(String owner);

    public int deleteBlogByType(String type);

}
