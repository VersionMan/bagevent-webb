package com.li.controller;

import com.li.entity.User;
import com.li.entity.userloginlog;
import com.li.service.userloginlogServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserLogCrontroller {
    @Autowired
    private userloginlogServiceDao uld;
    @RequestMapping(value="/allUserLog")
    public String register ( Model model)  {
        List<userloginlog>userLog =this.uld.allLog();
        model.addAttribute("userLog",userLog);
        return "index";
    }
}
