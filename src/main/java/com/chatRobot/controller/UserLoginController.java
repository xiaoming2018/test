package com.chatRobot.controller;


import com.chatRobot.model.Msg;
import com.chatRobot.model.User;
import com.chatRobot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("servlet")
public class UserLoginController {

    @Autowired
    UserServiceImpl UserService;

    @RequestMapping("/login")
    @ResponseBody
    public Msg userLogin(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("获取值:"+ email);

        List<User> userList = UserService.selectByEmail(email);
        if(userList.isEmpty()){
            return Msg.fail().add("message","该邮箱未注册！");
        }
        if(password.equals(userList.get(0).getUserPassword())){
            return Msg.success();
        }else{
            return Msg.fail().add("message","密码错误！");
        }
        //System.out.println(user.toString());
    }

}
