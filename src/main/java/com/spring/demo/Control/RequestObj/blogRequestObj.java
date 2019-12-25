package com.spring.demo.Control.RequestObj;

public class blogRequestObj {

    private String link;

    private String title;

    private String type;

    private String description;

    private String content;

    private int id;

    public blogRequestObj(){

    }

    public blogRequestObj(String link){
        this.link = link;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setLink(String link){
        this.link = link;
    }

    public void setContent(String content){
        this.content=content;
    }

    public String getLink(){
        return this.link;
    }

    public String getContent(){
        return this.content;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
