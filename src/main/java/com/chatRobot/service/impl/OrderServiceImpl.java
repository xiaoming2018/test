package com.chatRobot.service.impl;

import com.chatRobot.dao.OrderMapper;
import com.chatRobot.model.Order;
import com.chatRobot.model.OrderExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public List<Order> selectByUserId(Integer userId){
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<Order> orderList = orderMapper.selectByExample(orderExample);
        return orderList;
    }

}
