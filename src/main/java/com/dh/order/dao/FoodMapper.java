package com.dh.order.dao;

import com.dh.order.model.Food;

import java.util.List;

public interface FoodMapper {
    int deleteByPrimaryKey(Integer foodId);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(Integer foodId);

    List<Food> selectAll();

    List<Food> selectByMerchantId(Integer merchantId);

    int updateByPrimaryKeySelective(Food record);
}