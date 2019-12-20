package com.spring.demo.Service.Interface;

public interface IBlogContentService {

    public byte[] BlogContentRead(String filePath);

    public void BlogContentWrite(String filePath,byte[] content);
}
