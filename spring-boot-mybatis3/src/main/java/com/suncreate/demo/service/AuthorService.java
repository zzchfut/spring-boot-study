package com.suncreate.demo.service;

import com.suncreate.demo.domain.Author;
import com.suncreate.demo.domain.User;
import java.util.List;


public interface AuthorService {
    public List<Author> findAuthorList();
    public List<User> findUserList();
}
