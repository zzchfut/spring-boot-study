package com.suncreate.demo.action;

import com.alibaba.fastjson.JSONObject;
import com.suncreate.demo.domain.Author;
import com.suncreate.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @RestController // <=> @Controller + @ResponseBody
@Controller
@RequestMapping(value="/data/author")
public class AuthorController
{
    @Autowired
    private AuthorService authorService;
    /**
     * 查询用户列表
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAuthorList(HttpServletRequest request) {
        List<Author> authorList = this.authorService.findAuthorList();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("total", authorList.size());
        param.put("rows", authorList);
        return param;
    }
    /**
     * 查询用户信息
     */
    @RequestMapping(value = "/{userId:\\d+}", method = RequestMethod.GET)
    @ResponseBody
    public Author getAuthor(@PathVariable Long userId, HttpServletRequest request) {
        Author author = this.authorService.findAuthor(userId);
        if(author == null){
            throw new RuntimeException("查询错误");
        }
        return author;
    }

    /**
     * 新增方法
     */
    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody JSONObject jsonObject) {
        String userId = jsonObject.getString("user_id");
        String realName = jsonObject.getString("real_name");
        String nickName = jsonObject.getString("nick_name");
        Author author = new Author();
        if (author!=null) {
            author.setId(Long.valueOf(userId));
        }
        author.setRealName(realName);
        author.setNickName(nickName);
        try{
            this.authorService.add(author);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("新增错误");
        }
    }
    /**
     * 更新方法
     */
    @RequestMapping(value = "/{userId:\\d+}", method = RequestMethod.PUT)
    public void update(@PathVariable Long userId, @RequestBody JSONObject jsonObject) {
        Author author = this.authorService.findAuthor(userId);
        String realName = jsonObject.getString("real_name");
        String nickName = jsonObject.getString("nick_name");
        author.setRealName(realName);
        author.setNickName(nickName);
        try{
            this.authorService.update(author);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("更新错误");
        }
    }
    /**
     * 删除方法
     */
    @RequestMapping(value = "/{userId:\\d+}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long userId) {
        try{
            this.authorService.delete(userId);
        }catch(Exception e){
            throw new RuntimeException("删除错误");
        }
    }
}
