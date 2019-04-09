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

    public int insterSelective(User user) {
        return userMapper.insertSelective(user);
    }

    // admin select all users
    public List<User> selectAllUsers(){
        UserExample example = new UserExample();
        return userMapper.selectByExample(example);
    }

    // admin update User Selective
    public int updateUserBySelective(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }

    // admin delete User with UserIdList
    public int deleteUserByUserIds(List<Integer> userIds){
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdIn(userIds);
        return userMapper.deleteByExample(example);
    }

    // admin delete user with userId (single)
    public int deleteUserById(Integer userId){
        return userMapper.deleteByPrimaryKey(userId);
    }

}
