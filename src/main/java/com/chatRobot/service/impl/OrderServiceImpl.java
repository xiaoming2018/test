package com.chatRobot.service.impl;

import com.chatRobot.dao.OrderMapper;
import com.chatRobot.model.Order;
import com.chatRobot.model.OrderExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public int insertSelective(Order order) {
        int flag = orderMapper.insertSelective(order);
        return flag;
    }

    public List<Order> selectByUserId(Integer userId) {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<Order> orderList = orderMapper.selectByExample(orderExample);
        return orderList;
    }

    // get admin all orders
    public List<Order> selectAllOrders() {
        OrderExample example = new OrderExample();
        return orderMapper.selectByExample(example);
    }

    // get Order by orderId
    public Order selectByOrderId(Integer orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    // update order by orderselective
    public int updateOrderByselective(Order order) {
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    // delete order by orderId only
    public int deleteOrderByOrderId(Integer orderId) {
        return orderMapper.deleteByPrimaryKey(orderId);
    }

    // delete order by orderList
    public int deleteByorderList(List<Integer> del_orderList) {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andOrderIdIn(del_orderList);
        return orderMapper.deleteByExample(orderExample);
    }

    // 联合主键 唯一性检验
    public List<Order> selectByUserIdAndGoodsId(Integer userId, Integer goodsId) {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andGoodsIdEqualTo(goodsId);
        return orderMapper.selectByExample(orderExample);
    }

    // 根据 date and pastdate 查询订单数量
    public List<Integer> selectNumberOfOrderByDate(List<String> pastDaysList) {
        List<Integer> orderNumber = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < pastDaysList.size(); i++) {
            OrderExample example = new OrderExample();
            OrderExample.Criteria criteria = example.createCriteria();
            try {
                criteria.andOrderTimeEqualTo(sdf.parse(pastDaysList.get(i)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            long temp = orderMapper.countByExample(example);
            orderNumber.add(new Integer((int) temp));
        }
        return orderNumber;
    }

    // 根据 admin month date 查询订单数量
    public List<Integer> selectNumberOfOrderByMonth(List<String> pastDaysList) {
        List<Integer> orderNumber = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < pastDaysList.size() - 1; i++) {
            OrderExample example = new OrderExample();
            OrderExample.Criteria criteria = example.createCriteria();
            try {
                criteria.andOrderTimeBetween(sdf.parse(pastDaysList.get(i + 1)), sdf.parse(pastDaysList.get(i)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            long temp = orderMapper.countByExample(example);
            orderNumber.add(new Integer((int) temp));
        }
        return orderNumber;
    }

    // 获取销量前10的 goodsId and totalNumber
    public List<Map<Integer, Integer>> selectByLimit() {
        return orderMapper.selectByLimit();
    }


}
