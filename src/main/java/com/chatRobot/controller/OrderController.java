package com.chatRobot.controller;

import com.chatRobot.model.Msg;
import com.chatRobot.model.Order;
import com.chatRobot.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

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

    @RequestMapping("/add")
    @ResponseBody
    public Msg OrderAdd(Order order) {
        System.out.println(order.toString());

        Date date = new Date();
        order.setOrderTime(date);
        order.setOrderCreateTime(date);
        order.setOrderUpdateTime(date);

        // 存在性验证。。。

        try {
            int flag = orderService.insertSelective(order);
            if (flag > 0) {
                return Msg.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
        return null;
    }

}
