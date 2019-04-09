package com.chatRobot.controller;

import com.chatRobot.model.Manager;
import com.chatRobot.model.Msg;
import com.chatRobot.model.User;
import com.chatRobot.service.impl.ManagerServiceImpl;
import com.chatRobot.service.impl.UserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sun xiaoming
 * @Title: UserController
 * @ProjectName WebGLShop
 * @date 2019/4/8 21:19
 */
@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ManagerServiceImpl managerService;


    @ResponseBody
    @RequestMapping("/User/UsersData")
    public Msg getAllUsers(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "30") Integer limit) {
        try {
            PageHelper.startPage(page, limit);
            List<User> userList = userService.selectAllUsers();
            PageInfo pageInfo = new PageInfo(userList, limit);
            return Msg.success().add("PageInfo", pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }

    // 普通用户添加
    @ResponseBody
    @RequestMapping("/User/UserAdd")
    public Msg useradd(User user) {
        Date date = new Date();
        user.setUserUpdateTime(date);
        user.setUserCreateTime(date);
        // 用户使用email 作为唯一验证
        try {
            List<User> user1 = userService.selectByEmail(user.getUserEmail());
            if (user1.size() > 0) {
                return Msg.fail().add("message", "该邮箱已经存在，请重新注册");
            } else {
                try {
                    int flag = userService.insterSelective(user);
                    if (flag == 1) {
                        return Msg.success();
                    } else {
                        return Msg.fail().add("message", "user插入失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return Msg.fail().add("message", "user插入异常");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("message", "数据库查询失败");
        }
    }

    // 普通用户更新
    @ResponseBody
    @RequestMapping("/User/UserUpdate")
    public Msg userUpdate(User user) {
        System.out.println(user.toString());
        Date date = new Date();
        user.setUserUpdateTime(date);
        // 邮箱的唯一性检验  自身是否修改，修改后是否是唯一
        try {
            User usertemp = userService.selectByPrimaryKey(user.getUserId());
            if (usertemp.getUserEmail().equals(user.getUserEmail())) {
                // 如果邮箱未更改 进行其他数据的更新
                int flag = userService.updateUserBySelective(user);
                if (flag == 1) {
                    return Msg.success().add("message", "user数据更新成功");
                } else {
                    return Msg.fail().add("message", "user数据更新失败");
                }
            } else {
                List<User> userList = userService.selectByEmail(user.getUserEmail());
                if (userList.size() > 0) {
                    return Msg.fail().add("message", "该邮箱已经存在，请重新输入");
                } else {
                    try {
                        // user 数据更新
                        int flag = userService.updateUserBySelective(user);
                        if (flag == 1) {
                            return Msg.success().add("message", "user数据更新成功");
                        } else {
                            return Msg.fail().add("message", "user数据更新失败");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return Msg.fail();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("message", "根据邮箱查询User失败");
        }
    }

    // 普通用户删除
    @ResponseBody
    @RequestMapping("/User/UserDelete")
    public Msg userDelete(String del_userIds) {
        if (del_userIds.contains("-")) {
            // 批量删除
            List<Integer> del_userIdslist = new ArrayList<>();
            String[] del_userId = del_userIds.split("-");
            for (String string : del_userId) {
                del_userIdslist.add(Integer.parseInt(string));
            }
            try {
                int flag = userService.deleteUserByUserIds(del_userIdslist);
                if (flag == del_userId.length) {
                    return Msg.success().add("message", "user批量删除成功");
                } else {
                    return Msg.fail().add("message", "user批量删除失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail().add("message", "批量删除userList失败");
            }
        } else {
            // 单一删除
            try {
                int flag = userService.deleteUserById(Integer.parseInt(del_userIds));
                if (flag == 1) {
                    return Msg.success().add("message", "根据userId删除成功");
                } else {
                    return Msg.fail().add("message", "根据userId user删除失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail().add("message", "userId单一删除失败");
            }
        }
    }

    // 管理员用户 操作
    @ResponseBody
    @RequestMapping("/User/ManagerData")
    public Msg getAllmanager(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "30") Integer limit) {
        try {
            PageHelper.startPage(page, limit);
            //  manger 数据获取
            //List<Manager> managerList = userService.selectAllManagers();   ///  //  service重写
            List<Manager> managerList = managerService.selectAllManagers();
            PageInfo pageInfo = new PageInfo(managerList, limit);
            return Msg.success().add("PageInfo", pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }

    // 管理员用户 添加
    @ResponseBody
    @RequestMapping("/User/ManagerAdd")
    public Msg managerAdd(Manager manager) {
        Date date = new Date();
        manager.setCreateTime(date);
        manager.setUpdateTime(date);
        // 登陆账户的唯一性检验
        try {
            List<Manager> managerList = managerService.selectByExample(manager.getManagerAccount());
            if (managerList.size() == 0) {
                // 数据库插入
                int flag = managerService.insertBySelective(manager);
                if (flag == 1) {
                    return Msg.success().add("message", "manager 插入成功！");
                } else {
                    return Msg.fail().add("message", "manager 插入失败！");
                }
            } else {
                return Msg.fail().add("message", "该登陆账户已经存在，请重新设定！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("message", "账户唯一性检验失败");
        }
    }

    // 管理员用户 编辑更新
    @ResponseBody
    @RequestMapping("/User/ManagerUpdate")
    public Msg managerUpdate(Manager manager) {
        // 管理员账户的唯一性检验
        try {
            Manager oringinManager = managerService.selectByManagerId(manager.getManagerId());
            if (oringinManager.getManagerAccount().equals(manager.getManagerAccount())) {
                // 若登陆账户未改变 进行其他数据的更新
                int flag = managerService.updateByManagerSelective(manager);
                if (flag == 1) {
                    return Msg.success().add("message", "manager数据更新成功！");
                } else {
                    return Msg.fail().add("message", "manager数据更新失败！");
                }
            } else {
                // 唯一性检验后 继续数据库插入
                List<Manager> managerList = managerService.selectByExample(manager.getManagerAccount());
                if (managerList.size() == 0) {
                    int flag = managerService.updateByManagerSelective(manager);
                    if (flag == 1) {
                        return Msg.success().add("message", "manager数据更新成功！");
                    } else {
                        return Msg.fail().add("message", "manager数据更新失败！");
                    }
                } else {
                    return Msg.fail().add("message", "manager账户已经存在 请重新输入！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("message", "根据managerId查询数据失败！");
        }
    }

    // admin manager 删除操作
    @ResponseBody
    @RequestMapping("/User/ManagerDelete")
    public Msg managerDelete(String del_managerIds) {
        if (del_managerIds.contains("-")) {
            // 批量删除
            List<Integer> del_managerIdList = new ArrayList<>();
            String[] del_managerId = del_managerIds.split("-");
            for (String string : del_managerId) {
                del_managerIdList.add(Integer.parseInt(string));
            }
            try {
                int flag = managerService.deleteByManagerIds(del_managerIdList);
                if (flag == del_managerId.length) {
                    return Msg.success().add("message", "批量删除manager成功！");
                } else {
                    return Msg.fail().add("message", "批量删除manager失败！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail().add("message", "批量删除manager失败！");
            }
        } else {
            // 单一删除
            try{
                int flag = managerService.deleteManagerById(Integer.parseInt(del_managerIds));
                if(flag == 1){
                    return Msg.success().add("message","删除manager成功！");
                }else{
                    return Msg.fail().add("message","删除manager失败！");
                }
            }catch (Exception e){
                e.printStackTrace();
                return Msg.fail().add("message","删除manager失败！");
            }
        }
    }
}
