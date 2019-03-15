package com.dh.order.service;

import com.dh.order.dao.FoodMapper;
import com.dh.order.model.Food;
import com.dh.order.result.MessageResult;
import com.dh.order.result.code.OperationStatusCode;
import com.dh.order.result.enums.ResultEnums;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by YE
 * 2019-02-27 21:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FoodService {

    @Resource
    private FoodMapper foodMapper;

    /**
     * 根据分类id选取食品
     * @param categoryId
     * @return
     */
    public List<Food> selectFoodByCategoryId(Integer categoryId) {
        List<Food> foodList = foodMapper.selectFoodByCategoryId(categoryId);
        if (foodList != null) {
            return foodList;
        }
        return null;
    }

    /**
     * 添加菜品
     * @param food
     * @return
     */
    public MessageResult createFood(Food food) {
        if (foodMapper.insert(food) > 0) {
            return new MessageResult(OperationStatusCode.SUCCESS, ResultEnums.SUCCESS);
        } else {
            return new MessageResult(OperationStatusCode.ERROR,ResultEnums.ERROR);
        }
    }

    /**
     * 修改食品
     * @param food
     * @return
     */
    public MessageResult update(Food food) {
        if (foodMapper.updateByPrimaryKeySelective(food) > 0) {
            return new MessageResult(OperationStatusCode.SUCCESS, ResultEnums.SUCCESS);
        } else {
            return new MessageResult(OperationStatusCode.ERROR,ResultEnums.ERROR);
        }
    }

    /**
     * 删除食品
     * @param foodId
     * @return
     */
    public MessageResult deleteFood(Integer foodId) {
        if (foodMapper.deleteByPrimaryKey(foodId) > 0) {
            return new MessageResult(OperationStatusCode.SUCCESS, ResultEnums.SUCCESS);
        } else {
            return new MessageResult(OperationStatusCode.ERROR,ResultEnums.ERROR);
        }
    }
}
