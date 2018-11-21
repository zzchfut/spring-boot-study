package com.suncreate.demo.service;

import com.suncreate.demo.domain.Author;
import java.util.List;

public interface AuthorService {
    int add(Author author);
    int update(Author author);
    int delete(Long id);
    Author findAuthor(Long id);
    List<Author> findAuthorList();
}