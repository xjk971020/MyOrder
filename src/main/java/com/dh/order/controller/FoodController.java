package com.dh.order.controller;

import com.dh.order.model.Category;
import com.dh.order.model.Food;
import com.dh.order.result.DataResult;
import com.dh.order.result.MessageResult;
import com.dh.order.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by YE
 * 2019-02-27 21:27
 */
@RestController
@RequestMapping("/food")
@CrossOrigin
public class FoodController {

    @Autowired
    private FoodService foodService;

    /**
     * 添加食品
     * @param request
     * @param file
     * @return
     */
    @PostMapping("/create")
    public MessageResult create(HttpServletRequest request, @RequestParam(value = "foodPic")MultipartFile file) {
        String foodName = request.getParameter("foodName");
        String foodDescription = request.getParameter("foodDescription");
        Float foodPrice = Float.valueOf(request.getParameter("foodPrice"));
        Float foodDiscountPrice = Float.valueOf(request.getParameter("foodDiscountPrice"));

        Boolean speciality = false;
        String isSpecialty = request.getParameter("specialty");
        if (isSpecialty.equals("true")) {
            speciality = true;
        }
        Integer statue = 0;
        String foodStatue = request.getParameter("foodStatue");
        if (foodStatue.equals("下架")) {
            statue = 1;
        }
        String foodUrl = savePic(file);
        String merchantId = request.getParameter("merchantId");
        String categoryId = request.getParameter("categoryId");

        Category category = new Category();
        category.setCategoryId(Integer.valueOf(categoryId));

        Food food = new Food();
        food.setFoodName(foodName);
        food.setFoodDescription(foodDescription);
        food.setFoodPrice(foodPrice);
        food.setFoodDiscountPrice(foodDiscountPrice);
        food.setIsSpecialty(speciality);
        food.setFoodStatue(statue);
        food.setMerchantId(Integer.valueOf(merchantId));
        food.setCategory(category);
        food.setFoodUrl(foodUrl);
        food.setFoodCount(0);
        food.setFoodGrade(Float.valueOf(0));

        return foodService.createFood(food);
    }

    /**
     * 保存文件
     * @param file
     * @return
     */
    public String savePic(MultipartFile file) {
        if (file == null) {
            return null;
        }
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                String filePath = "D:/foodPic/"
                        + System.currentTimeMillis() + ".jpg";
                File fileDir = new File(filePath);
                File fileDirParent = fileDir.getParentFile();
                if(!fileDirParent.exists()){
                    fileDirParent.mkdirs();
                }
                fileDirParent.createNewFile();
                // 转存文件
                file.transferTo(new File(filePath));
                String finalPath = filePath.substring(10);
                return finalPath;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 删除食品
     * @param foodId
     * @return
     */
    @GetMapping("/delete/{foodId}")
    public MessageResult deleteFood(@PathVariable Integer foodId) {
        return foodService.deleteFood(foodId);
    }

    @PostMapping("/update")
    public MessageResult editFood(HttpServletRequest request, @RequestParam(value = "foodPic",required = false)MultipartFile file) {
        Integer foodId = Integer.valueOf(request.getParameter("foodId"));
        String foodName = request.getParameter("foodName");
        String foodDescription = request.getParameter("foodDescription");
        Float foodPrice = Float.valueOf(request.getParameter("foodPrice"));
        Float foodDiscountPrice = Float.valueOf(request.getParameter("foodDiscountPrice"));

        Boolean speciality = false;
        String isSpecialty = request.getParameter("specialty");
        if (isSpecialty.equals("true")) {
            speciality = true;
        }
        Integer statue = 0;
        String foodStatue = request.getParameter("foodStatue");
        if (foodStatue.equals("下架")) {
            statue = 1;
        }
        String foodUrl = savePic(file);
        String merchantId = request.getParameter("merchantId");
        String categoryId = request.getParameter("categoryId");

        Category category = new Category();
        category.setCategoryId(Integer.valueOf(categoryId));

        Food food = new Food();
        food.setFoodId(foodId);
        food.setFoodName(foodName);
        food.setFoodDescription(foodDescription);
        food.setFoodPrice(foodPrice);
        food.setFoodDiscountPrice(foodDiscountPrice);
        food.setIsSpecialty(speciality);
        food.setFoodStatue(statue);
        food.setMerchantId(Integer.valueOf(merchantId));
        food.setCategory(category);
        food.setFoodUrl(foodUrl);
        food.setFoodCount(0);
        food.setFoodGrade(Float.valueOf(0));

        return foodService.update(food);
    }
}
