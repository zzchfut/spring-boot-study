package com.suncreate.demo.dao.impl;

import com.suncreate.demo.dao.AuthorDao;
import com.suncreate.demo.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
@Repository
public class AuthorDaoImpl implements AuthorDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Author author) {
        return jdbcTemplate.update("insert into t_author(real_name,nick_name) values(?,?)",
                author.getRealName(),author.getNickName());
    }

    @Override
    public int update(Author author) {
        return jdbcTemplate.update("update t_author set real_name=?,nick_name=? where id=?",new Object[]{author.getRealName(),author.getNickName(),author.getId()},
                new int[]{Types.VARCHAR,Types.VARCHAR,Types.INTEGER});
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update("delete from t_author where id=?",id);
    }

    @Override
    public Author findAuthor(Long id) {
        List<Author> list = jdbcTemplate.query("select * from t_author where id = ?", new Object[]{id}, new BeanPropertyRowMapper(Author.class));
        if(null != list && list.size()>0) {
            Author author = list.get(0);
            return author;
        }
        return null;
    }

    @Override
    public List<Author> findAuthorList() {
        List<Author> list = jdbcTemplate.query("select * from t_author", new Object[]{}, new BeanPropertyRowMapper(Author.class));
        return list;
    }
}
