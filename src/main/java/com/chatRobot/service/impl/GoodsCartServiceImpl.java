package com.chatRobot.service.impl;

import com.chatRobot.dao.GoodsCartMapper;
import com.chatRobot.model.GoodsCart;
import com.chatRobot.model.GoodsCartExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sun xiaoming
 * @Title: GoodsCartServiceImpl
 * @ProjectName WebGLShop
 * @date 2019/2/23 16:15
 */
@Service("GoodsCartService")
public class GoodsCartServiceImpl {

    @Resource
    GoodsCartMapper goodsCartMapper;

    public int insertSelective(GoodsCart goodsCart){
        //根据 goodsCart对象插入goodscart表中
        int flag = goodsCartMapper.insertSelective(goodsCart);
        return flag;
    }

    public List<GoodsCart> selectByExample(GoodsCart goodsCart){
        // 根据goodsId 和 userId 查询购物车记录
        GoodsCartExample goodsCartExample = new GoodsCartExample();
        GoodsCartExample.Criteria criteria = goodsCartExample.createCriteria();
        criteria.andGoodsIdEqualTo(goodsCart.getGoodsId());
        criteria.andUserIdEqualTo(goodsCart.getUserId());
        List<GoodsCart> goodsCartList = goodsCartMapper.selectByExample(goodsCartExample);
        return goodsCartList;
    }

    public int updateByPrimaryKey(GoodsCart goodsCart){
        // 根据购物车主键进行商品数量的更新
        int flag = goodsCartMapper.updateByPrimaryKey(goodsCart);
        return flag;
    }

    public List<GoodsCart> selectByUserId(Integer UserId){
        // 根据UserId选取所有的加入购物车中的记录
        GoodsCartExample goodsCartExample = new GoodsCartExample();
        GoodsCartExample.Criteria criteria = goodsCartExample.createCriteria();
        criteria.andUserIdEqualTo(UserId);
        List<GoodsCart> goodsCartList = goodsCartMapper.selectByExample(goodsCartExample);
        return goodsCartList;
    }

    public int deleteByUserIdAndGoodsId(Integer userId,Integer goodsId){
        GoodsCartExample goodsCartExample = new GoodsCartExample();
        GoodsCartExample.Criteria criteria = goodsCartExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andGoodsIdEqualTo(goodsId);
        int flag = goodsCartMapper.deleteByExample(goodsCartExample);
        return flag;
    }
}
