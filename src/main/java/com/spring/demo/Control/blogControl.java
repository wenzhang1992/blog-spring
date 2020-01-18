package com.spring.demo.Control;


import com.spring.demo.Control.RequestObj.blogRequestObj;
import com.spring.demo.Control.ResponseObj.editResponseObj;
import com.spring.demo.Control.ResponseObj.submitResponseObj;
import com.spring.demo.Entity.BlogItem;
import com.spring.demo.Service.Interface.IBlogContentService;
import com.spring.demo.Service.Interface.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RestController
public class blogControl {

    @Autowired
    private IBlogService service;

    @GetMapping("/api/getListByOwner")
    @ResponseBody
    public ArrayList<BlogItem> getListByOwner(
            @RequestParam(value="Owner",required = true)String owner,
            @RequestParam(value="type",required = true)String type,
            HttpSession session
    ){
        try{
            ArrayList<BlogItem> items = (ArrayList<BlogItem> ) session.getAttribute("blogList");

            for(int i=0;i<items.size();i++){
                BlogItem item = items.get(i);
                if(item.getType().equals(type) == false){
                    items.remove(i);
                }
            }
            return items;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Autowired
    private IBlogContentService contentService;

    @Value("${blog.fileupload.path}")
    private String filePath;

    @GetMapping("/api/edit/get")
    @ResponseBody
    public editResponseObj getBlogContent(
            @RequestParam(value="id",required = true)int id,
            HttpSession session
    ){
        editResponseObj obj = new editResponseObj();
        try {
            //id值为-1，代表直接进行编辑操作
            if(id == -1){
                return obj;
            }

            ArrayList<BlogItem> items = (ArrayList<BlogItem>) session.getAttribute("blogList");
            boolean isExits = false;
            String link = null;
            for(int i=0 ;i<items.size();i++){
                BlogItem item = items.get(i);
                if(item.getId() == id){
                    isExits = true;
                    obj.setItem(item);
                }
            }

            if(isExits){
                byte[] content = contentService.BlogContentRead(filePath+obj.getItem().getLink());
                obj.setContent(content);
            }else{
                obj.setContent(null);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            obj.setContent(null);
        }
        return obj;
    }

    @PostMapping("/api/edit/put")
    @ResponseBody
    public submitResponseObj submitBlogContent(
            @RequestBody blogRequestObj requestData,
            HttpSession session
    ){

        String link = requestData.getLink();

        String content = requestData.getContent();

        submitResponseObj obj = new submitResponseObj();

        ArrayList<BlogItem> items = (ArrayList<BlogItem>) session.getAttribute("blogList");

        link = filePath+link;

        boolean isSuccess = contentService.BlogContentWrite(link,content);

        if(isSuccess == false){
            obj.setSubmitSuccess(false);
            obj.setSubmitInfo("文件写入失败");
        }else{
            if(items.size() == 0){
                BlogItem item = new BlogItem();
                item.setId(requestData.getId());
                item.setTitle(requestData.getTitle());
                item.setDescription(requestData.getDescription());
                item.setOwner((String) session.getAttribute("userName"));
                item.setType(requestData.getType());
                item.setLink(requestData.getLink());

                if(service.updateById(item) != 0 ){
                    isSuccess = true;
                }
            }else{
                for(int i =0;i<items.size();i++){
                    BlogItem item = items.get(i);
                    if(item.getId()==requestData.getId()){
                        item.setLink(link);
                        item.setType(requestData.getType());
                        item.setDescription(requestData.getDescription());
                        item.setTitle(requestData.getTitle());
                        if(service.updateById(item) != 0){
                            isSuccess = true;
                        }
                    }
                }
            }

            if(isSuccess == false){
                obj.setSubmitSuccess(false);
                obj.setSubmitInfo("服务器故障");
            }else{
                obj.setSubmitSuccess(true);
            }

        }

        return obj;
    }
}
