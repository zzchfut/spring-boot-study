package com.suncreate.demo.dao.master;

import com.suncreate.demo.domain.master.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // 分页查询：不需要分页插件
    @Query(value = "select id, realName, nickName from Author where id > ?1 and realName is not null order by id desc limit ?2, ?3",
           nativeQuery = true)
    List<Author> pageList(Long id, int skip, int pageSize);
}
