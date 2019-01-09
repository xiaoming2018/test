package com.chatRobot.controller;

import com.chatRobot.model.Goods;
import com.chatRobot.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/page/")
public class PageController {
    /**
     * 配置页面跳转
     * author ：xiaoming
     */
    @Autowired
    private GoodsServiceImpl goodsServiceImpl;

    @RequestMapping("toIndex")
    public String PageToIndex(Model model){
        /**
        * @Author: sun xiaoming
        * @Description: 跳转到主页
        * @Date: 2019/1/8 15:51
        */
        //获取商品信息，回显在主页上
        List<Goods> goodsList = goodsServiceImpl.selectAllGoods();
        model.addAttribute("goodList",goodsList);
        return "index";
    }


}
