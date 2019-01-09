package com.chatRobot.service.impl;

import com.chatRobot.dao.GoodsMapper;
import com.chatRobot.model.Goods;
import com.chatRobot.model.GoodsExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sun xiaoming
 * @Title: GoodsServiceImpl
 * @ProjectName WebGLShop
 * @date 2019/1/9 15:39
 */
@Service("GoodsService")
public class GoodsServiceImpl {

    @Resource
    private GoodsMapper goodsMapper;

    //查询所有商品
    public List<Goods> selectAllGoods(){
        GoodsExample goodsExample = new GoodsExample();
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        return goodsList;
    }
}
