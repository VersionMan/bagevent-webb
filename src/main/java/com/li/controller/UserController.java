package com.li.controller;

import com.li.entity.User;
import com.li.entity.userloginlog;
import com.li.service.UserServiceDao;
import com.li.service.userloginlogServiceDao;
import com.li.uitl.GetIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserServiceDao usd;


    @Autowired
    private userloginlogServiceDao loginlogServiceDao;

    @RequestMapping(value="/login.do")
    public String loginIn (User user, Model model)  {
        User Oneuser=this.usd.Login(user);
        if(Oneuser!=null){
            userloginlog us =new userloginlog();
            us.setUser_id(Oneuser.getUser_id());
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss zz");
            Date date = new Date(System.currentTimeMillis());
            us.setLogin_time(date);
            GetIp i=new GetIp();
            String ip=i.getHostIP();
            us.setLogin_ip(ip);
            loginlogServiceDao.insertOne(us);
            model.addAttribute("Oneuser",Oneuser);
            return "redirect:/allUserLog";
        }
 else {
            model.addAttribute("loginNo","登陆失败");
            return "login";
        }
    }

    @RequestMapping({"", "login"}) //这里为空或者是login都能进入该方法
    public String login() {
        return "login";
    }




    @RequestMapping(value="/register")
    public String register (User user, Model model)  {

        return "register";
    }


    @RequestMapping(value="/insert.do")
    public String insert (String user_name, String email,String cellphone,String  password, Model model)  {
        User user=new User();

        user.setUser_name(user_name);
        user.setEmail(email);
        user.setCellphone(cellphone);
        user.setPassword(password);

        String salt = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setSalt(salt);

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        user.setCreate_time(date);
        boolean isOk=this.usd.insertUser(user);
        if(isOk){
            model.addAttribute("insertYes","注册成功！");
            model.addAttribute("hahaha",user.getUser_name());
            return "login";
        }else {
            model.addAttribute("insertstates","注册失败,请稍后重新尝试");
            return "register";
        }

    }
}
