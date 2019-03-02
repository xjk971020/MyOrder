package com.dh.order.service;

import com.dh.order.dao.CategoryMapper;
import com.dh.order.dao.FoodMapper;
import com.dh.order.model.Category;
import com.dh.order.model.Food;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YE
 * 2019-02-27 21:28
 */
@Service
public class FoodService {

    @Resource
    private FoodMapper foodMapper;
    @Resource
    private CategoryMapper categoryMapper;

    public List<Map<String, Object>> selectByMerchantId(Integer merchantId) {
        List<Map<String, Object>> goods = new ArrayList<>();
        List<Category> categories = categoryMapper.selectByMerchantId(merchantId);
        List<Food> allFood = foodMapper.selectByMerchantId(merchantId);
        Map<Integer, List<Map<String, Object>>> listMap = new HashMap<>();
        for (Food food : allFood) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", food.getFoodName());
            map.put("price", food.getFoodPrice());
            map.put("description", food.getFoodDescription());
            map.put("sellCount", food.getFoodCount());
            map.put("Count", 0);
            map.put("info", food.getFoodDescription());
            map.put("icon", food.getFoodUrl());
            map.put("image", food.getFoodUrl());
            Integer key = food.getCategory().getCategoryId();
            if(listMap.containsKey(key)){
                listMap.get(key).add(map);
            }else{
                List<Map<String, Object>> value = new ArrayList<>();
                value.add(map);
                listMap.put(key, value);
            }
        }
        for(Category category:categories){
            Map<String, Object> good = new HashMap<>();
            good.put("name", category.getCategoryName());
            good.put("type", 1);
            good.put("foods", listMap.get(category.getCategoryId()));
            goods.add(good);
        }
        return goods;
    }
}
