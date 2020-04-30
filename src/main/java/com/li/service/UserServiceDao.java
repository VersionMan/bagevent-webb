package com.li.service;

import com.li.entity.User;

public interface UserServiceDao {
    public User Login(User user);
    public boolean insertUser(User user);
}
