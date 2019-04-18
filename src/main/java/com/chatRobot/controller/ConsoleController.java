package com.chatRobot.controller;

import com.chatRobot.model.GoodsType;
import com.chatRobot.model.Msg;
import com.chatRobot.service.impl.GoodsServiceImpl;
import com.chatRobot.service.impl.GoodsTypeServiceImpl;
import com.chatRobot.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private GoodsServiceImpl goodsService;
    @Autowired
    private GoodsTypeServiceImpl goodsTypeService;

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

    public static void solveresult(List<Map<Integer, Integer>> mapsList, List<Integer> goodsIds, List<Integer> goodsAmounts) {
        for (Map<Integer, Integer> map : mapsList) {
            Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Integer> entry = it.next();
                goodsAmounts.add(entry.getValue());
                Map.Entry<Integer, Integer> entry1 = it.next();
                goodsIds.add(entry1.getValue());
            }
        }
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
        try {
            // 1 从 orderinfo 表中 获取销量前 10 的goodsId 和 数量
            List<Map<Integer, Integer>> mapsList = orderService.selectByLimit();
            System.out.println(mapsList.size());
            List<Integer> goodsIds = new ArrayList<>(); // 保存 goodsId
            List<Integer> goodsAmounts = new ArrayList<>(); // 保存 goodsAmount
            solveresult(mapsList, goodsIds, goodsAmounts);
            // 2 从 goodsinfo 表中 获取 typeId 根据typeId进行 number 计算总量和
            List<Integer> typeIds = goodsService.selectTypeIdsByGoodsIds(goodsIds);
            // 获取所有商品类型
            List<GoodsType> goodsTypeList = goodsTypeService.selectAll();  // 将所有的类型进行展示，根据商品类型数量显示
            Integer[] totalNumberList = new Integer[goodsTypeList.size()]; // 开辟与 type类型同等大小的 totalnumber
            for (int i = 0; i < goodsTypeList.size(); i++) {
                totalNumberList[i] = 0; // 初始化数组
            }
            List<String> typeNames = new ArrayList<>();
            for (GoodsType goodsType : goodsTypeList) {
                typeNames.add(goodsType.getGoodstypeName()); // 获取所有的类型名
            }
            // 双层循环，进行typeId 进行计算数量
            for (int i = 0; i < typeIds.size(); i++) {
                for (GoodsType goodsType : goodsTypeList) {
                    if (typeIds.get(i) == goodsType.getGoodstypeId()) {
                        totalNumberList[typeIds.get(i)] += Integer.parseInt(String.valueOf(goodsAmounts.get(i)));
                        continue;
                    }
                }
            }
            return Msg.success().add("totalNumberList", totalNumberList).add("typeNames", typeNames);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("message", "获取统计信息时出错！");
        }
    }

    // get Product best sell
    @ResponseBody
    @RequestMapping("/Console/getProductSell")
    public Msg getProductSell() {
        try {
            List<Map<Integer, Integer>> mapsList = orderService.selectByLimit();
            System.out.println(mapsList.size());
            List<Integer> goodsIds = new ArrayList<>(); // 保存 goodsId
            List<Integer> goodsAmounts = new ArrayList<>(); // 保存 goodsAmount
            solveresult(mapsList, goodsIds, goodsAmounts);
            List<String> goodsNames = goodsService.selectGoodsNames(goodsIds); // 获取商品名称。
            return Msg.success().add("goodsAmount", goodsAmounts).add("goodsNames", goodsNames);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("message", "查询热卖商品出错！");
        }
    }
}
