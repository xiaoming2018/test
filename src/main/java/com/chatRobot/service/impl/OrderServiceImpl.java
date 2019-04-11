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

    // get admin all orders
    public List<Order> selectAllOrders(){
        OrderExample example = new OrderExample();
        return orderMapper.selectByExample(example);
    }

    // get Order by orderId
    public Order selectByOrderId(Integer orderId){
        return orderMapper.selectByPrimaryKey(orderId);
    }

    // update order by orderselective
    public int updateOrderByselective(Order order){
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    // delete order by orderId only
    public int deleteOrderByOrderId(Integer orderId){
        return orderMapper.deleteByPrimaryKey(orderId);
    }

    // delete order by orderList
    public int deleteByorderList(List<Integer> del_orderList){
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andOrderIdIn(del_orderList);
        return orderMapper.deleteByExample(orderExample);
    }

    // 联合主键 唯一性检验
    public List<Order> selectByUserIdAndGoodsId(Integer userId,Integer goodsId){
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andGoodsIdEqualTo(goodsId);
        return orderMapper.selectByExample(orderExample);
    }

}
