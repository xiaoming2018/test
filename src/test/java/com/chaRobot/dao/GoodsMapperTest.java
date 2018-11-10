package com.chaRobot.dao;

import com.chatRobot.dao.GoodsMapper;
import com.chatRobot.model.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class GoodsMapperTest {

    @Autowired
    private GoodsMapper GoodsMapper;

    @Test
    public void TestGoodMapper() throws Exception{
        Integer id = 1;
        Goods goods = this.GoodsMapper.selectByPrimaryKey(id);
        System.out.println(goods.toString());
    }
}
