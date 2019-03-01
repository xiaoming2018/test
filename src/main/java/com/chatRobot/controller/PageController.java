package com.chatRobot.controller;

import com.chatRobot.model.Goods;
import com.chatRobot.model.GoodsCart;
import com.chatRobot.model.GoodsModel;
import com.chatRobot.model.User;
import com.chatRobot.service.impl.GoodsCartServiceImpl;
import com.chatRobot.service.impl.GoodsServiceImpl;
import com.chatRobot.service.impl.UserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/page")
public class PageController {
    /**
     * 配置页面跳转
     * author ：xiaoming
     */
    @Autowired
    private GoodsServiceImpl goodsServiceImpl;
    @Autowired
    private GoodsCartServiceImpl goodsCartService;
    @Autowired
    private UserServiceImpl userService;


    @RequestMapping("/toIndex")
    public String PageToIndex(Model model) {
        /**
         * @Author: sun xiaoming
         * @Description: 跳转到主页
         * @Date: 2019/1/8 15:51
         */
        //获取商品信息，回显在主页上
        //设置页码 和 页面大小
        PageHelper.startPage(1, 1);
        List<Goods> goodsList = goodsServiceImpl.selectAllGoods();
        //navigatePages : 连续显示的页数
        PageInfo<Goods> pageInfo = new PageInfo(goodsList, 2);
        model.addAttribute("goodList", goodsList);
        // 将分页信息 查询得到数据信息 打包到 model中的pageinfo中。
        model.addAttribute("PageInfo", pageInfo);
        return "index";
    }


    @RequestMapping("/toGoods")
    public String PageToGoodsWithID(Integer id, Model model) {
        /**
         * @Author: sun xiaoming
         * @Description: 跳转到对应商品详情页
         * @parm: GoodsId and model
         */
        Goods good = goodsServiceImpl.selectGoodsWithId(id);
        GoodsModel goodsModel = goodsServiceImpl.selectGoodsModelWithId(good.getGoodsModelId());
        model.addAttribute("Goods", good);
        model.addAttribute("GoodsModelFile", goodsModel);
        return "GoodsInfo";
    }

    @RequestMapping("/toCart")
    public String PageToGoodsCartWithUserId(Integer userId, Model model) {
        /**
         * @Description: 跳转到用户的购物车界面
         */
        List<Goods> goodsList = new ArrayList<>();
        List<GoodsCart> goodsCartList = goodsCartService.selectByUserId(userId);
        if (!goodsCartList.isEmpty()) {
            //如果购物车非空
            for (int i = 0; i < goodsCartList.size(); i++) {
                Goods goods = goodsServiceImpl.selectGoodsWithId(goodsCartList.get(i).getGoodsId());
                // 返回购物车中商品数量 赋值给对应的商品数量
                goods.setGoodsAmount(goodsCartList.get(i).getGoodsAmount());
                goodsList.add(goods);
            }
            model.addAttribute("goodsList", goodsList);
            return "GoodsCart";
        } else {
            model.addAttribute("message", "购物车为空，请购物。");
            return "warn";
        }
    }

    @RequestMapping("/toCheckOut")
    public String PageToCheckOut(Integer userId, Integer goodsId, Model model) {
        /**
         * @Author: sun xiaoming
         * @Description: 跳转到订单确认页面
         * @Date: 2019/2/28 15:05
         */
        User user = userService.selectByPrimaryKey(userId);
        Goods goods = goodsServiceImpl.selectGoodsWithId(goodsId);
        model.addAttribute("User", user);
        model.addAttribute("Goods", goods);
        return "CheckOut";
    }
}
