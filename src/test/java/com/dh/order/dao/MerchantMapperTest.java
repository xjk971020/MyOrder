package com.dh.order.dao;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MerchantMapperTest {
    @Resource
    private MerchantMapper merchantMapper;

    @Test
    public void selectByPrimaryKey() {
        System.out.println(JSON.toJSONString(merchantMapper.selectByPrimaryKey(1)));
    }

    @Test
    public void selectAll() {
        System.out.println(JSON.toJSONString(merchantMapper.selectAll()));
    }
}