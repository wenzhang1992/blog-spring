package com.spring.demo.Control.ResponseObj;

public class submitResponseObj {

    private boolean isSubmitSuccess;

    private String submitInfo;

    public submitResponseObj(){

    }

    public submitResponseObj(boolean status,String info){
        this.isSubmitSuccess = status;

        this.submitInfo = info;
    }

    public boolean getSubmitSuccess(){
        return this.isSubmitSuccess;
    }

    public void setSubmitSuccess(boolean submitSuccess){
        this.isSubmitSuccess = submitSuccess;
    }

    public String getSubmitInfo(){
        return this.submitInfo;
    }

    public void setSubmitInfo(String submitInfo){
        this.submitInfo = submitInfo;
    }

}
