package com.spring.demo.Entity;


import org.apache.ibatis.type.Alias;

@Alias(value = "blogItem")
public class BlogItem {

    private String link;

    private String title;

    private String description;

    private String owner;

    private String type;

    private int id;

    public BlogItem(){

    }

    public void setId(int id){this.id = id;}

    public int getId(){return this.id;}

    public void setLink(String data){
        this.link = data;
    }

    public String getLink(){
        return this.link;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public void setOwner(String owner){
        this.owner = owner;
    }

    public String getOwner(){
        return this.owner;
    }

    public void setType(String type){
        this.type=type;
    }

    public String getType(){
        return this.type;
    }

}
