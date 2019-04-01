package com.chatRobot.service.impl;

import com.chatRobot.dao.GoodsMapper;
import com.chatRobot.dao.GoodsModelMapper;
import com.chatRobot.dao.GoodsTypeMapper;
import com.chatRobot.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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

    @Resource
    private GoodsTypeMapper goodsTypeMapper;

    // select all goods
    public List<Goods> selectAllGoods() {
        GoodsExample goodsExample = new GoodsExample();
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        return goodsList;
    }

    // select product with goodsId
    public Goods selectGoodsWithId(Integer id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        return goods;
    }

    // select product model file with goodsmodel id
    public GoodsModel selectGoodsModelWithId(Integer modelFileId) {
        GoodsModel goodsModel = goodsModelMapper.selectByPrimaryKey(modelFileId);
        return goodsModel;
    }

    // insertSelective
    public int insertSelective(Goods goods) {
        return goodsMapper.insertSelective(goods);
    }

    // updateSelective
    public int updateByExampleSelective(Goods goods) {
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    // delete by goodsId list
    public int deleteBygoodsIds(List<Integer> goodsIds) {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andGoodsIdIn(goodsIds);
        return goodsMapper.deleteByExample(goodsExample);
    }

    // delete by goodsId one
    public int deleteBygoodId(Integer goodsId){
        return goodsMapper.deleteByPrimaryKey(goodsId);
    }

    // select all good model
    public List<GoodsModel> selectAllGoodsModel(){
        GoodsModelExample example = new GoodsModelExample();
        List<GoodsModel> goodsModellist = goodsModelMapper.selectByExample(example);
        return goodsModellist;
    }

    // insertSelective modelFile
    public int insertSelectModelFile(GoodsModel goodsModel){
        return goodsModelMapper.insertSelective(goodsModel);
    }

    // select modelfile by example
    public GoodsModel selectModelFilebyExample(String modelName, String modelFile, String modelType, Date createTime){
        GoodsModelExample example = new GoodsModelExample();
        GoodsModelExample.Criteria criteria = example.createCriteria();
        criteria.andModelCreateTimeEqualTo(createTime);
        criteria.andModelNameEqualTo(modelName);
        criteria.andModelFileEqualTo(modelFile);
        criteria.andModelTypeEqualTo(modelType);
        List<GoodsModel> goodsModelList = goodsModelMapper.selectByExample(example);
        return goodsModelList.get(0);
    }

    // select all goodstype
    public List<GoodsType> selectAllGoodsType(){
        return goodsTypeMapper.selectByExample(new GoodsTypeExample());
    }

}
