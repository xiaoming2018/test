package com.chatRobot.service.impl;

import com.chatRobot.dao.OrderMapper;
import com.chatRobot.model.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sun xiaoming
 * @Title: OrderServiceImpl
 * @ProjectName WebGLShop
 * @date 2019/3/1 16:28
 */
@Service("OrderService")
public class OrderServiceImpl {
    @Resource
    private OrderMapper orderMapper;

    public int insertSelective(Order order){
        int flag = orderMapper.insertSelective(order);
        return flag;
    }

}
