package com.chatRobot.service;

import com.chatRobot.model.User;

public interface IUserService {
    public User selectByPrimaryKey(Integer userId);
}
