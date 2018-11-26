package com.suncreate.demo.impl;

import com.suncreate.demo.dao.impl.AuthorMapper;
import com.suncreate.demo.dao.impl.UserMapper;
import com.suncreate.demo.domain.Author;
import com.suncreate.demo.domain.User;
import com.suncreate.demo.service.AuthorService;
import com.suncreate.demo.utils.DataSourceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@EnableTransactionManagement
class AuthorServiceImpl implements AuthorService{
    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public List<User> findUserList(String dynDBType){
        DataSourceHolder.setDBType(dynDBType);

        List<User> userList = this.userMapper.findUserList();
        return userList;
    }

    @Transactional
    public List<Author> findAuthorList(String dynDBType){
        DataSourceHolder.setDBType(dynDBType);

        List<Author> authorList = this.authorMapper.findAuthorList();
        return authorList;
    }
}
