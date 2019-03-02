package com.dh.order.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YE
 * 2019-02-26 21:27
 */
@RestController
@RequestMapping(value = "/merchant")
public class MerchantController {
    @RequestMapping(value = "/test")
    public Map<String, Object> test(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", new Date());
        return map;
    }
}
