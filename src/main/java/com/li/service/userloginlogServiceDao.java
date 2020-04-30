package com.li.service;

import com.li.entity.userloginlog;

import java.util.List;

public interface userloginlogServiceDao {
    public List<userloginlog>  allLog();
    public boolean insertOne(userloginlog userloginlog);
}
