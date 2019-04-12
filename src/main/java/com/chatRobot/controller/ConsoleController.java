package com.chatRobot.controller;

import com.chatRobot.model.Msg;
import com.chatRobot.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author sun xiaoming
 * @Title: ConsoleController
 * @ProjectName WebGLShop
 * @date 2019/4/12 13:59
 * @desc : 控制台主页  图标显示
 */
@Controller
public class ConsoleController {

    @Autowired
    private OrderServiceImpl orderService;

    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    public static String getPastMonth(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }


    // get order number by days  获取最近7天订单数据
    @ResponseBody
    @RequestMapping("/Console/getOrderBydays")
    public Msg getOrderNumberBydate() {
        List<String> pastDaysList = new ArrayList<>(); // x轴数据 近7日的时间 String
        for (int i = 0; i < 7; i++) {
            pastDaysList.add(getPastDate(i));
        }
        try {
            List<Integer> orderList = orderService.selectNumberOfOrderByDate(pastDaysList);  // y 轴数据 近 7日的订单数量
            return Msg.success().add("pastDaysList", pastDaysList).add("orderList", orderList);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("message", "订单数据查询失败！");
        }
    }

    // get order numbers by month 获取近7个月订单数据
    @ResponseBody
    @RequestMapping("/Console/getOrderByMonth")
    public Msg getOrderNumberByMonth() {
        List<String> pastMonthList = new ArrayList<>();  // x轴数据 近7月的时间 String
        for (int i = 0; i < 8; i++) {
            pastMonthList.add(getPastMonth(i));
        }
        try {
            List<Integer> orderList = orderService.selectNumberOfOrderByMonth(pastMonthList); // y轴数据 近7月的订单数量
            return Msg.success().add("pastMonthList", pastMonthList).add("orderList", orderList);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("message", "订单数据查询失败！");
        }
    }

    // get order number total 销量统计
    @ResponseBody
    @RequestMapping("/Console/getOrderTotal")
    public Msg getOrderTotal() {
        // 根据商品销售数量的前十 查询
        try {
            //List<Integer> orderNumbers = orderService.selectNumberLimit(); // y 轴数据
            //return Msg.success().add("orderNumbers", orderNumbers);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("message", "数据库操作失败！");
        }
    }
}
