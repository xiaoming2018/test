package com.chatRobot.controller;

import com.chatRobot.model.Goods;
import com.chatRobot.model.GoodsCart;
import com.chatRobot.model.Msg;
import com.chatRobot.service.impl.GoodsCartServiceImpl;
import com.chatRobot.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
    @Autowired
    GoodsServiceImpl goodsService;


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
            // 否则根据主键进行原有数量的更新
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

    // 购物车中的数据更新
    @ResponseBody
    @RequestMapping("/Remove")
    public Msg CartRemoveGoods(Integer userId, Integer goodsId) {
        System.out.println(userId + goodsId);
        // 购物车中删除购物记录
        try {
            int flag;
            flag = goodsCartService.deleteByUserIdAndGoodsId(userId, goodsId);
            if (flag == 0) {
                return Msg.fail();
            } else {
                return Msg.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return Msg.success();
        }
    }

    // 购物界面的增加、减少按钮
    @ResponseBody
    @RequestMapping("/amountChange")
    public Msg goodsAmountAdd(Integer userId, Integer goodsId, Integer amount) {
        Date date = new Date();
        GoodsCart goodsCart = new GoodsCart();
        goodsCart.setGoodsId(goodsId);
        goodsCart.setUserId(userId);
        goodsCart.setGoodsAmount(amount);
        goodsCart.setUpdateTime(date);

        List<GoodsCart> goodsCartList = goodsCartService.selectByExample(goodsCart);
        if(goodsCartList.isEmpty()){
            return Msg.fail();
        }
        // 获取购物车 主键
        goodsCart.setCartid(goodsCartList.get(0).getCartid());
        Date createDate = goodsCartList.get(0).getCreateTime();
        goodsCart.setCreateTime(createDate);
        try{
            int flag = goodsCartService.updateByPrimaryKey(goodsCart);
            if(flag != 0){
                // 更新数据成功
                return Msg.success();
            }else{
                return Msg.fail();
            }
        }catch(Exception e){
            e.printStackTrace();
            return Msg.fail();
        }
    }

    // 结算中心的更新
    @ResponseBody
    @RequestMapping("/updateCart")
    public Msg updateGoodsCart(Integer userId, Model model) {
        List<Goods> goodsList = new ArrayList<>();
        List<GoodsCart> goodsCartList = goodsCartService.selectByUserId(userId);
        double totalPrice = 0;
        if (!goodsCartList.isEmpty()) {
            //如果购物车非空
            for (int i = 0; i < goodsCartList.size(); i++) {
                Goods goods = goodsService.selectGoodsWithId(goodsCartList.get(i).getGoodsId());
                // 返回购物车中商品数量 赋值给对应的商品数量
                goods.setGoodsAmount(goodsCartList.get(i).getGoodsAmount());
                totalPrice += goods.getGoodsPrice().doubleValue() * goods.getGoodsAmount();
                goodsList.add(goods);
            }
            model.addAttribute("goodsList", goodsList);
            model.addAttribute("totalPrice", totalPrice);
            return Msg.success().add("goodsList", goodsList).add("totalPrice", totalPrice);
        } else {
            return Msg.fail();
        }
    }
}
