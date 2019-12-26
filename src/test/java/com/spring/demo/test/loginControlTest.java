package com.spring.demo.test;


import com.spring.demo.Control.RequestObj.blogRequestObj;
import com.spring.demo.Control.loginControl;
import com.spring.demo.DemoApplication;
import com.spring.demo.Entity.BlogItem;
import com.spring.demo.Service.Imp.BlogServiceImp;
import com.spring.demo.Service.Interface.IBlogService;
import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class loginControlTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    /*
    MOCK单元测试
        测试loginControl控制器方法
        访问方法:POST
     */
    @Test
    public void testControl()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .param("userName","admin")
        .param("passWord","199203"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Autowired
    private IBlogService service;
    @Test
    public void testBlogService() throws Exception{
        BlogItem item = new BlogItem();
        item.setLink(new String("测试数据".getBytes(),"UTF-8"));
        item.setTitle(new String("测试数据".getBytes(),"UTF-8"));
        item.setDescription(new String("测试数据".getBytes(),"UTF-8"));
        item.setOwner("admin");
        item.setType("cpp");
        item.setId(2);

        int ret = service.updateById(item);
        Assert.assertEquals(1,ret);
    }

    /*
    MOCK单元测试
        测试blogControl控制器类的getBlogContent方法
        访问方法:GET
     */
    @Autowired
    MockHttpSession blogContentSession;

    @Test
    public void testBlogContent() throws Exception{

        //构造HttpSession对象
        ArrayList<BlogItem> itemList = new ArrayList<>();
        BlogItem item = new BlogItem();
        item.setLink(new String("Cpp测试标题.html".getBytes(),"UTF-8"));
        item.setTitle(new String("测试数据".getBytes(),"UTF-8"));
        item.setDescription(new String("测试数据".getBytes(),"UTF-8"));
        item.setOwner("admin");
        item.setType("cpp");
        item.setId(2);
        itemList.add(item);

        blogContentSession.setAttribute("blogList",itemList);

        //构造Mock请求
        MockHttpServletRequestBuilder blogContentRequestBuild = MockMvcRequestBuilders.get("/api/edit/get")
                                                                                    .accept(MediaType.APPLICATION_JSON_VALUE)
                                                                                    .param("id","2").session(blogContentSession);

        //进行单元测试
        mockMvc.perform(blogContentRequestBuild).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    /*
    MOCK单元测试
        测试blogControl控制器类的submitBlogContent方法
        访问方法Post
     */
    @Test
    public void testSubmitBlogContent() throws Exception{

        //构造请求数据
        blogRequestObj requestObj = new blogRequestObj();
        requestObj.setLink("Cpp测试标题1.html");
        requestObj.setTitle("标题测试数据");
        requestObj.setType("cpp");
        requestObj.setDescription("描述测试数据");
        requestObj.setContent("<p>数据</p>");
        requestObj.setId(3);


        //构造HttpSession对象
        ArrayList<BlogItem> itemList = new ArrayList<>();
        BlogItem item = new BlogItem();
        item.setLink(new String("Cpp测试标题.html".getBytes(),"UTF-8"));
        item.setTitle(new String("测试数据".getBytes(),"UTF-8"));
        item.setDescription(new String("测试数据".getBytes(),"UTF-8"));
        item.setOwner("admin");
        item.setType("cpp");
        item.setId(2);
        itemList.add(item);

        blogContentSession.setAttribute("blogList",itemList);

        String requestString = JSONObject.toJSONString(requestObj);
        //Mock请求
        //POST请求，使用application/json方式
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder submitContentRequest = MockMvcRequestBuilders.post("/api/edit/put")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .session(blogContentSession)
                .headers(headers)
                .content(requestString);

        //打印结果
        mockMvc.perform(submitContentRequest).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }


}
