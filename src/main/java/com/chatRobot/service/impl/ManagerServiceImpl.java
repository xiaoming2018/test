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
@Service("ManagerService")
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

    // 查询 所有的manager
    public List<Manager> selectAllManagers(){
        ManagerExample example = new ManagerExample();
        return managerMapper.selectByExample(example);
    }

    // 进行 manager 插入
    public int insertBySelective(Manager manager){
        return managerMapper.insertSelective(manager);
    }

    // 根据id 查询 manager
    public Manager selectByManagerId(Integer managerId){
        return managerMapper.selectByPrimaryKey(managerId);
    }

    // 根据 manager对象更新数据
    public int updateByManagerSelective(Manager manager){
        return managerMapper.updateByPrimaryKeySelective(manager);
    }

    // 根据 manager对象
    public int deleteByManagerIds(List<Integer> managerIds){
        ManagerExample example = new ManagerExample();
        ManagerExample.Criteria criteria = example.createCriteria();
        criteria.andManagerIdIn(managerIds);
        return managerMapper.deleteByExample(example);
    }

    // 根据manager Id 删除对象
    public int deleteManagerById(Integer managerId){
        return managerMapper.deleteByPrimaryKey(managerId);
    }
}
