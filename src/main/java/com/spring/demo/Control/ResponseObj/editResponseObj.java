package com.spring.demo.Control.ResponseObj;

import com.spring.demo.Entity.BlogItem;

public class editResponseObj {

    private String content;

    private BlogItem item;

    private String info;

    public editResponseObj(){

    }

    public editResponseObj(String content){
        this.content = content;
    }

    public String getContent(){
        return this.content;
    }

    public void setContent(byte[] content){
        this.content = new String(content);
    }

    public BlogItem getItem(){
        return this.item;
    }

    public void setItem(BlogItem item){
        this.item=item;
    }

    public String getInfo(){
        return  this.info;
    }

    public void setInfo(String info){
        this.info = info;
    }
}
