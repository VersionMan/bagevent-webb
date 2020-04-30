package com.li.dao;

import com.li.entity.userloginlog;

import java.util.List;

public interface userloginlogDao {
    public List<userloginlog>  alluserLogin();
    public int insertOneLog(userloginlog userloginlog);
}
