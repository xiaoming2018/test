package com.chatRobot.dao;

import com.chatRobot.model.GoodsCart;
import com.chatRobot.model.GoodsCartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsCartMapper {
    long countByExample(GoodsCartExample example);

    int deleteByExample(GoodsCartExample example);

    int deleteByPrimaryKey(Integer cartid);

    int insert(GoodsCart record);

    int insertSelective(GoodsCart record);

    List<GoodsCart> selectByExample(GoodsCartExample example);

    GoodsCart selectByPrimaryKey(Integer cartid);

    int updateByExampleSelective(@Param("record") GoodsCart record, @Param("example") GoodsCartExample example);

    int updateByExample(@Param("record") GoodsCart record, @Param("example") GoodsCartExample example);

    int updateByPrimaryKeySelective(GoodsCart record);

    int updateByPrimaryKey(GoodsCart record);
}