package com.spring.demo.Control;


import com.spring.demo.Entity.BlogItem;
import com.spring.demo.Service.Interface.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class blogControl {

    @Autowired
    private IBlogService service;

    @GetMapping("/api/getListByOwner")
    @ResponseBody
    public BlogItem[] getListByOwner(
            @RequestParam(value="Owner",required = true)String owner,
            @RequestParam(value="type",required = true)String type
    ){
        try{
            BlogItem[] ret = service.getBlogByTypeAndOwner(owner,type);
            return ret;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
