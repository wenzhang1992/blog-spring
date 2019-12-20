package com.spring.demo.Service.Imp;

import com.spring.demo.Service.Interface.IBlogContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

@Service
public class BlogContentServiceImp implements IBlogContentService {

    @Override
    public byte[] BlogContentRead(String path){
        File file = new File(path);
        if(file.exists() == true){
            try{
                byte[] content = null;

                FileInputStream stream = new FileInputStream(file);

                stream.read(content);

                return content;
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }else{
            return null;
        }

        return null;
    }

    @Override
    public void BlogContentWrite(String filePath,byte[] content){
        File file = new File(filePath);
        try{
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(content);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
