package com.suncreate.demo.action;

import com.suncreate.demo.dao.one.AuthorMapper;
import com.suncreate.demo.dao.two.UserMapper;
import com.suncreate.demo.domain.Author;
import com.suncreate.demo.domain.User;
import com.suncreate.demo.impl.AuthorServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated
@Controller
@RequestMapping(value="/data")
public class StaticAction {
    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private UserMapper userMapper;
/*
    @RequestMapping(value = "/author", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAuthorList(HttpServletRequest request) {
        List<Author> authorList = this.authorMapper.findAuthorList();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("total", authorList.size());
        param.put("rows", authorList);
        return param;
    }
    @RequestMapping(value = "/author/{userId:\\d+}", method = RequestMethod.GET)
    public Author getAuthor(@PathVariable Long userId, HttpServletRequest request) {
        Author author = this.authorMapper.findAuthor(userId);
        if(author == null){
            throw new RuntimeException("查询错误");
        }
        return author;
    }


    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getUserList(HttpServletRequest request) {
        List<User> userList = this.userMapper.findUserList();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("total", userList.size());
        param.put("rows", userList);
        return param;
    }
    @RequestMapping(value = "/user/{userId:\\d+}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long userId, HttpServletRequest request) {
        User user = this.userMapper.findUser(userId);
        if(user == null){
            throw new RuntimeException("查询错误");
        }
        return user;
    }*/
 }
