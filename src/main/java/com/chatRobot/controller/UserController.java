package com.chatRobot.controller;

import com.chatRobot.model.Msg;
import com.chatRobot.model.User;
import com.chatRobot.service.impl.UserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author sun xiaoming
 * @Title: UserController
 * @ProjectName WebGLShop
 * @date 2019/4/8 21:19
 */
@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @ResponseBody
    @RequestMapping("User/UsersData")
    public Msg getAllUsers(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "30") Integer limit){
        try{
            PageHelper.startPage(page, limit);
            List<User> userList = userService.selectAllUsers();
            PageInfo pageInfo = new PageInfo(userList, limit);
            return Msg.success().add("PageInfo",pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            return Msg.fail();
        }
    }
}
