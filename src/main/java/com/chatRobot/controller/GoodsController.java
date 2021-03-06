package com.chatRobot.controller;

import com.chatRobot.model.*;
import com.chatRobot.service.impl.GoodsCartServiceImpl;
import com.chatRobot.service.impl.GoodsServiceImpl;
import com.chatRobot.service.impl.GoodsTypeServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sun xiaoming
 * @Title: GoodsController
 * @ProjectName WebGLShop
 * @date 2019/1/9 20:12
 */

@Controller
@RequestMapping("/Goods")
public class GoodsController {

    @Autowired
    GoodsCartServiceImpl goodsCartService;
    @Autowired
    GoodsServiceImpl goodsService;
    @Autowired
    GoodsTypeServiceImpl goodsTypeService;

    @ResponseBody
    @RequestMapping("/GetGoods")
    public Msg getGoods(Integer goodsId){
        try{
            Goods goods = goodsService.selectGoodsWithId(goodsId);
            return Msg.success().add("Goods",goods);
        }catch (Exception e){
            e.printStackTrace();
            return Msg.fail().add("message","查询good失败！");
        }
    }

    @RequestMapping("/add")
    @ResponseBody
    public Msg addGoodsInShopCart(Integer goodsId, Integer userId, Integer goodsAmount) {
        GoodsCart goodsCart = new GoodsCart();
        Date date = new Date();
        goodsCart.setGoodsId(goodsId);
        goodsCart.setUserId(userId);
        goodsCart.setGoodsAmount(goodsAmount);
        goodsCart.setCreateTime(date);
        goodsCart.setUpdateTime(date);
        // 检测 购物车中是否已经存在userId 和 goodsId 相同记录 若存在 数据合并；否则新建记录加入数据库
        List<GoodsCart> goodsCartList = goodsCartService.selectByExample(goodsCart);
        if (goodsCartList.isEmpty()) {
            //如果为空，直接插入
            try {
                // 商品加入购物车的数据库操作
                int flag = goodsCartService.insertSelective(goodsCart);
                if (flag > 0) {
                    return Msg.success();
                } else
                    return Msg.fail();
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail();
            }
        } else {
            // 否则根据主键进行原有数量的更新
            goodsCart.setGoodsAmount(goodsCartList.get(0).getGoodsAmount() + 1);
            goodsCart.setCartid(goodsCartList.get(0).getCartid());
            try {
                int flag = goodsCartService.updateByPrimaryKey(goodsCart);
                if (flag > 0) {
                    return Msg.success();
                } else
                    return Msg.fail();
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail();
            }
        }
    }

