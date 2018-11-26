package com.suncreate.demo.action;

import com.suncreate.demo.domain.Author;
import com.suncreate.demo.domain.User;
import com.suncreate.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/data")
public class ActivateController {
    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/author", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAuthorList(HttpServletRequest request) {

        List<Author> authorList = this.authorService.findAuthorList("master");
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("total", authorList.size());
        param.put("rows", authorList);
        return param;
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getUserList(HttpServletRequest request) {

        List<User> userList = this.authorService.findUserList("slave");
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("total", userList.size());
        param.put("rows", userList);
        return param;
    }

}
