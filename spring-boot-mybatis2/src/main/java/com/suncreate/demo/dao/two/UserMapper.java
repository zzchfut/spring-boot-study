package com.suncreate.demo.dao.two;

import com.suncreate.demo.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface UserMapper {
    User findUser(@Param("id") Long id);
    List<User> findUserList();
}
