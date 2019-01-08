package com.chatRobot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/")
public class PageController {
    /**
     * 配置页面跳转
     * author ：xiaoming
     *
     */
    @RequestMapping("toIndex")
    public String PageToIndex(){
        /**
        * @Author: sun xiaoming
        * @Description: 跳转到主页
        * @Date: 2019/1/8 15:51
        */
        //获取商品信息，回显在主页上

        return "index";
    }


}
