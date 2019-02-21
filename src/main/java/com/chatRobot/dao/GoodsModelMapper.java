package com.chatRobot.dao;

import com.chatRobot.model.GoodsModel;
import com.chatRobot.model.GoodsModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsModelMapper {
    long countByExample(GoodsModelExample example);

    int deleteByExample(GoodsModelExample example);

    int deleteByPrimaryKey(Integer modelId);

    int insert(GoodsModel record);

    int insertSelective(GoodsModel record);

    List<GoodsModel> selectByExample(GoodsModelExample example);

    GoodsModel selectByPrimaryKey(Integer modelId);

    int updateByExampleSelective(@Param("record") GoodsModel record, @Param("example") GoodsModelExample example);

    int updateByExample(@Param("record") GoodsModel record, @Param("example") GoodsModelExample example);

    int updateByPrimaryKeySelective(GoodsModel record);

    int updateByPrimaryKey(GoodsModel record);
}