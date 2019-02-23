package com.chatRobot.controller;

import com.chatRobot.model.GoodsCart;
import com.chatRobot.model.Msg;
import com.chatRobot.service.impl.GoodsCartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author sun xiaoming
 * @Title: GoodsController
 * @ProjectName WebGLShop
 * @date 2019/1/9 20:12
 */

@Controller
@RequestMapping("/Goods")
public class GoodsController {

    @Autowired
    GoodsCartServiceImpl goodsCartService;

    @RequestMapping("/add")
    @ResponseBody
    public Msg addGoodsInShopCart(Integer goodsId, Integer userId, Integer goodsAmount) {
        GoodsCart goodsCart = new GoodsCart();
        Date date = new Date();
        goodsCart.setGoodsId(goodsId);
        goodsCart.setUserId(userId);
        goodsCart.setGoodsAmount(goodsAmount);
        goodsCart.setCreateTime(date);
        goodsCart.setUpdateTime(date);

        // 检测 购物车中是否已经存在userId 和 goodsId 相同记录 若存在 数据合并；否则新建记录加入数据库
        List<GoodsCart> goodsCartList = goodsCartService.selectByExample(goodsCart);
        if (goodsCartList.isEmpty()) {
            //如果为空，直接插入
            try {
                // 商品加入购物车的数据库操作
                int flag = goodsCartService.insertSelective(goodsCart);
                if (flag > 0) {
                    return Msg.success();
                } else
                    return Msg.fail();
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail();
            }
        } else {
            // 否则根据主键 进行原有数据的更新
            goodsCart.setGoodsAmount(goodsCartList.get(0).getGoodsAmount() + 1);
            goodsCart.setCartid(goodsCartList.get(0).getCartid());
            try {
                int flag = goodsCartService.updateByPrimaryKey(goodsCart);
                if (flag > 0) {
                    return Msg.success();
                } else
                    return Msg.fail();
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail();
            }
        }
    }
}
