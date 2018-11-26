package com.suncreate.demo.dao.impl;

import com.suncreate.demo.domain.Author;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface AuthorMapper {
    Author findAuthor(@Param("id") Long id);
    List<Author> findAuthorList();
}
