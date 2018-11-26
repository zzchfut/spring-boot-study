package com.suncreate.demo.impl;

import com.suncreate.demo.dao.master.AuthorMapper;
import com.suncreate.demo.dao.slave.UserMapper;
import com.suncreate.demo.domain.Author;
import com.suncreate.demo.domain.User;
import com.suncreate.demo.service.AuthorService;
import com.suncreate.demo.utils.DBType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private UserMapper userMapper;

    @DBType(name="master")
    @Transactional
    public List<Author> findAuthorList(){
        return this.authorMapper.findAuthorList();
    }

    @DBType(name="slave")
    @Transactional
    public List<User> findUserList(){
        return this.userMapper.findUserList();
    }
}
