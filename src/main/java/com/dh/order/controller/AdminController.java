package com.dh.order.controller;

import com.dh.order.model.Category;
import com.dh.order.model.Food;
import com.dh.order.model.Merchant;
import com.dh.order.result.DataResult;
import com.dh.order.service.CategoryService;
import com.dh.order.service.FoodService;
import com.dh.order.service.MerchantService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xjk
 * @date 2019/3/10 -  12:57
 * 控制页面跳转
 **/
@Controller
public class AdminController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private CategoryService categoryService;

    @Resource
    private FoodService foodService;

    /**
     * 跳转后台主页
     * @param merchantId
     * @return
     */
    @RequestMapping("/index/{merchantId}")
    public ModelAndView index(@PathVariable Integer merchantId) {
        Merchant merchant = merchantService.selectMerchantByMerchantId(merchantId);
        List<Category> categories = categoryService.allCategory();
        ModelAndView mav = new ModelAndView();
        mav.addObject("merchantName",merchant.getMerchantName());
        mav.addObject("merchantId",merchant.getMerchantId());
        mav.addObject("categoryList",categories);
        mav.setViewName("page/index");
        return mav;
    }

    /**
     * 菜品分类管理页面
     * @return
     */
    @RequestMapping("/page/groupmanager/{merchantId}")
    public ModelAndView groupManager(@PathVariable Integer merchantId) {
        Merchant merchant = merchantService.selectMerchantByMerchantId(merchantId);
        List<Category> categories = categoryService.allCategory();
        ModelAndView mav = new ModelAndView();
        mav.addObject("merchantId",merchant.getMerchantId());
        mav.addObject("merchantName",merchant.getMerchantName());
        mav.addObject("categoryList",categories);
        mav.setViewName("page/groupManager");
        return mav;
    }

    /**
     * 点击菜品分类后的菜品显示页面
     * @param merchantId
     * @param categoryId
     * @return
     */
    @GetMapping("/merchant/{merchantId}/food/{categoryId}")
    public ModelAndView food(@PathVariable Integer merchantId, @PathVariable Integer categoryId) {
        Merchant merchant = merchantService.selectMerchantByMerchantId(merchantId);
        Category category = categoryService.selectCategoryByCategoryId(categoryId);
        List<Food> foods = foodService.selectFoodByCategoryId(categoryId);
        List<Category> categories = categoryService.allCategory();


        ModelAndView mav = new ModelAndView();
        if (foods.isEmpty() ) {
            mav.addObject("info","该分类暂无食品");
        } else {
            mav.addObject("foodList",foods);
        }
        mav.addObject("category",category);
        mav.addObject("categoryList",categories);
        mav.addObject("merchant",merchant);
        mav.setViewName("page/food");
        return mav;
    }
}
