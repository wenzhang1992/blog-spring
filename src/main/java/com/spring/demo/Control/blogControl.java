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
                    link = item.getLink();
                }
            }
            if(isExits){
                byte[] content = contentService.BlogContentRead("classpath:/upload/"+link);
                obj.setContent(content);
                obj.setInfo("读取数据成功");
            }else{
                obj.setInfo("没有选择的文件");
                obj.setContent(null);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            obj.setInfo("数据读取失败");
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
            //obj.setSubmitSuccess(true);
            isSuccess = false;
            if(items.size() == 0){
                BlogItem item = new BlogItem();
                item.setLink(requestData.getLink());
                item.setDescription(requestData.getDescription());
                item.setType(requestData.getType());
                item.setOwner((String)session.getAttribute("userName"));

                items.add(item);
            }
            //将数据更新到session中，并同步更新到数据库中
            for(int i =0;i<items.size();i++){
                BlogItem item = items.get(i);
                if(item.getId()==requestData.getId()){
                    item.setLink(link);
                    if(service.updateById(item) != 0){
                        isSuccess = true;
                    }
                }
            }

            obj.setSubmitSuccess(true);
        }

        return obj;
    }
}
