package com.dh.order.dao;

import com.dh.order.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "categoryMapper")
public interface CategoryMapper {
    List<Category> selectAllcategory();

    int deleteByPrimaryKey(Integer categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryId);

    List<Category> selectByMerchantId(Integer merchantId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}