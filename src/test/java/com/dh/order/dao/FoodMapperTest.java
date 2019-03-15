package com.dh.order.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dh.order.model.Category;
import com.dh.order.model.Food;
import com.dh.order.service.FoodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodMapperTest {

    @Resource
    private FoodMapper foodMapper;

    @Resource
    private FoodService foodService;

    @Test
    public void selectAll() {
        System.out.println(JSON.toJSONString(foodMapper.selectAll(),
                SerializerFeature.DisableCircularReferenceDetect));
    }

//    @Test
//    public void insert() {
//        Food food = new Food();
//        food.setFoodName("酱饼");
//        food.setMerchantId(1);
//        food.setFoodUrl("http://fuss10.elemecdn.com/c/cd/c12745ed8a5171e13b427dbc39401jpeg.jpeg?imageView2/1/w/114/h/114");
//        food.setFoodDescription("非常好吃");
//        food.setFoodPrice(23f);
//        food.setFoodDiscountPrice(23f);
//        food.setIsSpecialty(true);
//        food.setFoodStatue(1);
//        Category category = new Category();
//        food.setCategory(category);
//        food.setFoodCount(0);
//        for (int i = 2; i <= 9; i++) {
//            category.setCategoryId(i);
//            foodMapper.insert(food);
//        }
//
//    }

    @Test
    public void selectByMerchantId() {
        System.out.println(JSON.toJSONString(foodMapper.selectByMerchantId(1),
                SerializerFeature.DisableCircularReferenceDetect));
    }

    @Test
    public void selectFoodByCategoryId() {
        List<Food> foods = foodService.selectFoodByCategoryId(1);
        if (foods == null) {
            System.out.println("no food");
        } else{
            System.out.println(foods);
        }

    }
}