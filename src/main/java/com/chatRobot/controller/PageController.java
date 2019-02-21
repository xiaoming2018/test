package com.chatRobot.controller;

import com.chatRobot.model.Goods;
import com.chatRobot.model.GoodsModel;
import com.chatRobot.service.impl.GoodsServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/page/")
public class PageController {
    /**
     * 配置页面跳转
     * author ：xiaoming
     */
    @Autowired
    private GoodsServiceImpl goodsServiceImpl;

    @RequestMapping("toIndex")
    public String PageToIndex(Model model){
        /**
        * @Author: sun xiaoming
        * @Description: 跳转到主页
        * @Date: 2019/1/8 15:51
        */
        //获取商品信息，回显在主页上
        //设置页码 和 页面大小
        PageHelper.startPage(1,1);
        List<Goods> goodsList = goodsServiceImpl.selectAllGoods();
        //navigatePages : 连续显示的页数
        PageInfo<Goods> pageInfo = new PageInfo(goodsList,2);
        model.addAttribute("goodList",goodsList);
        // 将分页信息 查询得到数据信息 打包到 model中的pageinfo中。
        model.addAttribute("PageInfo",pageInfo);
        return "index";
    }

    @RequestMapping("toGoods")
    public String PageToGoodsWithID(Integer id,Model model){
        Goods good = goodsServiceImpl.selectGoodsWithId(id);
        GoodsModel goodsModel = goodsServiceImpl.selectGoodsModelWithId(good.getGoodsModelId());
        model.addAttribute("Goods",good);
        model.addAttribute("GoodsModelFile",goodsModel);
        return "GoodsInfo";
    }


}
