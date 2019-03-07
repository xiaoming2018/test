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
        /**
         * @Author: sun xiaoming
         * @Description: 单一商品添加订单 订单数据库注入
         * @Date: 2019/3/7 10:52
         */
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
            for (int i = 0; i < goodsCartList.size(); i++) {
                good = goodsService.selectGoodsWithId(goodsCartList.get(i).getGoodsId());
                good.setGoodsAmount(goodsCartList.get(i).getGoodsAmount());
                goodsList.add(good);
                order.setGoodsId(goodsCartList.get(i).getGoodsId());
                order.setGoodsAmount(goodsCartList.get(i).getGoodsAmount());
                try {
                    int flag = orderService.insertSelective(order);
                    if (flag == 0) {
                        model.addAttribute("message", "订单插入失败1，请重新操作");
                        return "warn";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("message", "订单插入失败2，请重新操作");
                    return "warn";
                }
            }
            //订单数据入库后，进行订单展示。
            //订单生成，，购物车内容删除。
            model.addAttribute("goodsList", goodsList);
            model.addAttribute("User", user);
            return "CheckOutList";
        } else {
            model.addAttribute("message", "订单插入失败3，请重新操作");
            return "warn";
        }
    }

    @RequestMapping("/orderCenter")
    public String OrderCenter(Integer userId, Model model) {
        Goods goods;
        User user = userService.selectByPrimaryKey(userId);
        List<Goods> goodsList = new ArrayList<>();
        List<Order> orderList = orderService.selectByUserId(userId);
        if (!orderList.isEmpty()) {
            //订单非空
            for (int i = 0; i < orderList.size(); i++) {
                goods = goodsService.selectGoodsWithId(orderList.get(i).getGoodsId());
                goods.setGoodsAmount(orderList.get(i).getGoodsAmount());
                goodsList.add(goods);
            }
            model.addAttribute("goodsList", goodsList);
            model.addAttribute("User", user);
            model.addAttribute("orderList", orderList);
            return "orderDetails";
        } else {
            model.addAttribute("message", "您的订单为空，请先购物！");
            return "warn";
        }
    }

    // 订单中心的更新
    @ResponseBody
    @RequestMapping("/updateOrder")
    public Msg updateGoodsCart(Integer userId) {
        Goods goods;
        int totalGoodsAmount = 0;
        double totalPrice = 0.0;
        List<Goods> goodsList = new ArrayList<>();
        List<Order> orderList = orderService.selectByUserId(userId);
        if (!orderList.isEmpty()) {
            for (int i = 0; i < orderList.size(); i++) {
                goods = goodsService.selectGoodsWithId(orderList.get(i).getGoodsId());
                goods.setGoodsAmount(orderList.get(i).getGoodsAmount());
                totalGoodsAmount += orderList.get(i).getGoodsAmount();
                totalPrice += goods.getGoodsPrice().doubleValue() * orderList.get(i).getGoodsAmount();
                goodsList.add(goods);
            }
            return Msg.success().add("goodsList", goodsList).add("totalPrice", totalPrice).add("totalGoodsAmount",totalGoodsAmount);
        }else{
            return Msg.fail();
        }
    }
}
