package com.chatRobot.service.impl;

import com.chatRobot.dao.GoodsTypeMapper;
import com.chatRobot.model.GoodsType;
import com.chatRobot.model.GoodsTypeExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public List<GoodsType> selectAll(){
        GoodsTypeExample example = new GoodsTypeExample();
        List<GoodsType> goodsTypeList = goodsTypeMapper.selectByExample(example);
        return goodsTypeList;
    }

}
