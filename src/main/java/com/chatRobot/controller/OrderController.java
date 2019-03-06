package com.chatRobot.controller;

import com.chatRobot.model.*;
import com.chatRobot.service.impl.GoodsCartServiceImpl;
import com.chatRobot.service.impl.GoodsServiceImpl;
import com.chatRobot.service.impl.OrderServiceImpl;
import com.chatRobot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sun xiaoming
 * @Title: OrderController 订单管理控制器
 * @ProjectName WebGLShop
 * @date 2019/3/1 16:26
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private GoodsServiceImpl goodsService;
    @Autowired
    private GoodsCartServiceImpl goodsCartService;
    @Autowired
    private UserServiceImpl userService;


    @RequestMapping("/add")
    @ResponseBody
    public Msg OrderAdd(Order order) {
        Date date = new Date();
        order.setOrderTime(date);
        order.setOrderCreateTime(date);
        order.setOrderUpdateTime(date);
        try {
            int flag = orderService.insertSelective(order);
            if (flag > 0) {
                return Msg.success();
            } else {
                return Msg.fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }

    @RequestMapping("/ListAdd")
    public String OrderListAdd(Integer userId, Model model) {
        User user;
        user = userService.selectByPrimaryKey(userId);
        List<Goods> goodsList = new ArrayList<>();
        Goods good;
        List<GoodsCart> goodsCartList = goodsCartService.selectByUserId(userId);
        Order order = new Order();
        Date date = new Date();
        order.setUserId(userId);
        order.setOrderTime(date);
        order.setOrderUpdateTime(date);
        order.setOrderCreateTime(date);
        if (!goodsCartList.isEmpty()) {
            // 购物车非空
            for (int i = 0; i <goodsCartList.size();i++){
                good = goodsService.selectGoodsWithId(goodsCartList.get(i).getGoodsId());
                good.setGoodsAmount(goodsCartList.get(i).getGoodsAmount());
                goodsList.add(good);
                order.setGoodsId(goodsCartList.get(i).getGoodsId());
                order.setGoodsAmount(goodsCartList.get(i).getGoodsAmount());
                try{
                    int flag = orderService.insertSelective(order);
                    if(flag == 0) {
                        model.addAttribute("message","订单插入失败1，请重新操作");
                        return "warn";
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    model.addAttribute("message","订单插入失败2，请重新操作");
                    return "warn";
                }
            }
            //订单数据入库后，进行订单展示。
            model.addAttribute("goodsList",goodsList);
            model.addAttribute("User", user);
            return "CheckOutList";
        } else {
            model.addAttribute("message","订单插入失败3，请重新操作");
            return "warn";
        }
    }
}
