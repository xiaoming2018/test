package com.chatRobot.controller;


import com.chatRobot.model.Msg;
import com.chatRobot.model.User;
import com.chatRobot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("servlet")
public class UserLoginController {

    @Autowired
    UserServiceImpl UserService;

    @RequestMapping("/login")
    @ResponseBody
    public Msg userLogin(String email, String password, Model model){
        System.out.println("获取值:"+ email);

        List<User> userList = UserService.selectByEmail(email);
        if(userList.isEmpty()){
            return Msg.fail().add("message","该邮箱未注册！");
        }
        if(password.equals(userList.get(0).getUserPassword())){
            //model.addAttribute("User",userList.get(0));
            return Msg.success().add("user",userList.get(0));
        }else{
            return Msg.fail().add("message","密码错误！");
        }
    }

    @RequestMapping("/index")
    public String userForIndex(String email,String password,Model model){
        List<User> userList = UserService.selectByEmail(email);
        if(userList.isEmpty()){
            model.addAttribute("message","该邮箱未注册!");
            return "login"; // 直接带参数的网页跳转
        }
        if(password.equals(userList.get(0).getUserPassword())){
            model.addAttribute("message","登陆成功！");
            model.addAttribute("User",userList.get(0));
            String picturePath = userList.get(0).getUserPicture();
            System.out.println(picturePath);
            return "index";
        }else{
            model.addAttribute("message","密码错误！");
            return "login";
        }
    }
}
