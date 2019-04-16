package com.chatRobot.service.impl;

import com.chatRobot.dao.GoodsTypeMapper;
import com.chatRobot.model.GoodsType;
import com.chatRobot.model.GoodsTypeExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sun xiaoming
 * @Title: GoodsTypeServiceImpl
 * @ProjectName WebGLShop
 * @date 2019/3/21 10:24
 */
@Service("GoodsTypeService")
public class GoodsTypeServiceImpl {
    @Resource
    private GoodsTypeMapper goodsTypeMapper;

    public List<GoodsType> selectAll() {
        GoodsTypeExample example = new GoodsTypeExample();
        List<GoodsType> goodsTypeList = goodsTypeMapper.selectByExample(example);
        return goodsTypeList;
    }

    public GoodsType selectWithTypeId(Integer goodsTypeId) {
        GoodsTypeExample example = new GoodsTypeExample();
        GoodsTypeExample.Criteria criteria = example.createCriteria();
        criteria.andGoodstypeIdEqualTo(goodsTypeId);
        List<GoodsType> goodsTypeList = goodsTypeMapper.selectByExample(example);
        return goodsTypeList.get(0);
    }

    public int insertGoodsTypeSelective(GoodsType goodsType) {
        return goodsTypeMapper.insertSelective(goodsType);
    }

    public int deleteBatchByTypeIds(List<Integer> typeIdList) {
        GoodsTypeExample example = new GoodsTypeExample();
        GoodsTypeExample.Criteria criteria = example.createCriteria();
        criteria.andGoodstypeIdIn(typeIdList);
        return goodsTypeMapper.deleteByExample(example);
    }

    public int deleteByTypeId(Integer typeId) {
        return goodsTypeMapper.deleteByPrimaryKey(typeId);
    }

    public int updateTypeSelective(GoodsType goodsType) {
        return goodsTypeMapper.updateByPrimaryKeySelective(goodsType);
    }

    public List<String> selectWithIds(List<Integer> typeIds){
        GoodsTypeExample example = new GoodsTypeExample();
        GoodsTypeExample.Criteria criteria = example.createCriteria();
        criteria.andGoodstypeIdIn(typeIds);
        List<GoodsType> goodsTypes = goodsTypeMapper.selectByExample(example);
        List<String> typeNames = new ArrayList<>();
        for (GoodsType goodsType:goodsTypes) {
            typeNames.add(goodsType.getGoodstypeName());
        }
        return typeNames;
    }
}
