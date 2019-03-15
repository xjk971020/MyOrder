package com.dh.order.service;

import com.dh.order.dao.CategoryMapper;
import com.dh.order.model.Category;
import com.dh.order.result.DataResult;
import com.dh.order.result.MessageResult;
import com.dh.order.result.code.OperationStatusCode;
import com.dh.order.result.enums.ResultEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xjk
 * @date 2019/3/11 -  22:27
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 新建分类
     * @param category
     * @return
     */
    public MessageResult createCategory(Category category) {
        if (categoryMapper.insert(category) > 0) {
            return new MessageResult(OperationStatusCode.SUCCESS, ResultEnums.SUCCESS);
        } else {
            return new MessageResult(OperationStatusCode.ERROR,ResultEnums.ERROR);
        }
    }

    /**
     * 获取所有分类
     * @return
     */
    public DataResult<List<Category>> selectAllcategory() {
       return DataResult.success(categoryMapper.selectAllcategory());
    }

    public List<Category> allCategory() {
        return categoryMapper.selectAllcategory();
    }

    /**
     * 根据id删除分类
     * @param categoryId
     * @return
     */
    public MessageResult deleteCategory(Integer categoryId) {
        if (categoryMapper.deleteByPrimaryKey(categoryId) > 0) {
            return new MessageResult(OperationStatusCode.SUCCESS,ResultEnums.SUCCESS);
        } else {
            return new MessageResult(OperationStatusCode.ERROR,ResultEnums.ERROR);
        }
    }

    /**
     * 修改分类信息
     * @param category
     * @return
     */
    public MessageResult updateCategory(Category category) {
        if (categoryMapper.updateByPrimaryKeySelective(category) > 0) {
            return new MessageResult(OperationStatusCode.SUCCESS,ResultEnums.SUCCESS);
        } else {
            return new MessageResult(OperationStatusCode.ERROR,ResultEnums.ERROR);
        }
    }

    /**
     * 根据id获取category
     * @param categoryId
     * @return
     */
    public Category selectCategoryByCategoryId(Integer categoryId) {
        return categoryMapper.selectByPrimaryKey(categoryId);
    }
}
