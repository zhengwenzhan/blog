package com.wenzhan.blog.dao;

import com.wenzhan.blog.entity.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    UserRole getUserByUsername(String userName);

}
