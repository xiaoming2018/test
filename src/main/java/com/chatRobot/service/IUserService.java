package com.chatRobot.service;

import com.chatRobot.model.User;

import java.util.List;

public interface IUserService {
    User selectByPrimaryKey(Integer userId);

    List<User> selectByEmail(String email);
}
