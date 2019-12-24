package com.spring.demo.Service.Interface;

public interface IBlogContentService {

    public byte[] BlogContentRead(String filePath);

    public boolean BlogContentWrite(String filePath,String content);
}
