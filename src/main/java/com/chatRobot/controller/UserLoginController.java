package com.chatRobot.controller;


import com.chatRobot.model.Goods;
import com.chatRobot.model.Manager;
import com.chatRobot.model.Msg;
import com.chatRobot.model.User;
import com.chatRobot.service.impl.GoodsServiceImpl;
import com.chatRobot.service.impl.ManagerServiceImpl;
import com.chatRobot.service.impl.UserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("servlet")
public class UserLoginController {

    @Autowired
    private UserServiceImpl UserService;
    @Autowired
    private GoodsServiceImpl goodsServiceImpl;
    @Autowired
    private ManagerServiceImpl managerService;


    //ajax的后台登陆控制
    @RequestMapping("/login")
    @ResponseBody
    public Msg userLogin(String email, String password) {
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
    public String userForIndex(@RequestParam(value = "pn", defaultValue = "1") Integer pn, String email, String password, Model model, HttpSession session) {
        List<User> userList = UserService.selectByEmail(email);
        if (userList.isEmpty()) {
            model.addAttribute("message", "该邮箱未注册!");
            return "login"; // 直接带参数的网页跳转
        }
        if (password.equals(userList.get(0).getUserPassword())) {
            //model.addAttribute("message", "登陆成功！");
            session.setAttribute("loginFlag", "success");
            session.setAttribute("message", "登陆成功！");
            session.setAttribute("User", userList.get(0)); // 用户存入session
            PageHelper.startPage(pn, 6);
            List<Goods> goodsList = goodsServiceImpl.selectAllGoods();
            //navigatePages : 连续显示的页数
            PageInfo<Goods> pageInfo = new PageInfo(goodsList, 6);
            // 将分页信息 查询得到数据信息 打包到 model中的pageinfo中。
            model.addAttribute("PageInfo", pageInfo);
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

    //个人基础信息注册更新（根据form表单参数，进行个人信息更新）


    //配置个人资料标记跳转页面
    @RequestMapping("/editPage")
    public String userEditPage(int userId, Model model) {
        User user = UserService.selectByPrimaryKey(userId);
        model.addAttribute("User", user); //将user放入页面参数
        //跳转 个人信息编辑页面
        return "personInfo";
    }

    // 检测用户是否已经登陆
    @ResponseBody
    @RequestMapping("/CheckUserOnline")
    public Msg checkUserOnline(HttpSession session) {
        if (session.getAttribute("User") != null) {
            User user = (User) session.getAttribute("User");
            return Msg.success().add("user", user);
        } else {
            return Msg.fail();
        }
    }

    // 用户登出操作
    @RequestMapping("/Logout")
    public String userLogout(HttpSession session, Model model) {
        session.removeAttribute("message");
        session.removeAttribute("loginFlag");
        session.removeAttribute("User");
        PageHelper.startPage(1, 6);
        List<Goods> goodsList = goodsServiceImpl.selectAllGoods();
        //navigatePages : 连续显示的页数
        PageInfo<Goods> pageInfo = new PageInfo(goodsList, 6);
        // 将分页信息 查询得到数据信息 打包到 model中的pageinfo中。
        model.addAttribute("PageInfo", pageInfo);
        return "index";
    }

    @RequestMapping("/adminIndex")
    @ResponseBody
    public Msg managerLogin(String account, String password, HttpSession session) {
        /**
        * @Author: sun xiaoming
        * @Description: 后台管理账户验证
        * @Date: 2019/3/14 20:49
        */
        List<Manager> managerList = managerService.selectByExample(account);
        if (!managerList.isEmpty()) {
            if (password.equals(managerList.get(0).getManagerPassword())) {
                session.setAttribute("admin",managerList.get(0));
                return Msg.success().add("message","登陆成功");
            }else{
                return Msg.fail().add("message","密码错误").add("flag","wrong");
            }
        } else {
            return Msg.fail().add("message", "用户不存在").add("flag","null");
        }
    }

    @RequestMapping("/adminLogout")
    public String adminLogout(HttpSession session){
        session.removeAttribute("admin");
        return "admin/adminLogin";
    }

}
