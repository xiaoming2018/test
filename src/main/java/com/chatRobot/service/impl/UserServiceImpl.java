package com.chatRobot.service.impl;

import com.chatRobot.dao.UserMapper;
import com.chatRobot.model.User;
import com.chatRobot.model.UserExample;
import com.chatRobot.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;


    public User selectByPrimaryKey(Integer userId) {
        return this.userMapper.selectByPrimaryKey(userId);
    }

    public List<User> selectByEmail(String email) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserEmailEqualTo(email);
        List<User> userList = this.userMapper.selectByExample(userExample);
        return userList;
    }

}
