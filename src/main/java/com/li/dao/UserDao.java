package com.li.dao;

import com.li.entity.User;

public interface UserDao {
    public User login(User user);
    public int insert(User user);
}
