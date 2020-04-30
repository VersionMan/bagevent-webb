package com.li.service;

import com.li.dao.UserDao;
import com.li.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements  UserServiceDao{
@Autowired
private UserDao us ;
    @Override
    public User Login(User user) {
        return this.us.login(user);
    }

    @Override
    public boolean insertUser(User user) {
        int count=this.us.insert(user);
        if(count>0){
            return  true;
        }
        else {
            return  false;
        }

    }
}
