package com.dh.order.dao;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryMapperTest {
    @Resource
    private CategoryMapper categoryMapper;
    @Test
    public void selectByMerchantId() {
        System.out.println(JSON.toJSONString(categoryMapper.selectByMerchantId(1)));
    }
}