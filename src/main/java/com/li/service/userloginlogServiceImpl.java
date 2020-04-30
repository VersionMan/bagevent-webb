package com.li.service;

import com.li.dao.userloginlogDao;
import com.li.entity.userloginlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class userloginlogServiceImpl implements  userloginlogServiceDao{
    @Autowired
    private userloginlogDao uld;
    @Override
    public List<userloginlog> allLog() {
        return this.uld.alluserLogin();
    }

    @Override
    public boolean insertOne(userloginlog userloginlog) {
        int count=this.uld.insertOneLog(userloginlog);
        if(count>0){
            return  true;
        }
        else {
            return  false;
        }

    }
}
