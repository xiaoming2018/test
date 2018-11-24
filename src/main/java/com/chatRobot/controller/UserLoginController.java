package com.chatRobot.controller;


import com.chatRobot.model.Msg;
import com.chatRobot.model.User;
import com.chatRobot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("servlet")
public class UserLoginController {

    @Autowired
    UserServiceImpl UserService;

    //ajax的后台登陆控制
    @RequestMapping("/login")
    @ResponseBody
    public Msg userLogin(String email, String password) {
        System.out.println("获取值:" + email);

        List<User> userList = UserService.selectByEmail(email);
        if (userList.isEmpty()) {
            return Msg.fail().add("message", "该邮箱未注册！");
        }
        if (password.equals(userList.get(0).getUserPassword())) {
            //model.addAttribute("User",userList.get(0));
            return Msg.success().add("user", userList.get(0));
        } else {
            return Msg.fail().add("message", "密码错误！");
        }
    }

    //from action 登陆控制
    @RequestMapping("/index")
    public String userForIndex(String email, String password, Model model) {
        List<User> userList = UserService.selectByEmail(email);
        if (userList.isEmpty()) {
            model.addAttribute("message", "该邮箱未注册!");
            return "login"; // 直接带参数的网页跳转
        }
        if (password.equals(userList.get(0).getUserPassword())) {
            model.addAttribute("message", "登陆成功！");
            model.addAttribute("User", userList.get(0));
            return "index";
        } else {
            model.addAttribute("message", "密码错误！");
            return "login";
        }
    }

    //简单注册控制
    @RequestMapping("/register")
    public String userRegister(User user, String userPassword2, Model model) {
        Date date = new Date();
        user.setUserCreateTime(date); //创建时间
        user.setUserUpdateTime(date); //更新时间
        user.setUserPicture("upload\\20181123193008xiaoming.jpg"); //设置默认头像。
        //验证两次密码是否一致。
        if (!userPassword2.equals(user.getUserPassword())) {
            model.addAttribute("User", user);
            model.addAttribute("message", "两次密码不一致，请重新输入！");
            return "register";
        }
        //邮箱作为唯一标识
        List<User> userList = UserService.selectByEmail(user.getUserEmail());
        if (userList.isEmpty()) {
            try {
                int count = UserService.insterSelective(user);
                if (count > 0) {
                    // 注册成功，返回登陆页面。
                    model.addAttribute("userLogin", "注册成功，请登陆！");
                    return "login";
                } else {
                    model.addAttribute("message", "注册失败！请检查字段");
                    return "register";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            model.addAttribute("message", "该邮箱已被注册！");
            model.addAttribute("User", user);
            return "register";
        }
        return "register";
    }
}
