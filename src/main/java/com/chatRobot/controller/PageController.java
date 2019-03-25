package com.chatRobot.controller;

import com.chatRobot.model.*;
import com.chatRobot.service.impl.GoodsCartServiceImpl;
import com.chatRobot.service.impl.GoodsServiceImpl;
import com.chatRobot.service.impl.GoodsTypeServiceImpl;
import com.chatRobot.service.impl.UserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/page")
public class PageController {
    /**
     * 配置页面跳转
     * author ：xiaoming
     */
    @Autowired
    private GoodsServiceImpl goodsService;
    @Autowired
    private GoodsCartServiceImpl goodsCartService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private GoodsTypeServiceImpl goodsTypeService;


    @RequestMapping("/toIndex")
    public String PageToIndex(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        /**
         * @Author: sun xiaoming
         * @Description: 跳转到主页
         * @Date: 2019/1/8 15:51
         */
        //获取商品信息，回显在主页上
        //设置页码 和 页面大小
        PageHelper.startPage(pn, 6);
        List<Goods> goodsList = goodsService.selectAllGoods();
        //navigatePages : 连续显示的页数
        PageInfo<Goods> pageInfo = new PageInfo(goodsList, 6);
        model.addAttribute("goodList", goodsList);
        // 将分页信息 查询得到数据信息 打包到 model中的pageinfo中。
        model.addAttribute("PageInfo", pageInfo);
        return "index";
    }

    // 导航条 点击事件控制：
    @RequestMapping("/pageInfo")
    @ResponseBody
    public Msg updateIndexWithPageNumber(Integer pn, Model model) {
        System.out.println(pn);
        PageHelper.startPage(pn, 6);
        List<Goods> goodsList = goodsService.selectAllGoods();
        //navigatePages : 连续显示的页数
        PageInfo<Goods> pageInfo = new PageInfo(goodsList, 6);
        // 将分页信息 查询得到数据信息 打包到 model中的pageinfo中。
        //model.addAttribute("PageInfo", pageInfo);
        return Msg.success().add("pageInfo", pageInfo);
    }

    @RequestMapping("/toGoods")
    public String PageToGoodsWithID(Integer id, Model model) {
        /**
         * @Author: sun xiaoming
         * @Description: 跳转到对应商品详情页
         * @parm: GoodsId and model
         */
        Goods good = goodsService.selectGoodsWithId(id);
        GoodsModel goodsModel = goodsService.selectGoodsModelWithId(good.getGoodsModelId());
        model.addAttribute("Goods", good);
        model.addAttribute("GoodsModelFile", goodsModel);
        return "GoodsInfo";
    }

    @RequestMapping("/toCart")
    public String PageToGoodsCartWithUserId(Integer userId, Model model) {
        /**
         * @Description: 跳转到用户的购物车界面
         */
        List<Goods> goodsList = new ArrayList<>();
        List<GoodsCart> goodsCartList = goodsCartService.selectByUserId(userId);
        if (!goodsCartList.isEmpty()) {
            //如果购物车非空
            for (int i = 0; i < goodsCartList.size(); i++) {
                Goods goods = goodsService.selectGoodsWithId(goodsCartList.get(i).getGoodsId());
                // 返回购物车中商品数量 赋值给对应的商品数量
                goods.setGoodsAmount(goodsCartList.get(i).getGoodsAmount());
                goodsList.add(goods);
            }
            model.addAttribute("goodsList", goodsList);
            return "GoodsCart";
        } else {
            model.addAttribute("message", "购物车为空，请先购物。");
            return "warn";
        }
    }

    @RequestMapping("/toCheckOut")
    public String PageToCheckOut(Integer userId, Integer goodsId, Model model) {
        /**
         * @Author: sun xiaoming
         * @Description: 单一商品直接跳转到订单确认页面
         * @Date: 2019/2/28 15:05
         */
        User user = userService.selectByPrimaryKey(userId);
        Goods goods = goodsService.selectGoodsWithId(goodsId);
        model.addAttribute("User", user);
        model.addAttribute("Goods", goods);
        return "CheckOut";
    }

    // Good 商品页
    @RequestMapping("/Good")
    public String PageToGood(Model model) {
        model.addAttribute("message", "页面正在开发中。。");
        return "warn";
    }

    // Furniture 家具页面
    @RequestMapping("/Furniture")
    public String PageToFurniture(Model model) {
        model.addAttribute("message", "页面正在开发中。。");
        return "warn";
    }

    // Mail 邮箱
    @RequestMapping("/Mail")
    public String PageToMail(Model model) {
        model.addAttribute("message", "页面正在开发中。。");
        return "warn";
    }

    // admin 未开发页面提示
    @RequestMapping("/AdminWarn")
    public String PageToAdminWarn(Model model){
        model.addAttribute("message","页面正在开发中。。");
        return "AdminWarn";
    }

    @RequestMapping("/warn")
    public String PageToWarn(String message, Model model) {
        model.addAttribute("message", message);
        return "warn";
    }

    @RequestMapping("/Admin")
    public String PageToAdmin() {
        // 跳转到登陆界面
        return "adminLogin";
    }

    @RequestMapping("/AdminIndex")
    public String PageToAdminIndex(HttpSession session) {
        // 跳转到后台管理主界面
        if (session.getAttribute("admin") == null) {
            //若管理员未登陆
            return "adminLogin";
        } else {
            return "adminIndex";
        }
    }

    @RequestMapping("/AdminProduct")
    public String adminProductwith(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model){
        /**
        * @Author: sun xiaoming 
        * @Description: adminIndex 跳转到adminProduct 页面
        * @Date: 2019/3/17 15:15
        */
        PageHelper.startPage(pn, 6);
        List<Goods> goodsList = goodsService.selectAllGoods();
        //navigatePages : 连续显示的页数
        PageInfo<Goods> pageInfo = new PageInfo(goodsList, 6);
        model.addAttribute("goodList", goodsList);
        // 将分页信息 查询得到数据信息 打包到 model中的pageinfo中。
        model.addAttribute("PageInfo", pageInfo);
        return "adminProduct";
    }

    @RequestMapping("/getProductAdd")
    public String getProductAddJsp(Model model){
        /**
        * @Author: sun xiaoming 
        * @Description: return product add jsp页面
        */
        // 数据准备 查询到所有的产品类型
        List<GoodsType> goodsTypeList = goodsTypeService.selectAll();
        model.addAttribute("goodsTypeList",goodsTypeList);
        return "AddProduct";
    }

    @RequestMapping("getProductEdit")
    public String getProductEdit(Integer goodsId,Model model){
        Goods goods = goodsService.selectGoodsWithId(goodsId);
        List<GoodsType> goodsTypeList = goodsTypeService.selectAll();
        model.addAttribute("goodsTypeList",goodsTypeList);
        model.addAttribute("Goods",goods);
        return "EditProduct";
    }
}
