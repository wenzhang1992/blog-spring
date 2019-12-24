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

                int size = stream.available();

                content = new byte[size];

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
    public boolean BlogContentWrite(String filePath,String content){
        File file = new File(filePath);

        try{
            if(file.exists() == false){
                file.createNewFile();
            }

            FileOutputStream stream = new FileOutputStream(file);
            stream.write(content.getBytes());

            stream.flush();
            stream.close();

            return true;
        }catch(Exception ex){
            ex.printStackTrace();

        }

        return false;
    }

}
