package com.spring.demo.DAO;

import com.spring.demo.Entity.BlogItem;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogDao {

    public BlogItem[] getBlogByOwner(String owner);

    public BlogItem[] getBlogByType(String type);

    public BlogItem[] getBlogByTypeAndOwner(String owner,String type);

    public BlogItem[] getBlogByOwnerAndTitle(String owner,String title);

    public BlogItem[] getAllBlog();

    public BlogItem getBlogByTitle(String title);

    public int insertBlog(BlogItem item);

    public int deleteBlogByTitle(String title);

    public int deleteBlogByOwner(String owner);

    public int deleteBlogByType(String type);

}
