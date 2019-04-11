package com.chatRobot.controller;

import com.chatRobot.model.*;
import com.chatRobot.service.impl.*;
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
    @Autowired
    private ManagerServiceImpl managerService;
    @Autowired
    private OrderServiceImpl orderService;


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
    @RequestMapping("/adminWarn")
    public String PageToAdminWarn(Model model) {
        model.addAttribute("message", "页面正在开发中。。");
        return "admin/adminWarn";
    }

    @RequestMapping("/warn")
    public String PageToWarn(String message, Model model) {
        model.addAttribute("message", message);
        return "warn";
    }

    /*================= admin 请求 ================*/

    @RequestMapping("/admin")
    public String PageToAdmin() {
        // 跳转到登陆界面
        return "admin/adminLogin";
    }

    @RequestMapping("/adminIndex")
    public String PageToAdminIndex(HttpSession session) {
        // 跳转到后台管理主界面
        if (session.getAttribute("admin") == null) {
            //若管理员未登陆
            return "admin/adminLogin";
        } else {
            return "admin/adminIndex";
        }
    }

    @RequestMapping("/adminProduct")
    public String adminProductwith() {
        /**
         * @Author: sun xiaoming
         * @Description: adminIndex 跳转到adminProduct 页面
         */
        return "admin/adminProduct/adminProduct";
    }

    @RequestMapping("/adminProType")
    public String adminProType() {
        /**
         * @Author: sun xiaoming
         * @Description: 跳转到adminProtype 页面
         */
        return "admin/adminProduct/adminProType";
    }

    @RequestMapping("/adminProModelFile")
    public String adminProModelFile() {
        /**
         * @Author: sun xiaoming
         * @Description: 跳转到adminProModel jsp
         */
        return "admin/adminProduct/adminProModel";
    }

    @RequestMapping("/getProductAdd")
    public String getProductAddJsp(Model model) {
        /**
         * @Author: sun xiaoming
         * @Description: return product add jsp页面
         */
        // 数据准备 查询到所有的产品类型
        List<GoodsType> goodsTypeList = goodsTypeService.selectAll();
        model.addAttribute("goodsTypeList", goodsTypeList);
        return "admin/adminProduct/ProductBase/ProductBaseAdd";
    }

    @RequestMapping("/getProductEdit")
    public String getProductEdit(Integer goodsId, Model model) {
        Goods goods = goodsService.selectGoodsWithId(goodsId);
        List<GoodsType> goodsTypeList = goodsTypeService.selectAll();
        model.addAttribute("goodsTypeList", goodsTypeList);
        model.addAttribute("Goods", goods);
        return "admin/adminProduct/ProductBase/EditProduct";
    }

    @RequestMapping("/getProductDetail")
    public String getProductDetail(Integer goodsId, Model model) {
        /**
         * @Author: sun xiaoming
         * @Description: admin 下 返回商品详情页
         */
        try {
            Goods goods = goodsService.selectGoodsWithId(goodsId);
            GoodsModel goodsModel = goodsService.selectGoodsModelWithId(goods.getGoodsModelId());
            GoodsType goodsType = goodsTypeService.selectWithTypeId(goods.getGoodsTypeId());
            model.addAttribute("Goods", goods);
            model.addAttribute("goodsModel", goodsModel);
            model.addAttribute("goodsType", goodsType);
            return "admin/adminProduct/ProductBase/adminProductDetail";
        } catch (Exception e) {
            e.printStackTrace();
            return "warn";
        }
    }

    @RequestMapping("/getProductFile")
    public String getProductFile(Integer goodsId, Model model) {
        /**
         * @Author: sun xiaoming
         * @Description: 对模型文件进行设置上传。参数设置。
         */
        List<GoodsModel> modelList = goodsService.selectAllGoodsModel();
        model.addAttribute("GoodsModelList", modelList);
        Goods goods = goodsService.selectGoodsWithId(goodsId);
        model.addAttribute("Goods", goods);
        return "admin/adminProduct/ProductBase/FileProduct";
    }

    /*============================*/
    // 商品类型处理
    @RequestMapping("/getProductTypeAdd")
    public String getProductTypeAdd() {
        return "admin/adminProduct/ProductType/ProductTypeAdd";
    }

    @RequestMapping("/getProductTypeEdit")
    public String getProductTypeEdit(Integer goodstypeId, Model model) {
        try {
            GoodsType goodsType = goodsTypeService.selectWithTypeId(goodstypeId);
            model.addAttribute("goodsType", goodsType);
            return "admin/adminProduct/ProductType/ProductTypeEdit";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "根据typeId获取商品类型失败！");
            return "warn";
        }
    }

    @RequestMapping("/getProductTypeDetail")
    public String getProductTypeDetail(Integer goodstypeId, Model model) {
        /**
         * @Description: 返回 goodstype的详细信息
         */
        try {
            GoodsType goodsType = goodsTypeService.selectWithTypeId(goodstypeId);
            model.addAttribute("goodsType", goodsType);
            return "admin/adminProduct/ProductType/ProductTypeDetail";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "根据typeId查询商品模型信息失败！");
            return "Warn";
        }
    }

    /*============================*/
    // 商品模型处理
    @RequestMapping("/getProductModelFileAdd")
    public String getProductModelFileAdd() {
        /**
         * @Author: sun xiaoming
         * @Description: adminProModel jsp 请求 返回 addModelFile.jsp
         */
        return "admin/adminProduct/ProductModel/ProductModelAdd";
    }

    @RequestMapping("/getProductModelFileEdit")
    public String getProductModelFileEdit(Integer goodsModelFileId, Model model) {
        /**
         * @Description: adminProMode jsp 请求 返回 editModelFile.jsp
         */
        try {
            GoodsModel goodsModel = goodsService.selectGoodsModelWithId(goodsModelFileId);
            model.addAttribute("GoodsModelFile", goodsModel);
            return "admin/adminProduct/ProductModel/ProductModelEdit";
        } catch (Exception e) {
            e.printStackTrace();
            return "admin/adminWarn";
        }
    }

    /*============================*/
    // 商品库存处理
    @RequestMapping("/adminProStorge")
    public String getAdminProStroge() {
        /**
         * @Description: 获取商品库存页面
         */
        return "admin/adminProduct/adminProStorge";
    }

    /*==========  admin 用户管理 =================*/

    @RequestMapping("/adminUser")
    public String getadminUser() {
        /**
         * @Description: adminIndex 进入 用户管理页面
         */
        return "admin/adminUser/adminUserIndex";
    }

    // admin get UserAdd jsp
    @RequestMapping("/getUserAdd")
    public String getUserAdd() {
        /**
         * @Description: 获取 admin UserAdd jsp页面
         */
        return "admin/adminUser/adminBaseUser/adminBaseUseradd";
    }

    // admin get UserEdit jsp
    @RequestMapping("/getUserEdit")
    public String getUserEdit(Integer userId, Model model) {
        // 获取 admin UserEdit jsp页面
        try {
            User user = userService.selectByPrimaryKey(userId);
            model.addAttribute("User", user);
            return "admin/adminUser/adminBaseUser/adminBaseUserEdit";
        } catch (Exception e) {
            e.printStackTrace();
            return "warn";
        }
    }

    // 左侧功能区点击时返回admin User base jsp
    @RequestMapping("/adminBaseUser")
    public String getadminBseUserJsp() {
        return "admin/adminUser/adminBaseUser/adminBaseUser";
    }

    // 左侧功能区 点击是返回 admin User base jsp
    @RequestMapping("/adminManagerUser")
    public String getadminManagerUserJsp() {
        return "admin/adminUser/adminManager/adminManager";
    }

    // admin manager add jsp
    @RequestMapping("/getManagerAdd")
    public String getManagerAdd() {
        return "admin/adminUser/adminManager/adminManagerAdd";
    }

    // admin manager edit jsp
    @RequestMapping("/getManagerEdit")
    public String getgetManagerEdit(Integer managerId, Model model) {
        Manager manager = managerService.selectByManagerId(managerId);
        model.addAttribute("Manager", manager);
        return "admin/adminUser/adminManager/adminManagerEdit";
    }

    /*========================== 订单管理 =========================================*/
    @RequestMapping("/adminOrder")
    public String getadminOrder() {
        return "admin/adminOrder/adminOrderIndex";
    }

    // admin order add jsp
    @RequestMapping("/getOrderAdd")
    public String getOrderadd(Model model) {
        try {
            List<User> users = userService.selectAllUsers();
            List<Goods> goods = goodsService.selectAllGoods();
            model.addAttribute("Users", users);
            model.addAttribute("Goods", goods);
            return "admin/adminOrder/adminOrderAdd";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "order数据查询失败，请重新操作");
            return "admin/adminWarn";
        }
    }

    // admin get order Edit jsp
    @RequestMapping("/getOrderEdit")
    public String getOrderAdd(Integer orderId, Model model) {
        try{
            Order order = orderService.selectByOrderId(orderId);
            model.addAttribute("Order",order);
            List<User> users = userService.selectAllUsers();
            List<Goods> goods = goodsService.selectAllGoods();
            model.addAttribute("Users", users);
            model.addAttribute("Goods", goods);
            return "admin/adminOrder/adminOrderEdit";
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("message","查询order订单失败！");
            return "admin/adminWarn";
        }
    }

}
