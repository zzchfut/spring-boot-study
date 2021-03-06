package com.suncreate.demo.action;

import com.suncreate.demo.dao.impl.AuthorMapper;
import com.suncreate.demo.dao.impl.UserMapper;
import com.suncreate.demo.domain.Author;
import com.suncreate.demo.domain.User;
import com.suncreate.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1. mybatis的配置文件
 * 2. AOP的实现流程
 * 3.
 */
@Controller
@RequestMapping(value="/data")
public class StaticAction {
    
    @Autowired
    private AuthorService authorService;

   /* @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private UserMapper userMapper;*/

    @RequestMapping(value = "/author", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAuthorList(HttpServletRequest request) {
        List<Author> authorList = this.authorService.findAuthorList();
        // List<Author> authorList = this.authorMapper.findAuthorList();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("total", authorList.size());
        param.put("rows", authorList);
        return param;
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getUserList(HttpServletRequest request) {
        List<User> userList = this.authorService.findUserList();
        // List<User> userList = this.userMapper.findUserList();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("total", userList.size());
        param.put("rows", userList);
        return param;
    }
 }
