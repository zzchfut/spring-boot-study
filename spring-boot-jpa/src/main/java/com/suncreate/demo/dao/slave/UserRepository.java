package com.suncreate.demo.dao.slave;

import com.suncreate.demo.domain.slave.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
