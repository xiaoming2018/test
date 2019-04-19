package com.chatRobot.controller;

import com.chatRobot.model.*;
import com.chatRobot.service.impl.GoodsCartServiceImpl;
import com.chatRobot.service.impl.GoodsServiceImpl;
import com.chatRobot.service.impl.OrderServiceImpl;
import com.chatRobot.service.impl.UserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            for (int i = 0; i < goodsCartList.size(); i++) {
                // 通过userId 删除 购物车记录
                int flag = goodsCartService.deleteByUserIdAndGoodsId(goodsCartList.get(i).getUserId(), goodsCartList.get(i).getGoodsId());
                if (flag == 0) {
                    return "warn";
                }
            }
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
            return Msg.success().add("goodsList", goodsList).add("totalPrice", totalPrice).add("totalGoodsAmount", totalGoodsAmount);
        } else {
            return Msg.fail();
        }
    }

    // admin order index data
    @ResponseBody
    @RequestMapping("/orderData")
    public Msg getorderData(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "30") Integer limit) {
        try {
            // 联表查询
            PageHelper.startPage(page, limit);
            List<Order> managerList = orderService.selectAllOrders();
            PageInfo pageInfo = new PageInfo(managerList, limit);
            return Msg.success().add("PageInfo", pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("message", "");
        }
    }

    // admin order add
    @ResponseBody
    @RequestMapping("/OrderAdd")
    public Msg getOrderAdd(Order order) {
        Date date = new Date();
        // 订单唯一性检验 根据userId and goodsId 为联合主键
        try {
            List<Order> orderList = orderService.selectByUserIdAndGoodsId(order.getUserId(), order.getGoodsId());
            if (orderList.size() == 0) {
                // 新增
                order.setOrderCreateTime(date);
                order.setOrderUpdateTime(date);
                order.setOrderTime(date);
                try {
                    int flag = orderService.insertSelective(order);
                    if (flag == 1) {
                        return Msg.success().add("message", "添加订单成功！");
                    } else {
                        return Msg.fail().add("message", "order订单添加失败！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return Msg.fail().add("message", "order 添加成功！");
                }
            } else {
                // amount 更新
                order.setOrderId(orderList.get(0).getOrderId());
                order.setGoodsAmount(order.getGoodsAmount() + orderList.get(0).getGoodsAmount());
                order.setOrderUpdateTime(date);
                order.setOrderCreateTime(orderList.get(0).getOrderCreateTime());
                order.setOrderTime(orderList.get(0).getOrderTime());
                try {
                    int flag = orderService.updateOrderByselective(order);
                    if (flag == 1) {
                        return Msg.success();
                    } else {
                        return Msg.fail().add("message", "合并order订单数据出错!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return Msg.fail().add("message", "order订单更新失败！");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("message", "查询订单数据失败！");
        }
    }

    // admin order edit
    @ResponseBody
    @RequestMapping("/OrderEdit")
    public Msg getOrderEdit(Order order) {
        Date date = new Date();
        order.setOrderUpdateTime(date);
        order.setOrderUpdateTime(date);
        try {
            int flag = orderService.updateOrderByselective(order);
            if (flag == 1) {
                return Msg.success();
            } else {
                return Msg.fail().add("message", "order数据update失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }

    // admin order delete
    @ResponseBody
    @RequestMapping("/OrderDelete")
    public Msg getOrderDelete(String del_orderIds) {
        if (del_orderIds.contains("-")) {
            // 批量删除
            List<Integer> del_orderIdList = new ArrayList<>();
            String[] del_orderId = del_orderIds.split("_");
            for (String string : del_orderId) {
                del_orderIdList.add(Integer.parseInt(string));
            }
            try {
                int flag = orderService.deleteByorderList(del_orderIdList);
                if (flag == del_orderId.length) {
                    return Msg.success();
                } else {
                    return Msg.fail().add("message", "批量删除 orderList 出错！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail().add("message", "批量删除 order 出错！");
            }
        } else {
            // 单一删除
            try {
                int flag = orderService.deleteOrderByOrderId(Integer.parseInt(del_orderIds));
                if (flag == 1) {
                    return Msg.success();
                } else {
                    return Msg.fail().add("message", "删除order");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail().add("message", "单一删除 order 出错！");
            }
        }
    }

    // admin order confirm
    @ResponseBody
    @RequestMapping("/orderConfirm")
    public Msg orderConfirm(Integer orderId) {
        try {
            Order order = orderService.selectByOrderId(orderId);
            String status = order.getOrderStatus();
            if (status.equals("未确认")) {
                order.setOrderStatus("已确认");
                if (1 == orderService.updateOrderByselective(order)) {
                    return Msg.success().add("message", "订单确认成功！");
                } else {
                    return Msg.fail().add("message","订单数据更新失败！");
                }
            } else {
                return Msg.fail().add("message", "订单已经确认，无须重复确认！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("message", "order 确认失败！");
        }
    }

    // admin order unconfrim
    @ResponseBody
    @RequestMapping("/orderUnconfrim")
    public Msg orderUnconfrim(Integer orderId){
        try {
            Order order = orderService.selectByOrderId(orderId);
            String status = order.getOrderStatus();
            if (status.equals("已确认")) {
                order.setOrderStatus("未确认");
                if (1 == orderService.updateOrderByselective(order)) {
                    return Msg.success().add("message", "订单取消成功！");
                } else {
                    return Msg.fail().add("message","订单数据取消失败！");
                }
            } else {
                return Msg.fail().add("message", "订单已经取消，无须重复确认！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("message", "order 确认失败！");
        }
    }


}
