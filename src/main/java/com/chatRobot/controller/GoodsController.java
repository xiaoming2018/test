package com.chatRobot.controller;

import com.chatRobot.dao.GoodsCartMapper;
import com.chatRobot.model.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sun xiaoming
 * @Title: GoodsController
 * @ProjectName WebGLShop
 * @date 2019/1/9 20:12
 */

@Controller
@RequestMapping("Goods")
public class GoodsController {

    @Autowired
    GoodsCartMapper goodsCartMapper;

    @RequestMapping("add")
    public Msg addGoodsInShopCart(Integer goodsId,Integer userId, Integer goodsamount,Model model){
        System.out.println(goodsId+userId+goodsamount);

        return null;
    }
}
