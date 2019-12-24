package com.spring.demo.Control.ResponseObj;

public class editResponseObj {

    private byte[] content;

    private String info;

    public editResponseObj(){

    }

    public editResponseObj(byte[] content,String info){
        this.content = content;

        this.info = info;
    }

    public byte[] getContent(){
        return this.content;
    }

    public void setContent(byte[] content){
        this.content = content;
    }

    public String getInfo(){
        return this.info;
    }

    public void setInfo(String info){
        this.info = info;
    }

}