    // 购物车中的数据更新
    @ResponseBody
    @RequestMapping("/Remove")
    public Msg CartRemoveGoods(Integer userId, Integer goodsId) {
        // 购物车中删除购物记录
        try {
            int flag = goodsCartService.deleteByUserIdAndGoodsId(userId, goodsId);
            if (flag == 0) {
                return Msg.fail();
            } else {
                return Msg.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }

    // 购物界面的增加、减少按钮
    @ResponseBody
    @RequestMapping("/amountChange")
    public Msg goodsAmountAdd(Integer userId, Integer goodsId, Integer amount) {
        Date date = new Date();
        GoodsCart goodsCart = new GoodsCart();
        goodsCart.setGoodsId(goodsId);
        goodsCart.setUserId(userId);
        goodsCart.setGoodsAmount(amount);
        goodsCart.setUpdateTime(date);
        List<GoodsCart> goodsCartList = goodsCartService.selectByExample(goodsCart);
        if (goodsCartList.isEmpty()) {
            return Msg.fail();
        }
        // 获取购物车 主键
        goodsCart.setCartid(goodsCartList.get(0).getCartid());
        Date createDate = goodsCartList.get(0).getCreateTime();
        goodsCart.setCreateTime(createDate);
        try {
            int flag = goodsCartService.updateByPrimaryKey(goodsCart);
            if (flag != 0) {
                // 更新数据成功
                return Msg.success();
            } else {
                return Msg.fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }

    // 结算中心的更新
    @ResponseBody
    @RequestMapping("/updateCart")
    public Msg updateGoodsCart(Integer userId) {
        List<Goods> goodsList = new ArrayList<>();
        List<GoodsCart> goodsCartList = goodsCartService.selectByUserId(userId);
        double totalPrice = 0.0;
        int totalGoodsAmount = 0;
        if (!goodsCartList.isEmpty()) {
            //如果购物车非空 根据购物车获取 订单信息
            for (int i = 0; i < goodsCartList.size(); i++) {
                Goods goods = goodsService.selectGoodsWithId(goodsCartList.get(i).getGoodsId());
                // 返回购物车中商品数量 赋值给对应的商品数量
                goods.setGoodsAmount(goodsCartList.get(i).getGoodsAmount());
                totalGoodsAmount += goodsCartList.get(i).getGoodsAmount();
                totalPrice += goods.getGoodsPrice().doubleValue() * goods.getGoodsAmount();
                goodsList.add(goods);
            }
            return Msg.success().add("goodsList", goodsList).add("totalPrice", totalPrice).add("totalGoodsAmount", totalGoodsAmount);
        } else {
            // 购物车为空
            return Msg.fail().add("message","购物车为空！");
        }
    }

    // 商品基础信息数据表格
    @ResponseBody
    @RequestMapping("/GoodsData")
    public Msg getGoodsDataJson(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "30") Integer limit) {
        PageHelper.startPage(page, limit);
        List<Goods> goodsLists = goodsService.selectAllGoods();
        PageInfo pageInfo = new PageInfo(goodsLists, limit);
        return Msg.success().add("PageInfo", pageInfo);
    }

    // 商品类型的数据表格
    @ResponseBody
    @RequestMapping("/GoodsTypeData")
    public Msg getGoodsTypeDataJson(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "30") Integer limit) {
        PageHelper.startPage(page, limit);
        List<GoodsType> goodsTypeList = goodsService.selectAllGoodsType();
        PageInfo pageInfo = new PageInfo(goodsTypeList, limit);
        return Msg.success().add("PageInfo", pageInfo);
    }

    // 商品模型文件的数据表格
    @ResponseBody
    @RequestMapping("/GoodsModelFile")
    public Msg getGoodsModelFileDataJson(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "30") Integer limit) {
        PageHelper.startPage(page, limit);
        List<GoodsModel> goodsModelList = goodsService.selectAllGoodsModel();
        PageInfo pageInfo = new PageInfo(goodsModelList, limit);
        return Msg.success().add("PageInfo", pageInfo);
    }

    // Goodsinfo 添加商品
    @ResponseBody
    @RequestMapping("/GoodsAdd")
    public Msg addProduct(Goods goods) {
        // 商品信息参数初始化
        Date date = new Date();
        goods.setGoodsStatus("0"); // 设置商品状态
        goods.setGoodsCreateTime(date);
        goods.setGoodsUpdateTime(date);
        goods.setGoodsSellAmount(0);
        goods.setGoodsRemark("1");
        if (goods.getGoodsIsnew() == null) {
            goods.setGoodsIsnew(false);
        }
        try {
            int flag = goodsService.insertSelective(goods);
            if (flag == 1) {
                return Msg.success();
            } else {
                return Msg.fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }

    // goodsinfo 更新基础商品信息 无modelfile 属性
    @ResponseBody
    @RequestMapping("/GoodsUpdate")
    public Msg updateProduct(Goods goods) {
        Date date = new Date();
        goods.setGoodsUpdateTime(date);
        if (goods.getGoodsIsnew() == null) {
            goods.setGoodsIsnew(false);
        }
        try {
            int flag = goodsService.updateByExampleSelective(goods);
            if (flag == 1) {
                return Msg.success();
            } else {
                return Msg.fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }

    // add modelFile to database and update goodsInfo table
    @ResponseBody
    @RequestMapping("/GoodsModelFileUpdate")
    public Msg updateProductWithModelFile(Goods goods, GoodsModel goodsModel) {
        Date date = new Date();
        goodsModel.setModelCreateTime(date);
        goodsModel.setModelUpdateTime(date);
        try {
            // 添加 modelfile
            int flag = goodsService.insertSelectModelFile(goodsModel);
            if (flag >= 1) {
                GoodsModel goodsModel1 = goodsService.selectModelFilebyExample(goodsModel.getModelName(),
                        goodsModel.getModelFile(), goodsModel.getModelType(), date);
                goods.setGoodsModelId(goodsModel1.getModelId());
                int updateflag = goodsService.updateByExampleSelective(goods);
                if (updateflag >= 1) {
                    return Msg.success().add("message", "modelfileId 更新成功！");
                } else {
                    return Msg.fail().add("message", "modelfileId 更新失败！");
                }
            } else {
                return Msg.fail().add("message", "modelfile 插入失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("message", "modelfile 插入时出错！");
        }
    }

    // goodsinfo 删除商品
    @ResponseBody
    @RequestMapping("/GoodsDelete")
    public Msg DeleteProduct(String del_goodsIds) {
        if (del_goodsIds.contains("-")) {
            List<Integer> del_goodsIdList = new ArrayList<>();
            String[] goodsIds = del_goodsIds.split("-");
            for (String string : goodsIds) {
                del_goodsIdList.add(Integer.parseInt(string));
            }
            try {
                // 批量删除
                int flag = goodsService.deleteBygoodsIds(del_goodsIdList);
                if (flag == goodsIds.length) {
                    return Msg.success();
                } else {
                    return Msg.fail();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail();
            }
        } else {
            try {
                int flag = goodsService.deleteBygoodId(Integer.parseInt(del_goodsIds));
                if (flag == 1) {
                    return Msg.success();
                } else {
                    return Msg.fail();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail();
            }
        }
    }

    /*================================== 商品类型处理 ==========================================*/
    // 添加商品类型
    @ResponseBody
    @RequestMapping("/GoodsTypeAdd")
    public Msg goodsTypeAdd(GoodsType goodsType) {
        Date date = new Date();
        goodsType.setGoodstypeCreatetime(date);
        goodsType.setGoodstypeUpdatatime(date);
        try {
            int flag = goodsTypeService.insertGoodsTypeSelective(goodsType);
            if (flag == 1) {
                return Msg.success().add("message", "商品类型添加成功");
            } else {
                return Msg.fail().add("message", "商品类型添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("message", "商品类型添加失败！");
        }
    }

    // 删除批量或单独删除商品类型
    @ResponseBody
    @RequestMapping("/GoodsTypeDelete")
    public Msg goodsTypeDelete(String del_goodstypeIds) {
        if (del_goodstypeIds.contains("-")) {
            List<Integer> del_goodstypeIdsList = new ArrayList<>();
            String[] typeIds = del_goodstypeIds.split("-");
            for (String string : typeIds) {
                del_goodstypeIdsList.add(Integer.parseInt(string));
            }
            try {
                // 批量删除
                int flag = goodsTypeService.deleteBatchByTypeIds(del_goodstypeIdsList);
                if (flag == typeIds.length) {
                    return Msg.success().add("message", "删除成功！");
                } else {
                    return Msg.fail().add("message", "删除失败！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail();
            }
        } else {
            try {
                int flag = goodsTypeService.deleteByTypeId(Integer.parseInt(del_goodstypeIds));
                if (flag == 1) {
                    return Msg.success();
                } else {
                    return Msg.fail();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail();
            }
        }
    }

    // 更新商品类型
    @ResponseBody
    @RequestMapping("/GoodsTypeUpdate")
    public Msg goodstypeUpdate(GoodsType goodsType) {
        Date date = new Date();
        goodsType.setGoodstypeUpdatatime(date);
        try {
            int flag = goodsTypeService.updateTypeSelective(goodsType);
            if (flag == 1) {
                return Msg.success();
            } else {
                return Msg.fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }

    /*================================== 商品模型处理 ==========================================*/
    // 商品模型文件添加
    @ResponseBody
    @RequestMapping("/GoodsModelFileAdd")
    public Msg goodsModelFileAdd(GoodsModel goodsModel) {
        Date date = new Date();
        goodsModel.setModelCreateTime(date);
        goodsModel.setModelUpdateTime(date);
        try {
            // 姓名唯一性检验
            List<GoodsModel> goodsModelTemp = goodsService.selectByModelName(goodsModel.getModelName());
            if (goodsModelTemp.size() == 0) {
                int flag = goodsService.insertSelectModelFile(goodsModel);
                if (flag == 1) {
                    return Msg.success().add("message", "添加成功！");
                } else {
                    return Msg.fail().add("message", "模型添加失败！");
                }
            } else {
                return Msg.fail().add("message", "模型名称已存在,请重新命名！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("message", "后台数据库添加modelfile 失败！");
        }
    }

    // 商品模型文件更新
    @ResponseBody
    @RequestMapping("/GoodsModelUpdate")
    public Msg goodsModelUpdate(GoodsModel goodsModel) {
        Date date = new Date();
        goodsModel.setModelUpdateTime(date);
        try {
            int flag = goodsService.updateModelFileBySelective(goodsModel);
            if (flag == 1) {
                return Msg.success();
            } else {
                return Msg.fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }

    // 商品模型文件删除
    @ResponseBody
    @RequestMapping("/GoodsModelDelete")
    public Msg goodsModelDelete(String del_goodsModelIds) {
        if (del_goodsModelIds.contains("-")) {
            List<Integer> del_goodsModelIdList = new ArrayList<>();
            String[] goodsModelIds = del_goodsModelIds.split("-");
            for (String string : goodsModelIds) {
                del_goodsModelIdList.add(Integer.parseInt(string));
            }
            try {
                // 批量删除 modelfile with modelfileIds
                int flag = goodsService.deleteModelFileWithIds(del_goodsModelIdList);
                if (flag == goodsModelIds.length) {
                    return Msg.success().add("message", "批量删除成功！");
                } else {
                    return Msg.fail().add("message", "modelFile删除失败！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail().add("message", "批量删除modelfile失败！");
            }
        } else {
            try {
                int flag = goodsService.deleteModelById(Integer.parseInt(del_goodsModelIds));
                if (flag == 1) {
                    return Msg.success().add("message", "根据modelfileId,删除model成功！");
                } else {
                    return Msg.fail().add("message", "根据单一modelfileId,删除model失败!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail().add("message", "根据modelfileId删除失败");
            }
        }
    }

    // 根据模板文件 进行商品添加，入库
    @ResponseBody
    @RequestMapping("/AddGoodsList")
    public Msg addGoofsList(@RequestBody List<Goods> goodslist) {
        // 商品的唯一性检验 对于已经存在的商品进行库存的叠加，对于未入库的商品进行入库操作
        int count = 0;
        for (int i = 0; i < goodslist.size(); i++) {
            Goods good;
            try {
                good = goodsService.selectGoodsWithId(goodslist.get(i).getGoodsId());
                if (good == null) {
                    int flag = goodsService.insertSelective(goodslist.get(i));
                    if (flag == 0) {
                        return Msg.fail().add("message", "list-添加商品失败");
                    } else {
                        count++;
                    }
                }else{
                    good.setGoodsAmount(good.getGoodsAmount() + goodslist.get(i).getGoodsAmount());
                    int flag = goodsService.updateByExampleSelective(good);
                    if(flag == 1){
                        count++;
                    }else{
                        return Msg.fail().add("message","list-更新数据失败！");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail().add("message", "goodslist 添加失败！");
            }
        }
        if (count == goodslist.size()) {
            return Msg.success().add("message", "商品入库成功");
        } else {
            return Msg.fail().add("message", "商品入库失败");
        }
    }
}
