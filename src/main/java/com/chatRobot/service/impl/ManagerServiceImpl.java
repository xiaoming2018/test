package com.chatRobot.service.impl;

import com.chatRobot.dao.ManagerMapper;
import com.chatRobot.model.Manager;
import com.chatRobot.model.ManagerExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sun xiaoming
 * @Title: ManagerServiceImpl
 * @ProjectName WebGLShop
 * @date 2019/3/14 17:03
 */
@Service("MnagerService")
public class ManagerServiceImpl {
    @Resource
    private ManagerMapper managerMapper;

    public List<Manager> selectByExample(String account){
        ManagerExample managerExample = new ManagerExample();
        ManagerExample.Criteria criteria = managerExample.createCriteria();
        criteria.andManagerAccountEqualTo(account);
        List<Manager> managerList = managerMapper.selectByExample(managerExample);
        return managerList;
    }
}
