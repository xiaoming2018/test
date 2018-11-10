package com.chaRobot.dao;

import com.chatRobot.dao.IUserDao;
import com.chatRobot.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class IUserDaoTest {

    @Autowired
    private IUserDao dao;

    @Test
    public void testSelectUser() throws Exception{
        long id = 2;
        User user = dao.selectUser(id);
        System.out.println(user.getUsername());
    }
}
