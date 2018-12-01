package com.suncreate.demo.action;

import com.suncreate.demo.dao.master.AuthorRepository;
import com.suncreate.demo.dao.slave.UserRepository;
import com.suncreate.demo.domain.master.Author;
import com.suncreate.demo.domain.slave.User;
import com.suncreate.demo.service.AuthorService;
import com.suncreate.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/data")
public class AuthorAction {

   @Autowired
   private AuthorRepository authorRepository;

   @Autowired
   private UserRepository userRepository;

    @RequestMapping(value = "/author",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object>  getAuthorList(HttpServletRequest request) {
       List<Author> authorList = this.authorRepository.findAll();
       Map<String, Object> param = new HashMap<>();
       param.put("total", authorList.size());
       param.put("rows", authorList);
       return param;
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object>  getUserList(HttpServletRequest request) {
       List<User> authorList = this.userRepository.findAll();
       Map<String, Object> param = new HashMap<>();
       param.put("total", authorList.size());
       param.put("rows", authorList);
       return param;
    }
 }
