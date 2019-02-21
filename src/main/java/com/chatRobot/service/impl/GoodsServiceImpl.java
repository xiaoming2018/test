package com.chatRobot.service.impl;

import com.chatRobot.dao.GoodsMapper;
import com.chatRobot.dao.GoodsModelMapper;
import com.chatRobot.model.Goods;
import com.chatRobot.model.GoodsExample;
import com.chatRobot.model.GoodsModel;
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

    @Resource
    private GoodsModelMapper goodsModelMapper;

    //查询所有商品
    public List<Goods> selectAllGoods(){
        GoodsExample goodsExample = new GoodsExample();
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        return goodsList;
    }

    // select product with goodsId
    public Goods selectGoodsWithId(Integer id){
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        return goods;
    }

    // select product model file with goodsmodel id
    public GoodsModel selectGoodsModelWithId(Integer modelFileId){
        GoodsModel goodsModel = goodsModelMapper.selectByPrimaryKey(modelFileId);
        return goodsModel;
    }
}
