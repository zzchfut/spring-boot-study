package com.suncreate.demo.service;

import com.suncreate.demo.domain.Author;
import com.suncreate.demo.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
   List<User> findUserList(String dynDBType);
   List<Author> findAuthorList(String dynDBType);
}
