package com.dh.order.controller;

import com.dh.order.model.Category;
import com.dh.order.result.DataResult;
import com.dh.order.result.MessageResult;
import com.dh.order.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xjk
 * @date 2019/3/11 -  23:43
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public MessageResult createCategory(@RequestBody Category category) {
        return  categoryService.createCategory(category);
    }

    @GetMapping("/all")
    public DataResult<List<Category>> selectAll() {
        return categoryService.selectAllcategory();
    }

    @DeleteMapping("/delete/{categoryId}")
    public MessageResult deleteCategory(@PathVariable Integer categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

    @PostMapping("/edit")
    public MessageResult updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }
}
